package com.emobi.bjain.pojo.newcart;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sunil on 03-06-2017.
 */

public class NewCartResultPOJO {

    @SerializedName("cart_id")
    private String cartId;
    @SerializedName("quote_id")
    private String quoteId;
    @SerializedName("product_id")
    private String productId;
    @SerializedName("sku")
    private String sku;
    @SerializedName("name")
    private String name;
    @SerializedName("qty")
    private String qty;
    @SerializedName("price")
    private String price;
    @SerializedName("row_total")
    private String rowTotal;
    @SerializedName("value_id")
    private String valueId;
    @SerializedName("attribute_id")
    private String attributeId;
    @SerializedName("entity_id")
    private String entityId;
    @SerializedName("value")
    private String value;
    @SerializedName("subtotal")
    private Double subtotal;

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(String quoteId) {
        this.quoteId = quoteId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRowTotal() {
        return rowTotal;
    }

    public void setRowTotal(String rowTotal) {
        this.rowTotal = rowTotal;
    }

    public String getValueId() {
        return valueId;
    }

    public void setValueId(String valueId) {
        this.valueId = valueId;
    }

    public String getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(String attributeId) {
        this.attributeId = attributeId;
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

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public String toString() {
        return "NewCartResultPOJO{" +
                "cartId='" + cartId + '\'' +
                ", quoteId='" + quoteId + '\'' +
                ", productId='" + productId + '\'' +
                ", sku='" + sku + '\'' +
                ", name='" + name + '\'' +
                ", qty='" + qty + '\'' +
                ", price='" + price + '\'' +
                ", rowTotal='" + rowTotal + '\'' +
                ", valueId='" + valueId + '\'' +
                ", attributeId='" + attributeId + '\'' +
                ", entityId='" + entityId + '\'' +
                ", value='" + value + '\'' +
                ", subtotal=" + subtotal +
                '}';
    }
}
