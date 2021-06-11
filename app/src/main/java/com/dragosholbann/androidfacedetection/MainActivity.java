package com.dragosholbann.androidfacedetection;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.dragosholbann.androidfacedetection.ApiModels.VideoPaths;
import com.dragosholbann.androidfacedetection.ApiModels.VideolistItem;
import com.dragosholbann.androidfacedetection.ApiModels.Videos;
import com.dragosholbann.androidfacedetection.DBHelper.MyDB;
import com.dragosholbann.androidfacedetection.Interfaces.RetrofitInterface;
import com.dragosholbann.androidfacedetection.NetworkCheck.CheckNetwork;
import com.dragosholbann.androidfacedetection.RetrofitClients.RetrofitClient;
import com.dragosholbann.androidfacedetection.RetrofitClients.RetrofitClientVideo;
import com.dragosholbann.androidfacedetection.ServerConnect.ServerConnect;
import com.dragosholbann.androidfacedetection.SharedPref.SessionManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements Serializable {

    String mCurrentPhotoPath;
    String name_;
    String[] new_urls;
    static Toast toast;
    Context mContext;
    public  ArrayList<VideolistItem> video_link;
    public  ArrayList<VideoPaths> video_path;
    VideoPaths vp;
    Boolean return_flag = false,previousDownload = false;
     static int j=0,k=0;
        MyDB myDB;
    private static final String TAG = "MAIN ACTIVITY ___";
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_PERMISSION_WRITE_EXTERNAL_STORAGE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = MainActivity.this;
        myDB = new MyDB(mContext);

        methodnew();
        video_path = new ArrayList<>();
         vp = new VideoPaths();



    }

    /*public void onImageFromCameraClick(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG);
            }

            if (photoFile != null) {
                Uri photoUri = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".fileprovider", photoFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }*/

    private File createImageFile() throws IOException {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // permission not granted, initiate request
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSION_WRITE_EXTERNAL_STORAGE);
        } else {
            // Create an image file name
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String imageFileName = "JPEG_" + timeStamp + "_";
            File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            File image = File.createTempFile(
                    imageFileName,  /* prefix */
                    ".jpg",         /* suffix */
                    storageDir      /* directory */
            );
            mCurrentPhotoPath = image.getAbsolutePath(); // save this to use in the intent

            return image;
        }

        return null;
    }

    public void methodnew(){
        String a = "";
        //for (int i= 0 ; i<10;i++){

        //}
        Log.d("AAA", "methodnew: ");
        //toast= Toast.makeText(MainActivity.this,"Hello Javatpoint",Toast.LENGTH_SHORT); toast.setMargin(50,50); toast.show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Intent intent = new Intent(this, FaceDetectionActivity.class);
            intent.putExtra("mCurrentPhotoPath", mCurrentPhotoPath);
            startActivity(intent);
        }
    }

    public void onVideoFromCameraClick(View view) {
        if(CheckNetwork.isInternetAvailable(getApplicationContext()))
        {
            Intent intent = new Intent(this, TempClass.class);
          //  Intent intent = new Intent(this, VideoFaceDetectionActivity.class);
            //To pass:
            /*intent.putExtra("MyClass", video_path);*/
            /*Bundle bundle = new Bundle();
            bundle.putSerializable("value", video_path);
            intent.putExtras(bundle);*/
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }

    }

    public void downloadVideos(View view)
    {
    if(CheckNetwork.isInternetAvailable(mContext))
    {

        Retrofit retrofit = RetrofitClient.getInstance();

         RetrofitInterface downloadService =
                retrofit.create(RetrofitInterface.class);


        retrofitCall(downloadService);


Thread thread = new Thread() {
            public void run() {

                Looper.prepare();

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        // Do Work
                        if(return_flag)
                        {

                               new_urls = new String[video_link.size()];
                            for(int i=0; i<new_urls.length;i++)
                            {
                                //this.name = new_urls[i];
                               // Log.e(TAG,"____________ ["+j+"] ____________ VIDEO NAME = "+video_link.get(j).getVideoUrl());

                            /*OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
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

                            Retrofit retrofit2 = new Retrofit.Builder()
                                    .baseUrl(ServerConnect.URL_VIDEO)
                                    .addConverterFactory(GsonConverterFactory.create(gson))
                                    //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                                    .client(httpClient)
                                    .build();*/
                            /*if(j==k)
                            {*/

                                new_urls[i] = video_link.get(i).getVideoUrl()/*.replace("https://ensivosolutions.com/", "")*/;


                            Retrofit retrofit2 = RetrofitClientVideo.getInstance();

                            RetrofitInterface downloadService2 =
                                    retrofit2.create(RetrofitInterface.class);

                            Call<ResponseBody> call = downloadService2.downloadFileWithDynamicUrlAsync(new_urls[i]);
                            call.enqueue(new Callback<ResponseBody>() {
                                @SuppressLint("StaticFieldLeak")
                                @Override
                                public void onResponse(@NonNull Call<ResponseBody> call, @NonNull final Response<ResponseBody> response) {

                                    if (response.isSuccessful()) {
                                        Log.e(TAG, "server 2 contacted and has file");
                                       /* do
                                        {*/
                                            new AsyncTask<Void, Void, Void>() {
                                                @Override
                                                protected Void doInBackground(Void... voids) {
                                                    assert response.body() != null;
                                                    boolean writtenToDisk = writeResponseBodyToDisk(response.body());
                                                    if(writtenToDisk)
                                                       // Log.e(TAG, "MODEL PATH = " + video_path.get(j-1).getVideoPath());

                                                    Log.e(TAG, "Server 2 file download was a success ? " + writtenToDisk);
                                                    return null;
                                                }

                                                @Override
                                                protected void onProgressUpdate(Void... values) {

                                                }


                                            }.execute();
                                        /*} while (previousDownload);*/

                                    }
                                    else {
                                        Log.e(TAG, "server 2 contact failed");
                                    }
                                }

                                @Override
                                public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                                    Log.e(TAG, "Server 2 error");
                                }
                            });
                        }

                        handler.removeCallbacks(this);
                        Looper.myLooper().quit();
                    }
                    }

                }, 2000);

                Looper.loop();
            }
        };
        thread.start();

    }
    else
        Toast.makeText(mContext, "No Internet Connection", Toast.LENGTH_SHORT).show();
    }


    public  void retrofitCall(RetrofitInterface downloadService)
    {
        Call<Videos> call = downloadService.getVideoList();
        call.enqueue(new Callback<Videos>() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public void onResponse(@NonNull Call<Videos> call, @NonNull final Response<Videos> response) {

                if (response.isSuccessful()) {
                    Log.e(TAG, "server 1 contacted and has file");

                    if(response.body()!=null)
                    {
                        Videos videos = response.body();
                        video_link = new ArrayList<>();
                        video_link.addAll(videos.getVideolist());
                        return_flag = true;
                    }
                    else
                    {
                        return_flag = false;
                    }
                }
                else {
                    Log.e(TAG, "server 1 contact failed");
                    return_flag = false;
                }
            }

            @Override
            public void onFailure(@NonNull Call<Videos> call, @NonNull Throwable t) {
                Log.e(TAG, "Server 1 error");
                return_flag = false;
            }
        });
    }


    private boolean writeResponseBodyToDisk(ResponseBody body) {

        try {
            //Log.e(TAG," ____________ FILE NAME = "+new_urls[i]);
            final int min = 20;
            final int max = 8000;
            final int random = new Random().nextInt((max - min) + 1) + min;

            // todo change the file location/name according to your needs
            File imageFile = new File(Environment.getExternalStorageDirectory()
                    + File.separator + video_link.get(j).getVideoUrl()/*"MadAds"+random+".mp4"*/);

            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(imageFile);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);

                    fileSizeDownloaded += read;

                    Log.e(TAG, "file download: "+imageFile.getName() +" >>> "+ fileSizeDownloaded + " of " + fileSize);
                }

                outputStream.flush();
                previousDownload = true;
                name_ = imageFile.getAbsolutePath();
                /*myDB.createRecords(video_link.get(j).getVideoListId(),
                        video_link.get(j).getVideoName(),Environment.getExternalStorageDirectory()+"/"+video_link.get(j).getVideoUrl());*/
                /*vp.setVideoName(video_link.get(j).getVideoUrl());
                vp.setVideoPath(name_);
                video_path.add(vp);*/

                j++;
                return true;
            } catch (IOException e) {
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }

                Log.e(TAG, "file path : " + imageFile.getAbsolutePath());
            }
        } catch (IOException e) {
            return false;
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        j = 0;
    }
}
