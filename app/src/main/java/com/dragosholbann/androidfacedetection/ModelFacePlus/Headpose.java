package com.dragosholbann.androidfacedetection.ModelFacePlus;

import com.google.gson.annotations.SerializedName;

public class Headpose{

	@SerializedName("yaw_angle")
	private double yawAngle;

	@SerializedName("roll_angle")
	private double rollAngle;

	@SerializedName("pitch_angle")
	private double pitchAngle;

	public void setYawAngle(double yawAngle){
		this.yawAngle = yawAngle;
	}

	public double getYawAngle(){
		return yawAngle;
	}

	public void setRollAngle(double rollAngle){
		this.rollAngle = rollAngle;
	}

	public double getRollAngle(){
		return rollAngle;
	}

	public void setPitchAngle(double pitchAngle){
		this.pitchAngle = pitchAngle;
	}

	public double getPitchAngle(){
		return pitchAngle;
	}

	@Override
 	public String toString(){
		return 
			"Headpose{" + 
			"yaw_angle = '" + yawAngle + '\'' + 
			",roll_angle = '" + rollAngle + '\'' + 
			",pitch_angle = '" + pitchAngle + '\'' + 
			"}";
		}
}