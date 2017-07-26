package com.emobi.bjain.pojo.order;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sunil on 02-05-2017.
 */

public class OrderDetailPOJO {
    @SerializedName("increment_id")
    String increment_id;
    @SerializedName("store_id")
    String store_id;
    @SerializedName("created_at")
    String created_at;
    @SerializedName("updated_at")
    String updated_at;
    @SerializedName("customer_id")
    String customer_id;
    @SerializedName("tax_amount")
    String tax_amount;
    @SerializedName("shipping_amount")
    String shipping_amount;
    @SerializedName("discount_amount")
    String discount_amount;
    @SerializedName("subtotal")
    String subtotal;
    @SerializedName("grand_total")
    String grand_total;
    @SerializedName("total_qty_ordered")
    String total_qty_ordered;
    @SerializedName("base_tax_amount")
    String base_tax_amount;
    @SerializedName("base_shipping_amount")
    String base_shipping_amount;
    @SerializedName("base_discount_amount")
    String base_discount_amount;
    @SerializedName("base_subtotal")
    String base_subtotal;
    @SerializedName("base_grand_total")
    String base_grand_total;
    @SerializedName("billing_address_id")
    String billing_address_id;
    @SerializedName("shipping_address_id")
    String shipping_address_id;
    @SerializedName("store_to_base_rate")
    String store_to_base_rate;
    @SerializedName("store_to_order_rate")
    String store_to_order_rate;
    @SerializedName("base_to_global_rate")
    String base_to_global_rate;
    @SerializedName("base_to_order_rate")
    String base_to_order_rate;
    @SerializedName("weight")
    String weight;
    @SerializedName("store_name")
    String store_name;
    @SerializedName("remote_ip")
    String remote_ip;
    @SerializedName("status")
    String status;
    @SerializedName("state")
    String state;
    @SerializedName("global_currency_code")
    String global_currency_code;
    @SerializedName("base_currency_code")
    String base_currency_code;
    @SerializedName("store_currency_code")
    String store_currency_code;
    @SerializedName("order_currency_code")
    String order_currency_code;
    @SerializedName("shipping_method")
    String shipping_method;
    @SerializedName("shipping_description")
    String shipping_description;
    @SerializedName("customer_email")
    String customer_email;
    @SerializedName("customer_firstname")
    String customer_firstname;
    @SerializedName("customer_lastname")
    String customer_lastname;
    @SerializedName("quote_id")
    String quote_id;
    @SerializedName("is_virtual")
    String is_virtual;
    @SerializedName("customer_group_id")
    String customer_group_id;
    @SerializedName("customer_note_notify")
    String customer_note_notify;
    @SerializedName("customer_is_guest")
    String customer_is_guest;
    @SerializedName("order_id")
    String order_id;
    @SerializedName("shipping_address")
    ShippingPOJO shippingPOJO;
    @SerializedName("billing_address")
    BillingAddressPOJO biShippingPOJO;
    @SerializedName("items")
    List<ItemPOJO> itemPOJOList;


    public String getIncrement_id() {
        return increment_id;
    }

    public void setIncrement_id(String increment_id) {
        this.increment_id = increment_id;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
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

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getTax_amount() {
        return tax_amount;
    }

    public void setTax_amount(String tax_amount) {
        this.tax_amount = tax_amount;
    }

    public String getShipping_amount() {
        return shipping_amount;
    }

    public void setShipping_amount(String shipping_amount) {
        this.shipping_amount = shipping_amount;
    }

    public String getDiscount_amount() {
        return discount_amount;
    }

    public void setDiscount_amount(String discount_amount) {
        this.discount_amount = discount_amount;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public String getGrand_total() {
        return grand_total;
    }

    public void setGrand_total(String grand_total) {
        this.grand_total = grand_total;
    }

    public String getTotal_qty_ordered() {
        return total_qty_ordered;
    }

    public void setTotal_qty_ordered(String total_qty_ordered) {
        this.total_qty_ordered = total_qty_ordered;
    }

    public String getBase_tax_amount() {
        return base_tax_amount;
    }

    public void setBase_tax_amount(String base_tax_amount) {
        this.base_tax_amount = base_tax_amount;
    }

    public String getBase_shipping_amount() {
        return base_shipping_amount;
    }

    public void setBase_shipping_amount(String base_shipping_amount) {
        this.base_shipping_amount = base_shipping_amount;
    }

    public String getBase_discount_amount() {
        return base_discount_amount;
    }

    public void setBase_discount_amount(String base_discount_amount) {
        this.base_discount_amount = base_discount_amount;
    }

    public String getBase_subtotal() {
        return base_subtotal;
    }

    public void setBase_subtotal(String base_subtotal) {
        this.base_subtotal = base_subtotal;
    }

    public String getBase_grand_total() {
        return base_grand_total;
    }

    public void setBase_grand_total(String base_grand_total) {
        this.base_grand_total = base_grand_total;
    }

    public String getBilling_address_id() {
        return billing_address_id;
    }

    public void setBilling_address_id(String billing_address_id) {
        this.billing_address_id = billing_address_id;
    }

    public String getShipping_address_id() {
        return shipping_address_id;
    }

    public void setShipping_address_id(String shipping_address_id) {
        this.shipping_address_id = shipping_address_id;
    }

    public String getStore_to_base_rate() {
        return store_to_base_rate;
    }

    public void setStore_to_base_rate(String store_to_base_rate) {
        this.store_to_base_rate = store_to_base_rate;
    }

    public String getStore_to_order_rate() {
        return store_to_order_rate;
    }

    public void setStore_to_order_rate(String store_to_order_rate) {
        this.store_to_order_rate = store_to_order_rate;
    }

    public String getBase_to_global_rate() {
        return base_to_global_rate;
    }

    public void setBase_to_global_rate(String base_to_global_rate) {
        this.base_to_global_rate = base_to_global_rate;
    }

    public String getBase_to_order_rate() {
        return base_to_order_rate;
    }

    public void setBase_to_order_rate(String base_to_order_rate) {
        this.base_to_order_rate = base_to_order_rate;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getRemote_ip() {
        return remote_ip;
    }

    public void setRemote_ip(String remote_ip) {
        this.remote_ip = remote_ip;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getGlobal_currency_code() {
        return global_currency_code;
    }

    public void setGlobal_currency_code(String global_currency_code) {
        this.global_currency_code = global_currency_code;
    }

    public String getBase_currency_code() {
        return base_currency_code;
    }

    public void setBase_currency_code(String base_currency_code) {
        this.base_currency_code = base_currency_code;
    }

    public String getStore_currency_code() {
        return store_currency_code;
    }

    public void setStore_currency_code(String store_currency_code) {
        this.store_currency_code = store_currency_code;
    }

    public String getOrder_currency_code() {
        return order_currency_code;
    }

    public void setOrder_currency_code(String order_currency_code) {
        this.order_currency_code = order_currency_code;
    }

    public String getShipping_method() {
        return shipping_method;
    }

    public void setShipping_method(String shipping_method) {
        this.shipping_method = shipping_method;
    }

    public String getShipping_description() {
        return shipping_description;
    }

    public void setShipping_description(String shipping_description) {
        this.shipping_description = shipping_description;
    }

    public String getCustomer_email() {
        return customer_email;
    }

    public void setCustomer_email(String customer_email) {
        this.customer_email = customer_email;
    }

    public String getCustomer_firstname() {
        return customer_firstname;
    }

    public void setCustomer_firstname(String customer_firstname) {
        this.customer_firstname = customer_firstname;
    }

    public String getCustomer_lastname() {
        return customer_lastname;
    }

    public void setCustomer_lastname(String customer_lastname) {
        this.customer_lastname = customer_lastname;
    }

    public String getQuote_id() {
        return quote_id;
    }

    public void setQuote_id(String quote_id) {
        this.quote_id = quote_id;
    }

    public String getIs_virtual() {
        return is_virtual;
    }

    public void setIs_virtual(String is_virtual) {
        this.is_virtual = is_virtual;
    }

    public String getCustomer_group_id() {
        return customer_group_id;
    }

    public void setCustomer_group_id(String customer_group_id) {
        this.customer_group_id = customer_group_id;
    }

    public String getCustomer_note_notify() {
        return customer_note_notify;
    }

    public void setCustomer_note_notify(String customer_note_notify) {
        this.customer_note_notify = customer_note_notify;
    }

    public String getCustomer_is_guest() {
        return customer_is_guest;
    }

    public void setCustomer_is_guest(String customer_is_guest) {
        this.customer_is_guest = customer_is_guest;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public ShippingPOJO getShippingPOJO() {
        return shippingPOJO;
    }

    public void setShippingPOJO(ShippingPOJO shippingPOJO) {
        this.shippingPOJO = shippingPOJO;
    }

    public BillingAddressPOJO getBiShippingPOJO() {
        return biShippingPOJO;
    }

    public void setBiShippingPOJO(BillingAddressPOJO biShippingPOJO) {
        this.biShippingPOJO = biShippingPOJO;
    }

    public List<ItemPOJO> getItemPOJOList() {
        return itemPOJOList;
    }

    public void setItemPOJOList(List<ItemPOJO> itemPOJOList) {
        this.itemPOJOList = itemPOJOList;
    }

    @Override
    public String toString() {
        return "OrderDetailPOJO{" +
                "increment_id='" + increment_id + '\'' +
                ", store_id='" + store_id + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", customer_id='" + customer_id + '\'' +
                ", tax_amount='" + tax_amount + '\'' +
                ", shipping_amount='" + shipping_amount + '\'' +
                ", discount_amount='" + discount_amount + '\'' +
                ", subtotal='" + subtotal + '\'' +
                ", grand_total='" + grand_total + '\'' +
                ", total_qty_ordered='" + total_qty_ordered + '\'' +
                ", base_tax_amount='" + base_tax_amount + '\'' +
                ", base_shipping_amount='" + base_shipping_amount + '\'' +
                ", base_discount_amount='" + base_discount_amount + '\'' +
                ", base_subtotal='" + base_subtotal + '\'' +
                ", base_grand_total='" + base_grand_total + '\'' +
                ", billing_address_id='" + billing_address_id + '\'' +
                ", shipping_address_id='" + shipping_address_id + '\'' +
                ", store_to_base_rate='" + store_to_base_rate + '\'' +
                ", store_to_order_rate='" + store_to_order_rate + '\'' +
                ", base_to_global_rate='" + base_to_global_rate + '\'' +
                ", base_to_order_rate='" + base_to_order_rate + '\'' +
                ", weight='" + weight + '\'' +
                ", store_name='" + store_name + '\'' +
                ", remote_ip='" + remote_ip + '\'' +
                ", status='" + status + '\'' +
                ", state='" + state + '\'' +
                ", global_currency_code='" + global_currency_code + '\'' +
                ", base_currency_code='" + base_currency_code + '\'' +
                ", store_currency_code='" + store_currency_code + '\'' +
                ", order_currency_code='" + order_currency_code + '\'' +
                ", shipping_method='" + shipping_method + '\'' +
                ", shipping_description='" + shipping_description + '\'' +
                ", customer_email='" + customer_email + '\'' +
                ", customer_firstname='" + customer_firstname + '\'' +
                ", customer_lastname='" + customer_lastname + '\'' +
                ", quote_id='" + quote_id + '\'' +
                ", is_virtual='" + is_virtual + '\'' +
                ", customer_group_id='" + customer_group_id + '\'' +
                ", customer_note_notify='" + customer_note_notify + '\'' +
                ", customer_is_guest='" + customer_is_guest + '\'' +
                ", order_id='" + order_id + '\'' +
                ", shippingPOJO=" + shippingPOJO +
                ", biShippingPOJO=" + biShippingPOJO +
                ", itemPOJOList=" + itemPOJOList +
                '}';
    }
}
