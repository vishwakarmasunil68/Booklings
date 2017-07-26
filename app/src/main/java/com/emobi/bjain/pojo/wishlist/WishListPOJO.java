package com.emobi.bjain.pojo.wishlist;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sunil on 02-05-2017.
 */

public class WishListPOJO {
    @SerializedName("success")
    String success;
    @SerializedName("result")
    List<WishListResultPOJO> wishListResultPOJOs;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<WishListResultPOJO> getWishListResultPOJOs() {
        return wishListResultPOJOs;
    }

    public void setWishListResultPOJOs(List<WishListResultPOJO> wishListResultPOJOs) {
        this.wishListResultPOJOs = wishListResultPOJOs;
    }

    @Override
    public String toString() {
        return "WishListPOJO{" +
                "success='" + success + '\'' +
                ", wishListResultPOJOs=" + wishListResultPOJOs +
                '}';
    }
}
