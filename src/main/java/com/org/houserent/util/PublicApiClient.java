package com.org.houserent.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.houserent.exception.NonExistDataException;
import com.org.houserent.exception.NonExistHouseException;
import com.org.houserent.service.HouseService;
import com.org.houserent.service.dto.HouseDto;
import com.org.houserent.service.dto.HouseRentContractDto;
import com.org.houserent.service.dto.HouseSaleContractDto;
import com.org.houserent.util.publicApi.dto.HouseRentApiDataDto;
import com.org.houserent.util.publicApi.dto.HouseRentApiMainDto;
import com.org.houserent.util.publicApi.dto.HouseSaleApiDataDto;
import com.org.houserent.util.publicApi.dto.HouseSaleApiMainDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Getter
@RequiredArgsConstructor
public class PublicApiClient {
    private String testUrl = "http://openapi.seoul.go.kr:8088/5a75446d6b6c656539375244465959/json/tbLnOpendataRentV/1/5/2022/11620";

    @Value("${seoul-api.baseUrl}")
    private String baseUrl;

    @Value("${seoul-api.key}")
    private String key;

    @Value("${seoul-api.resultType}")
    private String resultType;

    @Value("${seoul-api.rent}")
    private String rent;

    @Value("${seoul-api.sale}")
    private String sale;

    private String start_index = "1";
    private String end_index = "10";

    private final HouseService houseService;

    /**
     * 서울시 부동산 실거래가 정보 API
     * http://openapi.seoul.go.kr:8088/인증키/요청파일_타입/서비스명/요청시작위치/요청종료위치 +
     *      /접수연도/자치구코드/자치구명/법정동코드/지번구분/지번구분명/본번/부번/건물명/계약일/건물용도
     * */
    public List<HouseSaleContractDto> getHouseSaleContractInfo(String searchAddress, String year, boolean isRoadAddress) {
        Optional<HouseDto> houseDto;
        if(isRoadAddress) houseDto = houseService.findHouseByRoadAddress(searchAddress);
        else houseDto = houseService.findHouseByLandAddress(searchAddress);

        houseDto.orElseThrow(() -> new NonExistHouseException("주소 정보가 존재하지 않습니다."));

        URI uri = makeUri(
                key, resultType, sale,
                start_index, end_index,
                year, houseDto.get().getSgg_cd(),
                houseDto.get().getSgg_nm(), houseDto.get().getBjdong_cd(),
                "{land_gbn}", "{land_gbn_nm}", String.format("%04d", houseDto.get().getLand_main_num()),
                String.format("%04d", houseDto.get().getLand_sub_num())
        );
        String str = callApiAcceptJson(uri);

        if(str.indexOf(sale) < 0) throw new NonExistDataException("해당하는 데이터가 존재하지 않습니다.");

        List<HouseSaleContractDto> houseSaleContractDtoList = new ArrayList<>();
        try {
            HouseSaleApiMainDto houseSaleApiMainDto = jsonStringToObject(str, HouseSaleApiMainDto.class);
            houseSaleContractDtoList = houseSaleApiMainDto.getTbLnOpendataRtmsV().getRow()
                    .stream()
                    .filter(o -> checkHouseInfo(houseDto.get(), o))
                    .map(o -> o.toHouseSaleContractDto(houseDto.get()))
                    .collect(Collectors.toList());

        } catch (JsonProcessingException jpe) {
            jpe.printStackTrace();
        }
        return houseSaleContractDtoList;
    }

    /**
     * 서울시 부동산 전월세가 정보 API
     * http://openapi.seoul.go.kr:8088/인증키/요청파일_타입/서비스명/요청시작위치/요청종료위치 +
     *      /접수연도/자치구코드/자치구명/법정동코드/지번구분/본번/부번/계약일/건물명/건물용도
     * */
    public List<HouseRentContractDto> getHouseRentContractInfo(String searchAddress, boolean isRoadAddress) throws JsonProcessingException {
        Optional<HouseDto> houseDto;
        if (isRoadAddress) houseDto = houseService.findHouseByRoadAddress(searchAddress);
        else houseDto = houseService.findHouseByLandAddress(searchAddress);

        houseDto.orElseThrow(() -> new NonExistHouseException("주소 정보가 존재하지 않습니다."));

        URI uri = makeUri(
                key, resultType, rent,
                start_index, end_index,
                getCurrentYear(), houseDto.get().getSgg_cd(),
                houseDto.get().getSgg_nm(), houseDto.get().getBjdong_cd(),
                "{land_gbn}", String.format("%04d", houseDto.get().getLand_main_num()),
                String.format("%04d", houseDto.get().getLand_sub_num())
        );
        String str = callApiAcceptJson(uri);

        if(str.indexOf(rent) < 0) throw new NonExistDataException("해당 데이터가 존재하지 않습니다.");

        List<HouseRentContractDto> houseRentContractDtoList = new ArrayList<>();

        HouseRentApiMainDto houseRentApiMainDto = jsonStringToObject(str, HouseRentApiMainDto.class);
        houseRentContractDtoList = houseRentApiMainDto.getTbLnOpendataRentV().getRow()
                .stream()
                .filter(o -> checkHouseInfo(houseDto.get(), o))
                .map(o -> o.toHouseRentContractDto(houseDto.get()))
                .collect(Collectors.toList());
        return houseRentContractDtoList;
    }

    public boolean checkHouseInfo(HouseDto houseDto, HouseSaleApiDataDto houseSaleApiDataDto) {
        return Objects.equals(houseDto.getBjdong_cd(), houseSaleApiDataDto.getBJDONG_CD())
                && Objects.equals(houseDto.getSgg_cd(), houseSaleApiDataDto.getSGG_CD())
                && Objects.equals(String.format("%04d", houseDto.getLand_main_num()), houseSaleApiDataDto.getBONBEON())
                && Objects.equals(String.format("%04d", houseDto.getLand_sub_num()), houseSaleApiDataDto.getBUBEON());
    }

    public boolean checkHouseInfo(HouseDto houseDto, HouseRentApiDataDto houseRentApiDataDto) {
        return Objects.equals(houseDto.getBjdong_cd(), houseRentApiDataDto.getBJDONG_CD())
                && Objects.equals(houseDto.getSgg_cd(), houseRentApiDataDto.getSGG_CD())
                && Objects.equals(String.format("%04d", houseDto.getLand_main_num()), houseRentApiDataDto.getBOBN())
                && Objects.equals(String.format("%04d", houseDto.getLand_sub_num()), houseRentApiDataDto.getBUBN());
    }

    private String getCurrentYear() {
        return String.valueOf(LocalDateTime.now().getYear());
    }
    
    private URI makeUri(String... strings) {
        return UriComponentsBuilder.fromHttpUrl(baseUrl)
                .pathSegment(strings)
                .build(" "," ", " ");
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
