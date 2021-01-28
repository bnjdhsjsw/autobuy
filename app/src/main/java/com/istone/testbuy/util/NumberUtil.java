package com.istone.testbuy.util;

import com.blankj.utilcode.util.NumberUtils;
import com.blankj.utilcode.util.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Created by Ruansu
 * on 2020/8/12 6:09 PM
 */
public class NumberUtil {

    public static String formatMoney(String value) {
        if (StringUtils.isEmpty(value)) value = "0";
        return formatMoney(Double.parseDouble(value));
    }

    public static String formatMoney(double value) {
        return NumberUtils.format(value, 2, false);
    }

    public static String formatCoupon(String value) {
        return formatCoupon(Double.parseDouble(value));
    }

    public static String formatCoupon(double value) {
        if (value == Double.valueOf(value).intValue())
            return String.valueOf(Double.valueOf(value).intValue());
        return formatMoney(value);
    }

    // 价格数据小数点后，三位转两位
    public static String numberFormat(double num) {
        if (num == Double.valueOf(num).intValue()) {
            return formatNumber(num);
        } else {
            return formatDecimal(num);
        }
    }

    public static String formatNumber(double num) {
        try {
            DecimalFormat format = new DecimalFormat("0.00");
            return format.format(num);
        } catch (Exception ignored) {
        }
        return "0.00";
    }

    public static String formatNumber(String numStr) {
        double num = 0;
        try {
            num = Double.parseDouble(numStr);
            DecimalFormat format = new DecimalFormat("0.00");
            return format.format(num);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0.00";
    }

    public static String formatDecimal(double value) {
        final DecimalFormat formater = new DecimalFormat();
        formater.setMaximumFractionDigits(2);
        formater.setGroupingSize(0);
        formater.setRoundingMode(RoundingMode.DOWN);
        return formater.format(value);
    }

    // 价格数据小数点后，三位转两位
    public static String numberFormat(String numStr) {
        if (StringUtils.isTrimEmpty(numStr)) {
            double num = 0;
            try {
                num = Double.parseDouble(numStr);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (num == Double.valueOf(num).intValue()) {
                return formatNumber(num);
            } else {
                return formatNumber(formatDecimal(num));
            }
        }
        return "0.00";
    }

    // 进行除法运算

    public static double div(double d1, double d2, int len) {// 进行除法运算
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        return b1.divide(b2, len, BigDecimal.ROUND_HALF_DOWN).doubleValue();
    }

    public static float divide(int a, int b) {
        BigDecimal num1 = new BigDecimal(a);

        BigDecimal num2 = new BigDecimal(b);

        return (num1.divide(num2, 2, BigDecimal.ROUND_HALF_UP)).floatValue();
    }

    // 进行减法运算

    public static double subtract(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }


    // 进行减法运算
    public static long subtract(long v1, long v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).longValue();
    }

}
