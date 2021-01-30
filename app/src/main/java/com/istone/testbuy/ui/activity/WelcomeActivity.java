package com.istone.testbuy.ui.activity;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.istone.testbuy.R;
import com.istone.testbuy.base.BaseActivity;
import com.istone.testbuy.base.BasePresenter;
import com.istone.testbuy.base.IBaseView;
import com.istone.testbuy.databinding.ActivityWelcomeBinding;

/**
 * Created by Ruansu
 * on 2020/8/25 4:21 PM
 */
public class WelcomeActivity extends BaseActivity<ActivityWelcomeBinding, BasePresenter> implements IBaseView {

    private static final boolean isChoose = true;

    @Override
    protected int setupLayoutId() {
        return R.layout.activity_welcome;
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
        if (isChoose)
            ThreadUtils.runOnUiThreadDelayed(() -> ActivityUtils.startActivity(ChooseActivity.class), 1000);
        else
            ThreadUtils.runOnUiThreadDelayed(() -> ActivityUtils.startActivity(MainActivity.class), 1000);
    }

    @Override
    public void showError(int code, String error) {

    }
}
