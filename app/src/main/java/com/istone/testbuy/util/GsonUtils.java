package com.istone.testbuy.util;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/***
 * Gson有2个最基本的方法 <li>toJson() – bean 2 String</li> <li>fromJson() – String 2
 * bean</li>
 */
public class GsonUtils {
	private static ObjectMapper mObjectMapper;

	static {
		mObjectMapper = new ObjectMapper();
		// 反序列化时，忽略不匹配或者Bean不存在的字段
		mObjectMapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mObjectMapper.configure(Feature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT,
				false);
		mObjectMapper.setVisibility(JsonMethod.FIELD, Visibility.ANY);
	}

	/**
	 * Object to Json str
	 */
	public static <PK> String getJsonStrByObj(PK obj) {
		String jsonStr = "";
		try {
			jsonStr = mObjectMapper.writeValueAsString(obj);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Gson gson = new Gson();
		// String jsonStr = gson.toJson(obj);
		return jsonStr;
	}

	/**
	 * List[Object] to Json str
	 */
	public static String getJsonStrByListObj1(List lst) {
		String jsonStr = "";
		try {
			jsonStr = mObjectMapper.writeValueAsString(lst);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Gson gson = new Gson();
		// String jsonStr = gson.toJson(obj);
		return jsonStr;
		// Gson gson = new Gson();
		// Type type = new TypeToken<List>() {
		// }.getType();
		// String beanListToJson = gson.toJson(lst, type);
		// return beanListToJson;
	}

	/**
	 * List[Object] to Json str
	 */
	public static <PK> String getJsonStrByListObj2(List<PK> lst) {
		String jsonStr = "";
		try {
			jsonStr = mObjectMapper.writeValueAsString(lst);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonStr;
		// StringBuilder strJson = new StringBuilder("[");
		// Gson gson = new Gson();
		// for (int i = 0; i < lst.size(); i++) {
		// if (i != lst.size() - 1) {
		// strJson.append(gson.toJson(lst.get(i)) + ",");
		// } else {
		// strJson.append(gson.toJson(lst.get(i)));
		// }
		// }
		// strJson = strJson.append("]");
		// return strJson.toString();
	}

	/**
	 * Json str to Object
	 */
	public static <PK> PK getObjByJsonStr(String jsonStr, Class<PK> clazz) {
		PK pk = null;
		try {
			pk = mObjectMapper.readValue(jsonStr, clazz);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd
		// HH:mm:ss").create();
		// try {
		// if (jsonStr != null) {
		// PK pk = gson.fromJson(jsonStr.toString(), clazz);
		// return pk;
		// }
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		return pk;
	}

	/**
	 * Json str to List[Object]
	 */
	public static <PK> List<PK> getListObjByJsonStr(String jsonStr,
			Class<PK> clazz) {
		List<PK> list = null;
		try {
			list = mObjectMapper.readValue(jsonStr, getCollectionType(ArrayList.class, clazz));
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// List<PK> list = new ArrayList<PK>();
		// JSONArray jsonArray = null;
		// try {
		// jsonArray = new JSONArray(jsonStr.toString());
		// for (int i = 0; i < jsonArray.length(); i++) {
		// list.add(getObjByJsonStr(jsonArray.get(i).toString(), clazz));
		// }
		// } catch (JSONException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		return list;
	}

	public static JavaType getCollectionType(Class<?> collectionClass,
			Class<?>... elementClasses) {
		return mObjectMapper.getTypeFactory().constructParametricType(
				collectionClass, elementClasses);
	}

}
