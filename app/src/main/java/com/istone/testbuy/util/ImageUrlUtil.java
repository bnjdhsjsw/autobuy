package com.istone.testbuy.util;

import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.StringUtils;

import java.util.Random;

public final class ImageUrlUtil {

    /**
     * CMS图片服务器配置 cdn
     **/
    public final static String[] CDN_IMG_SERVER_URL = {"http://m1.banggo.com", "http://m2.banggo.com", "http://m3.banggo.com", "http://m4.banggo.com"};
    public final static String YUN_SERVER_URL = "https://pic.banggo.com";

    public static String RESIZE_HEAD = "?x-oss-process=image/resize,m_fill";
    public static String RESIZE_END = "/quality,q_90";

    public static String VIDEO_SNAPSHOT = "?x-oss-process=video/snapshot,t_10000,m_fast";

    public static String getImageUrl(String url) {
        if (!StringUtils.isTrimEmpty(url))
            return fillUrl(url);
        return "";
    }

    public static String getImageUrlByWidth(String url, int width) {
        return getImageUrl(url, width, 0);
    }

    public static String getImageUrlByHeight(String url, int height) {
        return getImageUrl(url, 0, height);
    }

    public static String getGlideUrl(String url, int width, int height) {
        if (!StringUtils.isTrimEmpty(url) && !RegexUtils.isURL(url))
            return getImageUrl(url, width, height);
        return url;
    }

    public static String getImageUrl(String url, int width, int height) {
        if (StringUtils.isTrimEmpty(url) || url.length() < 5) return "";
        StringBuilder builder = new StringBuilder();
        builder.append(fillUrl(url));
        if (width > 0 || height > 0) {
            builder.append(RESIZE_HEAD);
            if (width > 0)
                builder.append(",w_").append(width);
            if (height > 0)
                builder.append(",h_").append(height);
            builder.append(RESIZE_END);
        }
        return builder.toString();
    }

    private static String fillUrl(String url) {
        if (!RegexUtils.isURL(url)) {
            StringBuilder builder = new StringBuilder();
            if (url.startsWith("/", 0)) {
                builder.append(YUN_SERVER_URL).append(url);
            } else {
                builder.append(YUN_SERVER_URL).append("/").append(cleanUrl(url));
            }
            return builder.toString();
        }
        return url;
    }

    public static String cleanUrl(String url) {
        int last = url.lastIndexOf(url, '?');
        if (last != -1)
            return url.substring(0, last).trim();
        else
            return url;
    }

    public static String getCdnImageUrl(String url) {
        if (StringUtils.isTrimEmpty(url)) return "";
        StringBuilder stringBuilder = new StringBuilder();
        if (!url.startsWith("http")) {
            if (url.startsWith("/", 0)) {
                stringBuilder.append(getRandomCdnUrl()).append(fillCdnUrl(url));
            } else {
                stringBuilder.append(YUN_SERVER_URL).append("/").append(fillCdnUrl(url));
            }
        }
        return "";
    }

    private static String fillCdnUrl(String url) {
        if (url.contains(".png") || url.contains(".jpg"))
            return url;
        else
            return url.concat(".png");
    }

    private static String getRandomCdnUrl() {
        Random ran = new Random();
        int index = ran.nextInt(CDN_IMG_SERVER_URL.length);
        return CDN_IMG_SERVER_URL[index];
    }
}