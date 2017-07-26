package com.emobi.bjain.pojo.newproductview;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sunil on 03-06-2017.
 */

public class NewPricePOJO {
    @SerializedName("discount_price")
    String discount_price;
    @SerializedName("main_price")
    String main_price;

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

    @Override
    public String toString() {
        return "NewPricePOJO{" +
                "discount_price='" + discount_price + '\'' +
                ", main_price='" + main_price + '\'' +
                '}';
    }
}
