package com.org.houserent.batch.entity;

import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class juso_road_name_cd_id implements Serializable {


    @EqualsAndHashCode.Include
    @Id
    private String juso_road_name_cd;

    @EqualsAndHashCode.Include
    @Id
    private String dong_serial_num;
}
