package com.org.houserent.util.jusoApi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@ToString
public class JusoApiDataDto {
    private String detBdNmList;
    private String engAddr;
    private String rn;
    private String emdNm;
    private String zipNo;
    private String roadAddrPart2;
    private String emdNo;
    private String sggNm;
    private String jibunAddr;
    private String siNm;
    private String roadAddrPart1;
    private String bdNm;
    private String admCd;
    private String udrtYn;
    private String lnbrMnnm;
    private String roadAddr;
    private String lnbrSlno;
    private String buldMnnm;
    private String bdKdcd;
    private String liNm;
    private String rnMgtSn;
    private String mtYn;
    private String bdMgtSn;
    private String buldSlno;
}
