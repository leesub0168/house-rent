package com.org.houserent.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class AddressTranslationTest {

    @Autowired
    AddressTranslation addressTranslation;

    @Test
    @Transactional
    public void test() throws Exception {
        String searchAddress = "방이동 100-23";
        addressTranslation.getAddressInfo(searchAddress);
    }

}