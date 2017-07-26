package com.emobi.bjain.pojo.featurelist;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sunil on 29-04-2017.
 */

public class FeatureDataPOJO implements Serializable {
    @SerializedName("product_id")
    String product_id;
    @SerializedName("sku")
    String sku;
    @SerializedName("set")
    String set;
    @SerializedName("type")
    String type;
    @SerializedName("categories")
    List<String> list_categories;
    @SerializedName("websites")
    List<String> list_websites;
    @SerializedName("created_at")
    String created_at;
    @SerializedName("updated_at")
    String updated_at;
    @SerializedName("type_id")
    String type_id;
    @SerializedName("name")
    String name;
    @SerializedName("description")
    String description;
    @SerializedName("short_description")
    String short_description;
    @SerializedName("weight")
    String weight;
    @SerializedName("status")
    String status;
    @SerializedName("url_key")
    String url_key;
    @SerializedName("url_path")
    String url_path;
    @SerializedName("visibility")
    String visibility;
    @SerializedName("category_ids")
    List<String> list_category_ids;
    @SerializedName("has_options")
    String has_options;
    @SerializedName("price")
    String price;
    @SerializedName("tax_class_id")
    String tax_class_id;
    @SerializedName("options_container")
    String options_container;
    @SerializedName("discount")
    String discount;
    @SerializedName("discount_price")
    String discount_price;
    @SerializedName("main_price")
    String main_price;
    @SerializedName("url")
    String url;
    @SerializedName("author_name")
    String author_name;


    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getSet() {
        return set;
    }

    public void setSet(String set) {
        this.set = set;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getList_categories() {
        return list_categories;
    }

    public void setList_categories(List<String> list_categories) {
        this.list_categories = list_categories;
    }

    public List<String> getList_websites() {
        return list_websites;
    }

    public void setList_websites(List<String> list_websites) {
        this.list_websites = list_websites;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShort_description() {
        return short_description;
    }

    public void setShort_description(String short_description) {
        this.short_description = short_description;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUrl_key() {
        return url_key;
    }

    public void setUrl_key(String url_key) {
        this.url_key = url_key;
    }

    public String getUrl_path() {
        return url_path;
    }

    public void setUrl_path(String url_path) {
        this.url_path = url_path;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public List<String> getList_category_ids() {
        return list_category_ids;
    }

    public void setList_category_ids(List<String> list_category_ids) {
        this.list_category_ids = list_category_ids;
    }

    public String getHas_options() {
        return has_options;
    }

    public void setHas_options(String has_options) {
        this.has_options = has_options;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTax_class_id() {
        return tax_class_id;
    }

    public void setTax_class_id(String tax_class_id) {
        this.tax_class_id = tax_class_id;
    }

    public String getOptions_container() {
        return options_container;
    }

    public void setOptions_container(String options_container) {
        this.options_container = options_container;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getDiscount_price() {
        return discount_price;
    }

    public void setDiscount_price(String discount_price) {
        this.discount_price = discount_price;
    }

    public String getMain_price() {
        return main_price;
    }

    public void setMain_price(String main_price) {
        this.main_price = main_price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    @Override
    public String toString() {
        return "FeatureDataPOJO{" +
                "product_id='" + product_id + '\'' +
                ", sku='" + sku + '\'' +
                ", set='" + set + '\'' +
                ", type='" + type + '\'' +
                ", list_categories=" + list_categories +
                ", list_websites=" + list_websites +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", type_id='" + type_id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", short_description='" + short_description + '\'' +
                ", weight='" + weight + '\'' +
                ", status='" + status + '\'' +
                ", url_key='" + url_key + '\'' +
                ", url_path='" + url_path + '\'' +
                ", visibility='" + visibility + '\'' +
                ", list_category_ids=" + list_category_ids +
                ", has_options='" + has_options + '\'' +
                ", price='" + price + '\'' +
                ", tax_class_id='" + tax_class_id + '\'' +
                ", options_container='" + options_container + '\'' +
                ", discount='" + discount + '\'' +
                ", discount_price='" + discount_price + '\'' +
                ", main_price='" + main_price + '\'' +
                ", url='" + url + '\'' +
                ", author_name='" + author_name + '\'' +
                '}';
    }
}
