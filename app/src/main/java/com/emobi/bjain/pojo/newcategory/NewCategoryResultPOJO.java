package com.emobi.bjain.pojo.newcategory;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sunil on 01-06-2017.
 */

public class NewCategoryResultPOJO {
    @SerializedName("value_id")
    private String valueId;
    @SerializedName("entity_type_id")
    private String entityTypeId;
    @SerializedName("attribute_id")
    private String attributeId;
    @SerializedName("store_id")
    private String storeId;
    @SerializedName("entity_id")
    private String entityId;
    @SerializedName("value")
    private String value;
    @SerializedName("category_id")
    private String categoryId;
    @SerializedName("product_id")
    private String productId;
    @SerializedName("position")
    private String position;
    @SerializedName("is_parent")
    private String isParent;
    @SerializedName("discount_price")
    private String discountPrice;
    @SerializedName("main_price")
    private String mainPrice;

    public String getValueId() {
        return valueId;
    }

    public void setValueId(String valueId) {
        this.valueId = valueId;
    }

    public String getEntityTypeId() {
        return entityTypeId;
    }

    public void setEntityTypeId(String entityTypeId) {
        this.entityTypeId = entityTypeId;
    }

    public String getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(String attributeId) {
        this.attributeId = attributeId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getIsParent() {
        return isParent;
    }

    public void setIsParent(String isParent) {
        this.isParent = isParent;
    }

    public String getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(String discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String getMainPrice() {
        return mainPrice;
    }

    public void setMainPrice(String mainPrice) {
        this.mainPrice = mainPrice;
    }

    @Override
    public String toString() {
        return "NewCategoryResultPOJO{" +
                "valueId='" + valueId + '\'' +
                ", entityTypeId='" + entityTypeId + '\'' +
                ", attributeId='" + attributeId + '\'' +
                ", storeId='" + storeId + '\'' +
                ", entityId='" + entityId + '\'' +
                ", value='" + value + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", productId='" + productId + '\'' +
                ", position='" + position + '\'' +
                ", isParent='" + isParent + '\'' +
                ", discountPrice='" + discountPrice + '\'' +
                ", mainPrice='" + mainPrice + '\'' +
                '}';
    }
}
