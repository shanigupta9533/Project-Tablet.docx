package com.dragosholbann.androidfacedetection.ModelFacePlus;

import com.google.gson.annotations.SerializedName;

public class Landmark{

	@SerializedName("mouth_upper_lip_left_contour2")
	private MouthUpperLipLeftContour2 mouthUpperLipLeftContour2;

	@SerializedName("contour_chin")
	private ContourChin contourChin;

	@SerializedName("mouth_upper_lip_bottom")
	private MouthUpperLipBottom mouthUpperLipBottom;

	@SerializedName("right_eye_pupil")
	private RightEyePupil rightEyePupil;

	public void setMouthUpperLipLeftContour2(MouthUpperLipLeftContour2 mouthUpperLipLeftContour2){
		this.mouthUpperLipLeftContour2 = mouthUpperLipLeftContour2;
	}

	public MouthUpperLipLeftContour2 getMouthUpperLipLeftContour2(){
		return mouthUpperLipLeftContour2;
	}

	public void setContourChin(ContourChin contourChin){
		this.contourChin = contourChin;
	}

	public ContourChin getContourChin(){
		return contourChin;
	}

	public void setMouthUpperLipBottom(MouthUpperLipBottom mouthUpperLipBottom){
		this.mouthUpperLipBottom = mouthUpperLipBottom;
	}

	public MouthUpperLipBottom getMouthUpperLipBottom(){
		return mouthUpperLipBottom;
	}

	public void setRightEyePupil(RightEyePupil rightEyePupil){
		this.rightEyePupil = rightEyePupil;
	}

	public RightEyePupil getRightEyePupil(){
		return rightEyePupil;
	}

	@Override
 	public String toString(){
		return 
			"Landmark{" + 
			"mouth_upper_lip_left_contour2 = '" + mouthUpperLipLeftContour2 + '\'' + 
			",contour_chin = '" + contourChin + '\'' + 
			",mouth_upper_lip_bottom = '" + mouthUpperLipBottom + '\'' + 
			",right_eye_pupil = '" + rightEyePupil + '\'' + 
			"}";
		}


		public void Landmark()
		{

		}
}