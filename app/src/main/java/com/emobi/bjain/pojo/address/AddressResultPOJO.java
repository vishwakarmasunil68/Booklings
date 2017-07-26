package com.emobi.bjain.pojo.address;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sunil on 06-05-2017.
 */

public class AddressResultPOJO {
    @SerializedName("region")
    private String region;
    @SerializedName("postcodes")
    private String postcodes;
    @SerializedName("lastname")
    private String lastname;
    @SerializedName("street")
    private String street;
    @SerializedName("city")
    private String city;
    @SerializedName("email")
    private String email;
    @SerializedName("telephone")
    private String telephone;
    @SerializedName("country_id")
    private String countryId;
    @SerializedName("firstname")
    private String firstname;
    @SerializedName("address_type")
    private String addressType;

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPostcodes() {
        return postcodes;
    }

    public void setPostcodes(String postcodes) {
        this.postcodes = postcodes;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
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

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    @Override
    public String toString() {
        return "AddressResultPOJO{" +
                "region='" + region + '\'' +
                ", postcodes='" + postcodes + '\'' +
                ", lastname='" + lastname + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", countryId='" + countryId + '\'' +
                ", firstname='" + firstname + '\'' +
                ", addressType='" + addressType + '\'' +
                '}';
    }
}
