package com.org.houserent.batch;

import com.org.houserent.domain.House;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class HouseRowMapper implements RowMapper<House> {
    @Override
    public House mapRow(ResultSet rs, int rowNum) throws SQLException {
        return House.builder()
                .sgg_cd(rs.getString("sgg_cd"))
                .sgg_nm(rs.getString("si_gun_gu_name"))
                .bjdong_cd(rs.getString("bjd_cd"))
                .bjdong_nm(rs.getString("bjd_dong_name"))
                .zipcode(rs.getString("zipcode"))
                .city(rs.getString("si_do_name"))
                .gu(rs.getString("si_gun_gu_name"))
                .dong(rs.getString("dong_name"))
                .road_name(rs.getString("road_address_name"))
                .detail_address(rs.getString("si_gun_gu_building_name"))
                .building_main_num(rs.getString("building_main_num"))
                .building_sub_num(rs.getString("building_sub_num"))
                .land_main_num(Integer.parseInt(rs.getString("land_main_num")))
                .land_sub_num(Integer.parseInt(rs.getString("land_sub_num")))
                .building_name(rs.getString("si_gun_gu_building_name"))
                .build();
    }
}
