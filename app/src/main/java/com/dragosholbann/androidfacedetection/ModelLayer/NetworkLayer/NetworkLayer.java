package com.dragosholbann.androidfacedetection.ModelLayer.NetworkLayer;

import android.content.Context;

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
import com.dragosholbann.androidfacedetection.ModelLayer.NetworkLayer.EndpointInterfaces.WebServices;
import com.dragosholbann.androidfacedetection.ModelLayer.NetworkLayer.Helpers.ApiClient;
import com.dragosholbann.androidfacedetection.ModelLayer.Register;
import com.dragosholbann.androidfacedetection.utils.Utils;

import java.util.List;
import java.util.Map;

import io.reactivex.Single;

public class NetworkLayer {


    WebServices services;

    //region Retrofit Instance

    //region Init NetworkLayer Instance
    public static volatile NetworkLayer instance;
    private static final Object LOCK = new Object();
    private static Context context;
    //endregion


    //region Handle NetworkLayer Instance
    public static NetworkLayer getInstance(Context mContext) {
        if (instance == null) {
            synchronized (LOCK) {
                if (instance == null) {
                    context = mContext;
                    instance = new NetworkLayer();
                }
            }
        }
        return instance;
    }




    public NetworkLayer() {
        services = ApiClient.createService(WebServices.class, Utils.getToken(context),Utils.getUserId(context));
    }



    // region Varify User
    public Single<Register> getLogin(String mobile) {
        services = ApiClient.createService(WebServices.class, Utils.getToken(context),Utils.getUserId(context));
        return services.getLogin(mobile);
    }
    // endregion


    // region Register User
    public Single<Register> getRegister(String name, String email, String password, String mobile, String userRole, String userImage) {
        services = ApiClient.createService(WebServices.class, Utils.getToken(context),Utils.getUserId(context));
        return services.getRegister( name,  email,  password,  mobile,  userRole, userImage);
    }
    // endregion



    // region Varify User
    public Single<Register> varifyUser(String mobile) {
        services = ApiClient.createService(WebServices.class, Utils.getToken(context),Utils.getUserId(context));
        return services.varifyUser(mobile);
    }
    // endregion



    public Single<Cab> addCabTabDetailsViewHolder(String cab_tab_driver_mobile) {
        services = ApiClient.createService(WebServices.class, Utils.getToken(context),Utils.getUserId(context));
        return services.getCabTabDetails(cab_tab_driver_mobile);
    }

    public Single<Cab> getCabTabByDriverImei(String cab_tab_imei) {
        services = ApiClient.createService(WebServices.class, Utils.getToken(context),Utils.getUserId(context));
        return services.getCabTabByDriverImei(cab_tab_imei);
    }



    public Single<Video> getVideoListByPincode(String pincode) {
        services = ApiClient.createService(WebServices.class, Utils.getToken(context),Utils.getUserId(context));
        return services.getVideoListByPincode(pincode);
    }

    public Single<VideoLogResponse> addErrorLog(String cab_tab_id,String error_message,String error_file,String error_line,String error_context,String error_level,String ip_address,String platform,String error_status) {
        services = ApiClient.createService(WebServices.class, Utils.getToken(context),Utils.getUserId(context));
        return services.addErrorLog(cab_tab_id, error_message, error_file, error_line, error_context, error_level, ip_address, platform, error_status);
    }

    public Single<Campaign> getCampaignByPincode(String pincode,int age,String gender) {
        services = ApiClient.createService(WebServices.class, Utils.getToken(context),Utils.getUserId(context));
        return services.getCampaignListByPincode(pincode, age, gender);
    }

    public Single<CampaignOffer> getCampaignOfferByPincode(String pincode, int age, String gender) {
        services = ApiClient.createService(WebServices.class, Utils.getToken(context),Utils.getUserId(context));
        return services.getCampaignListOfferByPincode(pincode, age, gender);
    }


    public Single<Campaign> getCampaignFillerByPincode(String pincode) {
        services = ApiClient.createService(WebServices.class, Utils.getToken(context),Utils.getUserId(context));
        return services.getCampaignListFillerByPincode(pincode);
    }

    public Single<CountryModel> getCountryModel() {
        services = ApiClient.createService(WebServices.class, Utils.getToken(context),Utils.getUserId(context));
        return services.getCountry();
    }

    public Single<CityModel> getCityModel() {
        services = ApiClient.createService(WebServices.class, Utils.getToken(context),Utils.getUserId(context));
        return services.getCity();
    }

    public Single<StateModel> getStateModel() {
        services = ApiClient.createService(WebServices.class, Utils.getToken(context),Utils.getUserId(context));
        return services.getState();
    }

    public Single<PincodeModel> getPincodeModel() {
        services = ApiClient.createService(WebServices.class, Utils.getToken(context),Utils.getUserId(context));
        return services.GetPincode();
    }

    public Single<Map> getTime() {
        services = ApiClient.createService(WebServices.class, Utils.getToken(context),Utils.getUserId(context));
        return services.GetTime();
    }


    public Single<VideoLogResponse> setVideoLogJson(String videoLog) {
        services = ApiClient.createService(WebServices.class, Utils.getToken(context),Utils.getUserId(context));
        return services.addVideoLogJson(videoLog);
    }


 public Single<OfferLogResponse> setOfferLogJson(String offerLog) {
        services = ApiClient.createService(WebServices.class, Utils.getToken(context),Utils.getUserId(context));
        return services.addOfferLogJson(offerLog);
    }


    /*public Single<ProductResponse> getProduct() {
        services = ApiClient.createService(WebServices.class, Utils.getToken(context),Utils.getUserId(context));
        return services.getProduct();
    }

    public Single<Address> updateAddress(String address_id, String user_id, String address_name, String phone, String address_type, String address_line1, String address_line2, String landmark, String city, String state, String country, String pincode) {
        services = ApiClient.createService(WebServices.class, Utils.getToken(context),Utils.getUserId(context));
        return services.updateAddress(address_id , user_id , address_name ,  phone ,  address_type ,  address_line1 ,  address_line2 ,  landmark ,  city , state , country,  pincode);
    }

    public Single<Coupon> applyCoupon(String coupon_code) {
        services = ApiClient.createService(WebServices.class, Utils.getToken(context),Utils.getUserId(context));
        return services.applyCoupon(coupon_code);
    }

    public Single<SubscriptionModifiedData> getSubscriptionProduct(String userId) {
        services = ApiClient.createService(WebServices.class, Utils.getToken(context),Utils.getUserId(context));
        return services.getSubscriptionOrder(userId);
    }

    public Single<OrderSubscription> getSubscription(String userId) {
        services = ApiClient.createService(WebServices.class, Utils.getToken(context),Utils.getUserId(context));
        return services.getSubscription(userId);
    }*/








    /*public Single<ResponseModel> proceedToCheckout(String userId, int couponId, int parseInt, String repeat_status_id, int addressId, String repeat, String order_status, String order_payment_mode, String order_payment_status, String orderDate, String total, String subtotal, String coupon_savings_amount, String startDate, String quantity, String doorBell) {
        services = ApiClient.createService(WebServices.class, Utils.getToken(context),Utils.getUserId(context));
        return services.proceedToCheckout(userId,  couponId,  parseInt,  repeat_status_id,  addressId,  repeat,  order_status,  order_payment_mode,  order_payment_status,  orderDate,  total ,  subtotal,  coupon_savings_amount ,  startDate,  quantity,  doorBell);
    }

    public Single<Coupon> couponUsed(int coupon_id, String userId, int orderId, String orderDate, String couponValue) {
        services = ApiClient.createService(WebServices.class, Utils.getToken(context),Utils.getUserId(context));
        return services.couponUsed(coupon_id,  userId,  orderId,  orderDate,  couponValue);
    }

    public Single<Address> getAddress(String userId) {
        services = ApiClient.createService(WebServices.class, Utils.getToken(context),Utils.getUserId(context));
        return services.getAddress(userId);
    }*/
}
