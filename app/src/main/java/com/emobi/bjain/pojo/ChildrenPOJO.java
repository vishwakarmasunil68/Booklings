package com.emobi.bjain.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sunil on 28-04-2017.
 */

public class ChildrenPOJO {
    @SerializedName("category_id")
    String category_id;
    @SerializedName("parent_id")
    String parent_id;
    @SerializedName("name")
    String name;
    @SerializedName("is_active")
    String is_active;
    @SerializedName("position")
    String position;
    @SerializedName("level")
    String level;
    @SerializedName("children")
    List<ChildrenPOJO> childrenPOJOs;

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

    public String getIs_active() {
        return is_active;
    }

    public void setIs_active(String is_active) {
        this.is_active = is_active;
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

    public List<ChildrenPOJO> getChildrenPOJOs() {
        return childrenPOJOs;
    }

    public void setChildrenPOJOs(List<ChildrenPOJO> childrenPOJOs) {
        this.childrenPOJOs = childrenPOJOs;
    }

    @Override
    public String toString() {
        return "ChildrenPOJO{" +
                "category_id='" + category_id + '\'' +
                ", parent_id='" + parent_id + '\'' +
                ", name='" + name + '\'' +
                ", is_active='" + is_active + '\'' +
                ", position='" + position + '\'' +
                ", level='" + level + '\'' +
                ", childrenPOJOs=" + childrenPOJOs +
                '}';
    }
}
