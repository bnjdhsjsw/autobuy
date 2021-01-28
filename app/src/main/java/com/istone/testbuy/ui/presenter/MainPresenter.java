package com.istone.testbuy.ui.presenter;

import com.istone.testbuy.base.BasePresenter;
import com.istone.testbuy.http.HttpManager;
import com.istone.testbuy.ui.entity.GoodsDetailResponse;
import com.istone.testbuy.ui.entity.GoodsSkInfolResponse;
import com.istone.testbuy.ui.entity.PriTmplIdResponse;
import com.istone.testbuy.ui.entity.RequestStateResponse;
import com.istone.testbuy.ui.entity.SkBuyBean;
import com.istone.testbuy.ui.entity.SkBuyResponse;
import com.istone.testbuy.ui.entity.StockResponse;
import com.istone.testbuy.ui.iView.IMainView;

/**
 * Created by Ruansu
 * on 11/20/20 4:59 PM
 */
public class MainPresenter extends BasePresenter<IMainView> {

    public MainPresenter(IMainView view) {
        super(view);
    }

    public void getProductInfo(String productCode) {
        HttpManager.getProductInfo1(productCode, new ResultCallback<GoodsDetailResponse>() {
            @Override
            protected void onResult(GoodsDetailResponse goodsDetailResponse) {
                view.getProductInfo(goodsDetailResponse);
            }

            @Override
            protected void showError(int code,String error) {
                view.showError(code,error);
            }
        });
    }

    public void getPriTmplId(int step)
    {
        HttpManager.getPriTmplId(step,new ResultCallback<PriTmplIdResponse>() {

            @Override
            protected void onResult(PriTmplIdResponse priTmplIdResponse) {
                view.getPriTmplId(priTmplIdResponse,step);
            }

            @Override
            protected void showError(int code,String error) {
                view.showError(code,error);
            }
        });
    }

    public void getPriTmplId1()
    {
        HttpManager.getPriTmplId(1,new ResultCallback<PriTmplIdResponse>() {

            @Override
            protected void onResult(PriTmplIdResponse priTmplIdResponse) {
                view.getPriTmplId(priTmplIdResponse,1);
            }

            @Override
            protected void showError(int code,String error) {
                view.showError(code,error);
            }
        });
    }

    public void getPriTmplId2()
    {
        HttpManager.getPriTmplId(2,new ResultCallback<PriTmplIdResponse>() {

            @Override
            protected void onResult(PriTmplIdResponse priTmplIdResponse) {
                view.getPriTmplId(priTmplIdResponse,2);
            }

            @Override
            protected void showError(int code,String error) {
                view.showError(code,error);
            }
        });
    }

    public void getStock(int skId,String sku)
    {
        HttpManager.getStock(skId,sku,new ResultCallback<StockResponse>() {

            @Override
            protected void onResult(StockResponse stockResponse) {
                view.getStock(stockResponse);
            }

            @Override
            protected void showError(int code,String error) {
                view.showError(code,error);
            }
        });
    }


    public void getSkProductInfo(int skId) {
        HttpManager.getskProductInfo(skId, new ResultCallback<GoodsSkInfolResponse>() {
            @Override
            protected void onResult(GoodsSkInfolResponse goodsSkInfolResponse) {
                view.getskProductInfo(goodsSkInfolResponse);
            }

            @Override
            protected void showError(int code,String error) {
                view.showError(code,error);
            }
        });
    }

    public void skBuy(SkBuyBean skBuyBean) {
        HttpManager.skBuy(skBuyBean, new ResultCallback<SkBuyResponse>() {

            @Override
            protected void onResult(SkBuyResponse skBuyResponse) {
                    view.skBuy(skBuyResponse);
            }

            @Override
            protected void showError(int code,String error) {
                view.showError(code,error);
            }
        });
    }

    public void requestState(String bizId,int siteId) {
        HttpManager.requestState(bizId,siteId, new ResultCallback<RequestStateResponse>() {
            @Override
            protected void onResult(RequestStateResponse requestStateResponse) {
                view.requestState(requestStateResponse);
            }

            @Override
            protected void showError(int code,String error) {
                view.showError(code,error);
            }
        });
    }
}
