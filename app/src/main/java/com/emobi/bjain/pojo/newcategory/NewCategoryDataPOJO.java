package com.emobi.bjain.pojo.newcategory;

/**
 * Created by sunil on 01-06-2017.
 */

public class NewCategoryDataPOJO {

    String product_name;
    String discount_price;
    String main_price;
    String product_image;
    String product_id;

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
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

    public String getProduct_image() {
        return product_image;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    @Override
    public String toString() {
        return "NewCategoryDataPOJO{" +
                "product_name='" + product_name + '\'' +
                ", discount_price='" + discount_price + '\'' +
                ", main_price='" + main_price + '\'' +
                ", product_image='" + product_image + '\'' +
                ", product_id='" + product_id + '\'' +
                '}';
    }
}
