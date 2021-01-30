package com.istone.testbuy.ui.activity;

import android.view.View;

import com.blankj.utilcode.util.CollectionUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.istone.testbuy.Constant;
import com.istone.testbuy.R;
import com.istone.testbuy.base.BaseActivity;
import com.istone.testbuy.databinding.ActivtyMainSimpleBinding;
import com.istone.testbuy.http.HttpConfig;
import com.istone.testbuy.ui.adapter.DescAdapter;
import com.istone.testbuy.ui.adapter.SearchAutoAdapterNew;
import com.istone.testbuy.ui.entity.GoodsDetailResponse;
import com.istone.testbuy.ui.entity.GoodsSkInfolResponse;
import com.istone.testbuy.ui.entity.PriTmplIdResponse;
import com.istone.testbuy.ui.entity.RequestStateResponse;
import com.istone.testbuy.ui.entity.SkBuyBean;
import com.istone.testbuy.ui.entity.SkBuyResponse;
import com.istone.testbuy.ui.entity.StockResponse;
import com.istone.testbuy.ui.iView.IMainView;
import com.istone.testbuy.ui.listener.OnSearchSelectListener;
import com.istone.testbuy.ui.presenter.MainPresenter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.istone.testbuy.http.HttpParams.addressId;
import static com.istone.testbuy.http.HttpParams.addressId1;
import static com.istone.testbuy.http.HttpParams.addressId2;
import static com.istone.testbuy.http.HttpParams.addressId3;
import static com.istone.testbuy.http.HttpParams.addressId4;
import static com.istone.testbuy.http.HttpParams.siteId;

/**
 * Created by Ruansu
 * on 2020/8/21 6:13 PM
 */
public class MainActivitySimple extends BaseActivity<ActivtyMainSimpleBinding, MainPresenter> implements IMainView, View.OnClickListener {
    private int currentType = Constant.SKType.SKTYPE_ALL;
    private long exitTime;
    private GoodsSkInfolResponse goodsSkInfolResponse;
    private GoodsSkInfolResponse.SkuItemsBean currentSkuItemsBean;
    private SearchAutoAdapterNew adapterNew;
    private DescAdapter adapter;
    int delay = 400;
    private boolean isRequest = false;
    private int currentStep = 0;
    private int skId = 0;
    private int count = 1;

    @Override
    protected int setupLayoutId() {
        return R.layout.activty_main_simple;
    }

    @Override
    protected MainPresenter setupPresenter() {
        return new MainPresenter(this);
    }


    private void reTry() {
        switch (currentStep) {
            case 3:
                startSK();
                break;
            case 4:
                startSK();
                break;
            case 5:
                startSK();
                break;
        }
    }


    @Override
    protected void initView() {
        binding.setListener(this);
        adapter = new DescAdapter(new ArrayList<>());
        binding.listDesc.setAdapter(adapter);
    }

    @Override
    public void getProductInfo(GoodsDetailResponse goodsDetailResponse) {
        String str = "获取商品信息成功";
        addString(str);
    }

    @Override
    public void getskProductInfo(GoodsSkInfolResponse goodsSkInfolResponse) {
        String str = "获取秒杀商品信息成功";
        addString(str);
        this.goodsSkInfolResponse = goodsSkInfolResponse;
        if (CollectionUtils.isNotEmpty(goodsSkInfolResponse.getSkuItems())) {
            if (adapterNew == null) {
                adapterNew = new SearchAutoAdapterNew(goodsSkInfolResponse.getSkuItems(), onAutoClickListener);
            } else {
                adapterNew.resetList(goodsSkInfolResponse.getSkuItems());
            }
            binding.listGoods.setAdapter(adapterNew);
            requestPriTmplId1();
            currentStep = 2;
            selectSKProduct();
        } else {
            showError(0, "商品尺码返回为空重新请求商品信息");
            reGetGoodsDetail();
        }

//        presenter.getProductInfo(goodsSkInfolResponse.getProductCode());
    }

    private void reGetGoodsDetail() {
        if (isRequest) {
            ThreadUtils.runOnUiThreadDelayed(new Runnable() {
                @Override
                public void run() {
                    requestSKInfo();
                }
            }, delay);
        }
    }

    @Override
    public void getPriTmplId(PriTmplIdResponse priTmplIdResponse, int step) {
//        if (step == 1) {
//            currentStep = 3;
//        } else if (step == 2) {
//            currentStep = 4;
//        }
    }

    @Override
    public void getStock(StockResponse stockResponse) {
        if (stockResponse.getStock() > 0) {
            currentStep = 4;
            addString("当前库存数量：" + stockResponse.getStock()+"   ");
            startBuy();
        } else {
            currentStep = 3;
            showError(0, "当前尺码没有库存");
        }
    }

    @Override
    public void skBuy(SkBuyResponse skBuyResponse) {
        if (StringUtils.isEmpty(skBuyResponse.getBizId())) {
            showError(0, "下单参数返回错误");
        } else {
            addString("验证秒杀请求下单状态");
            presenter.requestState(skBuyResponse.getBizId(), siteId);
        }
    }

    @Override
    public void requestState(RequestStateResponse requestStateResponse) {
        if (!StringUtils.isEmpty(requestStateResponse.getOrderId())) {
            addString("秒杀成功去付款 订单号：" + requestStateResponse.getOrderId());
            currentStep = 4;
        } else {
            currentStep = 3;
            showError(0, "请求其他尺码下单");
        }
    }

    /*第一次获取商品信息后 设置秒杀商品*/
    private void selectSKProduct() {
        if (goodsSkInfolResponse != null && goodsSkInfolResponse.getSkuItems().size() > 0) {
            setSKProductByItems(goodsSkInfolResponse.getSkuItems(), false);
        } else {
            showError(0, "秒杀详情返回错误");
            reGetGoodsDetail();
        }
    }

    private void setSKProductByItems(List<GoodsSkInfolResponse.SkuItemsBean> list, boolean isReset) {
        int start = 0;
        int end = goodsSkInfolResponse.getSkuItems().size();
        int position = 0;
        int stock = 0;
        if (isReset) {
            if (end <= 1) {
                position = 0;
            } else if (end < 4 && end > 1) {
                position = (int) (Math.random() * (end)) + start;
            } else if (end > 4) {
                position = (int) Math.random() * 4 + start;
            }
        } else {
            for (int i = start; i < end; i++) {
                GoodsSkInfolResponse.SkuItemsBean skuItemsBean = list.get(i);
                if (i == start)
                    stock = skuItemsBean.getStock();
                else if (skuItemsBean.getStock() > stock) {
                    stock = skuItemsBean.getStock();
                    position = i;
                }
            }
        }

        adapterNew.setSelecPosition(position);
        currentSkuItemsBean = list.get(position);
        addString("当前选择" + currentSkuItemsBean.getSize());
    }

    private OnSearchSelectListener onAutoClickListener = new OnSearchSelectListener() {
        @Override
        public void click(String s, int index) {
            addString("当前选择" + s);
            adapterNew.setSelecPosition(index);
            currentSkuItemsBean = goodsSkInfolResponse.getSkuItems().get(index);
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.getGoods:
                isRequest = true;
                if (!StringUtils.isEmpty(binding.etSkid.getText().toString())) {
                    if (adapterNew != null)
                        adapterNew.resetList(new ArrayList<>());
                    skId = Integer.parseInt(binding.etSkid.getText().toString());
                    requestSKInfo();
                    currentStep = 1;
                } else {
                    addString("秒杀编号为空");
                    currentStep = 0;
                }
                if (!StringUtils.isEmpty(binding.etCount.getText().toString())) {
                    count = Integer.parseInt(binding.etCount.getText().toString());
                }
                break;
            case R.id.startBuy:
                isRequest = true;
                startBuy();
                break;
            case R.id.stopBuy:
                isRequest = false;
                addString("停止秒杀");
                currentStep = 0;
                break;
        }
    }

    /*第1步请求秒杀商品信息*/
    private void requestSKInfo() {
        presenter.getSkProductInfo(skId);
    }

    /*第请求秒杀商品信息之后  第一次请求priTmplId*/
    private void requestPriTmplId1() {
        addString("请求PriTmplId  1");
        presenter.getPriTmplId1();
    }

    /*第2步请求秒杀商品信息  第二次请求priTmplId  查询选择尺码库存*/
    private void startSK() {
        presenter.getPriTmplId2();
        addString("请求PriTmplId  2");
        getGoodsStock();
//        startBuy();
    }

    private void getGoodsStock() {
        addString("查询库存");
        if (currentSkuItemsBean != null && !StringUtils.isEmpty(currentSkuItemsBean.getGoodsSku())) {
            currentStep = 3;
            presenter.getStock(skId, currentSkuItemsBean.getGoodsSku());
        } else {
            currentStep = 3;
            showError(0, "没有返回尺码信息 重新请求");
        }
    }

    /*第3步直接下单*/
    private void startBuy() {
        if (currentSkuItemsBean == null) return;
        currentStep = 3;
        int adress = addressId;
        if (HttpConfig.getCountIndex() == 1)
            adress = addressId1;
        else if (HttpConfig.getCountIndex() == 2)
            adress = addressId2;
        else if (HttpConfig.getCountIndex() == 3)
            adress = addressId3;
        else if (HttpConfig.getCountIndex() == 4)
            adress = addressId4;
        SkBuyBean skBuyBean = new SkBuyBean(adress, goodsSkInfolResponse.getSkId(), siteId);
        SkBuyBean.ProductInfoBean productInfoBean = new SkBuyBean.ProductInfoBean(currentSkuItemsBean.getGoodsSku(), goodsSkInfolResponse.getProductCode(), count);
        skBuyBean.setProductInfo(productInfoBean);
        skBuyBean.setOrderRemark("");
        addString("秒杀请求下单");
        presenter.skBuy(skBuyBean);
    }

    private void addString(String string) {
        adapter.addString(string + getDateStr());
        binding.listDesc.scrollToPosition(0);
    }

    private String getDateStr() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒SSS毫秒");
        return simpleDateFormat.format(date);
    }

    @Override
    public void showError(int code, String error) {
        if (code != 0) {
            addString("code：" + code + ":" + error);
        } else {
            addString(error);
        }
        if (code == 426) {
            isRequest = false;
        }
        updateDelay();
        if (isRequest && currentStep > 2) {
            ThreadUtils.runOnUiThreadDelayed(new Runnable() {
                @Override
                public void run() {
                    reTry();
                }
            }, delay);
        }
    }

    private void updateDelay() {
        if (currentStep > 2)
            delay = 500;
        else
            delay = 500;
    }

    @Override
    protected void onDestroy() {
        isRequest = false;
        super.onDestroy();
    }
}
