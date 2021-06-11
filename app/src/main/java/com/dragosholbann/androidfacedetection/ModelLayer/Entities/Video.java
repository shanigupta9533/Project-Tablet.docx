package com.dragosholbann.androidfacedetection.ModelLayer.Entities;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Video{

	@SerializedName("videolist")
	private ArrayList<VideolistItem> videolist;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private String status;

	public void setVideolist(ArrayList<VideolistItem> videolist){
		this.videolist = videolist;
	}

	public ArrayList<VideolistItem> getVideolist(){
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
			"Video{" + 
			"videolist = '" + videolist + '\'' + 
			",message = '" + message + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}