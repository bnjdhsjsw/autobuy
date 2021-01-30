package com.istone.testbuy.ui.entity;

import java.util.List;

/**
 * Created by lenovo
 * on 2020/12/11 0011
 */
public class GetOrderRequestBean {

    /**
     * buyerAddressId : 1574697
     * buyType : 2
     * products : [{"shareId":0,"qiKanId":0,"guiderCode":"","goodsSku":"885259549476","goodsCode":"NK415445-101","productNum":1,"productImage":"https://ssa.picture.realclub.cn/8eaf7480-3b5e-11eb-9904-69c59d21dc8e","productName":"AIR MONARCH IV","productSpecValue":"均色 8.5","price":549,"originPrice":549}]
     * latitude : 31.22114
     * longitude : 121.54409
     * siteId : 979
     */

    public GetOrderRequestBean(){};
    public GetOrderRequestBean(int buyerAddressId,int buyType,double latitude,double longitude,int siteId){
        this.buyerAddressId = buyerAddressId;
        this.buyType = buyType;
        this.latitude = latitude;
        this.longitude = longitude;
        this.siteId = siteId;
    };

    private int buyerAddressId;
    private int buyType;
    private double latitude;
    private double longitude;
    private int siteId;
    private List<ProductsBean> products;

    public int getBuyerAddressId() {
        return buyerAddressId;
    }

    public void setBuyerAddressId(int buyerAddressId) {
        this.buyerAddressId = buyerAddressId;
    }

    public int getBuyType() {
        return buyType;
    }

    public void setBuyType(int buyType) {
        this.buyType = buyType;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getSiteId() {
        return siteId;
    }

    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }

    public List<ProductsBean> getProducts() {
        return products;
    }

    public void setProducts(List<ProductsBean> products) {
        this.products = products;
    }

    public static class ProductsBean {
        /**
         * shareId : 0
         * qiKanId : 0
         * guiderCode :
         * goodsSku : 885259549476
         * goodsCode : NK415445-101
         * productNum : 1
         * productImage : https://ssa.picture.realclub.cn/8eaf7480-3b5e-11eb-9904-69c59d21dc8e
         * productName : AIR MONARCH IV
         * productSpecValue : 均色 8.5
         * price : 549
         * originPrice : 549
         */

        private int shareId;
        private int qiKanId;
        private String guiderCode;
        private String goodsSku;
        private String goodsCode;
        private int productNum;
        private String productImage;
        private String productName;
        private String productSpecValue;
        private int price;
        private int originPrice;

        public int getShareId() {
            return shareId;
        }

        public void setShareId(int shareId) {
            this.shareId = shareId;
        }

        public int getQiKanId() {
            return qiKanId;
        }

        public void setQiKanId(int qiKanId) {
            this.qiKanId = qiKanId;
        }

        public String getGuiderCode() {
            return guiderCode;
        }

        public void setGuiderCode(String guiderCode) {
            this.guiderCode = guiderCode;
        }

        public String getGoodsSku() {
            return goodsSku;
        }

        public void setGoodsSku(String goodsSku) {
            this.goodsSku = goodsSku;
        }

        public String getGoodsCode() {
            return goodsCode;
        }

        public void setGoodsCode(String goodsCode) {
            this.goodsCode = goodsCode;
        }

        public int getProductNum() {
            return productNum;
        }

        public void setProductNum(int productNum) {
            this.productNum = productNum;
        }

        public String getProductImage() {
            return productImage;
        }

        public void setProductImage(String productImage) {
            this.productImage = productImage;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getProductSpecValue() {
            return productSpecValue;
        }

        public void setProductSpecValue(String productSpecValue) {
            this.productSpecValue = productSpecValue;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getOriginPrice() {
            return originPrice;
        }

        public void setOriginPrice(int originPrice) {
            this.originPrice = originPrice;
        }
    }
}
