package com.emobi.bjain.pojo.newaddress;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by sunil on 12-06-2017.
 */

public class NewAddressResultPOJO implements Serializable{
    @SerializedName("customer_address_id")
    private Integer customerAddressId;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updated_at")
    private String updatedAt;
    @SerializedName("city")
    private String city;
    @SerializedName("country_id")
    private String countryId;
    @SerializedName("firstname")
    private String firstname;
    @SerializedName("lastname")
    private String lastname;
    @SerializedName("postcode")
    private String postcode;
    @SerializedName("region")
    private String region;
    @SerializedName("region_id")
    private Integer regionId;
    @SerializedName("street")
    private String street;
    @SerializedName("telephone")
    private String telephone;
    @SerializedName("is_default_billing")
    private Boolean isDefaultBilling;
    @SerializedName("is_default_shipping")
    private Boolean isDefaultShipping;

    public Integer getCustomerAddressId() {
        return customerAddressId;
    }

    public void setCustomerAddressId(Integer customerAddressId) {
        this.customerAddressId = customerAddressId;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Boolean getDefaultBilling() {
        return isDefaultBilling;
    }

    public void setDefaultBilling(Boolean defaultBilling) {
        isDefaultBilling = defaultBilling;
    }

    public Boolean getDefaultShipping() {
        return isDefaultShipping;
    }

    public void setDefaultShipping(Boolean defaultShipping) {
        isDefaultShipping = defaultShipping;
    }

    @Override
    public String toString() {
        return "NewAddressResultPOJO{" +
                "customerAddressId=" + customerAddressId +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", city='" + city + '\'' +
                ", countryId='" + countryId + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", postcode='" + postcode + '\'' +
                ", region='" + region + '\'' +
                ", regionId=" + regionId +
                ", street='" + street + '\'' +
                ", telephone='" + telephone + '\'' +
                ", isDefaultBilling=" + isDefaultBilling +
                ", isDefaultShipping=" + isDefaultShipping +
                '}';
    }
}
