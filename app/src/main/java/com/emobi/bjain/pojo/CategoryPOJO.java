package com.emobi.bjain.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sunil on 28-04-2017.
 */

public class CategoryPOJO {
    @SerializedName("success")
    String success;
    @SerializedName("Result")
    CategoryResultPOJO categoryResultPOJO;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public CategoryResultPOJO getCategoryResultPOJO() {
        return categoryResultPOJO;
    }

    public void setCategoryResultPOJO(CategoryResultPOJO categoryResultPOJO) {
        this.categoryResultPOJO = categoryResultPOJO;
    }

    @Override
    public String toString() {
        return "CategoryPOJO{" +
                "success='" + success + '\'' +
                ", categoryResultPOJO=" + categoryResultPOJO +
                '}';
    }
}
