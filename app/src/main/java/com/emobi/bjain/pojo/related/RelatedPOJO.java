package com.emobi.bjain.pojo.related;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sunil on 03-05-2017.
 */

public class RelatedPOJO {
    @SerializedName("name")
    String name;
    @SerializedName("name")
    String price;
    @SerializedName("name")
    String short_description;
    @SerializedName("name")
    String sku;
    @SerializedName("name")
    String small_image;
    @SerializedName("name")
    String thumbnail;
    @SerializedName("name")
    String status;
    @SerializedName("name")
    String book_authors;

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

    public String getShort_description() {
        return short_description;
    }

    public void setShort_description(String short_description) {
        this.short_description = short_description;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getSmall_image() {
        return small_image;
    }

    public void setSmall_image(String small_image) {
        this.small_image = small_image;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBook_authors() {
        return book_authors;
    }

    public void setBook_authors(String book_authors) {
        this.book_authors = book_authors;
    }

    @Override
    public String toString() {
        return "RelatedPOJO{" +
                "name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", short_description='" + short_description + '\'' +
                ", sku='" + sku + '\'' +
                ", small_image='" + small_image + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", status='" + status + '\'' +
                ", book_authors='" + book_authors + '\'' +
                '}';
    }
}
