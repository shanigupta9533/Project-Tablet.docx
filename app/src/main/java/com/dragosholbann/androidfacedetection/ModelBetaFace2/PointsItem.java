package com.dragosholbann.androidfacedetection.ModelBetaFace2;

import com.google.gson.annotations.SerializedName;

public class PointsItem{

	@SerializedName("x")
	private double X;

	@SerializedName("name")
	private String name;

	@SerializedName("y")
	private double Y;

	@SerializedName("type")
	private int type;

	public void setX(double X){
		this.X = X;
	}

	public double getX(){
		return X;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setY(double Y){
		this.Y = Y;
	}

	public double getY(){
		return Y;
	}

	public void setType(int type){
		this.type = type;
	}

	public int getType(){
		return type;
	}

	@Override
 	public String toString(){
		return 
			"PointsItem{" + 
			"x = '" + X + '\'' + 
			",name = '" + name + '\'' + 
			",y = '" + Y + '\'' + 
			",type = '" + type + '\'' + 
			"}";
		}
}