package com.dragosholbann.androidfacedetection.ModelLayer.Entities;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class CampaignOffer{

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private String status;

	@SerializedName("campaignlistOffer")
	private ArrayList<CampaignlistOfferItem> campaignlistOffer;

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

	public void setCampaignlistOffer(ArrayList<CampaignlistOfferItem> campaignlistOffer){
		this.campaignlistOffer = campaignlistOffer;
	}

	public ArrayList<CampaignlistOfferItem> getCampaignlistOffer(){
		return campaignlistOffer;
	}

	@Override
 	public String toString(){
		return 
			"CampaignOffer{" + 
			"message = '" + message + '\'' + 
			",status = '" + status + '\'' + 
			",campaignlistOffer = '" + campaignlistOffer + '\'' + 
			"}";
		}
}