package com.org.houserent;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class RoadAddressRowMapper implements RowMapper<RoadAddress> {
    @Override
    public RoadAddress mapRow(ResultSet rs, int rowNum) throws SQLException {
        return RoadAddress.builder()
                .rd_nm(rs.getString("road_address_name"))
                .sigungu_nm(rs.getString("si_gun_gu_name"))
                .bjdong_nm(rs.getString("dong_name"))
                .sigungu_cd(rs.getString("sgg_cd"))
                .build();
    }
}
