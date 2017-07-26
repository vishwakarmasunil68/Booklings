package com.emobi.bjain.pojo.wishlist;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sunil on 02-05-2017.
 */

public class WishListResultPOJO {
    @SerializedName("entity_id")
    private String entity_id;
    @SerializedName("wishlist_item_id")
    private String wishlistItemId;
    @SerializedName("wishlist_id")
    private String wishlistId;
    @SerializedName("description")
    private String description;
    @SerializedName("qty")
    private String qty;
    @SerializedName("name")
    private String name;
    @SerializedName("price")
    private String price;
    @SerializedName("short_description")
    private String shortDescription;
    @SerializedName("sku")
    private String sku;
    @SerializedName("small_image")
    private String smallImage;
    @SerializedName("thumbnail")
    private String thumbnail;

    public WishListResultPOJO(String entity_id, String wishlistItemId, String wishlistId, String description, String qty, String name, String price, String shortDescription, String sku, String smallImage, String thumbnail) {
        this.entity_id = entity_id;
        this.wishlistItemId = wishlistItemId;
        this.wishlistId = wishlistId;
        this.description = description;
        this.qty = qty;
        this.name = name;
        this.price = price;
        this.shortDescription = shortDescription;
        this.sku = sku;
        this.smallImage = smallImage;
        this.thumbnail = thumbnail;
    }

    public WishListResultPOJO() {
    }

    public String getWishlistItemId() {
        return wishlistItemId;
    }

    public void setWishlistItemId(String wishlistItemId) {
        this.wishlistItemId = wishlistItemId;
    }

    public String getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(String wishlistId) {
        this.wishlistId = wishlistId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getSmallImage() {
        return smallImage;
    }

    public void setSmallImage(String smallImage) {
        this.smallImage = smallImage;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getEntity_id() {
        return entity_id;
    }

    public void setEntity_id(String entity_id) {
        this.entity_id = entity_id;
    }

    @Override
    public String toString() {
        return "WishListResultPOJO{" +
                "wishlistItemId='" + wishlistItemId + '\'' +
                ", wishlistId='" + wishlistId + '\'' +
                ", entityid='" + entity_id + '\'' +
//                ", description=" + description +
                ", qty='" + qty + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
//                ", shortDescription='" + shortDescription + '\'' +
                ", sku='" + sku + '\'' +
                ", smallImage='" + smallImage + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                '}';
    }
}
