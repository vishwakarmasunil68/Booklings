package com.emobi.bjain.pojo.newproductview;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sunil on 03-06-2017.
 */

public class NewProductPOJO {
    @SerializedName("success")
    String success;
    @SerializedName("result")
    List<NewProductResultPOJO> newProductResultPOJOList;
    @SerializedName("Galary")
    List<NewProductGalleryPOJO> newProductGalleryPOJOList;
    @SerializedName("pgsprice")
    NewPricePOJO newPricePOJO;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<NewProductResultPOJO> getNewProductResultPOJOList() {
        return newProductResultPOJOList;
    }

    public void setNewProductResultPOJOList(List<NewProductResultPOJO> newProductResultPOJOList) {
        this.newProductResultPOJOList = newProductResultPOJOList;
    }

    public List<NewProductGalleryPOJO> getNewProductGalleryPOJOList() {
        return newProductGalleryPOJOList;
    }

    public void setNewProductGalleryPOJOList(List<NewProductGalleryPOJO> newProductGalleryPOJOList) {
        this.newProductGalleryPOJOList = newProductGalleryPOJOList;
    }

    public NewPricePOJO getNewPricePOJO() {
        return newPricePOJO;
    }

    public void setNewPricePOJO(NewPricePOJO newPricePOJO) {
        this.newPricePOJO = newPricePOJO;
    }

    @Override
    public String toString() {
        return "NewProductPOJO{" +
                "success='" + success + '\'' +
                ", newProductResultPOJOList=" + newProductResultPOJOList +
                ", newProductGalleryPOJOList=" + newProductGalleryPOJOList +
                ", newPricePOJO=" + newPricePOJO +
                '}';
    }
}
