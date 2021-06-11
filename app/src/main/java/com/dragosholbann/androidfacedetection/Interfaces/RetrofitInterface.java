package com.dragosholbann.androidfacedetection.Interfaces;
import com.dragosholbann.androidfacedetection.ApiModels.CityModel;
import com.dragosholbann.androidfacedetection.ApiModels.CountryModel;
import com.dragosholbann.androidfacedetection.ApiModels.PincodeModel;
import com.dragosholbann.androidfacedetection.ApiModels.StateModel;
import com.dragosholbann.androidfacedetection.ApiModels.Videos;
import com.dragosholbann.androidfacedetection.ModelBetaFace2.ImageInfo;
import com.dragosholbann.androidfacedetection.ModelFacePlus.ImageCredentials;
import com.dragosholbann.androidfacedetection.ModelLayer.Entities.CabDetails;
import com.dragosholbann.androidfacedetection.ModelLayer.Entities.Cabtabdetail;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface RetrofitInterface
{

    @Multipart
    @POST("detect")
    Call<ImageCredentials> getImageData(@PartMap Map<String, RequestBody>
                                                map);

    @Multipart
    @POST("file")
    Call<ImageInfo> getImageDataBetaFace(@PartMap Map<String, RequestBody>
                                                map);

    @Multipart
    @POST("file")
    Call<ImageInfo> getImageDataBetaFace2(@Part("api_key") RequestBody api_key,
                                           @Part MultipartBody.Part file
                                            ,@Part("recognize_parameters") RequestBody recognize_parameters);

    @FormUrlEncoded
    @POST("getCabTabByDriverMobile")
    Call<Cabtabdetail> getCabTabByDriverMobile(@Field("cab_tab_driver_mobile") String cab_tab_driver_mobile);


    @Multipart
    @POST("file")
    Call<ImageInfo> getImageDataBetaFace3(@PartMap Map<String, RequestBody>
                                                  map,
                                          @Part MultipartBody.Part file
            /*,@Part("recognize_parameters") RequestBody recognize_parameters*/);


    @Streaming
    @GET
    Call<ResponseBody> downloadFileWithDynamicUrlAsync(@Url String fileUrl);


    @GET("getVideoList")
    Call<Videos> getVideoList(/*@Field("name") String name,
                                    @Field("address") String address,
                                    @Field("email") String email,
                                    @Field("gender") String gender,
                                    @Field("dob") String dob,
                                    @Field("contact") String contact*/);

    @FormUrlEncoded
    @POST("getVideoList")
    Call<Videos> getVideoList2(/*@Field("name") String name,
                                    @Field("address") String address,
                                    @Field("email") String email,
                                    @Field("gender") String gender,
                                    @Field("dob") String dob,
                                    @Field("contact") String contact*/);

    @GET("solar/json_get_category.php")
    Call<Videos> getJSON();





}
