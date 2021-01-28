package com.istone.testbuy.util;

import com.blankj.utilcode.util.SPUtils;

import java.util.List;

/*列出特殊处理方法，其余put方法直接使用SPUtils.getInstance()*/
public class SPUtil {

    public static void putObject(String key, Object obj) {
        if (obj instanceof String)
            SPUtils.getInstance().put(key, (String) obj);
        else if (obj instanceof Integer)
            SPUtils.getInstance().put(key, (Integer) obj);
        else if (obj instanceof Long)
            SPUtils.getInstance().put(key, (Long) obj);
        else if (obj instanceof Boolean)
            SPUtils.getInstance().put(key, (Boolean) obj);
        else if (obj instanceof Float)
            SPUtils.getInstance().put(key, (Float) obj);
        else
            SPUtils.getInstance().put(key, GsonUtil.object2Json(obj));
    }

    public static <T> T getObject(String key, Class<T> clazz) {
        return GsonUtil.json2Object(SPUtils.getInstance().getString(key), clazz);
    }

    public static void putList(String key, List<?> list) {
        SPUtils.getInstance().put(key, GsonUtil.object2Json(list));
    }

    public static <T> List<T> getList(String key, Class<T> clazz) {
        return GsonUtil.json2List(SPUtils.getInstance().getString(key), clazz);
    }

}
