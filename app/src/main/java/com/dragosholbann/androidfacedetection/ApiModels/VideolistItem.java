package com.dragosholbann.androidfacedetection.ApiModels;

import com.google.gson.annotations.SerializedName;

public class VideolistItem{

	@SerializedName("video_name")
	private String videoName;

	@SerializedName("video_duration")
	private String videoDuration;

	@SerializedName("video_url")
	private String videoUrl;

	@SerializedName("video_format")
	private String videoFormat;

	@SerializedName("customer_id")
	private String customerId;

	@SerializedName("video_list_id")
	private String videoListId;

	@SerializedName("video_added")
	private String videoAdded;

	@SerializedName("video_type")
	private String videoType;

	public void setVideoName(String videoName){
		this.videoName = videoName;
	}

	public String getVideoName(){
		return videoName;
	}

	public void setVideoDuration(String videoDuration){
		this.videoDuration = videoDuration;
	}

	public String getVideoDuration(){
		return videoDuration;
	}

	public void setVideoUrl(String videoUrl){
		this.videoUrl = videoUrl;
	}

	public String getVideoUrl(){
		return videoUrl;
	}

	public void setVideoFormat(String videoFormat){
		this.videoFormat = videoFormat;
	}

	public String getVideoFormat(){
		return videoFormat;
	}

	public void setCustomerId(String customerId){
		this.customerId = customerId;
	}

	public String getCustomerId(){
		return customerId;
	}

	public void setVideoListId(String videoListId){
		this.videoListId = videoListId;
	}

	public String getVideoListId(){
		return videoListId;
	}

	public void setVideoAdded(String videoAdded){
		this.videoAdded = videoAdded;
	}

	public String getVideoAdded(){
		return videoAdded;
	}

	public void setVideoType(String videoType){
		this.videoType = videoType;
	}

	public String getVideoType(){
		return videoType;
	}

	@Override
 	public String toString(){
		return 
			"VideolistItem{" + 
			"video_name = '" + videoName + '\'' + 
			",video_duration = '" + videoDuration + '\'' + 
			",video_url = '" + videoUrl + '\'' + 
			",video_format = '" + videoFormat + '\'' + 
			",customer_id = '" + customerId + '\'' + 
			",video_list_id = '" + videoListId + '\'' + 
			",video_added = '" + videoAdded + '\'' + 
			",video_type = '" + videoType + '\'' + 
			"}";
		}
}