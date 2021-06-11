package com.dragosholbann.androidfacedetection.ModelLayer.Entities;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Campaign{

	@SerializedName("campaignlist")
	private ArrayList<CampaignlistItem> campaignlist;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private String status;

	public void setCampaignlist(ArrayList<CampaignlistItem> campaignlist){
		this.campaignlist = campaignlist;
	}

	public ArrayList<CampaignlistItem> getCampaignlist(){
		return campaignlist;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"Campaign{" + 
			"campaignlist = '" + campaignlist + '\'' + 
			",message = '" + message + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}