package com.org.houserent.batch.entity;

import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class JusoRoadNameCdId implements Serializable {


    @EqualsAndHashCode.Include
    @Id
    private String juso_road_name_cd;

    @EqualsAndHashCode.Include
    @Id
    private String dong_serial_num;
}
