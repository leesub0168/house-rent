package com.org.houserent.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter @Setter
public class SubEntity {
    private int list_total_count;

    @JsonProperty("RESULT")
    private ResultCode result;

    private List<ApiEntity> row;
}
