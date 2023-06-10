package com.org.houserent.domain.publicApi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter @Setter
@JsonNaming(PropertyNamingStrategies.UpperSnakeCaseStrategy.class)
public class PublicApiSubEntityResultCode {

    private String code;
    private String message;

}
