package com.nmssdmf.testmodule.tangram;

import java.util.List;

/**
 * Created by ${nmssdmf} on 2018/12/5 0005.
 */

public class TDataOne {
    private String commodity_name;
    private String commodity_id;
    private String imgs;
    private String unit;
    private String status;
    private String cart_sort;
    private List<SkuListBean> sku_list;

    public String getCommodity_name() {
        return commodity_name;
    }

    public void setCommodity_name(String commodity_name) {
        this.commodity_name = commodity_name;
    }

    public String getCommodity_id() {
        return commodity_id;
    }

    public void setCommodity_id(String commodity_id) {
        this.commodity_id = commodity_id;
    }

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCart_sort() {
        return cart_sort;
    }

    public void setCart_sort(String cart_sort) {
        this.cart_sort = cart_sort;
    }

    public List<SkuListBean> getSku_list() {
        return sku_list;
    }

    public void setSku_list(List<SkuListBean> sku_list) {
        this.sku_list = sku_list;
    }

    public static class SkuListBean {
        /**
         * cart_id : 4
         * sku_price : 0.03
         * sku_sum : 5
         * product_sku_id : 3
         * sku_product_text : 规格：白色+小
         * stock : 100
         */

        private String cart_id;
        private String sku_price;
        private String sku_sum;
        private String product_sku_id;
        private String sku_product_text;
        private String stock;

        public String getCart_id() {
            return cart_id;
        }

        public void setCart_id(String cart_id) {
            this.cart_id = cart_id;
        }

        public String getSku_price() {
            return sku_price;
        }

        public void setSku_price(String sku_price) {
            this.sku_price = sku_price;
        }

        public String getSku_sum() {
            return sku_sum;
        }

        public void setSku_sum(String sku_sum) {
            this.sku_sum = sku_sum;
        }

        public String getProduct_sku_id() {
            return product_sku_id;
        }

        public void setProduct_sku_id(String product_sku_id) {
            this.product_sku_id = product_sku_id;
        }

        public String getSku_product_text() {
            return sku_product_text;
        }

        public void setSku_product_text(String sku_product_text) {
            this.sku_product_text = sku_product_text;
        }

        public String getStock() {
            return stock;
        }

        public void setStock(String stock) {
            this.stock = stock;
        }
    }
}
