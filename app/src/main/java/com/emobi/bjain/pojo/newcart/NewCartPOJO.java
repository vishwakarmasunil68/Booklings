package com.emobi.bjain.pojo.newcart;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sunil on 03-06-2017.
 */

public class NewCartPOJO {
    @SerializedName("success")
    String success;
    @SerializedName("grand_total")
    String grand_total;
    @SerializedName("result")
    List<NewCartResultPOJO> newCartResultPOJOList;

    public NewCartPOJO() {
        super();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getGrand_total() {
        return grand_total;
    }

    public void setGrand_total(String grand_total) {
        this.grand_total = grand_total;
    }

    public List<NewCartResultPOJO> getNewCartResultPOJOList() {
        return newCartResultPOJOList;
    }

    public void setNewCartResultPOJOList(List<NewCartResultPOJO> newCartResultPOJOList) {
        this.newCartResultPOJOList = newCartResultPOJOList;
    }
}
