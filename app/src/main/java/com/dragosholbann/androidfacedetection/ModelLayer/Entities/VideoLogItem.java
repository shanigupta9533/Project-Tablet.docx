package com.dragosholbann.androidfacedetection.ModelLayer.Entities;

import com.google.gson.annotations.SerializedName;

public class VideoLogItem{

	@SerializedName("video_lat")
	private String videoLat;

	@SerializedName("video_city")
	private String videoCity;

	@SerializedName("cab_details_id")
	private String cabDetailsId;

	@SerializedName("duration_played")
	private String durationPlayed;

	@SerializedName("offer_consumed_id")
	private String offerConsumedId;

	@SerializedName("audience_id")
	private String audienceId;

	@SerializedName("video_pincode")
	private String videoPincode;

	@SerializedName("cab_tab_id")
	private String cabTabId;

	@SerializedName("video_played_datetime")
	private String videoPlayedDatetime;

	@SerializedName("campaign_name")
	private String campaignName;

	@SerializedName("video_gender")
	private String videoGender;

	@SerializedName("video_log_id")
	private String videoLogId;

	@SerializedName("video_played_ip_address")
	private String videoPlayedIpAddress;

	@SerializedName("video_country")
	private String videoCountry;

	@SerializedName("video_age_group")
	private String videoAgeGroup;

	@SerializedName("video_list_name")
	private String videoListName;

	@SerializedName("video_lng")
	private String videoLng;

	@SerializedName("video_amount_charged")
	private String videoAmountCharged;

	@SerializedName("audience_name")
	private String audienceName;

	@SerializedName("cab_tab_name")
	private String cabTabName;

	@SerializedName("video_state")
	private String videoState;

	@SerializedName("video_review")
	private String videoReview;

	@SerializedName("video_list_id")
	private String videoListId;

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




	public void setVideoLat(String videoLat){
		this.videoLat = videoLat;
	}

	public String getVideoLat(){
		return videoLat;
	}

	public void setVideoCity(String videoCity){
		this.videoCity = videoCity;
	}

	public String getVideoCity(){
		return videoCity;
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

	public void setVideoPincode(String videoPincode){
		this.videoPincode = videoPincode;
	}

	public String getVideoPincode(){
		return videoPincode;
	}

	public void setCabTabId(String cabTabId){
		this.cabTabId = cabTabId;
	}

	public String getCabTabId(){
		return cabTabId;
	}

	public void setVideoPlayedDatetime(String videoPlayedDatetime){
		this.videoPlayedDatetime = videoPlayedDatetime;
	}

	public String getVideoPlayedDatetime(){
		return videoPlayedDatetime;
	}

	public void setCampaignName(String campaignName){
		this.campaignName = campaignName;
	}

	public String getCampaignName(){
		return campaignName;
	}

	public void setVideoGender(String videoGender){
		this.videoGender = videoGender;
	}

	public String getVideoGender(){
		return videoGender;
	}

	public void setVideoLogId(String videoLogId){
		this.videoLogId = videoLogId;
	}

	public String getVideoLogId(){
		return videoLogId;
	}

	public void setVideoPlayedIpAddress(String videoPlayedIpAddress){
		this.videoPlayedIpAddress = videoPlayedIpAddress;
	}

	public String getVideoPlayedIpAddress(){
		return videoPlayedIpAddress;
	}

	public void setVideoCountry(String videoCountry){
		this.videoCountry = videoCountry;
	}

	public String getVideoCountry(){
		return videoCountry;
	}

	public void setVideoAgeGroup(String videoAgeGroup){
		this.videoAgeGroup = videoAgeGroup;
	}

	public String getVideoAgeGroup(){
		return videoAgeGroup;
	}

	public void setVideoListName(String videoListName){
		this.videoListName = videoListName;
	}

	public String getVideoListName(){
		return videoListName;
	}

	public void setVideoLng(String videoLng){
		this.videoLng = videoLng;
	}

	public String getVideoLng(){
		return videoLng;
	}

	public void setVideoAmountCharged(String videoAmountCharged){
		this.videoAmountCharged = videoAmountCharged;
	}

	public String getVideoAmountCharged(){
		return videoAmountCharged;
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

	public void setVideoState(String videoState){
		this.videoState = videoState;
	}

	public String getVideoState(){
		return videoState;
	}

	public void setVideoReview(String videoReview){
		this.videoReview = videoReview;
	}

	public String getVideoReview(){
		return videoReview;
	}

	public void setVideoListId(String videoListId){
		this.videoListId = videoListId;
	}

	public String getVideoListId(){
		return videoListId;
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
			"VideoLogItem{" + 
			"video_lat = '" + videoLat + '\'' + 
			",video_city = '" + videoCity + '\'' + 
			",offer_consumed_id = '" + offerConsumedId + '\'' + 
			",audience_id = '" + audienceId + '\'' + 
			",video_pincode = '" + videoPincode + '\'' + 
			",cab_tab_id = '" + cabTabId + '\'' + 
			",video_played_datetime = '" + videoPlayedDatetime + '\'' + 
			",campaign_name = '" + campaignName + '\'' + 
			",video_gender = '" + videoGender + '\'' + 
			",video_log_id = '" + videoLogId + '\'' + 
			",video_played_ip_address = '" + videoPlayedIpAddress + '\'' + 
			",video_country = '" + videoCountry + '\'' + 
			",video_age_group = '" + videoAgeGroup + '\'' + 
			",video_list_name = '" + videoListName + '\'' + 
			",video_lng = '" + videoLng + '\'' + 
			",video_amount_charged = '" + videoAmountCharged + '\'' + 
			",audience_name = '" + audienceName + '\'' + 
			",cab_tab_name = '" + cabTabName + '\'' + 
			",video_state = '" + videoState + '\'' + 
			",video_review = '" + videoReview + '\'' + 
			",video_list_id = '" + videoListId + '\'' + 
			",offer_consumed_name = '" + offerConsumedName + '\'' + 
			",campaign_id = '" + campaignId + '\'' + 
			"}";
		}
}