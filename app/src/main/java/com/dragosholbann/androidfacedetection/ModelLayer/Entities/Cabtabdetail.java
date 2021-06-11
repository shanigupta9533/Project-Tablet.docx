package com.dragosholbann.androidfacedetection.ModelLayer.Entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Cabtabdetail implements Parcelable {

	@SerializedName("cab_details_id")
	private String cabDetailsId;

	@SerializedName("cab_tab_driver_name")
	private String cabTabDriverName;

	@SerializedName("cab_tab_driver_mobile")
	private String cabTabDriverMobile;

	@SerializedName("cab_tab_modified")
	private String cabTabModified;

	@SerializedName("cab_details")
	private List<CabDetails> cabDetails = null;

	@SerializedName("cab_tab_email")
	private String cabTabEmail;

	@SerializedName("cab_tab_added")
	private String cabTabAdded;

	@SerializedName("is_verified")
	private String isVerified;

	@SerializedName("cab_tab_id")
	private String cabTabId;

	public void setCabDetailsId(String cabDetailsId){
		this.cabDetailsId = cabDetailsId;
	}

	public String getCabDetailsId(){
		return cabDetailsId;
	}

	public void setCabTabDriverName(String cabTabDriverName){
		this.cabTabDriverName = cabTabDriverName;
	}

	public String getCabTabDriverName(){
		return cabTabDriverName;
	}

	public void setCabTabDriverMobile(String cabTabDriverMobile){
		this.cabTabDriverMobile = cabTabDriverMobile;
	}

	public String getCabTabDriverMobile(){
		return cabTabDriverMobile;
	}

	public void setCabTabModified(String cabTabModified){
		this.cabTabModified = cabTabModified;
	}

	public String getCabTabModified(){
		return cabTabModified;
	}

	public void setCabDetails(List<CabDetails> cabDetails){
		this.cabDetails = cabDetails;
	}

	public List<CabDetails> getCabDetails(){
		return cabDetails;
	}

	public void setCabTabEmail(String cabTabEmail){
		this.cabTabEmail = cabTabEmail;
	}

	public String getCabTabEmail(){
		return cabTabEmail;
	}

	public void setCabTabAdded(String cabTabAdded){
		this.cabTabAdded = cabTabAdded;
	}

	public String getCabTabAdded(){
		return cabTabAdded;
	}

	public void setIsVerified(String isVerified){
		this.isVerified = isVerified;
	}

	public String getIsVerified(){
		return isVerified;
	}

	public void setCabTabId(String cabTabId){
		this.cabTabId = cabTabId;
	}

	public String getCabTabId(){
		return cabTabId;
	}




	public Cabtabdetail(Parcel in) {
		cabTabId = in.readString();
		cabDetailsId = in.readString();
		if (in.readByte() == 0x01) {
			//cabDetails = new ArrayList<CabDetails>();
			in.readList(cabDetails, Cabtabdetail.class.getClassLoader());
		} else {
			cabDetails = null;
		}
		cabTabEmail = in.readString();
		cabTabDriverName = in.readString();
		cabTabDriverMobile = in.readString();
		isVerified = in.readString();
		cabTabAdded = in.readString();
		cabTabModified = in.readString();
}

	@Override
 	public String toString(){
		return 
			"Cabtabdetail{" + 
			"cab_details_id = '" + cabDetailsId + '\'' + 
			",cab_tab_driver_name = '" + cabTabDriverName + '\'' + 
			",cab_tab_driver_mobile = '" + cabTabDriverMobile + '\'' + 
			",cab_tab_modified = '" + cabTabModified + '\'' + 
			",cab_details = '" + cabDetails + '\'' + 
			",cab_tab_email = '" + cabTabEmail + '\'' + 
			",cab_tab_added = '" + cabTabAdded + '\'' + 
			",is_verified = '" + isVerified + '\'' + 
			",cab_tab_id = '" + cabTabId + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(cabTabId);
		dest.writeString(cabDetailsId);
		if (cabDetails == null) {
			dest.writeByte((byte) (0x00));
		} else {
			dest.writeByte((byte) (0x01));
			dest.writeList(cabDetails);
		}
		dest.writeString(cabTabEmail);
		dest.writeString(cabTabDriverName);
		dest.writeString(cabTabDriverMobile);
		dest.writeString(isVerified);
		dest.writeString(cabTabAdded);
		dest.writeString(cabTabModified);

	}


	@SuppressWarnings("unused")
	public static final Parcelable.Creator<Cabtabdetail> CREATOR = new Parcelable.Creator<Cabtabdetail>() {
		@Override
		public Cabtabdetail createFromParcel(Parcel in) {
			return new Cabtabdetail(in);
		}

		@Override
		public Cabtabdetail[] newArray(int size) {
			return new Cabtabdetail[size];
		}
	};

}