package com.istone.testbuy.http;

import com.istone.testbuy.base.BaseModel;
import com.istone.testbuy.ui.entity.ConfirmOrderResponse;
import com.istone.testbuy.ui.entity.GetOrderResponse;
import com.istone.testbuy.ui.entity.GoodsDetailResponse;
import com.istone.testbuy.ui.entity.GoodsSkInfolResponse;
import com.istone.testbuy.ui.entity.PriTmplIdResponse;
import com.istone.testbuy.ui.entity.RequestStateResponse;
import com.istone.testbuy.ui.entity.SkBuyResponse;
import com.istone.testbuy.ui.entity.StockResponse;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 请求地址声名类，声明请求时将需要转成的对像传入
 */
public interface HttpApi {

    //渠道列表
    @POST("channel/getList")
    Call<BaseModel<Object>> getChannelList(@Body RequestBody body);

    //修改手机
    @POST("app/banggo/modifyMobile")
    Observable<BaseModel<String>> modifyMobile(@Body RequestBody body);

    @POST("sec/kill/product/request/state")
    Observable<BaseModel<RequestStateResponse>> requestState(@Body RequestBody body);


    @POST("sec/kill/product/buy")
    Observable<BaseModel<SkBuyResponse>> skBuy(@Body RequestBody body);

    @POST("subScribe/priTmplId/get")
    Observable<BaseModel<PriTmplIdResponse>> getPriTmplId(@Body RequestBody body);

    @POST("sec/kill/product/sku/stock")
    Observable<BaseModel<StockResponse>> getStock(@Body RequestBody body);

    //修改手机
    @GET("product/info")
    Observable<BaseModel<GoodsDetailResponse>> getProductInfo(@Query("siteId") int siteId, @Query("productCode") String productCode);

    @POST("sec/kill/product/info")
    Observable<BaseModel<GoodsSkInfolResponse>> getSkProductInfo(@Body RequestBody body);

    //修改手机
    @POST("order/new/goOrder")
    Observable<BaseModel<GetOrderResponse>> goOrder(@Body RequestBody body);

    //修改手机
    @POST("order/new/production")
    Observable<BaseModel<ConfirmOrderResponse>> confirmOrder(@Body RequestBody body);
}
