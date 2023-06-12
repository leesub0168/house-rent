package com.org.houserent.util.jusoApi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class JusoApiMainDto {
    private JusoApiSubDto results;
}
