package com.emobi.bjain.pojo.newsearch;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sunil on 10-07-2017.
 */

public class NewSearchPOJO {
    @SerializedName("success")
    String success;
    @SerializedName("result")
    List<String> result;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<String> getResult() {
        return result;
    }

    public void setResult(List<String> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "NewSearchPOJO{" +
                "success='" + success + '\'' +
                ", result=" + result +
                '}';
    }
}
