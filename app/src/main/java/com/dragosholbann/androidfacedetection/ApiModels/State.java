package com.dragosholbann.androidfacedetection.ApiModels;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class State {

    @SerializedName("state_id")
    @Expose
    private String stateId;
    @SerializedName("country_id")
    @Expose
    private String countryId;
    @SerializedName("state_name")
    @Expose
    private String stateName;
    @SerializedName("state_used")
    @Expose
    private String stateUsed;
    @SerializedName("state_added")
    @Expose
    private String stateAdded;

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

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getStateUsed() {
        return stateUsed;
    }

    public void setStateUsed(String stateUsed) {
        this.stateUsed = stateUsed;
    }

    public String getStateAdded() {
        return stateAdded;
    }

    public void setStateAdded(String stateAdded) {
        this.stateAdded = stateAdded;
    }

}