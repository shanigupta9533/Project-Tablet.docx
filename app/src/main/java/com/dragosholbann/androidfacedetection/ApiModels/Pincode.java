package com.dragosholbann.androidfacedetection.ApiModels;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pincode {

    @SerializedName("pincode_id")
    @Expose
    private String pincodeId;
    @SerializedName("city_id")
    @Expose
    private String cityId;
    @SerializedName("state_id")
    @Expose
    private String stateId;
    @SerializedName("country_id")
    @Expose
    private String countryId;
    @SerializedName("pincode")
    @Expose
    private String pincode;
    @SerializedName("post_office")
    @Expose
    private String postOffice;
    @SerializedName("district")
    @Expose
    private String district;
    @SerializedName("pincode_used")
    @Expose
    private String pincodeUsed;
    @SerializedName("pincode_added")
    @Expose
    private String pincodeAdded;

    public String getPincodeId() {
        return pincodeId;
    }

    public void setPincodeId(String pincodeId) {
        this.pincodeId = pincodeId;
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

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getPostOffice() {
        return postOffice;
    }

    public void setPostOffice(String postOffice) {
        this.postOffice = postOffice;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPincodeUsed() {
        return pincodeUsed;
    }

    public void setPincodeUsed(String pincodeUsed) {
        this.pincodeUsed = pincodeUsed;
    }

    public String getPincodeAdded() {
        return pincodeAdded;
    }

    public void setPincodeAdded(String pincodeAdded) {
        this.pincodeAdded = pincodeAdded;
    }

}