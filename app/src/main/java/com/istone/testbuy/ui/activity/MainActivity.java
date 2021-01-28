package com.istone.testbuy.ui.activity;

import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.StringUtils;
import com.istone.testbuy.Constant;
import com.istone.testbuy.R;
import com.istone.testbuy.base.BaseActivity;
import com.istone.testbuy.databinding.ActivtyMainBinding;
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
import static com.istone.testbuy.http.HttpParams.siteId;

/**
 * Created by Ruansu
 * on 2020/8/21 6:13 PM
 */
public class MainActivity extends BaseActivity<ActivtyMainBinding, MainPresenter> implements IMainView, View.OnClickListener {
    private int currentType = Constant.SKType.SKTYPE_ALL;
    private long exitTime;
    private GoodsDetailResponse goodsDetailResponse;
    private GoodsSkInfolResponse goodsSkInfolResponse;
    private GoodsSkInfolResponse.SkuItemsBean currentSkuItemsBean;
    private SearchAutoAdapterNew adapterNew;
    private DescAdapter adapter;
    int delay = 400;
    private boolean isRequest = false;
    private int currentStep = 0;
    private int skId = 0;

    @Override
    protected int setupLayoutId() {
        return R.layout.activty_main;
    }

    @Override
    protected MainPresenter setupPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected void initView() {
        binding.setListener(this);
        adapter = new DescAdapter(new ArrayList<>());
        binding.listDesc.setAdapter(adapter);
        updateType();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            showToast(R.string.click_exit);
            exitTime = System.currentTimeMillis();
        } else
            AppUtils.exitApp();
    }


    @Override
    public void getProductInfo(GoodsDetailResponse goodsDetailResponse) {
        String str = "获取商品信息成功";
        addString(str);
        this.goodsDetailResponse = goodsDetailResponse;
    }

    @Override
    public void getskProductInfo(GoodsSkInfolResponse goodsSkInfolResponse) {
        String str = "获取秒杀商品信息成功";
        addString(str);
        this.goodsSkInfolResponse = goodsSkInfolResponse;
//        presenter.getProductInfo(goodsSkInfolResponse.getProductCode());
        if (adapterNew == null) {
            adapterNew = new SearchAutoAdapterNew(goodsSkInfolResponse.getSkuItems(), onAutoClickListener);
        } else {
            adapterNew.resetList(goodsSkInfolResponse.getSkuItems());
        }
        binding.listGoods.setAdapter(adapterNew);
        selectSKProduct();
        currentStep = 2;
        requestPriTmplId(1);
    }

    @Override
    public void getPriTmplId(PriTmplIdResponse priTmplIdResponse, int step) {
        if (step == 1) {
            currentStep = 3;
            startSK();
        } else if (step == 2) {
            currentStep = 4;
            delay = 330;
            getGoodsStock();
        }
    }

    @Override
    public void getStock(StockResponse stockResponse) {
        if (stockResponse.getStock() > 0) {
            currentStep = 5;
            addString("当前库存数量：" + stockResponse.getStock());
            startBuy();
        } else {
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
        addString("秒杀成功去付款 订单号：" + requestStateResponse.getOrderId());
        currentStep = 4;
        showError(0, "请求其他尺码下单");
    }

    /*第一次获取商品信息后 设置秒杀商品*/
    private void selectSKProduct() {
        if (goodsSkInfolResponse != null && goodsSkInfolResponse.getSkuItems().size() > 0) {
            setSKProductByItems(goodsSkInfolResponse.getSkuItems(), false);
        } else {
            showError(0, "秒杀详情返回错误");
        }
    }

    /*查询商品没有库存重新设置秒杀商品*/
    private void resetSKProduct() {
        addString("重新选择尺码");
        setSKProductByItems(goodsSkInfolResponse.getSkuItems(), true);
        getGoodsStock();
    }

    private void setSKProductByItems(List<GoodsSkInfolResponse.SkuItemsBean> list, boolean isReset) {
        int start = 0;
        int end = goodsSkInfolResponse.getSkuItems().size();
        if (goodsSkInfolResponse.getSkuItems().size() > 4) {
            switch (currentType) {
                case Constant.SKType.SKTYPE_ALL:
                    break;
                case Constant.SKType.SKTYPE_FIRST:
                    start = 0;
                    end = 4;
                    break;
                case Constant.SKType.SKTYPE_END:
                    end = goodsSkInfolResponse.getSkuItems().size();
                    start = goodsSkInfolResponse.getSkuItems().size() - 4;
                    break;
            }
        }
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
        }
    };

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (isRequest) {
                reTry();
            }
        }
    };

    private void reTry() {
        switch (currentStep) {
            case 1:
                delay = 400;
                requestSKInfo();
                break;
            case 2:
                requestPriTmplId(1);
                break;
            case 3:
                startSK();
                break;
            case 4:
                resetSKProduct();
                break;
            case 5:
                startBuy();
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startBuy:
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
                break;
            case R.id.stopBuy:
                isRequest = false;
                addString("停止秒杀");
                currentStep = 0;
                break;
            case R.id.count10:
                currentType = Constant.SKType.SKTYPE_ALL;
                addString("自动秒杀库存最多的");
                updateType();
                break;
            case R.id.count20:
                currentType = Constant.SKType.SKTYPE_FIRST;
                addString("自动秒杀前4个");
                updateType();
                break;
            case R.id.count50:
                currentType = Constant.SKType.SKTYPE_END;
                addString("自动秒杀后4个");
                updateType();
                break;
        }
    }

    private void updateType() {
        binding.count10.setTextColor(getResources().getColor(R.color.e333333));
        binding.count20.setTextColor(getResources().getColor(R.color.e333333));
        binding.count50.setTextColor(getResources().getColor(R.color.e333333));
        if (currentType == Constant.SKType.SKTYPE_FIRST)
            binding.count20.setTextColor(getResources().getColor(R.color.ff4c1a));
        else if (currentType == Constant.SKType.SKTYPE_END)
            binding.count50.setTextColor(getResources().getColor(R.color.ff4c1a));
        else
            binding.count10.setTextColor(getResources().getColor(R.color.ff4c1a));
    }

    /*第1步请求秒杀商品信息*/
    private void requestSKInfo() {
        presenter.getSkProductInfo(skId);
    }

    /*第2步请求秒杀商品信息  第一次请求priTmplId*/
    private void requestPriTmplId(int step) {
        addString("请求PriTmplId  1");
        presenter.getPriTmplId1();
    }

    /*第3步请求秒杀商品信息  第二次请求priTmplId*/
    private void startSK() {
        isRequest = true;
        presenter.getPriTmplId2();
        addString("请求PriTmplId  2");
    }

    /*第4步 获取选择尺码的库存*/
    private void getGoodsStock() {
        addString("查询库存");
        if (currentSkuItemsBean != null && !StringUtils.isEmpty(currentSkuItemsBean.getGoodsSku()))
            presenter.getStock(skId, currentSkuItemsBean.getGoodsSku());
        else {
            currentStep = 1;
            showError(0, "");
        }
    }

    private void startBuy() {
        int adress = addressId;
        if (HttpConfig.getCountIndex() == 1)
            adress = addressId1;
        else if (HttpConfig.getCountIndex() == 2)
            adress = addressId2;
        else if (HttpConfig.getCountIndex() == 3)
            adress = addressId3;
        SkBuyBean skBuyBean = new SkBuyBean(adress, goodsSkInfolResponse.getSkId(), siteId);
        SkBuyBean.ProductInfoBean productInfoBean = new SkBuyBean.ProductInfoBean(currentSkuItemsBean.getGoodsSku(), goodsSkInfolResponse.getProductCode(), 1);
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
    public void showError( int code ,String error) {
        addString(error);
        if (isRequest) {
            handler.sendEmptyMessageDelayed(1, delay);
        }
//            startBuy();
    }
}
