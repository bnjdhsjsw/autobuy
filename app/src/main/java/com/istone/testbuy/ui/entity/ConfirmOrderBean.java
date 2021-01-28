package com.istone.testbuy.ui.entity;

/**
 * Created by lenovo
 * on 2020/12/11 0011
 */
public class ConfirmOrderBean {

    /**
     * buyerAddressId : 1574697
     * cartNos : wy3GQj-1-w10Dcn
     * buyType : 2
     * orderRemark :
     * promotionCode :
     * couponCode :
     * usePointsNum : 0
     * siteId : 979
     */

    private int buyerAddressId;
    private String cartNos;
    private int buyType;
    private String orderRemark;
    private String promotionCode;
    private String couponCode;
    private int usePointsNum;
    private int siteId;

    public ConfirmOrderBean(int buyerAddressId,String cartNos,int buyType,int siteId)
    {
        this.buyerAddressId = buyerAddressId;
        this.cartNos = cartNos;
        this.buyType = buyType;
        this.siteId = siteId;
    }

    public int getBuyerAddressId() {
        return buyerAddressId;
    }

    public void setBuyerAddressId(int buyerAddressId) {
        this.buyerAddressId = buyerAddressId;
    }

    public String getCartNos() {
        return cartNos;
    }

    public void setCartNos(String cartNos) {
        this.cartNos = cartNos;
    }

    public int getBuyType() {
        return buyType;
    }

    public void setBuyType(int buyType) {
        this.buyType = buyType;
    }

    public String getOrderRemark() {
        return orderRemark;
    }

    public void setOrderRemark(String orderRemark) {
        this.orderRemark = orderRemark;
    }

    public String getPromotionCode() {
        return promotionCode;
    }

    public void setPromotionCode(String promotionCode) {
        this.promotionCode = promotionCode;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public int getUsePointsNum() {
        return usePointsNum;
    }

    public void setUsePointsNum(int usePointsNum) {
        this.usePointsNum = usePointsNum;
    }

    public int getSiteId() {
        return siteId;
    }

    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }
}
