package com.emobi.bjain.pojo.address;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sunil on 06-05-2017.
 */

public class AddressPOJO {
    @SerializedName("success")
    String success;
    @SerializedName("result")
    List<AddressResultPOJO> addressResultPOJOList;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<AddressResultPOJO> getAddressResultPOJOList() {
        return addressResultPOJOList;
    }

    public void setAddressResultPOJOList(List<AddressResultPOJO> addressResultPOJOList) {
        this.addressResultPOJOList = addressResultPOJOList;
    }

    @Override
    public String toString() {
        return "AddressPOJO{" +
                "success='" + success + '\'' +
                ", addressResultPOJOList=" + addressResultPOJOList +
                '}';
    }
}
