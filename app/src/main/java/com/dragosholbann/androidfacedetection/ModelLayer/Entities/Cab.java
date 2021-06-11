package com.dragosholbann.androidfacedetection.ModelLayer.Entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Cab implements Parcelable {

	/*@SerializedName("cabtabdetail")
	private List<Cabtabdetail> cabtabdetail;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private String status;

	public Cab(Parcel in) {
		status = in.readString();
		message = in.readString();
		if (in.readByte() == 0x01) {
			cabtabdetail = new ArrayList<Cabtabdetail>();
			in.readList(cabtabdetail, Cabtabdetail.class.getClassLoader());
		} else {
			cabtabdetail = null;
		}
	}

	*//*protected SubscriptionData(Parcel in) {
		subscriptionDetailId = in.readString();
		userId = in.readString();
		orderId = in.readString();
		if (in.readByte() == 0x01) {
			orderDetail = new ArrayList<OrderDetail>();
			in.readList(orderDetail, OrderDetail.class.getClassLoader());
		} else {
			orderDetail = null;
		}
		subscriptionStatus = in.readString();
		subscriptionDate = in.readString();
	}
*//*

	public void setCabtabdetail(List<Cabtabdetail> cabtabdetail){
		this.cabtabdetail = cabtabdetail;
	}

	public List<Cabtabdetail> getCabtabdetail(){
		return cabtabdetail;
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
			"Cab{" + 
			"cabtabdetail = '" + cabtabdetail + '\'' + 
			",message = '" + message + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(status);
		dest.writeString(message);
		if (cabtabdetail == null) {
			dest.writeByte((byte) (0x00));
		} else {
			dest.writeByte((byte) (0x01));
			dest.writeList(cabtabdetail);
		}
	}


	@SuppressWarnings("unused")
	public static final Parcelable.Creator<Cab> CREATOR = new Parcelable.Creator<Cab>() {
		@Override
		public Cab createFromParcel(Parcel in) {
			return new Cab(in);
		}

		@Override
		public Cab[] newArray(int size) {
			return new Cab[size];
		}
	};*/


	@SerializedName("cabtabdetail")
	private List<Cabtabdetail> cabtabdetail = null;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private String status;

	public Cab(Parcel in) {
		status = in.readString();
		message = in.readString();
		if (in.readByte() == 0x01) {
			//cabtabdetail = new List<Cabtabdetail>();
			in.readList(cabtabdetail, Cab.class.getClassLoader());
		} else {
			cabtabdetail = null;
		}
	}

	/*protected SubscriptionData(Parcel in) {
		subscriptionDetailId = in.readString();
		userId = in.readString();
		orderId = in.readString();
		if (in.readByte() == 0x01) {
			orderDetail = new ArrayList<OrderDetail>();
			in.readList(orderDetail, OrderDetail.class.getClassLoader());
		} else {
			orderDetail = null;
		}
		subscriptionStatus = in.readString();
		subscriptionDate = in.readString();
	}
*/

	public void setCabtabdetail(List<Cabtabdetail> cabtabdetail){
		this.cabtabdetail = cabtabdetail;
	}

	public List<Cabtabdetail> getCabtabdetail(){
		return cabtabdetail;
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
				"Cab{" +
						"cabtabdetail = '" + cabtabdetail + '\'' +
						",message = '" + message + '\'' +
						",status = '" + status + '\'' +
						"}";
	}

	@Override
	public int describeContents() {
		return 0;
	}


	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(status);
		dest.writeString(message);
		if (cabtabdetail == null) {
			dest.writeByte((byte) (0x00));
		} else {
			dest.writeByte((byte) (0x01));
			dest.writeList(cabtabdetail);
		}
	}




	@SuppressWarnings("unused")
	public static final Parcelable.Creator<Cab> CREATOR = new Parcelable.Creator<Cab>() {
		@Override
		public Cab createFromParcel(Parcel in) {
			return new Cab(in);
		}

		@Override
		public Cab[] newArray(int size) {
			return new Cab[size];
		}
	};




}