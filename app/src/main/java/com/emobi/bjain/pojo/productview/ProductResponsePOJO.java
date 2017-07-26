package com.emobi.bjain.pojo.productview;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sunil on 03-05-2017.
 */

public class ProductResponsePOJO {
    @SerializedName("success")
    String success;
    @SerializedName("result")
    List<ProductPOJO> productPOJOList;
    @SerializedName("Galary")
    List<ProductGallery> productGalleryList;
    @SerializedName("pgsprice")
    PricePOJO pricePOJO;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<ProductPOJO> getProductPOJOList() {
        return productPOJOList;
    }

    public void setProductPOJOList(List<ProductPOJO> productPOJOList) {
        this.productPOJOList = productPOJOList;
    }

    public List<ProductGallery> getProductGalleryList() {
        return productGalleryList;
    }

    public void setProductGalleryList(List<ProductGallery> productGalleryList) {
        this.productGalleryList = productGalleryList;
    }

    public PricePOJO getPricePOJO() {
        return pricePOJO;
    }

    public void setPricePOJO(PricePOJO pricePOJO) {
        this.pricePOJO = pricePOJO;
    }

    @Override
    public String toString() {
        return "ProductResponsePOJO{" +
                "success='" + success + '\'' +
                ", productPOJOList=" + productPOJOList +
                ", productGalleryList=" + productGalleryList +
                ", pricePOJO=" + pricePOJO +
                '}';
    }
}
