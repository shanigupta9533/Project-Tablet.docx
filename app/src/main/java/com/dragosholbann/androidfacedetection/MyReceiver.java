package com.dragosholbann.androidfacedetection;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.PowerManager;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.vision.CameraSource;

import java.io.IOException;

import static com.dragosholbann.androidfacedetection.GraphicFaceTracker.MISING_FLAG;
import static com.dragosholbann.androidfacedetection.GraphicFaceTracker.screen_off_flag;
import static com.dragosholbann.androidfacedetection.TempClass.currentPosition;
import static com.dragosholbann.androidfacedetection.TempClass.mCameraSource;
import static com.dragosholbann.androidfacedetection.TempClass.mGraphicOverlay;
import static com.dragosholbann.androidfacedetection.TempClass.mPreview;
import static com.dragosholbann.androidfacedetection.TempClass.myFaceDetector;
import static com.dragosholbann.androidfacedetection.TempClass.power_video_flag;
import static com.dragosholbann.androidfacedetection.TempClass.videoView;

/*import static com.dragosholbann.androidfacedetection.VideoFaceDetectionActivity.currentPosition;
import static com.dragosholbann.androidfacedetection.VideoFaceDetectionActivity.mCameraSource;
import static com.dragosholbann.androidfacedetection.VideoFaceDetectionActivity.mGraphicOverlay;
import static com.dragosholbann.androidfacedetection.VideoFaceDetectionActivity.mPreview;
import static com.dragosholbann.androidfacedetection.VideoFaceDetectionActivity.myFaceDetector;
import static com.dragosholbann.androidfacedetection.VideoFaceDetectionActivity.power_video_flag;
import static com.dragosholbann.androidfacedetection.VideoFaceDetectionActivity.videoView;*/

public class MyReceiver extends BroadcastReceiver {
    static int countPowerOff=0;
    private Activity activity=null;
    public MyReceiver (Activity activity)
    {
        this.activity=activity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        power_video_flag = true;
        //intent.addFlags(Intent.FLAG_RECEIVER_FOREGROUND);
       // activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        Log.e("onReceive", "Power button is pressed..... MISING_FLAG = "+MISING_FLAG+", screen_off_flag = "+screen_off_flag);


        /*Log.e("onReceive","================= Duration = "+videoView.getDuration());
        Log.e("onReceive","================= Current Position = "+videoView.getCurrentPosition());

        currentPosition = videoView.getCurrentPosition();
*/
        /*if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF))
        {*/
            /*KeyguardManager km = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
            final KeyguardManager.KeyguardLock kl = km .newKeyguardLock("MyKeyguardLock");
            kl.disableKeyguard();*/

        if(intent.getAction().equals(Intent.ACTION_SCREEN_OFF) && !MISING_FLAG)
        {
            Log.e("onReceive", "... inside ...      MISING_FLAG = "+MISING_FLAG);

            PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        @SuppressLint("InvalidWakeLockTag") PowerManager.WakeLock wakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK
                | PowerManager.ACQUIRE_CAUSES_WAKEUP, "MyWakeLock");
                wakeLock.acquire(30*60*1000L );//5 Mins

            /*PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);

            @SuppressLint("InvalidWakeLockTag") PowerManager.WakeLock wakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK
                    | PowerManager.ACQUIRE_CAUSES_WAKEUP
                    , "MyWakeLock");
            wakeLock.acquire(30*60*1000L );//5 Mins*/

            // You can use your own settings for CameraSource
            mCameraSource = new CameraSource.Builder(context, myFaceDetector)
                    .setRequestedPreviewSize(1280, 720)
                    .setAutoFocusEnabled(true)
                    .setFacing(CameraSource.CAMERA_FACING_FRONT)
                    .setRequestedFps(10.0f)
                    .build();

            if (mCameraSource != null) {
                try {
                    mPreview.start(mCameraSource, mGraphicOverlay);

                } catch (IOException e) {
                    //   if(LogFlag)  Log.e(TAG, "Unable to start camera source.", e);
                    mCameraSource.release();
                        mCameraSource = null;
                }
            }
            else
                Log.e("MyReceiver", "102 ------------------- mCameraSource NULL NULL");

            countPowerOff++;
        }
        else {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {

        /*Log.e("onReceive","================= Duration = "+videoView.getDuration());
        Log.e("onReceive","================= Current Position = "+videoView.getCurrentPosition());

        currentPosition = videoView.getCurrentPosition();
*/
        /*if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF))
        {*/
            /*KeyguardManager km = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
            final KeyguardManager.KeyguardLock kl = km .newKeyguardLock("MyKeyguardLock");
            kl.disableKeyguard();*/


                    Log.e("onReceive", "... ELSE ...      MISING_FLAG = "+MISING_FLAG);

                    PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
                    @SuppressLint("InvalidWakeLockTag") PowerManager.WakeLock wakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK
                            | PowerManager.ACQUIRE_CAUSES_WAKEUP, "MyWakeLock");
                    wakeLock.acquire(30*60*1000L );//5 Mins

            /*PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);

            @SuppressLint("InvalidWakeLockTag") PowerManager.WakeLock wakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK
                    | PowerManager.ACQUIRE_CAUSES_WAKEUP
                    , "MyWakeLock");
            wakeLock.acquire(30*60*1000L );//5 Mins*/

                    // You can use your own settings for CameraSource
                    mCameraSource = new CameraSource.Builder(context, myFaceDetector)
                            .setRequestedPreviewSize(1280, 720)
                            .setAutoFocusEnabled(true)
                            .setFacing(CameraSource.CAMERA_FACING_FRONT)
                            .setRequestedFps(10.0f)
                            .build();

                    if (mCameraSource != null) {
                        try {
                            mPreview.start(mCameraSource, mGraphicOverlay);

                        } catch (IOException e) {
                            //   if(LogFlag)  Log.e(TAG, "Unable to start camera source.", e);
                            mCameraSource.release();


                            mCameraSource = null;

                        }
                    }
                    else
                        Log.e("MyReceiver", "159 ------------------- mCameraSource NULL NULL");
            }
        }



        /*}
        else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON))
        {
        }*/

    }
}