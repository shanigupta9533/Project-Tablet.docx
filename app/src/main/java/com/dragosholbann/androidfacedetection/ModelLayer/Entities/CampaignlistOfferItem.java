package com.dragosholbann.androidfacedetection.ModelLayer.Entities;

import com.google.gson.annotations.SerializedName;

public class CampaignlistOfferItem{

	@SerializedName("budget_amount_daily")
	private String budgetAmountDaily;

	@SerializedName("budget_start_date")
	private String budgetStartDate;

	@SerializedName("budget_id")
	private String budgetId;

	@SerializedName("approval")
	private String approval;

	@SerializedName("campaign_type")
	private String campaignType;

	@SerializedName("campaign_ip_address")
	private String campaignIpAddress;

	@SerializedName("count")
	private int count;




	@SerializedName("datediffStartdateandEnddate")
	private String datediffStartdateandEnddate;

	@SerializedName("audience_id")
	private String audienceId;

	@SerializedName("campaign_modified")
	private String campaignModified;

	@SerializedName("daily_budget_amount_lifetime")
	private double dailyBudgetAmountLifetime;

	@SerializedName("offer_id")
	private String offerId;

	@SerializedName("budget_end_date")
	private String budgetEndDate;

	@SerializedName("campaign_name")
	private String campaignName;

	@SerializedName("campaign_is_prime")
	private String campaignIsPrime;

	@SerializedName("getOfferlogCount")
	private int getOfferlogCount;

	@SerializedName("offer_data")
	private OfferData offerData;

	@SerializedName("campaign_added")
	private String campaignAdded;

	@SerializedName("customer_id")
	private String customerId;

	@SerializedName("offer_duration_in_seconds")
	private String offerDurationInSeconds;

	@SerializedName("budget_amount_lifetime")
	private int budgetAmountLifetime;

	@SerializedName("campaign_id")
	private String campaignId;

	@SerializedName("budget_type")
	private String budgetType;

	@SerializedName("status")
	private String status;

	public void setBudgetAmountDaily(String budgetAmountDaily){
		this.budgetAmountDaily = budgetAmountDaily;
	}

	public String getBudgetAmountDaily(){
		return budgetAmountDaily;
	}

	public void setBudgetStartDate(String budgetStartDate){
		this.budgetStartDate = budgetStartDate;
	}

	public String getBudgetStartDate(){
		return budgetStartDate;
	}

	public void setBudgetId(String budgetId){
		this.budgetId = budgetId;
	}

	public String getBudgetId(){
		return budgetId;
	}

	public void setApproval(String approval){
		this.approval = approval;
	}

	public String getApproval(){
		return approval;
	}

	public void setCampaignType(String campaignType){
		this.campaignType = campaignType;
	}

	public String getCampaignType(){
		return campaignType;
	}

	public void setCampaignIpAddress(String campaignIpAddress){
		this.campaignIpAddress = campaignIpAddress;
	}

	public String getCampaignIpAddress(){
		return campaignIpAddress;
	}

	public void setCount(int count){
		this.count = count;
	}

	public int getCount(){
		return count;
	}

	public void setDatediffStartdateandEnddate(String datediffStartdateandEnddate){
		this.datediffStartdateandEnddate = datediffStartdateandEnddate;
	}

	public String getDatediffStartdateandEnddate(){
		return datediffStartdateandEnddate;
	}

	public void setAudienceId(String audienceId){
		this.audienceId = audienceId;
	}

	public String getAudienceId(){
		return audienceId;
	}

	public void setCampaignModified(String campaignModified){
		this.campaignModified = campaignModified;
	}

	public String getCampaignModified(){
		return campaignModified;
	}

	public void setDailyBudgetAmountLifetime(int dailyBudgetAmountLifetime){
		this.dailyBudgetAmountLifetime = dailyBudgetAmountLifetime;
	}

	public double getDailyBudgetAmountLifetime(){
		return dailyBudgetAmountLifetime;
	}

	public void setOfferId(String offerId){
		this.offerId = offerId;
	}

	public String getOfferId(){
		return offerId;
	}

	public void setBudgetEndDate(String budgetEndDate){
		this.budgetEndDate = budgetEndDate;
	}

	public String getBudgetEndDate(){
		return budgetEndDate;
	}

	public void setCampaignName(String campaignName){
		this.campaignName = campaignName;
	}

	public String getCampaignName(){
		return campaignName;
	}

	public void setCampaignIsPrime(String campaignIsPrime){
		this.campaignIsPrime = campaignIsPrime;
	}

	public String getCampaignIsPrime(){
		return campaignIsPrime;
	}

	public void setGetOfferlogCount(int getOfferlogCount){
		this.getOfferlogCount = getOfferlogCount;
	}

	public int getGetOfferlogCount(){
		return getOfferlogCount;
	}

	public void setOfferData(OfferData offerData){
		this.offerData = offerData;
	}

	public OfferData getOfferData(){
		return offerData;
	}

	public void setCampaignAdded(String campaignAdded){
		this.campaignAdded = campaignAdded;
	}

	public String getCampaignAdded(){
		return campaignAdded;
	}

	public void setCustomerId(String customerId){
		this.customerId = customerId;
	}

	public String getCustomerId(){
		return customerId;
	}

	public void setOfferDurationInSeconds(String offerDurationInSeconds){
		this.offerDurationInSeconds = offerDurationInSeconds;
	}

	public String getOfferDurationInSeconds(){
		return offerDurationInSeconds;
	}

	public void setBudgetAmountLifetime(int budgetAmountLifetime){
		this.budgetAmountLifetime = budgetAmountLifetime;
	}

	public int getBudgetAmountLifetime(){
		return budgetAmountLifetime;
	}

	public void setCampaignId(String campaignId){
		this.campaignId = campaignId;
	}

	public String getCampaignId(){
		return campaignId;
	}

	public void setBudgetType(String budgetType){
		this.budgetType = budgetType;
	}

	public String getBudgetType(){
		return budgetType;
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
			"CampaignlistOfferItem{" + 
			"budget_amount_daily = '" + budgetAmountDaily + '\'' + 
			",budget_start_date = '" + budgetStartDate + '\'' + 
			",budget_id = '" + budgetId + '\'' + 
			",approval = '" + approval + '\'' + 
			",campaign_type = '" + campaignType + '\'' + 
			",campaign_ip_address = '" + campaignIpAddress + '\'' + 
			",count = '" + count + '\'' + 
			",datediffStartdateandEnddate = '" + datediffStartdateandEnddate + '\'' + 
			",audience_id = '" + audienceId + '\'' + 
			",campaign_modified = '" + campaignModified + '\'' + 
			",daily_budget_amount_lifetime = '" + dailyBudgetAmountLifetime + '\'' + 
			",offer_id = '" + offerId + '\'' + 
			",budget_end_date = '" + budgetEndDate + '\'' + 
			",campaign_name = '" + campaignName + '\'' + 
			",campaign_is_prime = '" + campaignIsPrime + '\'' + 
			",getOfferlogCount = '" + getOfferlogCount + '\'' + 
			",offer_data = '" + offerData + '\'' + 
			",campaign_added = '" + campaignAdded + '\'' + 
			",customer_id = '" + customerId + '\'' + 
			",offer_duration_in_seconds = '" + offerDurationInSeconds + '\'' + 
			",budget_amount_lifetime = '" + budgetAmountLifetime + '\'' + 
			",campaign_id = '" + campaignId + '\'' + 
			",budget_type = '" + budgetType + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}

	private String VideoName;
	private int Priority;

	private int TempCount;


	public int getTempCount() {
		return TempCount;
	}

	public void setTempCount(int tempCount) {
		TempCount = tempCount;
	}


	public int getPriority() {
		return Priority;
	}

	public void setPriority(int priority) {
		Priority = priority;
	}

	public void setVideoName(String VideoName){
		this.VideoName = VideoName;
	}

	public String getVideoName(){
		return VideoName;
	}

}