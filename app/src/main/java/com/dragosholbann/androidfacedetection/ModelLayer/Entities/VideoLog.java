package com.dragosholbann.androidfacedetection.ModelLayer.Entities;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class VideoLog{

	@SerializedName("VideoLog")
	private List<VideoLogItem> videoLog;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private String status;

	public void setVideoLog(List<VideoLogItem> videoLog){
		this.videoLog = videoLog;
	}

	public List<VideoLogItem> getVideoLog(){
		return videoLog;
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
			"VideoLog{" + 
			"videoLog = '" + videoLog + '\'' + 
			",message = '" + message + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}