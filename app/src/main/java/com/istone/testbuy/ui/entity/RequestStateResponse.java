package com.istone.testbuy.ui.entity;

/**
 * Created by lenovo
 * on 2020/12/11 0011
 */
public class RequestStateResponse {

    /**
     * state : ORDER
     * siteId : 979
     * orderId : 458115
     * skId : 14736
     */

    private String state;
    private int siteId;
    private String orderId;
    private int skId;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getSiteId() {
        return siteId;
    }

    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getSkId() {
        return skId;
    }

    public void setSkId(int skId) {
        this.skId = skId;
    }
}
