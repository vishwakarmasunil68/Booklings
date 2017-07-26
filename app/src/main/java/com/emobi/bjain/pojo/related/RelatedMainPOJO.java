package com.emobi.bjain.pojo.related;

import com.emobi.bjain.pojo.featurelist.FeatureDataPOJO;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sunil on 03-05-2017.
 */

public class RelatedMainPOJO {
    @SerializedName("success")
    String success;
    @SerializedName("result")
    List<FeatureDataPOJO> relatedPOJOs;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<FeatureDataPOJO> getRelatedPOJOs() {
        return relatedPOJOs;
    }

    public void setRelatedPOJOs(List<FeatureDataPOJO> relatedPOJOs) {
        this.relatedPOJOs = relatedPOJOs;
    }

    @Override
    public String toString() {
        return "RelatedMainPOJO{" +
                "success='" + success + '\'' +
                ", relatedPOJOs=" + relatedPOJOs +
                '}';
    }
}
