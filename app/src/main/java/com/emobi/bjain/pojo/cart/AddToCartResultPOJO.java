package com.emobi.bjain.pojo.cart;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sunil on 05-05-2017.
 */

public class AddToCartResultPOJO {
    @SerializedName("item_id")
    private String itemId;
    @SerializedName("quote_id")
    private String quoteId;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updated_at")
    private String updatedAt;
    @SerializedName("product_id")
    private String productId;
    @SerializedName("store_id")
    private String storeId;
    @SerializedName("parent_item_id")
    private Object parentItemId;
    @SerializedName("is_virtual")
    private Object isVirtual;
    @SerializedName("sku")
    private String sku;
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private Object description;
    @SerializedName("applied_rule_ids")
    private Object appliedRuleIds;
    @SerializedName("additional_data")
    private Object additionalData;
    @SerializedName("free_shipping")
    private String freeShipping;
    @SerializedName("is_qty_decimal")
    private Object isQtyDecimal;
    @SerializedName("no_discount")
    private String noDiscount;
    @SerializedName("weight")
    private String weight;
    @SerializedName("qty")
    private String qty;
    @SerializedName("price")
    private String price;
    @SerializedName("base_price")
    private String basePrice;
    @SerializedName("custom_price")
    private Object customPrice;
    @SerializedName("discount_percent")
    private String discountPercent;
    @SerializedName("discount_amount")
    private String discountAmount;
    @SerializedName("base_discount_amount")
    private String baseDiscountAmount;
    @SerializedName("tax_percent")
    private String taxPercent;
    @SerializedName("tax_amount")
    private String taxAmount;
    @SerializedName("base_tax_amount")
    private String baseTaxAmount;
    @SerializedName("row_total")
    private String rowTotal;
    @SerializedName("base_row_total")
    private String baseRowTotal;
    @SerializedName("row_total_with_discount")
    private String rowTotalWithDiscount;
    @SerializedName("row_weight")
    private String rowWeight;
    @SerializedName("product_type")
    private Object productType;
    @SerializedName("base_tax_before_discount")
    private Object baseTaxBeforeDiscount;
    @SerializedName("tax_before_discount")
    private Object taxBeforeDiscount;
    @SerializedName("original_custom_price")
    private Object originalCustomPrice;
    @SerializedName("redirect_url")
    private Object redirectUrl;
    @SerializedName("base_cost")
    private Object baseCost;
    @SerializedName("price_incl_tax")
    private Object priceInclTax;
    @SerializedName("base_price_incl_tax")
    private Object basePriceInclTax;
    @SerializedName("row_total_incl_tax")
    private Object rowTotalInclTax;
    @SerializedName("base_row_total_incl_tax")
    private Object baseRowTotalInclTax;
    @SerializedName("hidden_tax_amount")
    private Object hiddenTaxAmount;
    @SerializedName("base_hidden_tax_amount")
    private Object baseHiddenTaxAmount;
    @SerializedName("gift_message_id")
    private Object giftMessageId;
    @SerializedName("weee_tax_disposition")
    private Object weeeTaxDisposition;
    @SerializedName("weee_tax_row_disposition")
    private Object weeeTaxRowDisposition;
    @SerializedName("base_weee_tax_disposition")
    private Object baseWeeeTaxDisposition;
    @SerializedName("base_weee_tax_row_disposition")
    private Object baseWeeeTaxRowDisposition;
    @SerializedName("weee_tax_applied")
    private Object weeeTaxApplied;
    @SerializedName("weee_tax_applied_amount")
    private Object weeeTaxAppliedAmount;
    @SerializedName("weee_tax_applied_row_amount")
    private Object weeeTaxAppliedRowAmount;
    @SerializedName("base_weee_tax_applied_amount")
    private Object baseWeeeTaxAppliedAmount;
    @SerializedName("base_weee_tax_applied_row_amnt")
    private Object baseWeeeTaxAppliedRowAmnt;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(String quoteId) {
        this.quoteId = quoteId;
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

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public Object getParentItemId() {
        return parentItemId;
    }

    public void setParentItemId(Object parentItemId) {
        this.parentItemId = parentItemId;
    }

    public Object getIsVirtual() {
        return isVirtual;
    }

    public void setIsVirtual(Object isVirtual) {
        this.isVirtual = isVirtual;
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

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public Object getAppliedRuleIds() {
        return appliedRuleIds;
    }

    public void setAppliedRuleIds(Object appliedRuleIds) {
        this.appliedRuleIds = appliedRuleIds;
    }

    public Object getAdditionalData() {
        return additionalData;
    }

    public void setAdditionalData(Object additionalData) {
        this.additionalData = additionalData;
    }

    public String getFreeShipping() {
        return freeShipping;
    }

    public void setFreeShipping(String freeShipping) {
        this.freeShipping = freeShipping;
    }

    public Object getIsQtyDecimal() {
        return isQtyDecimal;
    }

    public void setIsQtyDecimal(Object isQtyDecimal) {
        this.isQtyDecimal = isQtyDecimal;
    }

    public String getNoDiscount() {
        return noDiscount;
    }

    public void setNoDiscount(String noDiscount) {
        this.noDiscount = noDiscount;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
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

    public String getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(String basePrice) {
        this.basePrice = basePrice;
    }

    public Object getCustomPrice() {
        return customPrice;
    }

    public void setCustomPrice(Object customPrice) {
        this.customPrice = customPrice;
    }

    public String getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(String discountPercent) {
        this.discountPercent = discountPercent;
    }

    public String getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(String discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getBaseDiscountAmount() {
        return baseDiscountAmount;
    }

    public void setBaseDiscountAmount(String baseDiscountAmount) {
        this.baseDiscountAmount = baseDiscountAmount;
    }

    public String getTaxPercent() {
        return taxPercent;
    }

    public void setTaxPercent(String taxPercent) {
        this.taxPercent = taxPercent;
    }

    public String getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(String taxAmount) {
        this.taxAmount = taxAmount;
    }

    public String getBaseTaxAmount() {
        return baseTaxAmount;
    }

    public void setBaseTaxAmount(String baseTaxAmount) {
        this.baseTaxAmount = baseTaxAmount;
    }

    public String getRowTotal() {
        return rowTotal;
    }

    public void setRowTotal(String rowTotal) {
        this.rowTotal = rowTotal;
    }

    public String getBaseRowTotal() {
        return baseRowTotal;
    }

    public void setBaseRowTotal(String baseRowTotal) {
        this.baseRowTotal = baseRowTotal;
    }

    public String getRowTotalWithDiscount() {
        return rowTotalWithDiscount;
    }

    public void setRowTotalWithDiscount(String rowTotalWithDiscount) {
        this.rowTotalWithDiscount = rowTotalWithDiscount;
    }

    public String getRowWeight() {
        return rowWeight;
    }

    public void setRowWeight(String rowWeight) {
        this.rowWeight = rowWeight;
    }

    public Object getProductType() {
        return productType;
    }

    public void setProductType(Object productType) {
        this.productType = productType;
    }

    public Object getBaseTaxBeforeDiscount() {
        return baseTaxBeforeDiscount;
    }

    public void setBaseTaxBeforeDiscount(Object baseTaxBeforeDiscount) {
        this.baseTaxBeforeDiscount = baseTaxBeforeDiscount;
    }

    public Object getTaxBeforeDiscount() {
        return taxBeforeDiscount;
    }

    public void setTaxBeforeDiscount(Object taxBeforeDiscount) {
        this.taxBeforeDiscount = taxBeforeDiscount;
    }

    public Object getOriginalCustomPrice() {
        return originalCustomPrice;
    }

    public void setOriginalCustomPrice(Object originalCustomPrice) {
        this.originalCustomPrice = originalCustomPrice;
    }

    public Object getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(Object redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public Object getBaseCost() {
        return baseCost;
    }

    public void setBaseCost(Object baseCost) {
        this.baseCost = baseCost;
    }

    public Object getPriceInclTax() {
        return priceInclTax;
    }

    public void setPriceInclTax(Object priceInclTax) {
        this.priceInclTax = priceInclTax;
    }

    public Object getBasePriceInclTax() {
        return basePriceInclTax;
    }

    public void setBasePriceInclTax(Object basePriceInclTax) {
        this.basePriceInclTax = basePriceInclTax;
    }

    public Object getRowTotalInclTax() {
        return rowTotalInclTax;
    }

    public void setRowTotalInclTax(Object rowTotalInclTax) {
        this.rowTotalInclTax = rowTotalInclTax;
    }

    public Object getBaseRowTotalInclTax() {
        return baseRowTotalInclTax;
    }

    public void setBaseRowTotalInclTax(Object baseRowTotalInclTax) {
        this.baseRowTotalInclTax = baseRowTotalInclTax;
    }

    public Object getHiddenTaxAmount() {
        return hiddenTaxAmount;
    }

    public void setHiddenTaxAmount(Object hiddenTaxAmount) {
        this.hiddenTaxAmount = hiddenTaxAmount;
    }

    public Object getBaseHiddenTaxAmount() {
        return baseHiddenTaxAmount;
    }

    public void setBaseHiddenTaxAmount(Object baseHiddenTaxAmount) {
        this.baseHiddenTaxAmount = baseHiddenTaxAmount;
    }

    public Object getGiftMessageId() {
        return giftMessageId;
    }

    public void setGiftMessageId(Object giftMessageId) {
        this.giftMessageId = giftMessageId;
    }

    public Object getWeeeTaxDisposition() {
        return weeeTaxDisposition;
    }

    public void setWeeeTaxDisposition(Object weeeTaxDisposition) {
        this.weeeTaxDisposition = weeeTaxDisposition;
    }

    public Object getWeeeTaxRowDisposition() {
        return weeeTaxRowDisposition;
    }

    public void setWeeeTaxRowDisposition(Object weeeTaxRowDisposition) {
        this.weeeTaxRowDisposition = weeeTaxRowDisposition;
    }

    public Object getBaseWeeeTaxDisposition() {
        return baseWeeeTaxDisposition;
    }

    public void setBaseWeeeTaxDisposition(Object baseWeeeTaxDisposition) {
        this.baseWeeeTaxDisposition = baseWeeeTaxDisposition;
    }

    public Object getBaseWeeeTaxRowDisposition() {
        return baseWeeeTaxRowDisposition;
    }

    public void setBaseWeeeTaxRowDisposition(Object baseWeeeTaxRowDisposition) {
        this.baseWeeeTaxRowDisposition = baseWeeeTaxRowDisposition;
    }

    public Object getWeeeTaxApplied() {
        return weeeTaxApplied;
    }

    public void setWeeeTaxApplied(Object weeeTaxApplied) {
        this.weeeTaxApplied = weeeTaxApplied;
    }

    public Object getWeeeTaxAppliedAmount() {
        return weeeTaxAppliedAmount;
    }

    public void setWeeeTaxAppliedAmount(Object weeeTaxAppliedAmount) {
        this.weeeTaxAppliedAmount = weeeTaxAppliedAmount;
    }

    public Object getWeeeTaxAppliedRowAmount() {
        return weeeTaxAppliedRowAmount;
    }

    public void setWeeeTaxAppliedRowAmount(Object weeeTaxAppliedRowAmount) {
        this.weeeTaxAppliedRowAmount = weeeTaxAppliedRowAmount;
    }

    public Object getBaseWeeeTaxAppliedAmount() {
        return baseWeeeTaxAppliedAmount;
    }

    public void setBaseWeeeTaxAppliedAmount(Object baseWeeeTaxAppliedAmount) {
        this.baseWeeeTaxAppliedAmount = baseWeeeTaxAppliedAmount;
    }

    public Object getBaseWeeeTaxAppliedRowAmnt() {
        return baseWeeeTaxAppliedRowAmnt;
    }

    public void setBaseWeeeTaxAppliedRowAmnt(Object baseWeeeTaxAppliedRowAmnt) {
        this.baseWeeeTaxAppliedRowAmnt = baseWeeeTaxAppliedRowAmnt;
    }

    @Override
    public String toString() {
        return "AddToCartResultPOJO{" +
                "itemId='" + itemId + '\'' +
                ", quoteId='" + quoteId + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", productId='" + productId + '\'' +
                ", storeId='" + storeId + '\'' +
                ", parentItemId=" + parentItemId +
                ", isVirtual=" + isVirtual +
                ", sku='" + sku + '\'' +
                ", name='" + name + '\'' +
                ", description=" + description +
                ", appliedRuleIds=" + appliedRuleIds +
                ", additionalData=" + additionalData +
                ", freeShipping='" + freeShipping + '\'' +
                ", isQtyDecimal=" + isQtyDecimal +
                ", noDiscount='" + noDiscount + '\'' +
                ", weight='" + weight + '\'' +
                ", qty='" + qty + '\'' +
                ", price='" + price + '\'' +
                ", basePrice='" + basePrice + '\'' +
                ", customPrice=" + customPrice +
                ", discountPercent='" + discountPercent + '\'' +
                ", discountAmount='" + discountAmount + '\'' +
                ", baseDiscountAmount='" + baseDiscountAmount + '\'' +
                ", taxPercent='" + taxPercent + '\'' +
                ", taxAmount='" + taxAmount + '\'' +
                ", baseTaxAmount='" + baseTaxAmount + '\'' +
                ", rowTotal='" + rowTotal + '\'' +
                ", baseRowTotal='" + baseRowTotal + '\'' +
                ", rowTotalWithDiscount='" + rowTotalWithDiscount + '\'' +
                ", rowWeight='" + rowWeight + '\'' +
                ", productType=" + productType +
                ", baseTaxBeforeDiscount=" + baseTaxBeforeDiscount +
                ", taxBeforeDiscount=" + taxBeforeDiscount +
                ", originalCustomPrice=" + originalCustomPrice +
                ", redirectUrl=" + redirectUrl +
                ", baseCost=" + baseCost +
                ", priceInclTax=" + priceInclTax +
                ", basePriceInclTax=" + basePriceInclTax +
                ", rowTotalInclTax=" + rowTotalInclTax +
                ", baseRowTotalInclTax=" + baseRowTotalInclTax +
                ", hiddenTaxAmount=" + hiddenTaxAmount +
                ", baseHiddenTaxAmount=" + baseHiddenTaxAmount +
                ", giftMessageId=" + giftMessageId +
                ", weeeTaxDisposition=" + weeeTaxDisposition +
                ", weeeTaxRowDisposition=" + weeeTaxRowDisposition +
                ", baseWeeeTaxDisposition=" + baseWeeeTaxDisposition +
                ", baseWeeeTaxRowDisposition=" + baseWeeeTaxRowDisposition +
                ", weeeTaxApplied=" + weeeTaxApplied +
                ", weeeTaxAppliedAmount=" + weeeTaxAppliedAmount +
                ", weeeTaxAppliedRowAmount=" + weeeTaxAppliedRowAmount +
                ", baseWeeeTaxAppliedAmount=" + baseWeeeTaxAppliedAmount +
                ", baseWeeeTaxAppliedRowAmnt=" + baseWeeeTaxAppliedRowAmnt +
                '}';
    }
}
