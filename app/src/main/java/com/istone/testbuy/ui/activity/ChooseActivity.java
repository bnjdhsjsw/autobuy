package com.istone.testbuy.ui.activity;

import android.view.View;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.StringUtils;
import com.istone.testbuy.R;
import com.istone.testbuy.base.BaseActivity;
import com.istone.testbuy.base.BasePresenter;
import com.istone.testbuy.base.IBaseView;
import com.istone.testbuy.databinding.ActivtyChooseBinding;
import com.istone.testbuy.http.HttpConfig;
import com.istone.testbuy.http.HttpParams;

/**
 * Created by Ruansu
 * on 2020/8/25 4:21 PM
 */
public class ChooseActivity extends BaseActivity<ActivtyChooseBinding, BasePresenter> implements IBaseView , View.OnClickListener{

    @Override
    protected int setupLayoutId() {
        return R.layout.activty_choose;
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
    }

    int count = 0;
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.count0:
                count ++;
                if (count>=3)
                    binding.count30.setVisibility(View.VISIBLE);
                    binding.count40.setVisibility(View.VISIBLE);
                    binding.count50.setVisibility(View.VISIBLE);
                break;
            case R.id.count10:
                HttpConfig.setCountIndex(1);
                skipMain();
                break;
            case R.id.count20:
                HttpConfig.setCountIndex(2);
                skipMain();
                break;
            case R.id.count30:
                HttpConfig.setCountIndex(3);
                skipMain();
                break;
            case R.id.count40:
                HttpConfig.setCountIndex(4);
                skipMain();
                break;
            case R.id.count50:
                HttpConfig.setCountIndex(5);
                skipMain();
                break;
        }
    }

    private void skipMain()
    {
        if (!StringUtils.isEmpty(binding.etSkid.getText().toString())) {
            HttpParams.siteId = Integer.valueOf(binding.etSkid.getText().toString())>0?Integer.valueOf(binding.etSkid.getText().toString()):821;
        }
        ActivityUtils.startActivity(ChooseTypeActivity.class);
    }

    @Override
    public void showError(int code, String error) {

    }
}
