package com.emobi.bjain.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sunil on 28-04-2017.
 */

public class CategoryResultPOJO {
    @SerializedName("category_id")
    String category_id;
    @SerializedName("parent_id")
    String parent_id;
    @SerializedName("name")
    String name;
    @SerializedName("position")
    String position;
    @SerializedName("level")
    String level;
    @SerializedName("children")
    List<ChildrenPOJO> childrenPOJOList;

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public List<ChildrenPOJO> getChildrenPOJOList() {
        return childrenPOJOList;
    }

    public void setChildrenPOJOList(List<ChildrenPOJO> childrenPOJOList) {
        this.childrenPOJOList = childrenPOJOList;
    }

    @Override
    public String toString() {
        return "CategoryResultPOJO{" +
                "category_id='" + category_id + '\'' +
                ", parent_id='" + parent_id + '\'' +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", level='" + level + '\'' +
                ", childrenPOJOList=" + childrenPOJOList +
                '}';
    }
}
