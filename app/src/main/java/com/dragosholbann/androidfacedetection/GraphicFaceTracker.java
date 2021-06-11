package com.dragosholbann.androidfacedetection;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.PowerManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Tracker;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;
import java.io.IOException;
import java.util.Timer;

import static com.dragosholbann.androidfacedetection.SplashActivity.compName;
import static com.dragosholbann.androidfacedetection.SplashActivity.deviceManger;
import static com.dragosholbann.androidfacedetection.TempClass.currentPosition;
import static com.dragosholbann.androidfacedetection.TempClass.mCameraSource;
import static com.dragosholbann.androidfacedetection.TempClass.mGraphicOverlay;
import static com.dragosholbann.androidfacedetection.TempClass.mPreview;
import static com.dragosholbann.androidfacedetection.TempClass.myFaceDetector;
import static com.dragosholbann.androidfacedetection.TempClass.videoView;

/*import static com.dragosholbann.androidfacedetection.VideoFaceDetectionActivity.currentPosition;
import static com.dragosholbann.androidfacedetection.VideoFaceDetectionActivity.mCameraSource;
import static com.dragosholbann.androidfacedetection.VideoFaceDetectionActivity.mGraphicOverlay;
import static com.dragosholbann.androidfacedetection.VideoFaceDetectionActivity.mPreview;
import static com.dragosholbann.androidfacedetection.VideoFaceDetectionActivity.myFaceDetector;
import static com.dragosholbann.androidfacedetection.VideoFaceDetectionActivity.videoView;*/

class GraphicFaceTracker extends Tracker<Face> {
    View vvvv;

    Thread thread;
    private static final String TAG = "GraphicFaceTracker___";
    boolean LogFlag = true;
    static Context mContext;
    private GraphicOverlay mOverlay;
    private FaceGraphic mFaceGraphic;
    static Boolean videoPaused = false,timer_flag = false,startTimerFlag =false,faceDetectFlag,
            screen_off_flag=false,cancellation_flag=false,oreo = false, NO_DATA1 = false,MISING_FLAG=false,play_flag = false;
    static int stopPosition=0,faceID=1100;
    Timer timer;
    int disconnectCounter=0;
    public static CountDownTimer countDownTimerOreo,countDownTimerVideoLogData;
    public static CountDownTimer countDownTimer2;
    PowerManager.WakeLock wakeLock;
    Activity activityRef;

    //   MainActivity mainActivity;

    GraphicFaceTracker(GraphicOverlay overlay, Context context, Activity mActivity) {
       // Log.e("GraphicFaceTracker","_________________________GraphicFaceTracker_________________________");

        mContext = context;
        mOverlay = overlay;
        mFaceGraphic = new FaceGraphic(overlay);
        activityRef = mActivity;
        vvvv = activityRef.findViewById(R.id.vvvv);
        //ss = new Fragment1();
        //  mainActivity = new MainActivity();
    }

    /**
     * Start tracking the detected face instance within the face overlay.
     */
    @Override
    public void onNewItem(int faceId, Face item) {


     //   Log.e(TAG, "deleteCampaignItemById  new Item __________ "+item.getWidth());
       // if (item.getWidth() >= 150) {
            faceID = faceId;
     //   Log.e("GraphicFaceTracker","onNewItem________________________________________________________________");


        MISING_FLAG = false;
            if (screen_off_flag)
                cancellation_flag = true;

            mFaceGraphic.setId(faceId);

            if (countDownTimer2 != null)
                countDownTimer2.cancel();

//        // When 1) Face Defocus -> Focus  2)Screen Off - No
//        if(faceDetectFlag!=null)
//        {
//            if(!faceDetectFlag&&screen_off_flag)
//                startTimerFlag = false;
//            /*else if((!faceDetectFlag&&!screen_off_flag)&&startTimerFlag)
//                startTimerFlag=false;*/
//        }
//        // When 1) New Face Detected  2)Screen On/Off - Yes
            faceDetectFlag = true;
            //startTimerFlag = false;

            // Log.e("onNewItem","___________ faceId = "+faceId+" __________________________onNewItem");
            //    mainActivity.methodnew();

       //}
    }

    /**
     * Update the position/characteristics of the face within the overlay.
     */
    @Override
    public void onUpdate(FaceDetector.Detections<Face> detectionResults, Face face) {
       // Log.e("GraphicFaceTracker","onUpdate________________________________________________________________");

    //    Log.e(TAG, "deleteCampaignItemById  ONUPdate Item __________ "+face.getWidth());
        if (face.getWidth() >= 150) {

            vvvv.post(new Runnable() {
                @Override
                public void run() {
                    vvvv.setBackgroundResource(R.drawable.rounded_green_whole);
                }
            });

            if (videoView.isPlaying()) {
                currentPosition = videoView.getCurrentPosition();
                //Log.e("GraphicFaceTracker","onUpdate________currentPosition = "+currentPosition+"_________________ "+face.getId()+" _________________________onUpdate Paused = "+videoPaused+", Prepared = "+VideoFaceDetectionActivity.video_prepared);
            }

            if (LogFlag)
                // Log.e("GraphicFaceTracker","onUpdate_________________________ "+face.getId()+" _________________________onUpdate Paused = "+videoPaused+", Prepared = "+VideoFaceDetectionActivity.video_prepared);
                MISING_FLAG = false;

            if (TempClass.countDownTimerScratchFACEOFF != null) {
                TempClass.countDownTimerScratchFACEOFF.cancel();
                // Log.e(TAG,"countDownTimerScratch FACEOFF .......  CANCEL ");
                TempClass.countDownTimerScratchFACEOFF = null;
            }

           /* if (VideoFaceDetectionActivity.countDownTimerScratchFACEOFF != null) {
                VideoFaceDetectionActivity.countDownTimerScratchFACEOFF.cancel();
                // Log.e(TAG,"countDownTimerScratch FACEOFF .......  CANCEL ");
                VideoFaceDetectionActivity.countDownTimerScratchFACEOFF = null;
            }*/

            if (countDownTimer2 != null)
                countDownTimer2.cancel();


            mOverlay.add(mFaceGraphic);
            mFaceGraphic.updateFace(face);

        /*if(VideoFaceDetectionActivity.videoView!=null)
        {
            if(VideoFaceDetectionActivity.video_prepared)
            {
                //Log.e("onUpdate","video_prepared ===========2=============== onUpdate Paused = "+videoPaused+", Prepared = "+VideoFaceDetectionActivity.video_prepared);

                if(VideoFaceDetectionActivity.videoView.isPlaying())
                {
                    if(LogFlag)   Log.e("GraphicFaceTracker","onUpdate________ faceDetectFlag_________________ "+face.getId());
                    faceDetectFlag = true;
                }

                if (videoPaused)
                {*/
            //Log.e("onUpdate","videoPaused ===========3=============== onUpdate Paused = "+videoPaused+", Prepared = "+VideoFaceDetectionActivity.video_prepared);

            if (screen_off_flag) {
                //Log.e("onUpdate","faceDetectFlag =============4============= onUpdate Paused = "+videoPaused+", Prepared = "+VideoFaceDetectionActivity.video_prepared);

                activityRef.runOnUiThread(new Runnable() {

                    @SuppressLint("InvalidWakeLockTag")
                    @Override
                    public void run() {


                        //Log.e("setKeyguardDisabled","______setKeyguardDisabled____________________setKeyguardDisabled_________setKeyguardDisabled = "+videoPaused+", Prepared = "+VideoFaceDetectionActivity.video_prepared);

                        //activityRef.getWindow().addFlags(android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                        boolean active = deviceManger.isAdminActive(compName);
//                            if (active) {
//                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                                    deviceManger.setKeyguardDisabled(compName,true);
//                                }
//
//                            }
                                /*if(VideoFaceDetectionActivity.mCameraSource==null)
                                {
                                    Log.e(TAG,"______mCameraSource________  NULL   NULL   NULL  ____________mCameraSource_________mCameraSource");

                                    // You can use your own settings for CameraSource
                                    VideoFaceDetectionActivity.mCameraSource = new CameraSource.Builder(mContext, myFaceDetector)
                                            .setRequestedPreviewSize(1280, 720)
                                            .setAutoFocusEnabled(true)

                                            .setFacing(CameraSource.CAMERA_FACING_FRONT)
                                            .setRequestedFps(10.0f)

                                            .build();
                                }
                                else
                                {
                                    Log.e(TAG,"______mCameraSource________  WORKING  WORKING  WORKING ____________mCameraSource_________mCameraSource");

                                }*/

                        KeyguardManager km = (KeyguardManager) mContext.getSystemService(Context.KEYGUARD_SERVICE);
                        final KeyguardManager.KeyguardLock kl = km.newKeyguardLock("MyKeyguardLock");
                        kl.disableKeyguard();

                        PowerManager pm = (PowerManager) mContext.getSystemService(Context.POWER_SERVICE);
                        PowerManager.WakeLock wakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK
                                | PowerManager.ACQUIRE_CAUSES_WAKEUP
                                | PowerManager.ON_AFTER_RELEASE, "MyWakeLock");
                        wakeLock.acquire(30 * 60 * 1000L);//5 Mins
                        screen_off_flag = false;

                        // You can use your own settings for CameraSource
                        mCameraSource = new CameraSource.Builder(mContext, myFaceDetector)
                                .setRequestedPreviewSize(1280, 720)
                                .setAutoFocusEnabled(true)

                                .setFacing(CameraSource.CAMERA_FACING_FRONT)
                                .setRequestedFps(30.0f)
                                .build();

                        if (mCameraSource != null) {
                            try {
                                mPreview.start(mCameraSource, mGraphicOverlay);

                            } catch (IOException e) {
                                Toast.makeText(mContext, "mCameraSource NULL NULL", Toast.LENGTH_SHORT).show();
                                Log.e(TAG, "???????????????????????????????????????????????????????????????Unable to start camera source.", e);


                                mCameraSource.release();
                                  if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
                                      mCameraSource = null;
                                                }

                            }
                        }
                        oreo = true;
                        countDownTimerOreo = new CountDownTimer(3000, 3000) {
                            @Override
                            public void onTick(long millisUntilFinished) {

                            }

                            @Override
                            public void onFinish() {
                                oreo = false;
                            }
                        };

                        countDownTimerOreo.start();
                        play_flag = true;
                        videoPaused = false;
                        Log.e("GraphicFaceTracker", "onUpdate - MISING_FLAG =" + MISING_FLAG + "  __________________________________");

                    }
                });

                //startTimerFlag = false;
            } else {
                if (startTimerFlag) {
                    //  Log.e("GraphicFaceTracker","onUpdate - 224  __________________________________");
                    //if(countDownTimer2!=null)
                    if(countDownTimer2!=null)
                    countDownTimer2.cancel();
                    startTimerFlag = false;
                }
            }
            // Resumes the video on Face Track appear
                /*}
            }
        }*/   // Log.d("aaaaa", "create visible ");
            //   ss.startTimer();

        }
    }

    /**
     * Hide the graphic when the corresponding face was not detected.  This can happen for
     * intermediate frames temporarily (e.g., if the face was momentarily blocked from
     * view).
     */
    @Override
    public void onMissing(FaceDetector.Detections<Face> detectionResults) {
       // if(LogFlag)  Log.e("GraphicFaceTracker","onMissing __________________________________"+faceID+" isPlaying  *  onMissing  * isPlaying  *  onMissing  *  OREO = "+oreo+"  *  isPlaying");

        if(!oreo)
        {
            faceDetectFlag = false;
            mOverlay.remove(mFaceGraphic);
            if(TempClass.video_prepared) {
                if (TempClass.videoView.isPlaying()) {
                     if (!startTimerFlag)
                    {
                     //   Log.e("GraphicFaceTracker","start_my_timer  %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%__________________________");
                       //if(countDownTimer2==null)
                        start_my_timer();
                    }
                }
            }

            /*if(VideoFaceDetectionActivity.video_prepared) {
                if (VideoFaceDetectionActivity.videoView.isPlaying()) {
                    if (!startTimerFlag)
                    {
                        Log.e("GraphicFaceTracker","start_my_timer  %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%__________________________");
                        //if(countDownTimer2==null)
                        start_my_timer();
                    }
                }
            }*/
        }

       // Log.e("onMissing","__________________  ________________________________onMissing");
    }

    private void  start_my_timer()
    {
        startTimerFlag = true;

        activityRef.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                vvvv.post(new Runnable() {
                    @Override
                    public void run() {
                        vvvv.setBackgroundResource(R.drawable.rounded_whole);
                    }
                });
                // Do Work
                        /*Settings.System.putInt(activityRef.getContentResolver(),
                                Settings.System.SCREEN_OFF_TIMEOUT, 0);*/

                countDownTimer2 = new CountDownTimer(45000, 45000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
//                                if(!video_prepared)
//                                {
//                                    screen_off_flag = false;
//                                }

                        // Log.e("onTick","___>______>________< 2 >___________ "+millisUntilFinished+" >_________>_______onTick");

                                /*if(videoPaused) {
                                    if (wakeLock != null) {
                                        if (wakeLock.isHeld())
                                            wakeLock.release();
                                        wakeLock = null;
                                    }
                                    android.provider.Settings.System.putInt(mContext.getContentResolver(),
                                            Settings.System.SCREEN_OFF_TIMEOUT, 10);
                                    faceDetectFlag2 = false;
                                    screen_off_flag = false;

                                }*/
                    }

                    @Override
                    public void onFinish() {

                        //Log.e("onFinish", " ______________ faceDetectFlag = "+faceDetectFlag+" ________________________________onMissing");
                        if (faceDetectFlag != null){
                            if (!faceDetectFlag) {
                                if (!LogFlag) {
                                }

                                MISING_FLAG = true;
                                //stopPosition = VideoFaceDetectionActivity.videoView.getCurrentPosition(); //stopPosition is an int
                                //int remaining_duration =VideoFaceDetectionActivity.videoView.getDuration()-stopPosition;
                                Log.e("onFinish", "REM DURR =  _____ MISING_FLAG = " + MISING_FLAG + "_________ faceDetectFlag = FALSE ________________________________onMissing");

                            /*VideoFaceDetectionActivity.videoView.pause();
                            screen_off_flag = true;
                            videoPaused = true;*/

                                // Pauses the video on Face Track gone

                                //Toast.makeText(mContext, "on Finish", Toast.LENGTH_SHORT).show();
                                //timer_flag = false;

                                countDownTimer2.cancel();
                            } else if (!screen_off_flag) {
                                //  if(LogFlag)   Log.e("onFinish","__________________  faceDetectFlag = TRUE ________________________________onMissing");
                                if (countDownTimer2 == null)
                                    startTimerFlag = false;

                            }

                    }
                    }
                };
                countDownTimer2.start();
                //                                   3 min = 180 sec timer     Interval = 20sec
                }

        });
        //Screen_Timer.schedule(Screen_Timer_Task, 1, 1);
    }


    /**
     * Called when the face is assumed to be gone for good. Remove the graphic annotation from
     * the overlay.
     */
    @Override
    public void onDone() {

        mOverlay.remove(mFaceGraphic);
        //if(LogFlag)  Log.e("GraphicFaceTracker","onDone______________________"+faceID+"____________________________onDone");

    }

}
