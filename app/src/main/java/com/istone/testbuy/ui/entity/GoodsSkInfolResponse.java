package com.istone.testbuy.ui.entity;

import java.util.List;

/**
 * Created by lenovo
 * on 2020/12/10 0010
 */
public class GoodsSkInfolResponse {

    /**
     * skId : 14713
     * productName : KYBRID S2 EP
     * productCode : NKCT1971-900
     * productOriginPrice : 1099.00
     * productPrice : 949.00
     * beforeBeginTime : 1607601333
     * actBeginTime : 1607686200
     * actEndTime : 1607700600
     * colorItems : [{"colorCode":"999","colorName":"均色"}]
     * sizeItems : ["4","4.5","5","5.5","6","6.5","7","7.5","8","8.5","9","9.5","10","10.5","11","12","13"]
     * skuItems : [{"sku":"194498953754","specVal":"均色 6.5","originPrice":1099,"price":949,"size":"6.5","colorCode":"999","sizeCode":"6.5","sizeName":"6.5","stock":1,"goodsSku":"194498953754"},{"sku":"194498953761","specVal":"均色 7","originPrice":1099,"price":949,"size":"7","colorCode":"999","sizeCode":"7","sizeName":"7","stock":4,"goodsSku":"194498953761"},{"sku":"194498953778","specVal":"均色 7.5","originPrice":1099,"price":949,"size":"7.5","colorCode":"999","sizeCode":"7.5","sizeName":"7.5","stock":3,"goodsSku":"194498953778"},{"sku":"194498953785","specVal":"均色 8","originPrice":1099,"price":949,"size":"8","colorCode":"999","sizeCode":"8","sizeName":"8","stock":4,"goodsSku":"194498953785"}]
     * productImages : ["https://ssa.picture.realclub.cn/93645930-3ade-11eb-8074-6df6648db1c8","https://ssa.picture.realclub.cn/93648040-3ade-11eb-8074-6df6648db1c8","https://ssa.picture.realclub.cn/93648041-3ade-11eb-8074-6df6648db1c8","https://ssa.picture.realclub.cn/9364a750-3ade-11eb-8074-6df6648db1c8","https://ssa.picture.realclub.cn/9364a751-3ade-11eb-8074-6df6648db1c8","https://ssa.picture.realclub.cn/9364ce60-3ade-11eb-8074-6df6648db1c8","https://ssa.picture.realclub.cn/9364ce61-3ade-11eb-8074-6df6648db1c8"]
     * maxBuyerNum : 0
     * state : 1
     * shippingState : 0
     * colorTitle : 颜色
     * sizeTitle : 尺码
     * isReminder : 0
     * skActState : 2
     * skActTime : 1464
     * isShowColor : 0
     */

    private int skId;
    private String productName;
    private String productCode;
    private String productOriginPrice;
    private String productPrice;
    private int beforeBeginTime;
    private int actBeginTime;
    private int actEndTime;
    private int maxBuyerNum;
    private int state;
    private int shippingState;
    private String colorTitle;
    private String sizeTitle;
    private int isReminder;
    private int skActState;
    private int skActTime;
    private int isShowColor;
    private List<ColorItemsBean> colorItems;
    private List<String> sizeItems;
    private List<SkuItemsBean> skuItems;
    private List<String> productImages;

    public int getSkId() {
        return skId;
    }

    public void setSkId(int skId) {
        this.skId = skId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductOriginPrice() {
        return productOriginPrice;
    }

    public void setProductOriginPrice(String productOriginPrice) {
        this.productOriginPrice = productOriginPrice;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public int getBeforeBeginTime() {
        return beforeBeginTime;
    }

    public void setBeforeBeginTime(int beforeBeginTime) {
        this.beforeBeginTime = beforeBeginTime;
    }

    public int getActBeginTime() {
        return actBeginTime;
    }

    public void setActBeginTime(int actBeginTime) {
        this.actBeginTime = actBeginTime;
    }

    public int getActEndTime() {
        return actEndTime;
    }

    public void setActEndTime(int actEndTime) {
        this.actEndTime = actEndTime;
    }

    public int getMaxBuyerNum() {
        return maxBuyerNum;
    }

    public void setMaxBuyerNum(int maxBuyerNum) {
        this.maxBuyerNum = maxBuyerNum;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getShippingState() {
        return shippingState;
    }

    public void setShippingState(int shippingState) {
        this.shippingState = shippingState;
    }

    public String getColorTitle() {
        return colorTitle;
    }

    public void setColorTitle(String colorTitle) {
        this.colorTitle = colorTitle;
    }

    public String getSizeTitle() {
        return sizeTitle;
    }

    public void setSizeTitle(String sizeTitle) {
        this.sizeTitle = sizeTitle;
    }

    public int getIsReminder() {
        return isReminder;
    }

    public void setIsReminder(int isReminder) {
        this.isReminder = isReminder;
    }

    public int getSkActState() {
        return skActState;
    }

    public void setSkActState(int skActState) {
        this.skActState = skActState;
    }

    public int getSkActTime() {
        return skActTime;
    }

    public void setSkActTime(int skActTime) {
        this.skActTime = skActTime;
    }

    public int getIsShowColor() {
        return isShowColor;
    }

    public void setIsShowColor(int isShowColor) {
        this.isShowColor = isShowColor;
    }

    public List<ColorItemsBean> getColorItems() {
        return colorItems;
    }

    public void setColorItems(List<ColorItemsBean> colorItems) {
        this.colorItems = colorItems;
    }

    public List<String> getSizeItems() {
        return sizeItems;
    }

    public void setSizeItems(List<String> sizeItems) {
        this.sizeItems = sizeItems;
    }

    public List<SkuItemsBean> getSkuItems() {
        return skuItems;
    }

    public void setSkuItems(List<SkuItemsBean> skuItems) {
        this.skuItems = skuItems;
    }

    public List<String> getProductImages() {
        return productImages;
    }

    public void setProductImages(List<String> productImages) {
        this.productImages = productImages;
    }

    public static class ColorItemsBean {
        /**
         * colorCode : 999
         * colorName : 均色
         */

        private String colorCode;
        private String colorName;

        public String getColorCode() {
            return colorCode;
        }

        public void setColorCode(String colorCode) {
            this.colorCode = colorCode;
        }

        public String getColorName() {
            return colorName;
        }

        public void setColorName(String colorName) {
            this.colorName = colorName;
        }
    }

    public static class SkuItemsBean {
        /**
         * sku : 194498953754
         * specVal : 均色 6.5
         * originPrice : 1099
         * price : 949
         * size : 6.5
         * colorCode : 999
         * sizeCode : 6.5
         * sizeName : 6.5
         * stock : 1
         * goodsSku : 194498953754
         */

        private String sku;
        private String specVal;
        private int originPrice;
        private int price;
        private String size;
        private String colorCode;
        private String sizeCode;
        private String sizeName;
        private int stock;
        private String goodsSku;

        public String getSku() {
            return sku;
        }

        public void setSku(String sku) {
            this.sku = sku;
        }

        public String getSpecVal() {
            return specVal;
        }

        public void setSpecVal(String specVal) {
            this.specVal = specVal;
        }

        public int getOriginPrice() {
            return originPrice;
        }

        public void setOriginPrice(int originPrice) {
            this.originPrice = originPrice;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public String getColorCode() {
            return colorCode;
        }

        public void setColorCode(String colorCode) {
            this.colorCode = colorCode;
        }

        public String getSizeCode() {
            return sizeCode;
        }

        public void setSizeCode(String sizeCode) {
            this.sizeCode = sizeCode;
        }

        public String getSizeName() {
            return sizeName;
        }

        public void setSizeName(String sizeName) {
            this.sizeName = sizeName;
        }

        public int getStock() {
            return stock;
        }

        public void setStock(int stock) {
            this.stock = stock;
        }

        public String getGoodsSku() {
            return goodsSku;
        }

        public void setGoodsSku(String goodsSku) {
            this.goodsSku = goodsSku;
        }
    }
}
