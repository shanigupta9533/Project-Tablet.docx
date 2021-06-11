package com.dragosholbann.androidfacedetection.ModelFacePlus;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ImageCredentials{

	@SerializedName("image_id")
	private String imageId;

	@SerializedName("faces")
	private List<FacesItem> faces;

	@SerializedName("request_id")
	private String requestId;

	@SerializedName("time_used")
	private int timeUsed;

	public void setImageId(String imageId){
		this.imageId = imageId;
	}

	public String getImageId(){
		return imageId;
	}

	public void setFaces(List<FacesItem> faces){
		this.faces = faces;
	}

	public List<FacesItem> getFaces(){
		return faces;
	}

	public void setRequestId(String requestId){
		this.requestId = requestId;
	}

	public String getRequestId(){
		return requestId;
	}

	public void setTimeUsed(int timeUsed){
		this.timeUsed = timeUsed;
	}

	public int getTimeUsed(){
		return timeUsed;
	}

	@Override
 	public String toString(){
		return 
			"ImageCredentials{" + 
			"image_id = '" + imageId + '\'' + 
			",faces = '" + faces + '\'' + 
			",request_id = '" + requestId + '\'' + 
			",time_used = '" + timeUsed + '\'' + 
			"}";
		}
}