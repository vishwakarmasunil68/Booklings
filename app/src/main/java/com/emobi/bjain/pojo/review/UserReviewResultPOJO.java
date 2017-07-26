package com.emobi.bjain.pojo.review;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sunil on 05-05-2017.
 */

public class UserReviewResultPOJO {
    @SerializedName("created_at")
    String created_at;
    @SerializedName("detail")
    String detail;
    @SerializedName("nickname")
    String nickname;

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "UserReviewResultPOJO{" +
                "created_at='" + created_at + '\'' +
                ", detail='" + detail + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
