package com.emobi.bjain.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sunil on 21-07-2017.
 */

public class NewCategoryStringPOJO {
    @SerializedName("success")
    String success;
    @SerializedName("result")
    List<String> newCategoryResultPOJOList;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<String> getNewCategoryResultPOJOList() {
        return newCategoryResultPOJOList;
    }

    public void setNewCategoryResultPOJOList(List<String> newCategoryResultPOJOList) {
        this.newCategoryResultPOJOList = newCategoryResultPOJOList;
    }
}
