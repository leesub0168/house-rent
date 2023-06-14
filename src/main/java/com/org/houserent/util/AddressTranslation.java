package com.org.houserent.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.houserent.service.HouseService;
import com.org.houserent.service.dto.HouseDto;
import com.org.houserent.util.jusoApi.dto.JusoApiDataDto;
import com.org.houserent.util.jusoApi.dto.JusoApiMainDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

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

    private int currentPage = 1;
    private int countPerPage = 10;

    public void getAddressInfo(String searchAddress) throws JsonProcessingException {
        URI uri = makeUri(searchAddress);

        String str = callApiAcceptJson(uri);

        JusoApiMainDto jusoApiMainDto = jsonStringToObject(str, JusoApiMainDto.class);
        List<JusoApiDataDto> jusoApiDataDtoList = jusoApiMainDto.getResults().getJuso();

        saveAddressInfo(jusoApiDataDtoList);
    }

    public void saveAddressInfo(List<JusoApiDataDto> jusoApiDataDtoList) {
        for (JusoApiDataDto jusoApiDataDto : jusoApiDataDtoList) {
            HouseDto houseDto = jusoApiDataDto.toHouseDto();
            houseService.saveHouse(houseDto);
        }
    }

    private URI makeUri(String keyword) {
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
                .blockOptional().orElseThrow(
                        () -> new IllegalArgumentException("공공 api 호출 실패")
                );
    }

}
