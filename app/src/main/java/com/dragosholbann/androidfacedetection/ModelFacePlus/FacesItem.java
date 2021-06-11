package com.dragosholbann.androidfacedetection.ModelFacePlus;

import com.google.gson.annotations.SerializedName;

public class FacesItem{

	@SerializedName("attributes")
	private Attributes attributes;

	@SerializedName("face_rectangle")
	private FaceRectangle faceRectangle;

	@SerializedName("face_token")
	private String faceToken;

	@SerializedName("landmark")
	private Landmark landmark;

	public void setAttributes(Attributes attributes){
		this.attributes = attributes;
	}

	public Attributes getAttributes(){
		return attributes;
	}

	public void setFaceRectangle(FaceRectangle faceRectangle){
		this.faceRectangle = faceRectangle;
	}

	public FaceRectangle getFaceRectangle(){
		return faceRectangle;
	}

	public void setFaceToken(String faceToken){
		this.faceToken = faceToken;
	}

	public String getFaceToken(){
		return faceToken;
	}

	public void setLandmark(Landmark landmark){
		this.landmark = landmark;
	}

	public Landmark getLandmark(){
		return landmark;
	}

	@Override
 	public String toString(){
		return 
			"FacesItem{" + 
			"attributes = '" + attributes + '\'' + 
			",face_rectangle = '" + faceRectangle + '\'' + 
			",face_token = '" + faceToken + '\'' + 
			",landmark = '" + landmark + '\'' + 
			"}";
		}
}