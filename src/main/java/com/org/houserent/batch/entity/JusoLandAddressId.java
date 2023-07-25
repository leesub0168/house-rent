package com.org.houserent.batch.entity;

import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class JusoLandAddressId implements Serializable {

    @EqualsAndHashCode.Include
    @Id
    private String jusoAddressInfo;

    @EqualsAndHashCode.Include
    @Id
    private String serial_num;
}
