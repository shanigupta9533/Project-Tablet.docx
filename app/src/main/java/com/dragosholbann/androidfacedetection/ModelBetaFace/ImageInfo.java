package com.dragosholbann.androidfacedetection.ModelBetaFace;

import com.google.gson.annotations.SerializedName;

public class ImageInfo{

	@SerializedName("media")
	private Media media;

	@SerializedName("recognize")
	private Object recognize;

	public void setMedia(Media media){
		this.media = media;
	}

	public Media getMedia(){
		return media;
	}

	public void setRecognize(Object recognize){
		this.recognize = recognize;
	}

	public Object getRecognize(){
		return recognize;
	}

	@Override
 	public String toString(){
		return 
			"ImageInfo{" + 
			"media = '" + media + '\'' + 
			",recognize = '" + recognize + '\'' + 
			"}";
		}
}