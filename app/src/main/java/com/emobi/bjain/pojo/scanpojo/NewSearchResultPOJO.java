package com.emobi.bjain.pojo.scanpojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sunil on 24-06-2017.
 */

public class NewSearchResultPOJO {
    @SerializedName("product_id")
    String product_id;
    @SerializedName("product_name")
    String product_name;
    @SerializedName("discount_price")
    String discount_price;
    @SerializedName("main_price")
    String main_price;
    @SerializedName("product_image")
    String product_image;
    @SerializedName("attribute_id")
    String attribute_id;
    @SerializedName("sku")
    String sku;

    @Override
    public int hashCode() {
        try {
            int hashcode = 0;
            hashcode = Integer.parseInt(product_id)*20;
            hashcode += product_name.hashCode();
            return hashcode;
        }
        catch (Exception e){
            return 0;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        NewSearchResultPOJO other = (NewSearchResultPOJO) obj;
        if (!product_id.equals(other.product_id))
            return false;
        return true;
    }




    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
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

    public String getAttribute_id() {
        return attribute_id;
    }

    public void setAttribute_id(String attribute_id) {
        this.attribute_id = attribute_id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    @Override
    public String toString() {
        return "NewSearchResultPOJO{" +
                "product_id='" + product_id + '\'' +
                ", discount_price='" + discount_price + '\'' +
                ", main_price='" + main_price + '\'' +
                ", product_image='" + product_image + '\'' +
                ", attribute_id='" + attribute_id + '\'' +
                '}';
    }


    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }
}
