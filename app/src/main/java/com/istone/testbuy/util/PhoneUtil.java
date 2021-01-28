package com.istone.testbuy.util;

/**
 * describe:
 * created by mip
 * on 2020/11/6
 */
public class PhoneUtil {
    /**
     * 手机号加省略号
     */
    public static String setPhoneCode(String code) {
        String tempCode = null;
        if (code != null && code.length() > 10)
            tempCode = code.substring(0, 3) + "****" + code.substring(code.length() - 4, code.length());
        else
            return "";
        return tempCode;
    }
}
