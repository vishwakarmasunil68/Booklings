package com.emobi.bjain.pojo.newcategory;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sunil on 01-06-2017.
 */

public class NewCategoryPOJO {
    @SerializedName("success")
    String success;
    @SerializedName("result")
    List<NewCategoryResultPOJO> newCategoryResultPOJOList;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<NewCategoryResultPOJO> getNewCategoryResultPOJOList() {
        return newCategoryResultPOJOList;
    }

    public void setNewCategoryResultPOJOList(List<NewCategoryResultPOJO> newCategoryResultPOJOList) {
        this.newCategoryResultPOJOList = newCategoryResultPOJOList;
    }

    @Override
    public String toString() {
        return "NewCategoryPOJO{" +
                "success='" + success + '\'' +
                ", newCategoryResultPOJOList=" + newCategoryResultPOJOList +
                '}';
    }
}
