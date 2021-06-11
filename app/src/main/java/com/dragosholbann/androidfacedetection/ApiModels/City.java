package com.dragosholbann.androidfacedetection.ApiModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class City {

    @SerializedName("city_id")
    @Expose
    private String cityId;
    @SerializedName("state_id")
    @Expose
    private String stateId;
    @SerializedName("country_id")
    @Expose
    private String countryId;
    @SerializedName("city_name")
    @Expose
    private String cityName;
    @SerializedName("city_used")
    @Expose
    private String cityUsed;
    @SerializedName("city_added")
    @Expose
    private String cityAdded;
    @SerializedName("non_prime_amount")
    @Expose
    private String nonPrimeAmount;
    @SerializedName("prime_amount")
    @Expose
    private String primeAmount;
    public String getNonPrimeAmount() {
        return nonPrimeAmount;
    }

    public void setNonPrimeAmount(String nonPrimeAmount) {
        this.nonPrimeAmount = nonPrimeAmount;
    }

    public String getPrimeAmount() {
        return primeAmount;
    }

    public void setPrimeAmount(String primeAmount) {
        this.primeAmount = primeAmount;
    }


    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityUsed() {
        return cityUsed;
    }

    public void setCityUsed(String cityUsed) {
        this.cityUsed = cityUsed;
    }

    public String getCityAdded() {
        return cityAdded;
    }

    public void setCityAdded(String cityAdded) {
        this.cityAdded = cityAdded;
    }

}