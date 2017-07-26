package com.emobi.bjain.pojo.newwishlist;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sunil on 03-06-2017.
 */

public class NewWishlistPOJO {
    @SerializedName("success")
    String success;
    @SerializedName("result")
    List<NewWishListResultPOJO> wishListResultPOJOList;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<NewWishListResultPOJO> getWishListResultPOJOList() {
        return wishListResultPOJOList;
    }

    public void setWishListResultPOJOList(List<NewWishListResultPOJO> wishListResultPOJOList) {
        this.wishListResultPOJOList = wishListResultPOJOList;
    }

    @Override
    public String toString() {
        return "NewWishlistPOJO{" +
                "success='" + success + '\'' +
                ", wishListResultPOJOList=" + wishListResultPOJOList +
                '}';
    }
}
