package com.org.houserent.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class AESCryptoUtil {
    static final String transformation = "AES/CBC/PKCS5PADDING";
    static final String initVector = "W1k3Rp26z3o9Ms7l";
    static final String key = "Bkr1g6q5Rcr1W3gE";
    static final IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(StandardCharsets.UTF_8));
    static final SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");



    public static String encrypt(String value) throws Exception {
        Cipher cipher = Cipher.getInstance(transformation);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, iv);

        byte[] bytes = cipher.doFinal(value.getBytes(StandardCharsets.UTF_8));

        return Base64.getEncoder().encodeToString(bytes);
    }

    public static String decrypt(String encrypted) throws Exception {
        Cipher cipher = Cipher.getInstance(transformation);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, iv);

        byte[] bytes = cipher.doFinal(Base64.getDecoder().decode(encrypted));

        return new String(bytes);
    }
}
