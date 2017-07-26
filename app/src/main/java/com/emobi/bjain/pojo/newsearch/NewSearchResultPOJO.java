package com.emobi.bjain.pojo.newsearch;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sunil on 10-07-2017.
 */

public class NewSearchResultPOJO {
    @SerializedName("product_id")
    String product_id;
    @SerializedName("product_name")
    String product_name;
    @SerializedName("search_value")
    String search_value;
    @SerializedName("discount_price")
    String discount_price;
    @SerializedName("main_price")
    String main_price;
    @SerializedName("product_image")
    String product_image;
    @SerializedName("sku")
    String sku;

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getSearch_value() {
        return search_value;
    }

    public void setSearch_value(String search_value) {
        this.search_value = search_value;
    }

    public String getDiscount_price() {
        return discount_price;
    }

    public void setDiscount_price(String discount_price) {
        this.discount_price = discount_price;
    }

    public String getMain_price() {
        return main_price;
    }

    public void setMain_price(String main_price) {
        this.main_price = main_price;
    }

    public String getProduct_image() {
        return product_image;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NewSearchResultPOJO)) return false;

        NewSearchResultPOJO that = (NewSearchResultPOJO) o;

        return getProduct_id().equals(that.getProduct_id());

    }

    @Override
    public int hashCode() {
        return getProduct_id().hashCode();
    }
}
