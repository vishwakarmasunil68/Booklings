package com.emobi.bjain.pojo.search;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sunil on 03-05-2017.
 */

public class SearchPOJO {
    @SerializedName("success")
    String success;
    @SerializedName("result")
    List<String> list_datas;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<String> getList_datas() {
        return list_datas;
    }

    public void setList_datas(List<String> list_datas) {
        this.list_datas = list_datas;
    }

    @Override
    public String toString() {
        return "SearchPOJO{" +
                "success='" + success + '\'' +
                ", list_datas=" + list_datas +
                '}';
    }
}
