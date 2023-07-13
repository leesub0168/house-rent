package com.org.houserent.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.houserent.exception.NonExistAddressException;
import com.org.houserent.service.HouseService;
import com.org.houserent.service.dto.HouseDto;
import com.org.houserent.util.jusoApi.dto.JusoApiDataDto;
import com.org.houserent.util.jusoApi.dto.JusoApiMainDto;
import com.org.houserent.util.jusoApi.dto.JusoApiSubDtoResultCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Getter
public class AddressTranslation {

    @Value("${juso-api.baseUrl}")
    private String baseUrl;

    @Value("${juso-api.key}")
    private String key;

    @Value("${juso-api.resultType}")
    private String resultType;

    private final HouseService houseService;

    private int countPerPage = 100;

    public Optional<HouseDto> getAddressInfo(String searchAddress) {
        Optional<HouseDto> houseDto = Optional.empty();
        int currentPage = 1;

        URI uri = makeUri(searchAddress, currentPage, countPerPage);

        String str = callApiAcceptJson(uri);
        try {
            JusoApiMainDto jusoApiMainDto = jsonStringToObject(str, JusoApiMainDto.class);
            JusoApiSubDtoResultCode resultCode = jusoApiMainDto.getResults().getCommon();

            if(resultCode.getTotalCount() <= 0) throw new NonExistAddressException("주소 정보를 찾을수 없습니다.");

            List<JusoApiDataDto> jusoApiDataDtoList = jusoApiMainDto.getResults().getJuso();
            JusoApiDataDto jusoApiDataDto = jusoApiDataDtoList.get(0);
            houseDto = Optional.ofNullable(jusoApiDataDto.toHouseDto());
        } catch (JsonProcessingException jpe) {
            jpe.printStackTrace();
        }
        houseDto.orElseThrow(() -> new NonExistAddressException("주소 정보를 찾을수 없습니다."));
        return houseDto;
    }

    public List<HouseDto> getAddressInfoList(String searchAddress) {
        List<HouseDto> result = new ArrayList<>();
        int currentPage = 1;
        URI uri = makeUri(searchAddress, currentPage, countPerPage);

        String str = callApiAcceptJson(uri);
        try {
            JusoApiMainDto jusoApiMainDto = jsonStringToObject(str, JusoApiMainDto.class);
            JusoApiSubDtoResultCode resultCode = jusoApiMainDto.getResults().getCommon();

            int totalCount = resultCode.getTotalCount();

            if(totalCount <= 0) throw new NonExistAddressException("주소 정보를 찾을수 없습니다.");

            List<JusoApiDataDto> jusoApiDataDtoList = jusoApiMainDto.getResults().getJuso();

            result.addAll(jusoApiDataDtoList.stream().map(JusoApiDataDto::toHouseDto).collect(Collectors.toList()));

            if(totalCount > countPerPage) {
                int callCount = Math.round(totalCount / countPerPage);
                for (int i = 0; i < callCount; i++) {
                    currentPage++;
                    URI uri1 = makeUri(searchAddress, currentPage, countPerPage);
                    String apiResult = callApiAcceptJson(uri1);

                    List<HouseDto> houseDtos = jusoApiResultToHouseDto(apiResult);

                    if(houseDtos.size() > 0) result.addAll(houseDtos);
                }
            }

        } catch (JsonProcessingException jpe) {
            jpe.printStackTrace();
        }
        return result;
    }

    private List<HouseDto> jusoApiResultToHouseDto(String str) {
        List<HouseDto> result = new ArrayList<>();
        try {
            JusoApiMainDto jusoApiMainDto = jsonStringToObject(str, JusoApiMainDto.class);
            JusoApiSubDtoResultCode resultCode = jusoApiMainDto.getResults().getCommon();

            if(resultCode.getTotalCount() <= 0) throw new NonExistAddressException("주소 정보를 찾을수 없습니다.");

            List<JusoApiDataDto> jusoApiDataDtoList = jusoApiMainDto.getResults().getJuso();

            result = jusoApiDataDtoList.stream().map(JusoApiDataDto::toHouseDto).collect(Collectors.toList());
        } catch (JsonProcessingException jpe) {
            jpe.printStackTrace();
        }
        return result;
    }

    public void saveAddressInfo(List<JusoApiDataDto> jusoApiDataDtoList) {
        for (JusoApiDataDto jusoApiDataDto : jusoApiDataDtoList) {
            HouseDto houseDto = jusoApiDataDto.toHouseDto();
            houseService.saveHouse(houseDto);
        }
    }

    private URI makeUri(String keyword, int currentPage, int countPerPage) {
        return UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("confmKey", key)
                .queryParam("currentPage", currentPage)
                .queryParam("countPerPage", countPerPage)
                .queryParam("keyword", keyword)
                .queryParam("resultType", resultType)
                .build().toUri();
    }

    private <T> T jsonStringToObject(String str, Class<T> objectType) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(str, objectType);
    }

    public String callApiAcceptJson(URI uri) {
        WebClient webClient = WebClient.create();
        return webClient.get().uri(uri)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .blockOptional().orElseThrow(() -> new IllegalArgumentException("공공 api 호출 실패"));
    }

}
