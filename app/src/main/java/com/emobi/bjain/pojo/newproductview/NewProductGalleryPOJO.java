package com.emobi.bjain.pojo.newproductview;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sunil on 03-06-2017.
 */

public class NewProductGalleryPOJO {
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
        return "NewProductGalleryPOJO{" +
                "value='" + value + '\'' +
                '}';
    }
}
