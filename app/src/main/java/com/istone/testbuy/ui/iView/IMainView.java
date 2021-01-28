package com.istone.testbuy.ui.iView;

import com.istone.testbuy.base.IBaseView;
import com.istone.testbuy.ui.entity.GoodsDetailResponse;
import com.istone.testbuy.ui.entity.GoodsSkInfolResponse;
import com.istone.testbuy.ui.entity.PriTmplIdResponse;
import com.istone.testbuy.ui.entity.RequestStateResponse;
import com.istone.testbuy.ui.entity.SkBuyResponse;
import com.istone.testbuy.ui.entity.StockResponse;

/**
 * Created by Ruansu
 * on 11/20/20 5:01 PM
 */
public interface IMainView extends IBaseView {

    void getProductInfo(GoodsDetailResponse goodsDetailResponse);
    void getskProductInfo(GoodsSkInfolResponse goodsSkInfolResponse);
    void getPriTmplId(PriTmplIdResponse priTmplIdResponse,int step);
    void getStock(StockResponse stockResponse);
    void skBuy(SkBuyResponse skBuyResponse);
    void requestState(RequestStateResponse requestStateResponse);

}
