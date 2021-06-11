package com.dragosholbann.androidfacedetection.ModelLayer.Entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class CabDetails implements Parcelable {

	@SerializedName("cab_details_id")
	private String cabDetailsId;

	@SerializedName("cab_owner_id_proof_name")
	private String cabOwnerIdProofName;

	@SerializedName("cab_driver_license_no")
	private String cabDriverLicenseNo;

	@SerializedName("cab_details_modified")
	private String cabDetailsModified;

	@SerializedName("cab_owner_bank_account_name")
	private String cabOwnerBankAccountName;

	@SerializedName("cab_owner_name")
	private String cabOwnerName;

	@SerializedName("cab_owner_id_proof_number")
	private String cabOwnerIdProofNumber;

	@SerializedName("cab_details_added")
	private String cabDetailsAdded;

	@SerializedName("cab_owner_bank_account_number")
	private String cabOwnerBankAccountNumber;

	@SerializedName("cab_vehicle_type")
	private String cabVehicleType;

	@SerializedName("cab_registration_date")
	private String cabRegistrationDate;

	@SerializedName("cab_associated_company")
	private String cabAssociatedCompany;

	@SerializedName("cab_owner_bank_name")
	private String cabOwnerBankName;

	@SerializedName("cab_owner_address")
	private String cabOwnerAddress;

	@SerializedName("cab_registration_city")
	private String cabRegistrationCity;

	@SerializedName("cab_registration_number")
	private String cabRegistrationNumber;

	@SerializedName("cab_insurance_valid")
	private String cabInsuranceValid;

	@SerializedName("cab_driver_name")
	private String cabDriverName;

	@SerializedName("cab_vehicle_make")
	private String cabVehicleMake;

	@SerializedName("cab_owner_bank_ifsc")
	private String cabOwnerBankIfsc;

	@SerializedName("cab_driver_mobile_no")
	private String cabDriverMobileNo;

	@SerializedName("cab_vehicle_model")
	private String cabVehicleModel;

	@SerializedName("cab_registration_country")
	private String cabRegistrationCountry;

	@SerializedName("customer_id")
	private String customerId;

	@SerializedName("cab_owner_mobile")
	private String cabOwnerMobile;

	@SerializedName("cab_registration_state")
	private String cabRegistrationState;

	public CabDetails(Parcel in) {
		customerId = in.readString();
		cabOwnerName = in.readString();
		cabOwnerMobile = in.readString();
		cabOwnerIdProofName = in.readString();
		cabOwnerIdProofNumber = in.readString();
		cabOwnerAddress = in.readString();
		cabDriverName = in.readString();
		cabDriverMobileNo = in.readString();
		cabDriverLicenseNo = in.readString();
		cabVehicleType = in.readString();
		cabRegistrationNumber = in.readString();
		cabRegistrationCountry = in.readString();
		cabRegistrationState = in.readString();
		cabRegistrationCity = in.readString();
		cabRegistrationDate = in.readString();
		cabInsuranceValid = in.readString();
		cabAssociatedCompany = in.readString();
		cabVehicleModel = in.readString();
		cabVehicleMake = in.readString();
		cabDetailsAdded = in.readString();
		cabDetailsModified = in.readString();
		cabOwnerBankName = in.readString();
		cabOwnerBankIfsc = in.readString();
		cabOwnerBankAccountNumber = in.readString();
		cabOwnerBankAccountName = in.readString();
	}

	public void setCabDetailsId(String cabDetailsId){
		this.cabDetailsId = cabDetailsId;
	}

	public String getCabDetailsId(){
		return cabDetailsId;
	}

	public void setCabOwnerIdProofName(String cabOwnerIdProofName){
		this.cabOwnerIdProofName = cabOwnerIdProofName;
	}

	public String getCabOwnerIdProofName(){
		return cabOwnerIdProofName;
	}

	public void setCabDriverLicenseNo(String cabDriverLicenseNo){
		this.cabDriverLicenseNo = cabDriverLicenseNo;
	}

	public String getCabDriverLicenseNo(){
		return cabDriverLicenseNo;
	}

	public void setCabDetailsModified(String cabDetailsModified){
		this.cabDetailsModified = cabDetailsModified;
	}

	public String getCabDetailsModified(){
		return cabDetailsModified;
	}

	public void setCabOwnerBankAccountName(String cabOwnerBankAccountName){
		this.cabOwnerBankAccountName = cabOwnerBankAccountName;
	}

	public String getCabOwnerBankAccountName(){
		return cabOwnerBankAccountName;
	}

	public void setCabOwnerName(String cabOwnerName){
		this.cabOwnerName = cabOwnerName;
	}

	public String getCabOwnerName(){
		return cabOwnerName;
	}

	public void setCabOwnerIdProofNumber(String cabOwnerIdProofNumber){
		this.cabOwnerIdProofNumber = cabOwnerIdProofNumber;
	}

	public String getCabOwnerIdProofNumber(){
		return cabOwnerIdProofNumber;
	}

	public void setCabDetailsAdded(String cabDetailsAdded){
		this.cabDetailsAdded = cabDetailsAdded;
	}

	public String getCabDetailsAdded(){
		return cabDetailsAdded;
	}

	public void setCabOwnerBankAccountNumber(String cabOwnerBankAccountNumber){
		this.cabOwnerBankAccountNumber = cabOwnerBankAccountNumber;
	}

	public String getCabOwnerBankAccountNumber(){
		return cabOwnerBankAccountNumber;
	}

	public void setCabVehicleType(String cabVehicleType){
		this.cabVehicleType = cabVehicleType;
	}

	public String getCabVehicleType(){
		return cabVehicleType;
	}

	public void setCabRegistrationDate(String cabRegistrationDate){
		this.cabRegistrationDate = cabRegistrationDate;
	}

	public String getCabRegistrationDate(){
		return cabRegistrationDate;
	}

	public void setCabAssociatedCompany(String cabAssociatedCompany){
		this.cabAssociatedCompany = cabAssociatedCompany;
	}

	public String getCabAssociatedCompany(){
		return cabAssociatedCompany;
	}

	public void setCabOwnerBankName(String cabOwnerBankName){
		this.cabOwnerBankName = cabOwnerBankName;
	}

	public String getCabOwnerBankName(){
		return cabOwnerBankName;
	}

	public void setCabOwnerAddress(String cabOwnerAddress){
		this.cabOwnerAddress = cabOwnerAddress;
	}

	public String getCabOwnerAddress(){
		return cabOwnerAddress;
	}

	public void setCabRegistrationCity(String cabRegistrationCity){
		this.cabRegistrationCity = cabRegistrationCity;
	}

	public String getCabRegistrationCity(){
		return cabRegistrationCity;
	}

	public void setCabRegistrationNumber(String cabRegistrationNumber){
		this.cabRegistrationNumber = cabRegistrationNumber;
	}

	public String getCabRegistrationNumber(){
		return cabRegistrationNumber;
	}

	public void setCabInsuranceValid(String cabInsuranceValid){
		this.cabInsuranceValid = cabInsuranceValid;
	}

	public String getCabInsuranceValid(){
		return cabInsuranceValid;
	}

	public void setCabDriverName(String cabDriverName){
		this.cabDriverName = cabDriverName;
	}

	public String getCabDriverName(){
		return cabDriverName;
	}

	public void setCabVehicleMake(String cabVehicleMake){
		this.cabVehicleMake = cabVehicleMake;
	}

	public String getCabVehicleMake(){
		return cabVehicleMake;
	}

	public void setCabOwnerBankIfsc(String cabOwnerBankIfsc){
		this.cabOwnerBankIfsc = cabOwnerBankIfsc;
	}

	public String getCabOwnerBankIfsc(){
		return cabOwnerBankIfsc;
	}

	public void setCabDriverMobileNo(String cabDriverMobileNo){
		this.cabDriverMobileNo = cabDriverMobileNo;
	}

	public String getCabDriverMobileNo(){
		return cabDriverMobileNo;
	}

	public void setCabVehicleModel(String cabVehicleModel){
		this.cabVehicleModel = cabVehicleModel;
	}

	public String getCabVehicleModel(){
		return cabVehicleModel;
	}

	public void setCabRegistrationCountry(String cabRegistrationCountry){
		this.cabRegistrationCountry = cabRegistrationCountry;
	}

	public String getCabRegistrationCountry(){
		return cabRegistrationCountry;
	}

	public void setCustomerId(String customerId){
		this.customerId = customerId;
	}

	public String getCustomerId(){
		return customerId;
	}

	public void setCabOwnerMobile(String cabOwnerMobile){
		this.cabOwnerMobile = cabOwnerMobile;
	}

	public String getCabOwnerMobile(){
		return cabOwnerMobile;
	}

	public void setCabRegistrationState(String cabRegistrationState){
		this.cabRegistrationState = cabRegistrationState;
	}

	public String getCabRegistrationState(){
		return cabRegistrationState;
	}

	@Override
 	public String toString(){
		return 
			"CabDetails{" + 
			"cab_details_id = '" + cabDetailsId + '\'' + 
			",cab_owner_id_proof_name = '" + cabOwnerIdProofName + '\'' + 
			",cab_driver_license_no = '" + cabDriverLicenseNo + '\'' + 
			",cab_details_modified = '" + cabDetailsModified + '\'' + 
			",cab_owner_bank_account_name = '" + cabOwnerBankAccountName + '\'' + 
			",cab_owner_name = '" + cabOwnerName + '\'' + 
			",cab_owner_id_proof_number = '" + cabOwnerIdProofNumber + '\'' + 
			",cab_details_added = '" + cabDetailsAdded + '\'' + 
			",cab_owner_bank_account_number = '" + cabOwnerBankAccountNumber + '\'' + 
			",cab_vehicle_type = '" + cabVehicleType + '\'' + 
			",cab_registration_date = '" + cabRegistrationDate + '\'' + 
			",cab_associated_company = '" + cabAssociatedCompany + '\'' + 
			",cab_owner_bank_name = '" + cabOwnerBankName + '\'' + 
			",cab_owner_address = '" + cabOwnerAddress + '\'' + 
			",cab_registration_city = '" + cabRegistrationCity + '\'' + 
			",cab_registration_number = '" + cabRegistrationNumber + '\'' + 
			",cab_insurance_valid = '" + cabInsuranceValid + '\'' + 
			",cab_driver_name = '" + cabDriverName + '\'' + 
			",cab_vehicle_make = '" + cabVehicleMake + '\'' + 
			",cab_owner_bank_ifsc = '" + cabOwnerBankIfsc + '\'' + 
			",cab_driver_mobile_no = '" + cabDriverMobileNo + '\'' + 
			",cab_vehicle_model = '" + cabVehicleModel + '\'' + 
			",cab_registration_country = '" + cabRegistrationCountry + '\'' + 
			",customer_id = '" + customerId + '\'' + 
			",cab_owner_mobile = '" + cabOwnerMobile + '\'' + 
			",cab_registration_state = '" + cabRegistrationState + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(customerId);
		dest.writeString(cabOwnerName);
		dest.writeString(cabOwnerMobile);
		dest.writeString(cabOwnerIdProofName);
		dest.writeString(cabOwnerIdProofNumber);
		dest.writeString(cabOwnerAddress);
		dest.writeString(cabDriverName);
		dest.writeString(cabDriverMobileNo);
		dest.writeString(cabDriverLicenseNo);
		dest.writeString(cabVehicleType);
		dest.writeString(cabRegistrationNumber);
		dest.writeString(cabRegistrationCountry);
		dest.writeString(cabRegistrationState);
		dest.writeString(cabRegistrationCity);
		dest.writeString(cabRegistrationDate);
		dest.writeString(cabInsuranceValid);
		dest.writeString(cabAssociatedCompany);
		dest.writeString(cabVehicleModel);
		dest.writeString(cabVehicleMake);
		dest.writeString(cabDetailsAdded);
		dest.writeString(cabDetailsModified);
		dest.writeString(cabOwnerBankName);
		dest.writeString(cabOwnerBankIfsc);
		dest.writeString(cabOwnerBankAccountNumber);
		dest.writeString(cabOwnerBankAccountName);
	}


	@SuppressWarnings("unused")
	public static final Parcelable.Creator<CabDetails> CREATOR = new Parcelable.Creator<CabDetails>() {
		@Override
		public CabDetails createFromParcel(Parcel in) {
			return new CabDetails(in);
		}

		@Override
		public CabDetails[] newArray(int size) {
			return new CabDetails[size];
		}
	};
}