package com.emobi.bjain.pojo.newproductview;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sunil on 03-06-2017.
 */

public class NewProductResultPOJO {
    @SerializedName("value_id")
    String value_id;
    @SerializedName("description")
    String description;
    @SerializedName("sku")
    String sku;
    @SerializedName("entity_type_id")
    String entity_type_id;
    @SerializedName("attribute_id")
    String attribute_id;
    @SerializedName("store_id")
    String store_id;
    @SerializedName("entity_id")
    String entity_id;
    @SerializedName("value")
    String value;
    @SerializedName("author_name")
    String author_name;
    @SerializedName("is_in_stock")
    String is_in_stock;


    public String getValue_id() {
        return value_id;
    }

    public void setValue_id(String value_id) {
        this.value_id = value_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getEntity_type_id() {
        return entity_type_id;
    }

    public void setEntity_type_id(String entity_type_id) {
        this.entity_type_id = entity_type_id;
    }

    public String getAttribute_id() {
        return attribute_id;
    }

    public void setAttribute_id(String attribute_id) {
        this.attribute_id = attribute_id;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getEntity_id() {
        return entity_id;
    }

    public void setEntity_id(String entity_id) {
        this.entity_id = entity_id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getIs_in_stock() {
        return is_in_stock;
    }

    public void setIs_in_stock(String is_in_stock) {
        this.is_in_stock = is_in_stock;
    }

    @Override
    public String toString() {
        return "NewProductResultPOJO{" +
                "value_id='" + value_id + '\'' +
                ", description='" + description + '\'' +
                ", sku='" + sku + '\'' +
                ", entity_type_id='" + entity_type_id + '\'' +
                ", attribute_id='" + attribute_id + '\'' +
                ", store_id='" + store_id + '\'' +
                ", entity_id='" + entity_id + '\'' +
                ", value='" + value + '\'' +
                ", author_name='" + author_name + '\'' +
                ", is_in_stock='" + is_in_stock + '\'' +
                '}';
    }
}
