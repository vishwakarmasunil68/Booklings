package com.emobi.bjain.pojo.offer;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sunil on 02-06-2017.
 */

public class OfferPOJO {
    @SerializedName("success")
    String success;
    @SerializedName("result")
    List<String> offerStringList;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<String> getOfferStringList() {
        return offerStringList;
    }

    public void setOfferStringList(List<String> offerStringList) {
        this.offerStringList = offerStringList;
    }
}
