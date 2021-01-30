package com.istone.testbuy.util;

import android.Manifest;
import android.annotation.SuppressLint;

import com.blankj.utilcode.util.ArrayUtils;
import com.blankj.utilcode.util.EncodeUtils;
import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.MapUtils;
import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.PhoneUtils;
import com.blankj.utilcode.util.StringUtils;

import java.util.Map;
import java.util.Random;

import static com.istone.testbuy.http.HttpParams.BEST_TIME;
import static com.istone.testbuy.http.HttpParams.INV_CONTENT;
import static com.istone.testbuy.http.HttpParams.INV_PAYEE;
import static com.istone.testbuy.http.HttpParams.POST_SCRIPT;
import static com.istone.testbuy.http.HttpParams.SIGN;
import static com.istone.testbuy.http.HttpParams.SUR_PLUS;
import static com.istone.testbuy.http.HttpParams.TER_NO;
import static com.istone.testbuy.http.HttpParams.WEB_LOG_ID;

/**
 * Created by Ruansu
 * on 2020/8/27 3:23 PM
 */
public class ParamUtil {

    private static final String COMPANY_STR = "ISOFTSTORE2METERSBONWE";
    private static final String CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private final static String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCzFlrzRNV94ZIxUntlZzPVaE99" + "wmBgV4bPU8lFBmKaxtPEXqQIewBekdkXCsVvj9zA7WTSTkMQn+56G3zotcz5b3SD" + "nlKBkRA2yNao0UB75WQTWMTYdJO8NIdKFZ0vLhIE2zRh+DbS3onkmGV33bgXSVnw" + "aXYJLb1VqLEuhqS5ZQIDAQAB";
    private static final byte[] iv = new byte[]{1, 2, 3, 4, 5, 6, 7, 8};
    private static final String[] keys = new String[]{TER_NO, WEB_LOG_ID, BEST_TIME, INV_PAYEE, INV_CONTENT, POST_SCRIPT, SUR_PLUS, SIGN};

    private static String webLogId;

    @SuppressLint("MissingPermission")
    public static String getIMEI() {
        if (PermissionUtils.isGranted(Manifest.permission.READ_PHONE_STATE))
            return PhoneUtils.getIMEI();
        return "";
    }

    public static String getWebLogId() {
        if (StringUtils.isEmpty(webLogId))
            webLogId = EncryptUtils.encryptMD5ToString(
                    EncryptUtils.encryptMD5ToString(COMPANY_STR) + getRandomString(5));
        return webLogId;
    }

    public static String getParamSign(Map<String, Object> params) {
        StringBuilder builder = new StringBuilder();
        if (MapUtils.isNotEmpty(params)) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                if (!ArrayUtils.contains(keys, entry.getKey()))
                    builder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
        }
        if (builder.length() > 0)
            builder.deleteCharAt(builder.length() - 1);
        return getSign(builder.toString());
    }

    private static String getSign(String data) {
        try {
            String desKey = getDecodeKey(8);
            String desContent = new String(EncryptUtils.encryptDES2Base64(EncodeUtils.urlDecode(data).getBytes(),
                    desKey.getBytes(), "DES/CBC/PKCS5Padding", iv));
            String rsaContent = new String(EncryptUtils.encryptRSA2Base64(desKey.getBytes(),
                    EncodeUtils.base64Decode(PUBLIC_KEY), Integer.MAX_VALUE, "RSA/ECB/PKCS1Padding"));
            return rsaContent + "//BaNggO//" + desContent;
        } catch (Exception e) {
            LogUtils.d(e);
        }
        return "";
    }

    private static String getDecodeKey(int length) {
        String characters = "0123456789abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++)
            builder.append(characters.charAt(random.nextInt(71)));
        return builder.toString();
    }

    private static String getRandomString(int length) {
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++)
            builder.append(CHARACTERS.charAt(
                    Math.abs(random.nextInt(500)) % CHARACTERS.length()));
        return builder.toString();
    }

}
