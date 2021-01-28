package com.istone.testbuy.util;

import com.blankj.utilcode.util.EncryptUtils;

/**
 * Created by Ruansu
 * on 2020/10/12 5:00 PM
 */
public class EncryptUtil {

    private static final String secretKey = "FAD4qYQXRB7Bx39D";
    private static final String TRANSFORMATION = "AES/ECB/PKCS5Padding";

    public static String encryptAES2Base64(String content) {
        return new String(EncryptUtils.encryptAES2Base64(
                content.getBytes(), secretKey.getBytes(), TRANSFORMATION, null));
    }

}
