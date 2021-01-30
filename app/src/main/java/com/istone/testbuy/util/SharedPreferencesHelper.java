package com.istone.testbuy.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.List;

public class SharedPreferencesHelper {
	public static final String SS_NAME = "autobuy";

	public static <T> void cacheObject(Context context, String key, T obj) {
		if(context==null) return;
		try {
			SharedPreferences ss = context.getSharedPreferences(SS_NAME,
					Context.MODE_PRIVATE);
			ss.edit().putString(key, GsonUtils.getJsonStrByObj(obj)).commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static <T> void cacheObject(Context context, String cacheName,
									   String key, T obj) {
		if(context==null) return;
		try {
			SharedPreferences ss = context.getSharedPreferences(cacheName,
					Context.MODE_PRIVATE);
			ss.edit().putString(key, GsonUtils.getJsonStrByObj(obj)).commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static <T> void cacheList(Context context, String key, List<T> lst) {
		if(context==null) return;
		try {
			SharedPreferences ss = context.getSharedPreferences(SS_NAME,
					Context.MODE_PRIVATE);
			ss.edit().putString(key, GsonUtils.getJsonStrByListObj1(lst))
					.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static <T> void cacheList(Context context, String cacheName,
									 String key, List<T> lst) {
		if(context==null) return;
		try {
			SharedPreferences ss = context.getSharedPreferences(cacheName,
					Context.MODE_PRIVATE);
			ss.edit().putString(key, GsonUtils.getJsonStrByListObj1(lst))
					.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static <T> T getCacheObject(Context context, String key,
									   Class<T> clazz) {
		if(context==null) return null;
		try {
			SharedPreferences ss = context.getSharedPreferences(SS_NAME,
					Context.MODE_PRIVATE);
			String jsonStr = ss.getString(key, null);
			if (null == jsonStr || "".equals(jsonStr))
				return null;
			return GsonUtils.getObjByJsonStr(jsonStr, clazz);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static <T> T getCacheObject(Context context, String key,String defaultValue,
									   Class<T> clazz) {
		if(context==null) return null;
		try {
			SharedPreferences ss = context.getSharedPreferences(SS_NAME,
					Context.MODE_PRIVATE);
			String jsonStr = ss.getString(key, defaultValue);
			if (null == jsonStr || "".equals(jsonStr))
				return null;
			return GsonUtils.getObjByJsonStr(jsonStr, clazz);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static <T> T getCacheObject(Context context, String cacheName,
									   String key, String defaultValue, Class<T> clazz) {
		if(context==null) return null;
		try {
			SharedPreferences ss = context.getSharedPreferences(cacheName,
					Context.MODE_PRIVATE);
			String jsonStr = ss.getString(key, defaultValue);
			if (null == jsonStr || "".equals(jsonStr))
				return null;
			return GsonUtils.getObjByJsonStr(jsonStr, clazz);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static <T> List<T> getCacheLists(Context context, String key,
											Class<T> clazz) {
		if(context==null) return null;
		try {
			SharedPreferences ss = context.getSharedPreferences(SS_NAME,
					Context.MODE_PRIVATE);
			String jsonStr = ss.getString(key, null);
			if (null == jsonStr || "".equals(jsonStr))
				return null;
			return GsonUtils.getListObjByJsonStr(jsonStr, clazz);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static <T> List<T> getCacheLists(Context context, String cacheName,
											String key, String defaultValue, Class<T> clazz) {
		if(context==null) return null;
		try {
			SharedPreferences ss = context.getSharedPreferences(cacheName,
					Context.MODE_PRIVATE);
			String jsonStr = ss.getString(key, defaultValue);
			if (null == jsonStr || "".equals(jsonStr))
				return null;
			return GsonUtils.getListObjByJsonStr(jsonStr, clazz);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 清理sharedPreferences SS_NAME 的缓存
	 *
	 * @param context
	 * @param key
	 */
	public static void clearCache(Context context, String key) {
		if(context==null) return;
		SharedPreferences ss = context.getSharedPreferences(SS_NAME,
				Context.MODE_PRIVATE);
		if (StringUtils.isBlank(key))
			ss.edit().clear().commit();
		else
			ss.edit().remove(key).commit();
	}

	/**
	 * 清理sharedPreferences SS_NAME 的缓存
	 *
	 * @param context
	 * @param cacheName
	 *            缓存名称
	 * @param key
	 */
	public static void clearCache(Context context, String cacheName, String key) {
		if(context==null) return;
		SharedPreferences ss = context.getSharedPreferences(cacheName,
				Context.MODE_PRIVATE);
		if (StringUtils.isBlank(key))
			ss.edit().clear().commit();
		else
			ss.edit().remove(key).commit();
	}

	/**
	 * 清理sharedPreferences SS_NAME 的缓存
	 *
	 * @param context
	 * @param cacheName
	 *            缓存名称
	 */
	public static void clearCacheName(Context context, String cacheName) {
		if(context==null) return;
		SharedPreferences ss = context.getSharedPreferences(cacheName,
				Context.MODE_PRIVATE);
		ss.edit().clear().commit();
	}
}
