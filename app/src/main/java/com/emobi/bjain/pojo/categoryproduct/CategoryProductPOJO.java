package com.emobi.bjain.pojo.categoryproduct;

import com.emobi.bjain.pojo.featurelist.FeatureDataPOJO;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sunil on 02-05-2017.
 */

public class CategoryProductPOJO {
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
        return "CategoryProductPOJO{" +
                "featureDataPOJOList=" + featureDataPOJOList +
                '}';
    }
}
