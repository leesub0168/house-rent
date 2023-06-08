package com.org.houserent.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SHACryptoUtilTest {
    @Test
    public void 암호화_SHA() throws Exception {
        //given
        String original = "testA";
        String other = "testA";

        //when
        String encryptSha1 = SHACryptoUtil.encrypt(original);
        String encryptSha2 = SHACryptoUtil.encrypt(other);

        //then
        assertEquals(encryptSha1, encryptSha2);
    }
}