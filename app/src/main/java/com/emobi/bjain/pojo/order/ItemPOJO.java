package com.emobi.bjain.pojo.order;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sunil on 02-05-2017.
 */

public class ItemPOJO {
    @SerializedName("item_id")
    private String itemId;
    @SerializedName("order_id")
    private String orderId;
    @SerializedName("quote_item_id")
    private String quoteItemId;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updated_at")
    private String updatedAt;
    @SerializedName("product_id")
    private String productId;
    @SerializedName("product_type")
    private String productType;
    @SerializedName("product_options")
    private String productOptions;
    @SerializedName("weight")
    private String weight;
    @SerializedName("is_virtual")
    private String isVirtual;
    @SerializedName("sku")
    private String sku;
    @SerializedName("name")
    private String name;
    @SerializedName("free_shipping")
    private String freeShipping;
    @SerializedName("is_qty_decimal")
    private String isQtyDecimal;
    @SerializedName("no_discount")
    private String noDiscount;
    @SerializedName("qty_canceled")
    private String qtyCanceled;
    @SerializedName("qty_invoiced")
    private String qtyInvoiced;
    @SerializedName("qty_ordered")
    private String qtyOrdered;
    @SerializedName("qty_refunded")
    private String qtyRefunded;
    @SerializedName("qty_shipped")
    private String qtyShipped;
    @SerializedName("price")
    private String price;
    @SerializedName("base_price")
    private String basePrice;
    @SerializedName("original_price")
    private String originalPrice;
    @SerializedName("base_original_price")
    private String baseOriginalPrice;
    @SerializedName("tax_percent")
    private String taxPercent;
    @SerializedName("tax_amount")
    private String taxAmount;
    @SerializedName("base_tax_amount")
    private String baseTaxAmount;
    @SerializedName("tax_invoiced")
    private String taxInvoiced;
    @SerializedName("base_tax_invoiced")
    private String baseTaxInvoiced;
    @SerializedName("discount_percent")
    private String discountPercent;
    @SerializedName("discount_amount")
    private String discountAmount;
    @SerializedName("base_discount_amount")
    private String baseDiscountAmount;
    @SerializedName("discount_invoiced")
    private String discountInvoiced;
    @SerializedName("base_discount_invoiced")
    private String baseDiscountInvoiced;
    @SerializedName("amount_refunded")
    private String amountRefunded;
    @SerializedName("base_amount_refunded")
    private String baseAmountRefunded;
    @SerializedName("row_total")
    private String rowTotal;
    @SerializedName("base_row_total")
    private String baseRowTotal;
    @SerializedName("row_invoiced")
    private String rowInvoiced;
    @SerializedName("base_row_invoiced")
    private String baseRowInvoiced;
    @SerializedName("row_weight")
    private String rowWeight;
    @SerializedName("weee_tax_applied")
    private String weeeTaxApplied;
    @SerializedName("weee_tax_applied_amount")
    private String weeeTaxAppliedAmount;
    @SerializedName("weee_tax_applied_row_amount")
    private String weeeTaxAppliedRowAmount;
    @SerializedName("base_weee_tax_applied_amount")
    private String baseWeeeTaxAppliedAmount;
    @SerializedName("base_weee_tax_applied_row_amount")
    private String baseWeeeTaxAppliedRowAmount;
    @SerializedName("weee_tax_disposition")
    private String weeeTaxDisposition;
    @SerializedName("weee_tax_row_disposition")
    private String weeeTaxRowDisposition;
    @SerializedName("base_weee_tax_disposition")
    private String baseWeeeTaxDisposition;
    @SerializedName("base_weee_tax_row_disposition")
    private String baseWeeeTaxRowDisposition;
    @SerializedName("url")
    private String url;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getQuoteItemId() {
        return quoteItemId;
    }

    public void setQuoteItemId(String quoteItemId) {
        this.quoteItemId = quoteItemId;
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

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductOptions() {
        return productOptions;
    }

    public void setProductOptions(String productOptions) {
        this.productOptions = productOptions;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getIsVirtual() {
        return isVirtual;
    }

    public void setIsVirtual(String isVirtual) {
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

    public String getFreeShipping() {
        return freeShipping;
    }

    public void setFreeShipping(String freeShipping) {
        this.freeShipping = freeShipping;
    }

    public String getIsQtyDecimal() {
        return isQtyDecimal;
    }

    public void setIsQtyDecimal(String isQtyDecimal) {
        this.isQtyDecimal = isQtyDecimal;
    }

    public String getNoDiscount() {
        return noDiscount;
    }

    public void setNoDiscount(String noDiscount) {
        this.noDiscount = noDiscount;
    }

    public String getQtyCanceled() {
        return qtyCanceled;
    }

    public void setQtyCanceled(String qtyCanceled) {
        this.qtyCanceled = qtyCanceled;
    }

    public String getQtyInvoiced() {
        return qtyInvoiced;
    }

    public void setQtyInvoiced(String qtyInvoiced) {
        this.qtyInvoiced = qtyInvoiced;
    }

    public String getQtyOrdered() {
        return qtyOrdered;
    }

    public void setQtyOrdered(String qtyOrdered) {
        this.qtyOrdered = qtyOrdered;
    }

    public String getQtyRefunded() {
        return qtyRefunded;
    }

    public void setQtyRefunded(String qtyRefunded) {
        this.qtyRefunded = qtyRefunded;
    }

    public String getQtyShipped() {
        return qtyShipped;
    }

    public void setQtyShipped(String qtyShipped) {
        this.qtyShipped = qtyShipped;
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

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getBaseOriginalPrice() {
        return baseOriginalPrice;
    }

    public void setBaseOriginalPrice(String baseOriginalPrice) {
        this.baseOriginalPrice = baseOriginalPrice;
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

    public String getTaxInvoiced() {
        return taxInvoiced;
    }

    public void setTaxInvoiced(String taxInvoiced) {
        this.taxInvoiced = taxInvoiced;
    }

    public String getBaseTaxInvoiced() {
        return baseTaxInvoiced;
    }

    public void setBaseTaxInvoiced(String baseTaxInvoiced) {
        this.baseTaxInvoiced = baseTaxInvoiced;
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

    public String getDiscountInvoiced() {
        return discountInvoiced;
    }

    public void setDiscountInvoiced(String discountInvoiced) {
        this.discountInvoiced = discountInvoiced;
    }

    public String getBaseDiscountInvoiced() {
        return baseDiscountInvoiced;
    }

    public void setBaseDiscountInvoiced(String baseDiscountInvoiced) {
        this.baseDiscountInvoiced = baseDiscountInvoiced;
    }

    public String getAmountRefunded() {
        return amountRefunded;
    }

    public void setAmountRefunded(String amountRefunded) {
        this.amountRefunded = amountRefunded;
    }

    public String getBaseAmountRefunded() {
        return baseAmountRefunded;
    }

    public void setBaseAmountRefunded(String baseAmountRefunded) {
        this.baseAmountRefunded = baseAmountRefunded;
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

    public String getRowInvoiced() {
        return rowInvoiced;
    }

    public void setRowInvoiced(String rowInvoiced) {
        this.rowInvoiced = rowInvoiced;
    }

    public String getBaseRowInvoiced() {
        return baseRowInvoiced;
    }

    public void setBaseRowInvoiced(String baseRowInvoiced) {
        this.baseRowInvoiced = baseRowInvoiced;
    }

    public String getRowWeight() {
        return rowWeight;
    }

    public void setRowWeight(String rowWeight) {
        this.rowWeight = rowWeight;
    }

    public String getWeeeTaxApplied() {
        return weeeTaxApplied;
    }

    public void setWeeeTaxApplied(String weeeTaxApplied) {
        this.weeeTaxApplied = weeeTaxApplied;
    }

    public String getWeeeTaxAppliedAmount() {
        return weeeTaxAppliedAmount;
    }

    public void setWeeeTaxAppliedAmount(String weeeTaxAppliedAmount) {
        this.weeeTaxAppliedAmount = weeeTaxAppliedAmount;
    }

    public String getWeeeTaxAppliedRowAmount() {
        return weeeTaxAppliedRowAmount;
    }

    public void setWeeeTaxAppliedRowAmount(String weeeTaxAppliedRowAmount) {
        this.weeeTaxAppliedRowAmount = weeeTaxAppliedRowAmount;
    }

    public String getBaseWeeeTaxAppliedAmount() {
        return baseWeeeTaxAppliedAmount;
    }

    public void setBaseWeeeTaxAppliedAmount(String baseWeeeTaxAppliedAmount) {
        this.baseWeeeTaxAppliedAmount = baseWeeeTaxAppliedAmount;
    }

    public String getBaseWeeeTaxAppliedRowAmount() {
        return baseWeeeTaxAppliedRowAmount;
    }

    public void setBaseWeeeTaxAppliedRowAmount(String baseWeeeTaxAppliedRowAmount) {
        this.baseWeeeTaxAppliedRowAmount = baseWeeeTaxAppliedRowAmount;
    }

    public String getWeeeTaxDisposition() {
        return weeeTaxDisposition;
    }

    public void setWeeeTaxDisposition(String weeeTaxDisposition) {
        this.weeeTaxDisposition = weeeTaxDisposition;
    }

    public String getWeeeTaxRowDisposition() {
        return weeeTaxRowDisposition;
    }

    public void setWeeeTaxRowDisposition(String weeeTaxRowDisposition) {
        this.weeeTaxRowDisposition = weeeTaxRowDisposition;
    }

    public String getBaseWeeeTaxDisposition() {
        return baseWeeeTaxDisposition;
    }

    public void setBaseWeeeTaxDisposition(String baseWeeeTaxDisposition) {
        this.baseWeeeTaxDisposition = baseWeeeTaxDisposition;
    }

    public String getBaseWeeeTaxRowDisposition() {
        return baseWeeeTaxRowDisposition;
    }

    public void setBaseWeeeTaxRowDisposition(String baseWeeeTaxRowDisposition) {
        this.baseWeeeTaxRowDisposition = baseWeeeTaxRowDisposition;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ItemPOJO{" +
                "itemId='" + itemId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", quoteItemId='" + quoteItemId + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", productId='" + productId + '\'' +
                ", productType='" + productType + '\'' +
                ", productOptions='" + productOptions + '\'' +
                ", weight='" + weight + '\'' +
                ", isVirtual='" + isVirtual + '\'' +
                ", sku='" + sku + '\'' +
                ", name='" + name + '\'' +
                ", freeShipping='" + freeShipping + '\'' +
                ", isQtyDecimal='" + isQtyDecimal + '\'' +
                ", noDiscount='" + noDiscount + '\'' +
                ", qtyCanceled='" + qtyCanceled + '\'' +
                ", qtyInvoiced='" + qtyInvoiced + '\'' +
                ", qtyOrdered='" + qtyOrdered + '\'' +
                ", qtyRefunded='" + qtyRefunded + '\'' +
                ", qtyShipped='" + qtyShipped + '\'' +
                ", price='" + price + '\'' +
                ", basePrice='" + basePrice + '\'' +
                ", originalPrice='" + originalPrice + '\'' +
                ", baseOriginalPrice='" + baseOriginalPrice + '\'' +
                ", taxPercent='" + taxPercent + '\'' +
                ", taxAmount='" + taxAmount + '\'' +
                ", baseTaxAmount='" + baseTaxAmount + '\'' +
                ", taxInvoiced='" + taxInvoiced + '\'' +
                ", baseTaxInvoiced='" + baseTaxInvoiced + '\'' +
                ", discountPercent='" + discountPercent + '\'' +
                ", discountAmount='" + discountAmount + '\'' +
                ", baseDiscountAmount='" + baseDiscountAmount + '\'' +
                ", discountInvoiced='" + discountInvoiced + '\'' +
                ", baseDiscountInvoiced='" + baseDiscountInvoiced + '\'' +
                ", amountRefunded='" + amountRefunded + '\'' +
                ", baseAmountRefunded='" + baseAmountRefunded + '\'' +
                ", rowTotal='" + rowTotal + '\'' +
                ", baseRowTotal='" + baseRowTotal + '\'' +
                ", rowInvoiced='" + rowInvoiced + '\'' +
                ", baseRowInvoiced='" + baseRowInvoiced + '\'' +
                ", rowWeight='" + rowWeight + '\'' +
                ", weeeTaxApplied='" + weeeTaxApplied + '\'' +
                ", weeeTaxAppliedAmount='" + weeeTaxAppliedAmount + '\'' +
                ", weeeTaxAppliedRowAmount='" + weeeTaxAppliedRowAmount + '\'' +
                ", baseWeeeTaxAppliedAmount='" + baseWeeeTaxAppliedAmount + '\'' +
                ", baseWeeeTaxAppliedRowAmount='" + baseWeeeTaxAppliedRowAmount + '\'' +
                ", weeeTaxDisposition='" + weeeTaxDisposition + '\'' +
                ", weeeTaxRowDisposition='" + weeeTaxRowDisposition + '\'' +
                ", baseWeeeTaxDisposition='" + baseWeeeTaxDisposition + '\'' +
                ", baseWeeeTaxRowDisposition='" + baseWeeeTaxRowDisposition + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
