package com.emobi.bjain.pojo.neworder;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sunil on 20-06-2017.
 */

public class NewOrderResultPOJO {
    @SerializedName("entity_id")
    private String entityId;
    @SerializedName("status")
    private String status;
    @SerializedName("customer_id")
    private String customerId;
    @SerializedName("grand_total")
    private String grandTotal;
    @SerializedName("increment_id")
    private String incrementId;
    @SerializedName("customer_email")
    private Object customerEmail;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updated_at")
    private String updatedAt;
    @SerializedName("order_currency_code")
    private String orderCurrencyCode;
    @SerializedName("lastname")
    private String lastname;
    @SerializedName("firstname")
    private String firstname;

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(String grandTotal) {
        this.grandTotal = grandTotal;
    }

    public String getIncrementId() {
        return incrementId;
    }

    public void setIncrementId(String incrementId) {
        this.incrementId = incrementId;
    }

    public Object getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(Object customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getOrderCurrencyCode() {
        return orderCurrencyCode;
    }

    public void setOrderCurrencyCode(String orderCurrencyCode) {
        this.orderCurrencyCode = orderCurrencyCode;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Override
    public String toString() {
        return "NewOrderResultPOJO{" +
                "entityId='" + entityId + '\'' +
                ", status='" + status + '\'' +
                ", customerId='" + customerId + '\'' +
                ", grandTotal='" + grandTotal + '\'' +
                ", incrementId='" + incrementId + '\'' +
                ", customerEmail=" + customerEmail +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", orderCurrencyCode='" + orderCurrencyCode + '\'' +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                '}';
    }
}
