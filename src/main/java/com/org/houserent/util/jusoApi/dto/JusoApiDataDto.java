package com.org.houserent.util.jusoApi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.org.houserent.service.dto.HouseDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@ToString
public class JusoApiDataDto {
    private String detBdNmList; // 상세 건물명
    private String engAddr;     // 도로명주소 (영문)
    private String rn;          // 도로명
    private String emdNm;       // 읍면동 명
    private String zipNo;       // 우편번호
    private String roadAddrPart2;   // 도로명주소 참고항목
    private String emdNo;       // 읍면동 일련번호
    private String sggNm;       // 시군구 명
    private String jibunAddr;   // 지번주소
    private String siNm;        // 시도명
    private String roadAddrPart1;   // 도로명주소 참고항목 제외
    private String bdNm;        // 건물명
    private String admCd;       // 행정구역 코드 -> 시군구 코드(5자리) + 법정동 코드(5자리)
    private String udrtYn;      // 지하여부 (0:지상, 1:지하)
    private String lnbrMnnm;    // 지번-본번
    private String roadAddr;    // 전체 도로명 주소
    private String lnbrSlno;    // 지번-부번 (부번이 없는 경우 0)
    private String buldMnnm;    // 건물 번호 - 본번
    private String bdKdcd;      // 공동 주택여부 (1:공동주택, 0: 비공동주택)
    private String liNm;        // 법정리명
    private String rnMgtSn;     // 도로명 코드
    private String mtYn;        // 산 여부 (0:대지, 1:산)
    private String bdMgtSn;     // 건물관리번호
    private String buldSlno;    // 건물 번호 - 부번 (부번이 없는 경우 0)

    public HouseDto toHouseDto() {
        return HouseDto.builder()
                .city(getSiNm())
                .gu(getSggNm())
                .dong(getEmdNm())
                .road_name(getRn())
                .building_main_num(getBuldMnnm())
                .building_sub_num(getBuldSlno())
                .land_main_num(getLnbrMnnm())
                .land_sub_num(getLnbrSlno())
                .detail_address(getBdNm())
                .zipcode(getZipNo())
                .sgg_cd(admCdToSggCd())
                .bjdong_cd(admCdToBjdongCd())
                .build();

    }

    public String admCdToSggCd() {
        return getAdmCd().substring(0, 5);
    }
    public String admCdToBjdongCd() {
        return getAdmCd().substring(5, 10);
    }
}
