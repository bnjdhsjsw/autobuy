package com.istone.testbuy.base;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.blankj.utilcode.util.CollectionUtils;
import com.blankj.utilcode.util.StringUtils;

import java.util.List;

/**
 * Created by Ruansu
 * on 2020/8/5 3:59 PM
 * <p>
 * 自定义view基类
 */
public abstract class BaseView<B extends ViewDataBinding> extends LinearLayout {

    protected B binding;

    public BaseView(Context context) {
        super(context);
        init();
    }

    public BaseView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        setViewFromAttr(attrs);
    }

    public BaseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        setViewFromAttr(attrs);
    }

    private void init() {
        if (setupLayoutId() == 0) return;
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()),
                setupLayoutId(), this, true);
        initView();
    }

    protected abstract int setupLayoutId();

    protected void initView() {
    }

    protected int[] setupStyleable() {//设置自定义属性
        return null;
    }

    private void setViewFromAttr(AttributeSet attrs) {//根据自定义属性设置view
        if (setupStyleable() == null) return;
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, setupStyleable());
        setViewFromTypedArray(typedArray);
        typedArray.recycle();
    }

    protected void setViewFromTypedArray(TypedArray typedArray) {//拓展方法便于获取属性值
    }

    public boolean isEmpty(String text) {
        return StringUtils.isTrimEmpty(text);
    }

    public boolean isListEmpty(List<?> list) {
        return CollectionUtils.isEmpty(list);
    }

}
