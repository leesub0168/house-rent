package com.org.houserent.domain.publicApi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PublicApiMainEntity {
    @JsonProperty("tbLnOpendataRentV")
    private PublicApiSubEntity tbLnOpendataRentV;

}
