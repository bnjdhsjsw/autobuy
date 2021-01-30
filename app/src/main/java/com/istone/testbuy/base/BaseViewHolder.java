package com.istone.testbuy.base;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Ruansu
 * on 2020/8/10 3:06 PM
 * <p>
 * recycleView适配器ViewHolder基类
 */
public class BaseViewHolder<T, B extends ViewDataBinding> extends RecyclerView.ViewHolder {

    protected T bean;
    protected B binding;
    protected int position;//当前位置
    protected Context context;//通用context

    public BaseViewHolder(@NonNull B b) {
        super(b.getRoot());
        binding = b;
        context = b.getRoot().getContext();
    }

    public void setData(T t) {//设置holder
    }

    public void setData(T t, int position) {//设置holder和position
        this.bean = t;
        this.position = position;
        setData(t);
    }

}
