package com.dragosholbann.androidfacedetection.ModelFacePlus;

import com.google.gson.annotations.SerializedName;

public class Attributes{

	@SerializedName("glass")
	private Glass glass;

	@SerializedName("gender")
	private Gender gender;

	@SerializedName("headpose")
	private Headpose headpose;

	@SerializedName("age")
	private Age age;

	@SerializedName("smile")
	private Smile smile;

	public void setGlass(Glass glass){
		this.glass = glass;
	}

	public Glass getGlass(){
		return glass;
	}

	public void setGender(Gender gender){
		this.gender = gender;
	}




	public Gender getGender(){
		return gender;
	}

	public void setHeadpose(Headpose headpose){
		this.headpose = headpose;
	}

	public Headpose getHeadpose(){
		return headpose;
	}

	public void setAge(Age age){
		this.age = age;
	}

	public Age getAge(){
		return age;
	}

	public void setSmile(Smile smile){
		this.smile = smile;
	}

	public Smile getSmile(){
		return smile;
	}

	@Override
 	public String toString(){
		return 
			"Attributes{" + 
			"glass = '" + glass + '\'' + 
			",gender = '" + gender + '\'' + 
			",headpose = '" + headpose + '\'' + 
			",age = '" + age + '\'' + 
			",smile = '" + smile + '\'' + 
			"}";
		}
}