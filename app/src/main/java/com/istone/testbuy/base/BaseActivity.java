package com.istone.testbuy.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.CollectionUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.StringUtils;
import com.istone.testbuy.util.ToastUtil;

import java.util.List;

/**
 * Created by Ruansu
 * on 2020/7/27 4:13 PM
 * <p>
 * activity基类，定义基本规则方法以及通用方法
 */
public abstract class BaseActivity<B extends ViewDataBinding, P extends BasePresenter> extends AppCompatActivity
        implements IBaseView {

    protected B binding;//由重写setupLayoutId的布局转为ViewDataBinding全局变量
    protected P presenter;//由重写setupPresenter传入presenter全局变量

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScreenUtils.setPortrait(this);
        setContentView(getContentView());
        presenter = setupPresenter();
        if (presenter != null) presenter.setContext(this);
        initView();
        Intent intent = getIntent();
        if (intent != null) dealWithIntent(intent);
        BarUtils.transparentStatusBar(this);//将activity内容延伸至状态栏
        setStatusBarLightMode(isLightMode());
        if (isTopMarginStatusBar()) addMarginTopEqualStatusBarHeight(binding.getRoot());
    }

    protected abstract int setupLayoutId();//设置布局id

    protected View getContentView() {//扩展设置布局方法，子类可重写该方法覆写binding值
        if (setupLayoutId() != 0)
            binding = DataBindingUtil.inflate(LayoutInflater.from(this),
                    setupLayoutId(), null, false);
        return binding.getRoot();
    }

    protected void initView() {//初始化view
    }

    protected P setupPresenter() {//设置presenter
        return null;
    }

    protected void dealWithIntent(Intent intent) {//处理传递参数
    }

    protected boolean isLightMode() {//系统提供的修改状态栏背景和状态栏文字颜色，默认白底黑字，设置false为白字
        return true;
    }

    protected boolean isTopMarginStatusBar() {//子类可重写该方法让内容不延伸至状态栏
        return false;
    }

    public String getTag() {//获取activity类名，打断点tag使用
        return getClass().getSimpleName();
    }

    protected boolean isEmpty(String text) {//String判空方法
        return StringUtils.isTrimEmpty(text);
    }

    protected boolean isListEmpty(List<?> list) {//List判空方法
        return CollectionUtils.isEmpty(list);
    }

    protected void showToast(String message) {//传入String弹出Toast
        ToastUtil.show(message);
    }

    protected void showToast(@StringRes int resId) {//传入String ID弹出Toast
        ToastUtil.show(resId);
    }

    protected void setStatusBarLightMode(boolean isLightMode) {//子类可调用该方法重新设置状态栏模式
        BarUtils.setStatusBarLightMode(this, isLightMode);
    }

    protected void addMarginTopEqualStatusBarHeight(View view) {//给View添加状态栏高度的头部间距
        BarUtils.addMarginTopEqualStatusBarHeight(view);
    }

    @Override
    public void showLoading() {//展示等待框
    }

    @Override
    public void hideLoading() {//取消等待框
    }
}
