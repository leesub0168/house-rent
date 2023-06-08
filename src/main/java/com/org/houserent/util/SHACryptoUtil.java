package com.org.houserent.util;

import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Slf4j
public class SHACryptoUtil {

    public static String encrypt(String value) {
        String result = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] digest = messageDigest.digest(value.getBytes());
            result = new String(digest);
        } catch (NoSuchAlgorithmException nae) {
            log.error("password encrypt fail", nae);
        }

        return result;
    }
}
