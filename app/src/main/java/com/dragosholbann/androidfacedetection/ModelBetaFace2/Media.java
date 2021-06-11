package com.dragosholbann.androidfacedetection.ModelBetaFace2;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Media{

	@SerializedName("duration")
	private String duration;

	@SerializedName("original_filename")
	private String originalFilename;

	@SerializedName("media_uuid")
	private String mediaUuid;

	@SerializedName("checksum")
	private String checksum;

	@SerializedName("faces")
	private List<FacesItem> faces;

	@SerializedName("tags")
	private Object tags;

	public void setDuration(String duration){
		this.duration = duration;
	}

	public String getDuration(){
		return duration;
	}

	public void setOriginalFilename(String originalFilename){
		this.originalFilename = originalFilename;
	}

	public String getOriginalFilename(){
		return originalFilename;
	}

	public void setMediaUuid(String mediaUuid){
		this.mediaUuid = mediaUuid;
	}

	public String getMediaUuid(){
		return mediaUuid;
	}

	public void setChecksum(String checksum){
		this.checksum = checksum;
	}

	public String getChecksum(){
		return checksum;
	}

	public void setFaces(List<FacesItem> faces){
		this.faces = faces;
	}

	public List<FacesItem> getFaces(){
		return faces;
	}

	public void setTags(Object tags){
		this.tags = tags;
	}

	public Object getTags(){
		return tags;
	}

	@Override
 	public String toString(){
		return 
			"Media{" + 
			"duration = '" + duration + '\'' + 
			",original_filename = '" + originalFilename + '\'' + 
			",media_uuid = '" + mediaUuid + '\'' + 
			",checksum = '" + checksum + '\'' + 
			",faces = '" + faces + '\'' + 
			",tags = '" + tags + '\'' + 
			"}";
		}
}