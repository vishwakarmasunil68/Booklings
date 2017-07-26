package com.emobi.bjain.pojo.newaddress;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sunil on 12-06-2017.
 */

public class NewAddressPOJO {
    @SerializedName("success")
    String success;
    @SerializedName("result")
    List<NewAddressResultPOJO> newAddressResultPOJOList;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<NewAddressResultPOJO> getNewAddressResultPOJOList() {
        return newAddressResultPOJOList;
    }

    public void setNewAddressResultPOJOList(List<NewAddressResultPOJO> newAddressResultPOJOList) {
        this.newAddressResultPOJOList = newAddressResultPOJOList;
    }

    @Override
    public String toString() {
        return "NewAddressPOJO{" +
                "success='" + success + '\'' +
                ", newAddressResultPOJOList=" + newAddressResultPOJOList +
                '}';
    }
}
