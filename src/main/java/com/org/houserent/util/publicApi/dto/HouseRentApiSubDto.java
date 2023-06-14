package com.org.houserent.util.publicApi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter @Setter
public class HouseRentApiSubDto {
    private int list_total_count;

    @JsonProperty("RESULT")
    private CommonApiSubDtoResultCode result;

    private List<HouseRentApiDataDto> row;
}
