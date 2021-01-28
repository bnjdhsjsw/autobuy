package com.istone.testbuy.http;

import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.StringUtils;
import com.istone.testbuy.ui.entity.ConfirmOrderBean;
import com.istone.testbuy.ui.entity.GetOrderRequestBean;
import com.istone.testbuy.ui.entity.SkBuyBean;
import com.istone.testbuy.util.ParamUtil;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

import static com.istone.testbuy.http.HttpConfig.JSON;
import static com.istone.testbuy.http.HttpConfig.getApi;
import static com.istone.testbuy.http.HttpParams.CHECK_CODE;
import static com.istone.testbuy.http.HttpParams.NEW_MOBILE;
import static com.istone.testbuy.http.HttpParams.OLD_MOBILE;
import static com.istone.testbuy.http.HttpParams.OLD_MOBILE_CHECK_CODE;
import static com.istone.testbuy.http.HttpParams.SIGN;
import static com.istone.testbuy.http.HttpParams.TER_NO;
import static com.istone.testbuy.http.HttpParams.UA;
import static com.istone.testbuy.http.HttpParams.WEB_LOG_ID;
import static com.istone.testbuy.http.HttpParams.siteId;

/**
 * Created by Ruansu
 * on 2020/7/27 5:43 PM
 * <p>
 * 请求管理类，声名需要传递的参数
 */
public class HttpManager {

    private static final String appId = "2";

    private static boolean isNotEmpty(String text) {
        return !StringUtils.isTrimEmpty(text);
    }

    private static RequestBody getRequestBody(Object object) {
        return RequestBody.create(GsonUtils.toJson(object), JSON);
    }

    private static RequestBody getRequestBodyByJson(String json) {
        return RequestBody.create(json, JSON);
    }

    private static Map<String, Object> getBaseMap() {
        Map<String, Object> params = new HashMap<>();
        return params;
    }

    private static Map<String, Object> resetMap(Map<String, Object> params) {
        params.put(UA, "android");
        params.put(TER_NO, ParamUtil.getIMEI());
        params.put(WEB_LOG_ID, ParamUtil.getWebLogId());
        params.put(SIGN, ParamUtil.getParamSign(params));
        return params;
    }

    private static <T> void bindObserver(Observable<T> observable, Observer<T> observer) {
        if (observable != null && observer != null)
            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);
    }

    public static void getProductInfo1(String productCode, Observer observer) {
        Map<String, Object> params = getBaseMap();
        bindObserver(getApi().getProductInfo(siteId, productCode), observer);
    }

    public static void getskProductInfo(int skid, Observer observer) {
        Map<String, Object> params = getBaseMap();
        params.put("skId", skid);
        params.put("shareId",  0);
        params.put("siteId",  siteId);
        bindObserver(getApi().getSkProductInfo(getRequestBody(params)), observer);
    }

    public static void getPriTmplId(int step, Observer observer) {
        Map<String, Object> params = getBaseMap();
        String type = "skProductWillBuy";
        if (step == 2)
            type = "skProductOrderSuccess";
        params.put("type", type);
        params.put("siteId", siteId);
        bindObserver(getApi().getPriTmplId(getRequestBody(params)), observer);
    }

    public static void getStock(int skid,String sku, Observer observer) {
        Map<String, Object> params = getBaseMap();
        params.put("skId", skid);
        params.put("siteId", siteId);
        params.put("sku", sku);
        bindObserver(getApi().getStock(getRequestBody(params)), observer);
    }

    public static void requestState(String bizId,int siteId, Observer observer) {
        Map<String, Object> params = getBaseMap();
        params.put("bizId", bizId);
        params.put("siteId", siteId);
        bindObserver(getApi().requestState(getRequestBody(params)), observer);
    }

    public static void skBuy(SkBuyBean skBuyBean, Observer observer) {
        bindObserver(getApi().skBuy(getRequestBodyByJson(com.istone.testbuy.util.GsonUtils.getJsonStrByObj(skBuyBean))), observer);
    }

    public static void goOrder(GetOrderRequestBean getOrderRequestBean, Observer observer) {
        bindObserver(getApi().goOrder(getRequestBodyByJson(com.istone.testbuy.util.GsonUtils.getJsonStrByObj(getOrderRequestBean))), observer);
    }

    public static void confirmOrder(ConfirmOrderBean confirmOrderBean, Observer observer) {
        bindObserver(getApi().confirmOrder(getRequestBodyByJson(com.istone.testbuy.util.GsonUtils.getJsonStrByObj(confirmOrderBean))), observer);
    }

    public static void modifyMobile(String checkCode, String oldMobile, String newMobile, String oldMobileCheckCode, Observer observer) {
        Map<String, Object> params = getBaseMap();
        params.put(CHECK_CODE, checkCode);
        params.put(OLD_MOBILE, oldMobile);
        params.put(NEW_MOBILE, newMobile);
        params.put(OLD_MOBILE_CHECK_CODE, oldMobileCheckCode);
        bindObserver(getApi().modifyMobile(getRequestBody(params)), observer);
    }

    public static Map<String, String> bean2Map(Object bean) {
        Map<String, String> param = new LinkedHashMap<String, String>();
        if (bean == null)
            return param;
        try {
            Field[] fields = bean.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String key = field.getName();
                Object value = field.get(bean);
                if (null != value)
                    param.put(key, value.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return param;
    }
}
