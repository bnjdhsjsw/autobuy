package com.istone.testbuy.base;

import android.content.Context;

import com.blankj.utilcode.util.CollectionUtils;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.StringUtils;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;
import retrofit2.Response;

/**
 * Created by Ruansu
 * on 2020/7/27 4:10 PM
 * <p>
 * presenter基类，定义通用请求回调实现类，所有网络请求回调都要在presenter下监听返回
 */
public class BasePresenter<V extends IBaseView> {

    protected V view;
    protected Context context;

    public BasePresenter(V view) {
        this.view = view;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    protected void showLoading() {
        if (view != null) view.showLoading();
    }

    protected void hideLoading() {
        if (view != null) view.hideLoading();
    }

    protected boolean isEmpty(String text) {
        return StringUtils.isTrimEmpty(text);
    }

    protected boolean isListEmpty(List<?> list) {
        return CollectionUtils.isEmpty(list);
    }

    protected abstract class ResultCallback<T> implements Observer<BaseModel<T>> {

        protected abstract void onResult(T t);

        protected abstract void showError(int code, String error);

        @Override
        public void onSubscribe(@NotNull Disposable d) {//默认订阅时展示等待框，可重写取消等待框
            showLoading();
        }

        @Override
        public void onNext(@NotNull BaseModel<T> baseModel) {
            if (baseModel.getCode() == 20000) {//返回成功回调onResult
                onResult(baseModel.getContent() == null ? //result为空时取message的值，不为空返回result
                        GsonUtils.fromJson(baseModel.getMessage(), new TypeToken<T>() {
                        }.getType()) : baseModel.getContent());
            } else {
                showError(baseModel.getCode(), baseModel.getMessage());
            }
        }

        @Override
        public void onError(Throwable e) {//请求出错时默认关闭弹框，并Toast展示出错信息
            hideLoading();
            try {
                HttpException httpException = (HttpException) e;
                Response modelResponse = httpException.response();
                BaseModel baseModel = GsonUtils.fromJson(modelResponse.errorBody().string(), new TypeToken<BaseModel>() {
                }.getType());
                showError(baseModel.getCode(), baseModel.getMessage());
            } catch (Exception e1) {

            }
        }

        @Override
        public void onComplete() {//默认完成时关闭弹框，可重写为不关闭弹框
            hideLoading();
        }
    }

}
