package com.emobi.bjain.pojo.order;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sunil on 02-05-2017.
 */

public class PaymentPOJO {
    @SerializedName("parent_id")
    private String parentId;
    @SerializedName("amount_ordered")
    private String amountOrdered;
    @SerializedName("shipping_amount")
    private String shippingAmount;
    @SerializedName("base_amount_ordered")
    private String baseAmountOrdered;
    @SerializedName("base_shipping_amount")
    private String baseShippingAmount;
    @SerializedName("method")
    private String method;
    @SerializedName("cc_exp_month")
    private String ccExpMonth;
    @SerializedName("cc_exp_year")
    private String ccExpYear;
    @SerializedName("cc_ss_start_month")
    private String ccSsStartMonth;
    @SerializedName("cc_ss_start_year")
    private String ccSsStartYear;
    @SerializedName("payment_id")
    private String paymentId;

    @Override
    public String toString() {
        return "PaymentPOJO{" +
                "parentId='" + parentId + '\'' +
                ", amountOrdered='" + amountOrdered + '\'' +
                ", shippingAmount='" + shippingAmount + '\'' +
                ", baseAmountOrdered='" + baseAmountOrdered + '\'' +
                ", baseShippingAmount='" + baseShippingAmount + '\'' +
                ", method='" + method + '\'' +
                ", ccExpMonth='" + ccExpMonth + '\'' +
                ", ccExpYear='" + ccExpYear + '\'' +
                ", ccSsStartMonth='" + ccSsStartMonth + '\'' +
                ", ccSsStartYear='" + ccSsStartYear + '\'' +
                ", paymentId='" + paymentId + '\'' +
                '}';
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getAmountOrdered() {
        return amountOrdered;
    }

    public void setAmountOrdered(String amountOrdered) {
        this.amountOrdered = amountOrdered;
    }

    public String getShippingAmount() {
        return shippingAmount;
    }

    public void setShippingAmount(String shippingAmount) {
        this.shippingAmount = shippingAmount;
    }

    public String getBaseAmountOrdered() {
        return baseAmountOrdered;
    }

    public void setBaseAmountOrdered(String baseAmountOrdered) {
        this.baseAmountOrdered = baseAmountOrdered;
    }

    public String getBaseShippingAmount() {
        return baseShippingAmount;
    }

    public void setBaseShippingAmount(String baseShippingAmount) {
        this.baseShippingAmount = baseShippingAmount;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getCcExpMonth() {
        return ccExpMonth;
    }

    public void setCcExpMonth(String ccExpMonth) {
        this.ccExpMonth = ccExpMonth;
    }

    public String getCcExpYear() {
        return ccExpYear;
    }

    public void setCcExpYear(String ccExpYear) {
        this.ccExpYear = ccExpYear;
    }

    public String getCcSsStartMonth() {
        return ccSsStartMonth;
    }

    public void setCcSsStartMonth(String ccSsStartMonth) {
        this.ccSsStartMonth = ccSsStartMonth;
    }

    public String getCcSsStartYear() {
        return ccSsStartYear;
    }

    public void setCcSsStartYear(String ccSsStartYear) {
        this.ccSsStartYear = ccSsStartYear;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }
}
