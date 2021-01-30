package com.istone.testbuy.ui.activity;

import android.content.Intent;
import android.view.View;

import com.istone.testbuy.R;
import com.istone.testbuy.base.BaseActivity;
import com.istone.testbuy.base.BasePresenter;
import com.istone.testbuy.databinding.ActivityChooseTypeLayoutBinding;

/**
 * Created by Ruansu
 * on 2020/8/21 6:13 PM
 */
public class ChooseTypeActivity extends BaseActivity<ActivityChooseTypeLayoutBinding, BasePresenter> implements View.OnClickListener {

    @Override
    protected int setupLayoutId() {
        return R.layout.activity_choose_type_layout;
    }

    @Override
    protected BasePresenter setupPresenter() {
        return new BasePresenter(this);
    }


    @Override
    protected void initView() {
        binding.setListener(this);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.count0:
                startActivity(new Intent(this, MainActivityAuto.class));
                break;
            case R.id.count10:
                startActivity(new Intent(this,MainActivitySimple.class));
                break;
        }
    }

    @Override
    public void showError(int code, String error) {

    }
}
