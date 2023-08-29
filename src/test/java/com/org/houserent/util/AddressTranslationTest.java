package com.org.houserent.util;

import com.org.houserent.exception.NonExistAddressException;
import com.org.houserent.service.dto.HouseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AddressTranslationTest {

    @Autowired
    AddressTranslation addressTranslation;

    @Test
    public void 주소검색_성공() throws Exception {
        //given
        String searchAddress = "방이동 100-23";

        //when
        Optional<HouseDto> addressInfo = addressTranslation.getAddressInfo(searchAddress);

        //then
        assertTrue(addressInfo.isPresent());
        assertEquals("방이동", addressInfo.get().getDong());
        assertEquals(100, addressInfo.get().getLand_main_num());
    }

    @Test
    public void 주소검색_실패() throws Exception {
        //given
        String searchAddress = "방이동5325";

        //when
        assertThrows(NonExistAddressException.class, () -> addressTranslation.getAddressInfo(searchAddress));
        //then
    }

    @Test
    public void 주소검색_다건_조회() throws Exception {
        //given
        String searchAddress1 = "아리랑로9길";

        //when
        List<HouseDto> addressInfoList1 = addressTranslation.getAddressInfoList(searchAddress1);

        //then
        assertNotNull(addressInfoList1);
    }
}