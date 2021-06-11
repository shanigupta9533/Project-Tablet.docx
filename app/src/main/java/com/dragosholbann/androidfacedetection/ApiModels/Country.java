package com.dragosholbann.androidfacedetection.ApiModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Country {

    @SerializedName("country_id")
    @Expose
    private String countryId;
    @SerializedName("country_name")
    @Expose
    private String countryName;
    @SerializedName("country_phone_code")
    @Expose
    private String countryPhoneCode;
    @SerializedName("country_used")
    @Expose
    private String countryUsed;
    @SerializedName("country_added")
    @Expose
    private String countryAdded;

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryPhoneCode() {
        return countryPhoneCode;
    }

    public void setCountryPhoneCode(String countryPhoneCode) {
        this.countryPhoneCode = countryPhoneCode;
    }

    public String getCountryUsed() {
        return countryUsed;
    }

    public void setCountryUsed(String countryUsed) {
        this.countryUsed = countryUsed;
    }

    public String getCountryAdded() {
        return countryAdded;
    }

    public void setCountryAdded(String countryAdded) {
        this.countryAdded = countryAdded;
    }

}