package com.istone.testbuy.base;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.blankj.utilcode.util.CollectionUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.StringUtils;

import java.util.List;

/**
 * Created by Ruansu
 * on 2020/8/5 10:11 AM
 * <p>
 * recycleView适配器基类，传入数据对象和holder泛型
 */
public abstract class BaseRecyclerAdapter<T, VH extends ViewHolder> extends Adapter<VH> {

    protected List<T> list;

    public BaseRecyclerAdapter(List<T> list) {
        this.list = list;
    }

    public void resetList(List<T> list) {//重置集合数据并刷新
        this.list = list;
        notifyDataSetChanged();
    }

    public List<T> getList() {//获取集合数据
        return list;
    }

    protected boolean isListEmpty(List<?> list) {
        return CollectionUtils.isEmpty(list);
    }

    protected boolean isEmpty(String text) {//String判空方法
        return StringUtils.isTrimEmpty(text);
    }

    public T getItem(int position) {//获取指定位置的数据
        return list.get(position);
    }

    public void deleteItem(T t) {//删除数据并更新
        if (getItemCount() > 0) {
            for (int i = 0; i < getItemCount(); i++) {
                T T = list.get(i);
                if (ObjectUtils.equals(t, T)) {
                    list.remove(T);
                    notifyItemRemoved(i);
                    return;
                }
            }
        }
    }

    public void addItems(List<T> list) {//添加集合书籍并刷新
        if (!isListEmpty(list)) {
            int start = getItemCount();
            if (this.list != null) {
                this.list.addAll(list);
            } else {
                this.list = list;
            }
            notifyItemRangeInserted(start, getItemCount() - start);
        }
    }

    protected <B extends ViewDataBinding> B getHolderBinding(@NonNull ViewGroup parent, @LayoutRes int layoutId) {
        return DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), layoutId, parent, false);
    }

    @Override
    public int getItemCount() {
        return isListEmpty(list) ? 0 : list.size();
    }
}
