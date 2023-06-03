package com.org.houserent.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.houserent.domain.ApiEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PublicApiClientTest {

    @Test
    public void testCallApi() throws Exception {
        //given
        PublicApiClient publicApiClient = new PublicApiClient();
        //when
        publicApiClient.testCallApi();

        //then
    }

    @Test
    public void testParse() throws Exception {
        String str = "{\"ACC_YEAR\":\"2022\",\"SGG_CD\":\"11620\",\"SGG_NM\":\"관악구\",\"BJDONG_CD\":\"10100\",\"BJDONG_NM\":\"봉천동\",\"LAND_GBN\":\"1\",\"LAND_GBN_NM\":\"대지\",\"BOBN\":\"1719\",\"BUBN\":\"0000\",\"FLR_NO\":5.0,\"CNTRCT_DE\":\"20230527\",\"RENT_GBN\":\"월세\",\"RENT_AREA\":84.7,\"RENT_GTN\":\"30000\",\"RENT_FEE\":\"100\",\"BLDG_NM\":\"관악동부센트레빌\",\"BUILD_YEAR\":\"2004\",\"HOUSE_GBN_NM\":\"아파트\",\"CNTRCT_PRD\":\"\",\"NEW_RON_SECD\":\"신규\",\"CNTRCT_UPDT_RQEST_AT\":\"\",\"BEFORE_GRNTY_AMOUNT\":\"0\",\"BEFORE_MT_RENT_CHRGE\":\"0\"}";
        ObjectMapper mapper = new ObjectMapper();
        ApiEntity apiEntity = mapper.readValue(str, ApiEntity.class);
        System.out.println(apiEntity.toString());

    }

}