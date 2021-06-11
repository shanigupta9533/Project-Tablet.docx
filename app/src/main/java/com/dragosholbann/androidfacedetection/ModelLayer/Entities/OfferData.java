package com.dragosholbann.androidfacedetection.ModelLayer.Entities;

import com.google.gson.annotations.SerializedName;

public class OfferData{


	@SerializedName("offer_description")
	private String offerDescription;

	@SerializedName("offer_duration")
	private String offerDuration;

	@SerializedName("offer_title")
	private String offerTitle;

	@SerializedName("offer_code")
	private String offerCode;

	@SerializedName("customer_id")
	private String customerId;

	@SerializedName("offer_added")
	private String offerAdded;

	@SerializedName("offer_id")
	private String offerId;

	@SerializedName("offer_img")
	private String offer_img;

	public String getOfferImg() {
		return offer_img;
	}

	public void setOfferImg(String offer_img) {
		this.offer_img = offer_img;
	}

	public String getOfferExpiryDate() {
		return offer_expiry_date;
	}

	public void setOfferExpiryDate(String offer_expiry_date) {
		this.offer_expiry_date = offer_expiry_date;
	}

	@SerializedName("offer_expiry_date")
	private String offer_expiry_date;

	public void setOfferDescription(String offerDescription){
		this.offerDescription = offerDescription;
	}

	public String getOfferDescription(){
		return offerDescription;
	}

	public void setOfferDuration(String offerDuration){
		this.offerDuration = offerDuration;
	}

	public String getOfferDuration(){
		return offerDuration;
	}

	public void setOfferTitle(String offerTitle){
		this.offerTitle = offerTitle;
	}

	public String getOfferTitle(){
		return offerTitle;
	}

	public void setOfferCode(String offerCode){
		this.offerCode = offerCode;
	}

	public String getOfferCode(){
		return offerCode;
	}

	public void setCustomerIdOffer(String customerId){
		this.customerId = customerId;
	}

	public String getCustomerIdOffer(){
		return customerId;
	}

	public void setOfferAdded(String offerAdded){
		this.offerAdded = offerAdded;
	}

	public String getOfferAdded(){
		return offerAdded;
	}

	public void setOfferId(String offerId){
		this.offerId = offerId;
	}

	public String getOfferId(){
		return offerId;
	}

	@Override
 	public String toString(){
		return 
			"OfferData{" + 
			"offer_description = '" + offerDescription + '\'' + 
			",offer_duration = '" + offerDuration + '\'' + 
			",offer_title = '" + offerTitle + '\'' + 
			",offer_code = '" + offerCode + '\'' + 
			",customer_id = '" + customerId + '\'' + 
			",offer_added = '" + offerAdded + '\'' + 
			",offer_id = '" + offerId + '\'' + 
			"}";
		}
}