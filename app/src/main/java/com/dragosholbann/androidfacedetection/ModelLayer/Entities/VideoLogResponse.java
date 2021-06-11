package com.dragosholbann.androidfacedetection.ModelLayer.Entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VideoLogResponse {

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private String status;

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
						"message = '" + message + '\'' +
						",status = '" + status + '\'' +
						"}";
	}
}