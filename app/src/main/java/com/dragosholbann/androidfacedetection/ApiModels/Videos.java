package com.dragosholbann.androidfacedetection.ApiModels;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Videos{

	@SerializedName("videolist")
	private List<VideolistItem> videolist;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private String status;

	public void setVideolist(List<VideolistItem> videolist){
		this.videolist = videolist;
	}

	public List<VideolistItem> getVideolist(){
		return videolist;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"Videos{" + 
			"videolist = '" + videolist + '\'' + 
			",message = '" + message + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}