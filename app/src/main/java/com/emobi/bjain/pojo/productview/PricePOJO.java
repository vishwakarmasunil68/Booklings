package com.emobi.bjain.pojo.productview;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sunil on 31-05-2017.
 */

public class PricePOJO {
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
        return "PricePOJO{" +
                "discount_price='" + discount_price + '\'' +
                ", main_price='" + main_price + '\'' +
                '}';
    }
}
