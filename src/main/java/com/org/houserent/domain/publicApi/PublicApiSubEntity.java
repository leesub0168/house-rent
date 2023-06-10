package com.org.houserent.domain.publicApi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter @Setter
public class PublicApiSubEntity {
    private int list_total_count;

    @JsonProperty("RESULT")
    private PublicApiSubEntityResultCode result;

    private List<PublicApiDataEntity> row;
}
