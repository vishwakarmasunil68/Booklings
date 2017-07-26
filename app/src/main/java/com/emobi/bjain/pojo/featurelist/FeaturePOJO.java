package com.emobi.bjain.pojo.featurelist;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sunil on 29-04-2017.
 */

public class FeaturePOJO implements Serializable{
    @SerializedName("data")
    List<FeatureDataPOJO> featureDataPOJOList;

    public List<FeatureDataPOJO> getFeatureDataPOJOList() {
        return featureDataPOJOList;
    }

    public void setFeatureDataPOJOList(List<FeatureDataPOJO> featureDataPOJOList) {
        this.featureDataPOJOList = featureDataPOJOList;
    }

    @Override
    public String toString() {
        return "FeaturePOJO{" +
                "featureDataPOJOList=" + featureDataPOJOList.toString() +
                '}';
    }
}
