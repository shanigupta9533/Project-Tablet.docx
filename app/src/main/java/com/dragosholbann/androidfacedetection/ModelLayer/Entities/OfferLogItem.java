package com.dragosholbann.androidfacedetection.ModelLayer.Entities;

import com.google.gson.annotations.SerializedName;

public class OfferLogItem {

	@SerializedName("offer_lat")
	private String offerLat;

	@SerializedName("offer_city")
	private String offerCity;

	@SerializedName("cab_details_id")
	private String cabDetailsId;

	@SerializedName("duration_played")
	private String durationPlayed;

	@SerializedName("offer_consumed_id")
	private String offerConsumedId;

	@SerializedName("audience_id")
	private String audienceId;

	@SerializedName("offer_pincode")
	private String offerPincode;

	@SerializedName("cab_tab_id")
	private String cabTabId;

	@SerializedName("offer_played_datetime")
	private String offerPlayedDatetime;

	@SerializedName("campaign_name")
	private String campaignName;

	@SerializedName("offer_gender")
	private String offerGender;

	@SerializedName("offer_log_id")
	private String offerLogId;

	@SerializedName("offer_played_ip_address")
	private String offerPlayedIpAddress;

	@SerializedName("offer_country")
	private String offerCountry;

	@SerializedName("offer_age_group")
	private String offerAgeGroup;

	@SerializedName("offer_list_name")
	private String offerListName;

	@SerializedName("offer_lng")
	private String offerLng;

	@SerializedName("offer_amount_charged")
	private String offerAmountCharged;

	@SerializedName("audience_name")
	private String audienceName;

	@SerializedName("cab_tab_name")
	private String cabTabName;

	@SerializedName("offer_state")
	private String offerState;

	@SerializedName("offer_review")
	private String offerReview;

	@SerializedName("offer_id")
	private String offerId;

	@SerializedName("offer_consumed_name")
	private String offerConsumedName;

	@SerializedName("campaign_id")
	private String campaignId;

	public void setCabDetailsId(String cabDetailsId){
		this.cabDetailsId = cabDetailsId;
	}

	public String getCabDetailsId(){
		return cabDetailsId;
	}


	public void setDurationPlayed(String durationPlayed){
		this.durationPlayed = durationPlayed;
	}

	public String getDurationPlayed(){
		return durationPlayed;
	}




	public void setOfferLat(String offerLat){
		this.offerLat = offerLat;
	}

	public String getOfferLat(){
		return offerLat;
	}

	public void setOfferCity(String offerCity){
		this.offerCity = offerCity;
	}

	public String getOfferCity(){
		return offerCity;
	}

	public void setOfferConsumedId(String offerConsumedId){
		this.offerConsumedId = offerConsumedId;
	}

	public String getOfferConsumedId(){
		return offerConsumedId;
	}

	public void setAudienceId(String audienceId){
		this.audienceId = audienceId;
	}

	public String getAudienceId(){
		return audienceId;
	}

	public void setOfferPincode(String offerPincode){
		this.offerPincode = offerPincode;
	}

	public String getOfferPincode(){
		return offerPincode;
	}

	public void setCabTabId(String cabTabId){
		this.cabTabId = cabTabId;
	}

	public String getCabTabId(){
		return cabTabId;
	}

	public void setOfferPlayedDatetime(String offerPlayedDatetime){
		this.offerPlayedDatetime = offerPlayedDatetime;
	}

	public String getOfferPlayedDatetime(){
		return offerPlayedDatetime;
	}

	public void setCampaignName(String campaignName){
		this.campaignName = campaignName;
	}

	public String getCampaignName(){
		return campaignName;
	}

	public void setOfferGender(String offerGender){
		this.offerGender = offerGender;
	}

	public String getOfferGender(){
		return offerGender;
	}

	public void setOfferLogId(String offerLogId){
		this.offerLogId = offerLogId;
	}

	public String getOfferLogId(){
		return offerLogId;
	}

	public void setOfferPlayedIpAddress(String offerPlayedIpAddress){
		this.offerPlayedIpAddress = offerPlayedIpAddress;
	}

	public String getOfferPlayedIpAddress(){
		return offerPlayedIpAddress;
	}

	public void setOfferCountry(String offerCountry){
		this.offerCountry = offerCountry;
	}

	public String getOfferCountry(){
		return offerCountry;
	}

	public void setOfferAgeGroup(String offerAgeGroup){
		this.offerAgeGroup = offerAgeGroup;
	}

	public String getOfferAgeGroup(){
		return offerAgeGroup;
	}

	public void setOfferListName(String offerListName){
		this.offerListName = offerListName;
	}

	public String getOfferListName(){
		return offerListName;
	}

	public void setOfferLng(String offerLng){
		this.offerLng = offerLng;
	}

	public String getOfferLng(){
		return offerLng;
	}

	public void setOfferAmountCharged(String offerAmountCharged){
		this.offerAmountCharged = offerAmountCharged;
	}

	public String getOfferAmountCharged(){
		return offerAmountCharged;
	}

	public void setAudienceName(String audienceName){
		this.audienceName = audienceName;
	}

	public String getAudienceName(){
		return audienceName;
	}

	public void setCabTabName(String cabTabName){
		this.cabTabName = cabTabName;
	}

	public String getCabTabName(){
		return cabTabName;
	}

	public void setOfferState(String offerState){
		this.offerState = offerState;
	}

	public String getOfferState(){
		return offerState;
	}

	public void setOfferReview(String offerReview){
		this.offerReview = offerReview;
	}

	public String getOfferReview(){
		return offerReview;
	}

	public void setOfferId(String offerId){
		this.offerId = offerId;
	}

	public String getOfferId(){
		return offerId;
	}

	public void setOfferConsumedName(String offerConsumedName){
		this.offerConsumedName = offerConsumedName;
	}

	public String getOfferConsumedName(){
		return offerConsumedName;
	}

	public void setCampaignId(String campaignId){
		this.campaignId = campaignId;
	}

	public String getCampaignId(){
		return campaignId;
	}

	@Override
 	public String toString(){
		return 
			"OfferLogItem{" + 
			"offer_lat = '" + offerLat + '\'' + 
			",offer_city = '" + offerCity + '\'' + 
			",offer_consumed_id = '" + offerConsumedId + '\'' + 
			",audience_id = '" + audienceId + '\'' + 
			",offer_pincode = '" + offerPincode + '\'' + 
			",cab_tab_id = '" + cabTabId + '\'' + 
			",offer_played_datetime = '" + offerPlayedDatetime + '\'' + 
			",campaign_name = '" + campaignName + '\'' + 
			",offer_gender = '" + offerGender + '\'' + 
			",offer_log_id = '" + offerLogId + '\'' + 
			",offer_played_ip_address = '" + offerPlayedIpAddress + '\'' + 
			",offer_country = '" + offerCountry + '\'' + 
			",offer_age_group = '" + offerAgeGroup + '\'' + 
			",offer_list_name = '" + offerListName + '\'' + 
			",offer_lng = '" + offerLng + '\'' + 
			",offer_amount_charged = '" + offerAmountCharged + '\'' + 
			",audience_name = '" + audienceName + '\'' + 
			",cab_tab_name = '" + cabTabName + '\'' + 
			",offer_state = '" + offerState + '\'' + 
			",offer_review = '" + offerReview + '\'' + 
			",offer_id = '" + offerId + '\'' +
			",offer_consumed_name = '" + offerConsumedName + '\'' + 
			",campaign_id = '" + campaignId + '\'' + 
			"}";
		}
}