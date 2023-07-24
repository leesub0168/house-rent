package com.org.houserent.batch;

import com.org.houserent.batch.entity.RoadAddress;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class RoadAddressRowMapper implements RowMapper<RoadAddress> {
    @Override
    public RoadAddress mapRow(ResultSet rs, int rowNum) throws SQLException {
        return RoadAddress.builder()
                .sigungu_cd(rs.getString("sigungu_cd"))
                .sigungu_nm(rs.getString("si_gun_gu_name"))
                .bjdong_cd(rs.getString("bjdong_cd"))
                .building_name(rs.getString("building_register_name"))
                .building_main_num(rs.getString("building_main_num"))
                .building_sub_num(rs.getString("building_sub_num"))
                .build();
    }
}