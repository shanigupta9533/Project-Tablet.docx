package com.dragosholbann.androidfacedetection.ModelBetaFace;

import com.google.gson.annotations.SerializedName;

public class TagsItem{

	@SerializedName("confidence")
	private int confidence;

	@SerializedName("name")
	private String name;

	@SerializedName("value")
	private String value;

	public void setConfidence(int confidence){
		this.confidence = confidence;
	}

	public int getConfidence(){
		return confidence;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setValue(String value){
		this.value = value;
	}

	public String getValue(){
		return value;
	}

	@Override
 	public String toString(){
		return 
			"TagsItem{" + 
			"confidence = '" + confidence + '\'' + 
			",name = '" + name + '\'' + 
			",value = '" + value + '\'' + 
			"}";
		}
}