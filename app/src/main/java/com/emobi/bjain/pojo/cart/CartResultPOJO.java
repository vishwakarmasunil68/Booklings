package com.emobi.bjain.pojo.cart;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by sunil on 03-05-2017.
 */

public class CartResultPOJO implements Serializable {
    @SerializedName("product_id")
    String product_id;
    @SerializedName("cart_id")
    String cart_id;
    @SerializedName("name")
    String name;
    @SerializedName("price")
    String price;
    @SerializedName("image")
    String image;
    @SerializedName("sku")
    String sku;
    @SerializedName("qty")
    String qty;
    @SerializedName("subtotal")
    String subtotal;

    public CartResultPOJO(String product_id, String cart_id, String name, String price, String image, String sku, String qty, String subtotal) {
        this.product_id = product_id;
        this.cart_id = cart_id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.sku = sku;
        this.qty = qty;
        this.subtotal = subtotal;
    }

    public CartResultPOJO() {
    }


    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getCart_id() {
        return cart_id;
    }

    public void setCart_id(String cart_id) {
        this.cart_id = cart_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public String toString() {
        return "CartResultPOJO{" +
                "product_id='" + product_id + '\'' +
                ", cart_id='" + cart_id + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", image='" + image + '\'' +
                ", sku='" + sku + '\'' +
                ", qty='" + qty + '\'' +
                ", subtotal='" + subtotal + '\'' +
                '}';
    }
}
