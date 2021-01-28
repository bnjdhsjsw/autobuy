package com.istone.testbuy.http;

import android.text.TextUtils;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.PathUtils;
import com.istone.testbuy.Constant;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import okhttp3.Authenticator;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.Route;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.istone.testbuy.http.HttpParams.Authorization;
import static com.istone.testbuy.http.HttpParams.Authorization_JSON;
import static com.istone.testbuy.http.HttpParams.Authorization_JSON1;
import static com.istone.testbuy.http.HttpParams.Authorization_JSON2;
import static com.istone.testbuy.http.HttpParams.Authorization_JSON3;
import static com.istone.testbuy.http.HttpParams.Authorization_JSON4;

/**
 * 定义okHttpClient类
 */
public class HttpConfig {

    private static final String APP_ID = "2";
    private static final int TIME_OUT_SECONDS = 15;
    private static final long CACHE_SIZE = 10 * 1024 * 1024; // 10MB
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private static int countIndex = 0;
    private static Retrofit retrofit;
    private static HttpApi httpApi;

    public static int getCountIndex() {
        return countIndex;
    }

    public static void setCountIndex(int countIndex) {
        HttpConfig.countIndex = countIndex;
    }

    private static OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder()
                .readTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS)
                .callTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS)
                .writeTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS)
                .connectTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS)
                .cache(new Cache(new File(PathUtils.getInternalAppCachePath()), CACHE_SIZE))
                .authenticator(new Authenticator() {
                    @Nullable
                    @Override
                    public Request authenticate(@Nullable Route route, @NotNull Response response) throws IOException {
                        return response.request().newBuilder().header(Authorization, Authorization_JSON).build();
                    }
                })
                .addInterceptor(new RequestInterceptor())
                .retryOnConnectionFailure(true).build();
    }

    private static Retrofit getRetrofit() {
        if (retrofit == null)
            retrofit = new Retrofit.Builder()
                    .client(getOkHttpClient())
                    .baseUrl(Constant.Http.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        return retrofit;
    }

    public static HttpApi getApi() {
        if (null == httpApi)
            httpApi = getRetrofit().create(HttpApi.class);
        return httpApi;
    }

    private static class RequestInterceptor implements Interceptor {

        @NotNull
        @Override
        public Response intercept(@NotNull Chain chain) throws IOException {
            Request request = addRequestHeader(chain.request());
            String requestBody = getRequestBody(request);
            Response response = chain.proceed(request);
            String responseBody = getResponseBody(response);
            if (TextUtils.isEmpty(responseBody))
                return response;
            else {
                LogUtils.iTag(HttpParams.HTTP_TAG, request.method(),
                        request.url(), request.headers(), requestBody, responseBody);
                return response.newBuilder()
                        .body(ResponseBody.create(responseBody, HttpConfig.JSON))
                        .build();
            }
        }

        private String getRequestBody(Request request) throws IOException {
            RequestBody requestBody = request.body();
            if (requestBody != null) {
                Buffer buffer = new Buffer();
                requestBody.writeTo(buffer);
                return buffer.readString(Charset.defaultCharset());
            }
            return null;
        }

        private String getResponseBody(Response response) throws IOException {
            ResponseBody responseBody = response.body();
            if (responseBody != null)
                return responseBody.string();
            return null;
        }

        private Request addRequestHeader(Request request) {
            if (countIndex ==1)
            {
                Request.Builder builder = request.newBuilder().header(Authorization, Authorization_JSON1)
                        .addHeader(HttpParams.ACCEPT, HttpParams.APPLICATION_JSON)
                        .addHeader(HttpParams.Connection, HttpParams.Connection_JSON)
                        .addHeader(HttpParams.Host, HttpParams.Host_JSON)
                        .addHeader(HttpParams.CONTENT_TYPE, HttpParams.APPLICATION_JSON)
                        .addHeader(HttpParams.Referer, HttpParams.Referer_JSON)
                        .addHeader(HttpParams.UserAgent, HttpParams.UserAgent_JSON);
                return builder.build();
            }
            else if (countIndex == 2)
            {
                Request.Builder builder = request.newBuilder().header(Authorization, Authorization_JSON2)
                        .addHeader(HttpParams.ACCEPT, HttpParams.APPLICATION_JSON)
                        .addHeader(HttpParams.Connection, HttpParams.Connection_JSON)
                        .addHeader(HttpParams.Host, HttpParams.Host_JSON)
                        .addHeader(HttpParams.CONTENT_TYPE, HttpParams.APPLICATION_JSON)
                        .addHeader(HttpParams.Referer, HttpParams.Referer_JSON)
                        .addHeader(HttpParams.UserAgent, HttpParams.UserAgent_JSON);
                return builder.build();
            }
            else if (countIndex == 3)
            {
                Request.Builder builder = request.newBuilder().header(Authorization, Authorization_JSON3)
                        .addHeader(HttpParams.ACCEPT, HttpParams.APPLICATION_JSON)
                        .addHeader(HttpParams.Connection, HttpParams.Connection_JSON)
                        .addHeader(HttpParams.Host, HttpParams.Host_JSON)
                        .addHeader(HttpParams.CONTENT_TYPE, HttpParams.APPLICATION_JSON)
                        .addHeader(HttpParams.Referer, HttpParams.Referer_JSON)
                        .addHeader(HttpParams.UserAgent, HttpParams.UserAgent_JSON);
                return builder.build();
            }
            else if (countIndex == 4)
            {
                Request.Builder builder = request.newBuilder().header(Authorization, Authorization_JSON4)
                        .addHeader(HttpParams.ACCEPT, HttpParams.APPLICATION_JSON)
                        .addHeader(HttpParams.Connection, HttpParams.Connection_JSON)
                        .addHeader(HttpParams.Host, HttpParams.Host_JSON)
                        .addHeader(HttpParams.CONTENT_TYPE, HttpParams.APPLICATION_JSON)
                        .addHeader(HttpParams.Referer, HttpParams.Referer_JSON)
                        .addHeader(HttpParams.UserAgent, HttpParams.UserAgent_JSON);
                return builder.build();
            }
            else
            {
                Request.Builder builder = request.newBuilder().header(Authorization, Authorization_JSON)
                        .addHeader(HttpParams.ACCEPT, HttpParams.APPLICATION_JSON)
                        .addHeader(HttpParams.Connection, HttpParams.Connection_JSON)
                        .addHeader(HttpParams.Host, HttpParams.Host_JSON)
                        .addHeader(HttpParams.CONTENT_TYPE, HttpParams.APPLICATION_JSON)
                        .addHeader(HttpParams.Referer, HttpParams.Referer_JSON)
                        .addHeader(HttpParams.UserAgent, HttpParams.UserAgent_JSON);
                return builder.build();
            }
        }

    }

}
