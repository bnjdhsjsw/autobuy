package com.istone.testbuy.base;

/**
 * Created by Ruansu
 * on 2020/7/27 4:06 PM
 * <p>
 * presenter持有的view回调基类
 */
public interface IBaseView {
    void showLoading();
    void hideLoading();
    void showError(int code ,String error);
}
