package com.emobi.bjain.pojo.neworder;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sunil on 20-06-2017.
 */

public class NewOrderPOJO {
    @SerializedName("Success")
    String Success;
    @SerializedName("Result")
    List<NewOrderResultPOJO> newOrderResultPOJOList;

    public String getSuccess() {
        return Success;
    }

    public void setSuccess(String success) {
        Success = success;
    }

    public List<NewOrderResultPOJO> getNewOrderResultPOJOList() {
        return newOrderResultPOJOList;
    }

    public void setNewOrderResultPOJOList(List<NewOrderResultPOJO> newOrderResultPOJOList) {
        this.newOrderResultPOJOList = newOrderResultPOJOList;
    }

    @Override
    public String toString() {
        return "NewOrderPOJO{" +
                "Success='" + Success + '\'' +
                ", newOrderResultPOJOList=" + newOrderResultPOJOList +
                '}';
    }
}
