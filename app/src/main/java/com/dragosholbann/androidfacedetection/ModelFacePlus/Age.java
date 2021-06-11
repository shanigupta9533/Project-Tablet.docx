package com.dragosholbann.androidfacedetection.ModelFacePlus;

import com.google.gson.annotations.SerializedName;

public class Age{

	@SerializedName("value")
	private int value;

	public void setValue(int value){
		this.value = value;
	}

	public int getValue(){
		return value;
	}

	@Override
 	public String toString(){
		return 
			"Age{" + 
			"value = '" + value + '\'' + 
			"}";
		}
}