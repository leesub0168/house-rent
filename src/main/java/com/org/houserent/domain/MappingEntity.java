package com.org.houserent.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MappingEntity {
    @JsonProperty("tbLnOpendataRentV")
    private SubEntity tbLnOpendataRentV;

}
