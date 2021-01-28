package com.istone.testbuy.ui.entity;

import java.util.List;

/**
 * Created by lenovo
 * on 2020/12/11 0011
 */
public class GetOrderResponse {

    /**
     * isVirtualOrder : 0
     * promotionList : []
     * couponList : []
     * products : [{"goodsSku":"885259549476","goodsCode":"NK415445-101","productOriginPrice":549,"productPrice":549,"sumProductPrice":549,"discountProductPrice":0,"goodsEndPrice":549,"productNum":1,"goodsAmount":549,"usePromotionList":[],"useCouponList":[],"useMemberDisPrice":1,"productSpecValue":"均色 8.5 ","outCouponPrice":549,"qiKanId":0,"guiderCode":"","releaseGuiderCode":"","vGuiderId":0,"productName":"AIR MONARCH IV","productImage":"https://ssa.picture.realclub.cn/8eaf7480-3b5e-11eb-9904-69c59d21dc8e"}]
     * price : 549
     * endPrice : 549
     * payPrice : 549
     * discountPrice : 0
     * cartNos : wy3GQj-1-w10Dcn
     * outAllCouponPrice : 549
     * buyType : 2
     * freightPrice : 0
     * shippingInfo : {"shippingPrice":0,"freeType":0,"typePrice":0}
     */

    private int isVirtualOrder;
    private int price;
    private int endPrice;
    private int payPrice;
    private int discountPrice;
    private String cartNos;
    private int outAllCouponPrice;
    private int buyType;
    private int freightPrice;
    private ShippingInfoBean shippingInfo;
    private List<?> promotionList;
    private List<?> couponList;
    private List<ProductsBean> products;

    public int getIsVirtualOrder() {
        return isVirtualOrder;
    }

    public void setIsVirtualOrder(int isVirtualOrder) {
        this.isVirtualOrder = isVirtualOrder;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getEndPrice() {
        return endPrice;
    }

    public void setEndPrice(int endPrice) {
        this.endPrice = endPrice;
    }

    public int getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(int payPrice) {
        this.payPrice = payPrice;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(int discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String getCartNos() {
        return cartNos;
    }

    public void setCartNos(String cartNos) {
        this.cartNos = cartNos;
    }

    public int getOutAllCouponPrice() {
        return outAllCouponPrice;
    }

    public void setOutAllCouponPrice(int outAllCouponPrice) {
        this.outAllCouponPrice = outAllCouponPrice;
    }

    public int getBuyType() {
        return buyType;
    }

    public void setBuyType(int buyType) {
        this.buyType = buyType;
    }

    public int getFreightPrice() {
        return freightPrice;
    }

    public void setFreightPrice(int freightPrice) {
        this.freightPrice = freightPrice;
    }

    public ShippingInfoBean getShippingInfo() {
        return shippingInfo;
    }

    public void setShippingInfo(ShippingInfoBean shippingInfo) {
        this.shippingInfo = shippingInfo;
    }

    public List<?> getPromotionList() {
        return promotionList;
    }

    public void setPromotionList(List<?> promotionList) {
        this.promotionList = promotionList;
    }

    public List<?> getCouponList() {
        return couponList;
    }

    public void setCouponList(List<?> couponList) {
        this.couponList = couponList;
    }

    public List<ProductsBean> getProducts() {
        return products;
    }

    public void setProducts(List<ProductsBean> products) {
        this.products = products;
    }

    public static class ShippingInfoBean {
        /**
         * shippingPrice : 0
         * freeType : 0
         * typePrice : 0
         */

        private int shippingPrice;
        private int freeType;
        private int typePrice;

        public int getShippingPrice() {
            return shippingPrice;
        }

        public void setShippingPrice(int shippingPrice) {
            this.shippingPrice = shippingPrice;
        }

        public int getFreeType() {
            return freeType;
        }

        public void setFreeType(int freeType) {
            this.freeType = freeType;
        }

        public int getTypePrice() {
            return typePrice;
        }

        public void setTypePrice(int typePrice) {
            this.typePrice = typePrice;
        }
    }

    public static class ProductsBean {
        /**
         * goodsSku : 885259549476
         * goodsCode : NK415445-101
         * productOriginPrice : 549
         * productPrice : 549
         * sumProductPrice : 549
         * discountProductPrice : 0
         * goodsEndPrice : 549
         * productNum : 1
         * goodsAmount : 549
         * usePromotionList : []
         * useCouponList : []
         * useMemberDisPrice : 1
         * productSpecValue : 均色 8.5
         * outCouponPrice : 549
         * qiKanId : 0
         * guiderCode :
         * releaseGuiderCode :
         * vGuiderId : 0
         * productName : AIR MONARCH IV
         * productImage : https://ssa.picture.realclub.cn/8eaf7480-3b5e-11eb-9904-69c59d21dc8e
         */

        private String goodsSku;
        private String goodsCode;
        private int productOriginPrice;
        private int productPrice;
        private int sumProductPrice;
        private int discountProductPrice;
        private int goodsEndPrice;
        private int productNum;
        private int goodsAmount;
        private int useMemberDisPrice;
        private String productSpecValue;
        private int outCouponPrice;
        private int qiKanId;
        private String guiderCode;
        private String releaseGuiderCode;
        private int vGuiderId;
        private String productName;
        private String productImage;
        private List<?> usePromotionList;
        private List<?> useCouponList;

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

        public int getProductOriginPrice() {
            return productOriginPrice;
        }

        public void setProductOriginPrice(int productOriginPrice) {
            this.productOriginPrice = productOriginPrice;
        }

        public int getProductPrice() {
            return productPrice;
        }

        public void setProductPrice(int productPrice) {
            this.productPrice = productPrice;
        }

        public int getSumProductPrice() {
            return sumProductPrice;
        }

        public void setSumProductPrice(int sumProductPrice) {
            this.sumProductPrice = sumProductPrice;
        }

        public int getDiscountProductPrice() {
            return discountProductPrice;
        }

        public void setDiscountProductPrice(int discountProductPrice) {
            this.discountProductPrice = discountProductPrice;
        }

        public int getGoodsEndPrice() {
            return goodsEndPrice;
        }

        public void setGoodsEndPrice(int goodsEndPrice) {
            this.goodsEndPrice = goodsEndPrice;
        }

        public int getProductNum() {
            return productNum;
        }

        public void setProductNum(int productNum) {
            this.productNum = productNum;
        }

        public int getGoodsAmount() {
            return goodsAmount;
        }

        public void setGoodsAmount(int goodsAmount) {
            this.goodsAmount = goodsAmount;
        }

        public int getUseMemberDisPrice() {
            return useMemberDisPrice;
        }

        public void setUseMemberDisPrice(int useMemberDisPrice) {
            this.useMemberDisPrice = useMemberDisPrice;
        }

        public String getProductSpecValue() {
            return productSpecValue;
        }

        public void setProductSpecValue(String productSpecValue) {
            this.productSpecValue = productSpecValue;
        }

        public int getOutCouponPrice() {
            return outCouponPrice;
        }

        public void setOutCouponPrice(int outCouponPrice) {
            this.outCouponPrice = outCouponPrice;
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

        public String getReleaseGuiderCode() {
            return releaseGuiderCode;
        }

        public void setReleaseGuiderCode(String releaseGuiderCode) {
            this.releaseGuiderCode = releaseGuiderCode;
        }

        public int getVGuiderId() {
            return vGuiderId;
        }

        public void setVGuiderId(int vGuiderId) {
            this.vGuiderId = vGuiderId;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getProductImage() {
            return productImage;
        }

        public void setProductImage(String productImage) {
            this.productImage = productImage;
        }

        public List<?> getUsePromotionList() {
            return usePromotionList;
        }

        public void setUsePromotionList(List<?> usePromotionList) {
            this.usePromotionList = usePromotionList;
        }

        public List<?> getUseCouponList() {
            return useCouponList;
        }

        public void setUseCouponList(List<?> useCouponList) {
            this.useCouponList = useCouponList;
        }
    }
}
