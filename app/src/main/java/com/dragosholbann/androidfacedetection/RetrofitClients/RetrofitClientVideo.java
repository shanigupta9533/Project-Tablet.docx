package com.dragosholbann.androidfacedetection.RetrofitClients;

import android.support.annotation.NonNull;

import com.dragosholbann.androidfacedetection.ServerConnect.ServerConnect;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientVideo {

   private static Retrofit retrofit = null;

       private RetrofitClientVideo(){

       }  //private constructor.

        public static Retrofit getInstance(){
           if (retrofit == null){

               //if there is no instance available... create new one

               OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
               httpClientBuilder.addInterceptor(new Interceptor() {
                   @Override
                   public okhttp3.Response intercept(@NonNull Chain chain) throws IOException {
                       Request.Builder requestBuilder = chain.request().newBuilder();
                       requestBuilder.header("Content-Type", "application/json");
                       requestBuilder.header("Accept", "application/json");
                       return chain.proceed(requestBuilder.build());
                   }
               });

               OkHttpClient httpClient = httpClientBuilder.build();

               Gson gson = new GsonBuilder()
                       .setLenient()
                       .create();


                retrofit = new Retrofit.Builder()
                       .baseUrl(ServerConnect.URL_VIDEO)
                       .addConverterFactory(GsonConverterFactory.create(gson))
                       //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                       .client(httpClient)
                       .build();
           }

           return retrofit;
       }




}
