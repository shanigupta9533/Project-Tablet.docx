package com.dragosholbann.androidfacedetection.ModelFacePlus;

import com.google.gson.annotations.SerializedName;

public class Gender{

	@SerializedName("value")
	private String value;

	public void setValue(String value){
		this.value = value;
	}

	public String getValue(){
		return value;
	}

	@Override
 	public String toString(){
		return 
			"Gender{" + 
			"value = '" + value + '\'' + 
			"}";
		}
}