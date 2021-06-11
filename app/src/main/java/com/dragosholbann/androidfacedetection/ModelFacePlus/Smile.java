package com.dragosholbann.androidfacedetection.ModelFacePlus;

import com.google.gson.annotations.SerializedName;

public class Smile{

	@SerializedName("threshold")
	private double threshold;

	@SerializedName("value")
	private double value;

	public void setThreshold(double threshold){
		this.threshold = threshold;
	}

	public double getThreshold(){
		return threshold;
	}

	public void setValue(double value){
		this.value = value;
	}

	public double getValue(){
		return value;
	}

	@Override
 	public String toString(){
		return 
			"Smile{" + 
			"threshold = '" + threshold + '\'' + 
			",value = '" + value + '\'' + 
			"}";
		}
}