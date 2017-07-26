package com.emobi.bjain.pojo.review;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sunil on 05-05-2017.
 */

public class UserReviewPOJO {
    @SerializedName("success")
    String success;
    @SerializedName("result")
    List<UserReviewResultPOJO>  userReviewResultPOJOList;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<UserReviewResultPOJO> getUserReviewResultPOJOList() {
        return userReviewResultPOJOList;
    }

    public void setUserReviewResultPOJOList(List<UserReviewResultPOJO> userReviewResultPOJOList) {
        this.userReviewResultPOJOList = userReviewResultPOJOList;
    }

    @Override
    public String toString() {
        return "UserReviewPOJO{" +
                "success='" + success + '\'' +
                ", userReviewResultPOJOList=" + userReviewResultPOJOList +
                '}';
    }
}
