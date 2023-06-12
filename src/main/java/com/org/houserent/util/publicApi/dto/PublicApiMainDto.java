package com.org.houserent.util.publicApi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PublicApiMainDto {
    @JsonProperty("tbLnOpendataRentV")
    private PublicApiSubDto tbLnOpendataRentV;

}
