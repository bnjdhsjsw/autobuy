package com.istone.testbuy.util;

import android.view.Gravity;

import androidx.annotation.StringRes;

import com.blankj.utilcode.util.ColorUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.istone.testbuy.R;

public class ToastUtil {

    public static void show(@StringRes int resId) {
        show(StringUtils.getString(resId));
    }

    public static void show(String msg) {
        if (StringUtils.isTrimEmpty(msg)) return;
        ToastUtils.make().setTextSize(14)
                .setGravity(Gravity.CENTER, 0, 0)
                .setBgColor(ColorUtils.getColor(R.color.translucent_99))
                .setTextColor(ColorUtils.getColor(android.R.color.white)).show(msg);
    }

}
