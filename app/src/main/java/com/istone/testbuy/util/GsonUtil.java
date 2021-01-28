package com.istone.testbuy.util;

import com.blankj.utilcode.util.GsonUtils;

import java.util.List;
import java.util.Map;

public class GsonUtil {

    public static String object2Json(Object object) {
        return GsonUtils.toJson(object);
    }

    public static <T> T json2Object(String json, Class<T> clazz) {
        return GsonUtils.fromJson(json, clazz);
    }

    public static <T> List<T> json2List(String json, Class<T> clazz) {
        return GsonUtils.fromJson(json, GsonUtils.getListType(clazz));
    }

    public static Map<String, String> json2Map(String json) {
        return GsonUtils.fromJson(json,
                GsonUtils.getMapType(String.class, String.class));
    }

    public static Map<String, String> object2Map(Object object) {
        return json2Map(GsonUtils.toJson(object));
    }

}
