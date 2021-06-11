package com.dragosholbann.androidfacedetection.ModelLayer.Entities;

import com.google.gson.annotations.SerializedName;

public class CampaignlistItemSortedCombinations {

	@SerializedName("video_duration")
	private String videoDuration;

	@SerializedName("budget_start_date")
	private String budgetStartDate;

	@SerializedName("budget_id")
	private String budgetId;

	@SerializedName("approval")
	private String approval;

	@SerializedName("budget-amount-lifetime")
	private int budgetAmountLifetime;

	@SerializedName("campaign_type")
	private String campaignType;

	@SerializedName("campaign_ip_address")
	private String campaignIpAddress;

	@SerializedName("budget-amount-daily")
	private int budgetAmountDaily;

	@SerializedName("count")
	private int count;

	@SerializedName("datediffStartdateandEnddate")
	private String datediffStartdateandEnddate;

	@SerializedName("audience_id")
	private String audienceId;

	@SerializedName("campaign_modified")
	private String campaignModified;

	@SerializedName("daily-budget-amount-lifetime")
	private int dailyBudgetAmountLifetime;

	@SerializedName("budget_end_date")
	private String budgetEndDate;

	@SerializedName("campaign_name")
	private String campaignName;

	@SerializedName("campaign_is_prime")
	private String campaignIsPrime;

	@SerializedName("video_duration_in_seconds")
	private int videoDurationInSeconds;

	@SerializedName("campaign_added")
	private String campaignAdded;

	@SerializedName("customer_id")
	private String customerId;

	@SerializedName("video_list_id")
	private String videoListId;

	@SerializedName("campaign_id")
	private String campaignId;

	@SerializedName("budget_type")
	private String budgetType;

	@SerializedName("status")
	private String status;

	public void setVideoDuration(String videoDuration){
		this.videoDuration = videoDuration;
	}

	public String getVideoDuration(){
		return videoDuration;
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

	public void setBudgetAmountLifetime(int budgetAmountLifetime){
		this.budgetAmountLifetime = budgetAmountLifetime;
	}

	public int getBudgetAmountLifetime(){
		return budgetAmountLifetime;
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

	public void setBudgetAmountDaily(int budgetAmountDaily){
		this.budgetAmountDaily = budgetAmountDaily;
	}

	public int getBudgetAmountDaily(){
		return budgetAmountDaily;
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

	public int getDailyBudgetAmountLifetime(){
		return dailyBudgetAmountLifetime;
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

	public void setVideoDurationInSeconds(int videoDurationInSeconds){
		this.videoDurationInSeconds = videoDurationInSeconds;
	}

	public int getVideoDurationInSeconds(){
		return videoDurationInSeconds;
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

	public void setVideoListId(String videoListId){
		this.videoListId = videoListId;
	}

	public String getVideoListId(){
		return videoListId;
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
			"CampaignlistItem{" + 
			"video_duration = '" + videoDuration + '\'' + 
			",budget_start_date = '" + budgetStartDate + '\'' + 
			",budget_id = '" + budgetId + '\'' + 
			",approval = '" + approval + '\'' + 
			",budget-amount-lifetime = '" + budgetAmountLifetime + '\'' + 
			",campaign_type = '" + campaignType + '\'' + 
			",campaign_ip_address = '" + campaignIpAddress + '\'' + 
			",budget-amount-daily = '" + budgetAmountDaily + '\'' + 
			",count = '" + count + '\'' + 
			",datediffStartdateandEnddate = '" + datediffStartdateandEnddate + '\'' + 
			",audience_id = '" + audienceId + '\'' + 
			",campaign_modified = '" + campaignModified + '\'' + 
			",daily-budget-amount-lifetime = '" + dailyBudgetAmountLifetime + '\'' + 
			",budget_end_date = '" + budgetEndDate + '\'' + 
			",campaign_name = '" + campaignName + '\'' + 
			",campaign_is_prime = '" + campaignIsPrime + '\'' + 
			",video_duration_in_seconds = '" + videoDurationInSeconds + '\'' + 
			",campaign_added = '" + campaignAdded + '\'' + 
			",customer_id = '" + customerId + '\'' + 
			",video_list_id = '" + videoListId + '\'' + 
			",campaign_id = '" + campaignId + '\'' + 
			",budget_type = '" + budgetType + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}