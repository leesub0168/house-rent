package com.org.houserent.batch.entity;

import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class juso_land_address_id implements Serializable {

    @EqualsAndHashCode.Include
    @Id
    private String control_num;

    @EqualsAndHashCode.Include
    @Id
    private String serial_num;
}
