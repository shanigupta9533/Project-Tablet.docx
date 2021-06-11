package com.dragosholbann.androidfacedetection.ModelLayer.NetworkLayer.Helpers;

import android.support.annotation.NonNull;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient
{
    //private static final String BASE_URL = "http://192.168.10.105:8080/ensivo/madads/Api_controller/";
    //private static final String BASE_URL = "http://madads.in/adminn/api_controller/";
    private static final String BASE_URL = "http://madads.in/maddy/Api_controller/";
    public static final String BASE_IMAGE_URL = "http://madads.in/maddy/assets/uploaded-images/";

    private static HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    private static Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    //region Handle Logging Interceptor
    static {
        httpClient.connectTimeout(30, TimeUnit.MINUTES) // connect timeout
                .writeTimeout(30, TimeUnit.MINUTES) // write timeout
                .readTimeout(30, TimeUnit.MINUTES); // read timeout

        //if (ApplicationConstant.DEBUG) {
        httpClient.addInterceptor(loggingInterceptor);
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //}
    }
    //endregion

    //region Init Retrofit Builder
    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()); //NOTE: Jon Bott - Add this line for RxRetrofit
    //endregion

    //region Init Service Without Auth
    public static <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass, null,null);
    }
    //endregion

    //region Init Service With Auth
    public static <S> S createService(Class<S> serviceClass, @NonNull String authToken, String userId) {
        if (authToken != null && userId !=null) {
            addRequestHeaders(authToken, userId);
        }

        Log.d("Auth Token:- ", " "+authToken);

        OkHttpClient client = httpClient.build();
        Retrofit retrofit = builder.client(client).build();
        return retrofit.create(serviceClass);
    }
    //endregion

    //region Init RequestHeaders
    public static void addRequestHeaders(final @NonNull String authToken , final @NonNull String userID) {
        httpClient.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Request.Builder builder = originalRequest.newBuilder()
                        .header("Authkey", authToken)
                        .header("Userid",userID)
                        .method(originalRequest.method(), originalRequest.body());

                builder.addHeader("Content-Type", "application/x-www-form-urlencoded");
                Request modifiedRequest = builder.build();
                return chain.proceed(modifiedRequest);
            }
        });
    }
    //endregion
}


