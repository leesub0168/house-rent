package com.org.houserent.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AESCryptoUtilTest {
    @Test
    public void 암호화() throws Exception {
        //given
        String original = "testA";

        //when
        String testA = AESCryptoUtil.encrypt(original);

        //then
        assertNotEquals(testA, original);
    }
    
    @Test
    public void 복호화() throws Exception {
        //given
        String original = "testA";
        String encrypted = AESCryptoUtil.encrypt(original);

        //when
        String decrypted = AESCryptoUtil.decrypt(encrypted);
        
        //then
        assertEquals(decrypted, original);
    }
}