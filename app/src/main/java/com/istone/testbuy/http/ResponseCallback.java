package com.istone.testbuy.http;

import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.LogUtils;
import com.istone.testbuy.base.BaseModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResponseCallback implements Callback<BaseModel<Object>> {

    private HttpCallback callback;

    ResponseCallback(HttpCallback callback) {
        this.callback = callback;
    }

    @Override
    public void onResponse(Call<BaseModel<Object>> call, Response<BaseModel<Object>> response) {
        try {
            if (callback != null) {
                if (response.isSuccessful()) {
                    BaseModel<Object> body = response.body();
                    if (body != null) {
                        if (body.getCode()==2000)
                            callback.onResponseReturn(body.getContent() == null ?
                                    body.getMessage() : GsonUtils.toJson(body.getContent()));
                    }
                } else
                    callback.onResponseReturn(response.message());
            }
        } catch (Exception e) {
            LogUtils.eTag(HttpParams.ERROR_TAG, e);
        }
    }

    @Override
    public void onFailure(Call<BaseModel<Object>> call, Throwable t) {
        try {
            if (callback != null)
                callback.onResponseReturn(t.getMessage());
        } catch (Exception e) {
            LogUtils.eTag(HttpParams.ERROR_TAG, e);
        }
    }

    public interface HttpCallback {
        void onResponseReturn(String data) throws Exception;
    }

}
