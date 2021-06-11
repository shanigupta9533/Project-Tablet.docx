package com.dragosholbann.androidfacedetection.ModelBetaFace2;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class FacesItem{

	@SerializedName("start")
	private String start;

	@SerializedName("points")
	private List<PointsItem> points;

	@SerializedName("tags")
	private List<TagsItem> tags;

	@SerializedName("duration")
	private String duration;

	@SerializedName("user_points")
	private Object userPoints;

	@SerializedName("media_uuid")
	private String mediaUuid;

	@SerializedName("face_uuid")
	private String faceUuid;

	@SerializedName("x")
	private double X;

	@SerializedName("width")
	private double width;

	@SerializedName("y")
	private double Y;

	@SerializedName("angle")
	private double angle;

	@SerializedName("appearance_id")
	private double appearanceId;

	@SerializedName("detection_score")
	private double detectionScore;

	@SerializedName("height")
	private double height;

	@SerializedName("person_id")
	private String personId;

	public void setStart(String start){
		this.start = start;
	}

	public String getStart(){
		return start;
	}

	public void setPoints(List<PointsItem> points){
		this.points = points;
	}

	public List<PointsItem> getPoints(){
		return points;
	}

	public void setTags(List<TagsItem> tags){
		this.tags = tags;
	}

	public List<TagsItem> getTags(){
		return tags;
	}

	public void setDuration(String duration){
		this.duration = duration;
	}

	public String getDuration(){
		return duration;
	}

	public void setUserPoints(Object userPoints){
		this.userPoints = userPoints;
	}

	public Object getUserPoints(){
		return userPoints;
	}

	public void setMediaUuid(String mediaUuid){
		this.mediaUuid = mediaUuid;
	}

	public String getMediaUuid(){
		return mediaUuid;
	}

	public void setFaceUuid(String faceUuid){
		this.faceUuid = faceUuid;
	}

	public String getFaceUuid(){
		return faceUuid;
	}

	public void setX(int X){
		this.X = X;
	}

	public double getX(){
		return X;
	}

	public void setWidth(double width){
		this.width = width;
	}

	public double getWidth(){
		return width;
	}

	public void setY(int Y){
		this.Y = Y;
	}

	public double getY(){
		return Y;
	}

	public void setAngle(double angle){
		this.angle = angle;
	}

	public double getAngle(){
		return angle;
	}

	public void setAppearanceId(int appearanceId){
		this.appearanceId = appearanceId;
	}

	public double getAppearanceId(){
		return appearanceId;
	}

	public void setDetectionScore(int detectionScore){
		this.detectionScore = detectionScore;
	}

	public double getDetectionScore(){
		return detectionScore;
	}

	public void setHeight(double height){
		this.height = height;
	}

	public double getHeight(){
		return height;
	}

	public void setPersonId(String personId){
		this.personId = personId;
	}

	public String getPersonId(){
		return personId;
	}

	@Override
 	public String toString(){
		return 
			"FacesItem{" + 
			"start = '" + start + '\'' + 
			",points = '" + points + '\'' + 
			",tags = '" + tags + '\'' + 
			",duration = '" + duration + '\'' + 
			",user_points = '" + userPoints + '\'' + 
			",media_uuid = '" + mediaUuid + '\'' + 
			",face_uuid = '" + faceUuid + '\'' + 
			",x = '" + X + '\'' + 
			",width = '" + width + '\'' + 
			",y = '" + Y + '\'' + 
			",angle = '" + angle + '\'' + 
			",appearance_id = '" + appearanceId + '\'' + 
			",detection_score = '" + detectionScore + '\'' + 
			",height = '" + height + '\'' + 
			",person_id = '" + personId + '\'' + 
			"}";
		}
}