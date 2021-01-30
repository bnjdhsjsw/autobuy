package com.istone.testbuy.ui.activity;

import android.view.View;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.StringUtils;
import com.istone.testbuy.R;
import com.istone.testbuy.base.BaseActivity;
import com.istone.testbuy.base.BasePresenter;
import com.istone.testbuy.base.IBaseView;
import com.istone.testbuy.databinding.ActivityStartBinding;
import com.istone.testbuy.http.HttpParams;
import com.istone.testbuy.util.SPUtil;
import com.istone.testbuy.util.SharedPreferencesHelper;

/**
 * Created by Ruansu
 * on 2020/8/25 4:21 PM
 */
public class StartActivity extends BaseActivity<ActivityStartBinding, BasePresenter> implements IBaseView, View.OnClickListener {

    @Override
    protected int setupLayoutId() {
        return R.layout.activity_start;
    }

    @Override
    protected BasePresenter setupPresenter() {
        return new BasePresenter(this);
    }

    @Override
    protected boolean isLightMode() {
        return false;
    }

    @Override
    protected void initView() {
        binding.setListener(this);
        try {
            String siteId = SharedPreferencesHelper.getCacheObject(StartActivity.this,"siteId","",String.class);
            if (com.istone.testbuy.util.StringUtils.isNotBlank(siteId)) binding.siteId.setText(siteId);

            String adressId = SharedPreferencesHelper.getCacheObject(StartActivity.this,"addressId","", String.class);
            if (com.istone.testbuy.util.StringUtils.isNotBlank(adressId)) binding.adressId.setText(adressId);

            String Referer = SharedPreferencesHelper.getCacheObject(StartActivity.this,"Referer","", String.class);
            if (com.istone.testbuy.util.StringUtils.isNotBlank(Referer)) binding.Referer.setText(Referer);

            String Authorization = SharedPreferencesHelper.getCacheObject(StartActivity.this,"Authorization","", String.class);
            if (com.istone.testbuy.util.StringUtils.isNotBlank(Authorization)) binding.Authorization.setText(Authorization);
        } catch (Exception e) {

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next:
                if (StringUtils.isEmpty(binding.Authorization.getText())
                        || StringUtils.isEmpty(binding.siteId.getText())
                        || StringUtils.isEmpty(binding.Referer.getText())
                        || StringUtils.isEmpty(binding.adressId.getText())) {
                    showToast("必要参数没有填写");
                } else {
                    if (!StringUtils.isEmpty(binding.siteId.getText().toString())) {
                        int siteId = Integer.valueOf(binding.siteId.getText().toString());
                        HttpParams.siteId = siteId > 0 ? siteId : 821;
                        SPUtil.putObject("siteId", siteId);
                        SharedPreferencesHelper.cacheObject(StartActivity.this,"siteId",String.valueOf(siteId));
                    }
                    if (!StringUtils.isEmpty(binding.Referer.getText().toString())) {
                        String Referer = binding.Referer.getText().toString();
                        HttpParams.Referer_JSON = Referer;
                        SharedPreferencesHelper.cacheObject(StartActivity.this,"Referer",Referer);
                    }
                    if (!StringUtils.isEmpty(binding.Authorization.getText().toString())) {
                        String Authorization = binding.Authorization.getText().toString();
                        HttpParams.Authorization_JSON = Authorization;
                        SharedPreferencesHelper.cacheObject(StartActivity.this,"Authorization",Authorization);
                    }
                    if (!StringUtils.isEmpty(binding.adressId.getText().toString())) {
                        int addressId = Integer.valueOf(binding.adressId.getText().toString());
                        HttpParams.addressId = addressId > 0 ? addressId : 1574697;
                        SharedPreferencesHelper.cacheObject(StartActivity.this,"addressId",String.valueOf(addressId));
                    }
                    skipMain();
                }
                break;
        }
    }

    private void skipMain() {
        ActivityUtils.startActivity(ChooseTypeActivity.class);
    }

    @Override
    public void showError(int code, String error) {

    }
}
