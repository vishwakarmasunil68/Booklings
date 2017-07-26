package com.emobi.bjain.pojo.cart;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sunil on 05-05-2017.
 */

public class AddToCartPOJO {
    @SerializedName("success")
    String success;
    @SerializedName("result")
    AddToCartResultPOJO addToCartResultPOJO;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public AddToCartResultPOJO getAddToCartResultPOJO() {
        return addToCartResultPOJO;
    }

    public void setAddToCartResultPOJO(AddToCartResultPOJO addToCartResultPOJO) {
        this.addToCartResultPOJO = addToCartResultPOJO;
    }

    @Override
    public String toString() {
        return "AddToCartPOJO{" +
                "success='" + success + '\'' +
                ", addToCartResultPOJO=" + addToCartResultPOJO +
                '}';
    }
}
