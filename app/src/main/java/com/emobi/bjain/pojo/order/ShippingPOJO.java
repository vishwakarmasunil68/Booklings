package com.emobi.bjain.pojo.order;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sunil on 02-05-2017.
 */

public class ShippingPOJO {
    @SerializedName("parent_id")
    String parent_id;
    @SerializedName("address_type")
    String address_type;
    @SerializedName("firstname")
    String firstname;
    @SerializedName("lastname")
    String lastname;
    @SerializedName("company")
    String company;
    @SerializedName("street")
    String street;
    @SerializedName("city")
    String city;
    @SerializedName("region")
    String region;
    @SerializedName("postcode")
    String postcode;
    @SerializedName("country_id")
    String country_id;
    @SerializedName("telephone")
    String telephone;
    @SerializedName("fax")
    String fax;
    @SerializedName("address_id")
    String address_id;

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getAddress_type() {
        return address_type;
    }

    public void setAddress_type(String address_type) {
        this.address_type = address_type;
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCountry_id() {
        return country_id;
    }

    public void setCountry_id(String country_id) {
        this.country_id = country_id;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getAddress_id() {
        return address_id;
    }

    public void setAddress_id(String address_id) {
        this.address_id = address_id;
    }

    @Override
    public String toString() {
        return "ShippingPOJO{" +
                "parent_id='" + parent_id + '\'' +
                ", address_type='" + address_type + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", company='" + company + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", region='" + region + '\'' +
                ", postcode='" + postcode + '\'' +
                ", country_id='" + country_id + '\'' +
                ", telephone='" + telephone + '\'' +
                ", fax='" + fax + '\'' +
                ", address_id='" + address_id + '\'' +
                '}';
    }
}
