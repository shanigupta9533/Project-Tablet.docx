package com.dragosholbann.androidfacedetection.ModelLayer.NetworkLayer.EndpointInterfaces;

import com.dragosholbann.androidfacedetection.ApiModels.CityModel;
import com.dragosholbann.androidfacedetection.ApiModels.CountryModel;
import com.dragosholbann.androidfacedetection.ApiModels.PincodeModel;
import com.dragosholbann.androidfacedetection.ApiModels.StateModel;
import com.dragosholbann.androidfacedetection.ModelLayer.Entities.Cab;
import com.dragosholbann.androidfacedetection.ModelLayer.Entities.Campaign;
import com.dragosholbann.androidfacedetection.ModelLayer.Entities.CampaignOffer;
import com.dragosholbann.androidfacedetection.ModelLayer.Entities.OfferLog;
import com.dragosholbann.androidfacedetection.ModelLayer.Entities.OfferLogResponse;
import com.dragosholbann.androidfacedetection.ModelLayer.Entities.Video;
import com.dragosholbann.androidfacedetection.ModelLayer.Entities.VideoLogResponse;
import com.dragosholbann.androidfacedetection.ModelLayer.Register;
import java.util.Map;
import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface WebServices
{
    @FormUrlEncoded
    @POST("getCabTabByDriverMobile")
    Single<Cab> getCabTabDetails(@Field("cab_tab_driver_mobile") String cab_tab_driver_mobile);

    @FormUrlEncoded
    @POST("getCabTabByImei")
    Single<Cab> getCabTabByDriverImei(@Field("cab_tab_imei") String cab_tab_imei);

    @FormUrlEncoded
    @POST("getVideoBypincodeMYNew")
    Single<Video> getVideoListByPincode(@Field("pin_code") String pincode);

    @FormUrlEncoded
    @POST("addError")
    Single<VideoLogResponse> addErrorLog(@Field("cab_tab_id") String cab_tab_id,
                                         @Field("error_message") String error_message,
                                         @Field("error_file") String error_file,
                                         @Field("error_line") String error_line,
                                         @Field("error_context") String error_context,
                                         @Field("error_level") String error_level,
                                         @Field("ip_address") String ip_address,
                                         @Field("platform") String platform,
                                         @Field("error_status") String error_status);


    @FormUrlEncoded
    @POST("getCampaignlistBypincodeMYNew")
    Single<Campaign> getCampaignListByPincode(@Field("pin_code") String pincode,
                                              @Field("age") int age,
                                              @Field("gender") String gender);

    @FormUrlEncoded
    @POST("getCampaignlistFiller")
    Single<CampaignOffer> getCampaignListOfferByPincode(@Field("pin_code") String pincode,
                                                        @Field("age") int age,
                                                        @Field("gender") String gender);

    @FormUrlEncoded
    @POST("getCampaignlistFillerBypincode")
    Single<Campaign> getCampaignListFillerByPincode(@Field("pin_code") String pincode);

    @FormUrlEncoded
    @POST("verifyUser")
    Single<Register> varifyUser(@Field("user_mobile") String mobile);

    @FormUrlEncoded
    @POST("verifyUserInfo")
    Single<Register> getLogin(@Field("user_mobile") String mobile);

    @FormUrlEncoded
    @POST("addVideoLogJson")
    Single<VideoLogResponse> addVideoLogJson (@Field("videoLog_data") String videoLog_data);

    @FormUrlEncoded
    @POST("addOfferLogJson")
    Single<OfferLogResponse> addOfferLogJson (@Field("offerLog_data") String offerLog_data);

    @FormUrlEncoded
    @POST("userSignUp")
    Single<Register> getRegister(@Field("user_name") String user_name,
                                 @Field("user_email") String user_email,
                                 @Field("user_password") String userPassword,
                                 @Field("user_mobile") String userMobile,
                                 @Field("user_role") String userRole  ,
                                 @Field("user_image") String user_image);

    @GET("getCountry")
    Single<CountryModel> getCountry();

    @GET("getCity")
    Single<CityModel> getCity();

    @GET("getState")
    Single<StateModel> getState();

    @GET("getPincode")
    Single<PincodeModel> GetPincode();

    @GET("getCurrentDateTime")
    Single<Map> GetTime();
    }