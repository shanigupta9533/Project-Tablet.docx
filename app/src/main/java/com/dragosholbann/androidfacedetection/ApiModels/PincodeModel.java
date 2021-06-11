package com.dragosholbann.androidfacedetection.ApiModels;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PincodeModel {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("pincode")
    @Expose
    private List<Pincode> pincode = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Pincode> getPincode() {
        return pincode;
    }

    public void setPincode(List<Pincode> pincode) {
        this.pincode = pincode;
    }

}