package com.istone.testbuy.ui.entity;

/**
 * Created by lenovo
 * on 2020/12/11 0011
 */
public class SkBuyBean {

    /**
     * skId : 14736
     * buyerAddressId : 1574697
     * productInfo : {"sku":"883412740920","productCode":"NK315122-111","productNum":1}
     * orderRemark :
     * siteId : 979
     */

    private int skId;
    private int buyerAddressId;
    private ProductInfoBean productInfo;
    private String orderRemark;
    private int siteId;

    public SkBuyBean(){};
    public SkBuyBean(int buyerAddressId,int skId,int siteId){
        this.buyerAddressId = buyerAddressId;
        this.skId = skId;
        this.siteId = siteId;
    };
    public int getSkId() {
        return skId;
    }

    public void setSkId(int skId) {
        this.skId = skId;
    }

    public int getBuyerAddressId() {
        return buyerAddressId;
    }

    public void setBuyerAddressId(int buyerAddressId) {
        this.buyerAddressId = buyerAddressId;
    }

    public ProductInfoBean getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(ProductInfoBean productInfo) {
        this.productInfo = productInfo;
    }

    public String getOrderRemark() {
        return orderRemark;
    }

    public void setOrderRemark(String orderRemark) {
        this.orderRemark = orderRemark;
    }

    public int getSiteId() {
        return siteId;
    }

    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }

    public static class ProductInfoBean {
        /**
         * sku : 883412740920
         * productCode : NK315122-111
         * productNum : 1
         */

        private String sku;
        private String productCode;
        private int productNum;

        public ProductInfoBean(){};
        public ProductInfoBean(String sku,String productCode,int productNum){
            this.sku = sku;
            this.productCode = productCode;
            this.productNum = productNum;
        };

        public String getSku() {
            return sku;
        }

        public void setSku(String sku) {
            this.sku = sku;
        }

        public String getProductCode() {
            return productCode;
        }

        public void setProductCode(String productCode) {
            this.productCode = productCode;
        }

        public int getProductNum() {
            return productNum;
        }

        public void setProductNum(int productNum) {
            this.productNum = productNum;
        }
    }
}
