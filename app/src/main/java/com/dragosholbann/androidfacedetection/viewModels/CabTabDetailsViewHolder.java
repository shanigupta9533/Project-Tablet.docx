package com.dragosholbann.androidfacedetection.viewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.dragosholbann.androidfacedetection.ApiModels.CityModel;
import com.dragosholbann.androidfacedetection.ApiModels.CountryModel;
import com.dragosholbann.androidfacedetection.ApiModels.PincodeModel;
import com.dragosholbann.androidfacedetection.ApiModels.StateModel;
import com.dragosholbann.androidfacedetection.ModelLayer.Entities.Cab;
import com.dragosholbann.androidfacedetection.ModelLayer.Entities.CabDetails;
import com.dragosholbann.androidfacedetection.ModelLayer.Entities.Campaign;
import com.dragosholbann.androidfacedetection.ModelLayer.Entities.CampaignOffer;
import com.dragosholbann.androidfacedetection.ModelLayer.Entities.OfferLogResponse;
import com.dragosholbann.androidfacedetection.ModelLayer.Entities.Video;
import com.dragosholbann.androidfacedetection.ModelLayer.Entities.VideoLogResponse;
import com.dragosholbann.androidfacedetection.ModelLayer.NetworkLayer.NetworkLayer;


import java.util.Map;

import io.reactivex.Single;

public class CabTabDetailsViewHolder extends AndroidViewModel
{
    private NetworkLayer networkLayer;

    public CabTabDetailsViewHolder(@NonNull Application application) {
        super(application);
        this.networkLayer = NetworkLayer.getInstance(application.getApplicationContext());
    }

    //region CabTabDetails API Call
    public Single<Cab> addCabTabDetailsViewHolder(String cab_tab_driver_mobile) {
        return networkLayer.addCabTabDetailsViewHolder(cab_tab_driver_mobile);
    }
    //endregion

    //region CabTabDetails API Call
    public Single<Cab> getCabTabByDriverImei(String cab_tab_imei) {
        return networkLayer.getCabTabByDriverImei(cab_tab_imei);
    }
    //endregion


    //region CabTabDetails API Call
    public Single<Video> addDataPincode(String pincode) {
        return networkLayer.getVideoListByPincode(pincode);
    }
    //endregion


    //region AddErrorLog Api Call
    public Single<VideoLogResponse> addErrorLog(String cab_tab_id,String error_message,String error_file,String error_line,String error_context,String error_level,String ip_address,String platform,String error_status) {
        return networkLayer.addErrorLog( cab_tab_id, error_message, error_file, error_line, error_context, error_level, ip_address, platform, error_status);
    }
    //endregion

    //region CabTabDetails API Call
    public Single<Campaign> getCampaignDataPincode(String pincode,int age,String gender) {
        return networkLayer.getCampaignByPincode(pincode, age, gender);
    }
    //endregion

    //region CabTabDetails API Call
    public Single<CampaignOffer> getCampaignDataOfferPincode(String pincode, int age, String gender) {
        return networkLayer.getCampaignOfferByPincode(pincode, age, gender);
    }
    //endregion

    //region CabTabDetails API CallN
    public Single<VideoLogResponse> setVideoLogJson(String videoLog) {
        return networkLayer.setVideoLogJson(videoLog);
    }
    //endregion

  //region CabTabDetails API CallN
    public Single<OfferLogResponse> setOfferLogJson(String offerLog) {
        return networkLayer.setOfferLogJson(offerLog);
    }
    //endregion


    //region CabTabDetails API Call
    public Single<Campaign> getCampaignFillerDataPincode(String pincode) {
        return networkLayer.getCampaignFillerByPincode(pincode);
    }
    //endregion


    public Single<CountryModel> getCountry() {
        return networkLayer.getCountryModel();
    }

    public Single<CityModel> getCity() {
        return networkLayer.getCityModel();
    }



    public Single<StateModel> getState() {
        return networkLayer.getStateModel();
    }

    public Single<PincodeModel> getPincode() {
        return networkLayer.getPincodeModel();
    }

    public Single<Map> getTime() {
        return networkLayer.getTime();
    }





}
