package com.istone.testbuy.util;

import com.blankj.utilcode.util.StringUtils;
import com.istone.testbuy.R;

import static com.blankj.utilcode.util.StringUtils.getString;

public class StringConcatUtil {

    public static String concatGoodsName(String brand, String name) {
        StringBuilder stringBuilder = new StringBuilder();
        if (!StringUtils.isEmpty(brand)) {
            stringBuilder.append(brand);
            if (!StringUtils.isEmpty(name)) {
                stringBuilder.append(" | ");
                stringBuilder.append(name);
            }
        } else {
            stringBuilder.append(name);
        }
        return stringBuilder.toString();
    }

    public static String concatMoney(String money)
    {
        return getString(R.string.order_detail_money,
                 NumberUtil.formatMoney(money));
    }

    public static String concatMoney(double money)
    {
        return getString(R.string.order_detail_money,
                NumberUtil.formatMoney(money));
    }

}
