package com.istone.testbuy.ui.entity;

/**
 * Created by lenovo
 * on 2020/12/11 0011
 */
public class ConfirmOrderResponse {

    /**
     * orderId : 455883
     * paySn : reallyBJNK2720201211538100001
     */

    private int orderId;
    private String paySn;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getPaySn() {
        return paySn;
    }

    public void setPaySn(String paySn) {
        this.paySn = paySn;
    }
}
