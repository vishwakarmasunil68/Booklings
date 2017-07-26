package com.emobi.bjain.pojo.productview;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sunil on 29-05-2017.
 */

public class ProductGallery {
    @SerializedName("value")
    String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ProductGallery{" +
                "value='" + value + '\'' +
                '}';
    }
}
