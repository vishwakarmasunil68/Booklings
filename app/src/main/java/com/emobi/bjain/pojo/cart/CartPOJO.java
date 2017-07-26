package com.emobi.bjain.pojo.cart;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sunil on 03-05-2017.
 */

public class CartPOJO implements Serializable{
    @SerializedName("success")
    String success;
    @SerializedName("grand_total")
    String grand_total;
    @SerializedName("result")
    List<CartResultPOJO> cartResultPOJOList;

    public String getGrand_total() {
        return grand_total;
    }

    public void setGrand_total(String grand_total) {
        this.grand_total = grand_total;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<CartResultPOJO> getCartResultPOJOList() {
        return cartResultPOJOList;
    }

    public void setCartResultPOJOList(List<CartResultPOJO> cartResultPOJOList) {
        this.cartResultPOJOList = cartResultPOJOList;
    }

    @Override
    public String toString() {
        return "CartPOJO{" +
                "success='" + success + '\'' +
                "grand_total='" + grand_total + '\'' +
                ", cartResultPOJOList=" + cartResultPOJOList +
                '}';
    }
}
