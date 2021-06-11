package com.dragosholbann.androidfacedetection;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.admin.DevicePolicyManager;
import android.app.admin.SystemUpdatePolicy;
import android.arch.lifecycle.ViewModelProviders;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.BatteryManager;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.UserManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.format.Formatter;
import android.text.method.KeyListener;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;


import com.bumptech.glide.Glide;
import com.dragosholbann.androidfacedetection.ApiModels.City;
import com.dragosholbann.androidfacedetection.ApiModels.Pincode;
import com.dragosholbann.androidfacedetection.DBHelper.MyDB;
import com.dragosholbann.androidfacedetection.Interfaces.RetrofitInterface;
import com.dragosholbann.androidfacedetection.ModelBetaFace2.FacesItem;
import com.dragosholbann.androidfacedetection.ModelBetaFace2.Media;
import com.dragosholbann.androidfacedetection.ModelBetaFace2.TagsItem;
import com.dragosholbann.androidfacedetection.ModelBetaFace2.ImageInfo;
import com.dragosholbann.androidfacedetection.ModelLayer.Entities.Campaign;
import com.dragosholbann.androidfacedetection.ModelLayer.Entities.CampaignOffer;
import com.dragosholbann.androidfacedetection.ModelLayer.Entities.CampaignlistItem;
import com.dragosholbann.androidfacedetection.ModelLayer.Entities.CampaignlistOfferItem;
import com.dragosholbann.androidfacedetection.ModelLayer.Entities.OfferLogItem;
import com.dragosholbann.androidfacedetection.ModelLayer.Entities.OfferLogResponse;
import com.dragosholbann.androidfacedetection.ModelLayer.Entities.Video;
import com.dragosholbann.androidfacedetection.ModelLayer.Entities.VideoLogItem;
import com.dragosholbann.androidfacedetection.ModelLayer.Entities.VideoLogResponse;
import com.dragosholbann.androidfacedetection.ModelLayer.Entities.VideolistItem;
import com.dragosholbann.androidfacedetection.NetworkCheck.CheckNetwork;
import com.dragosholbann.androidfacedetection.RetrofitClients.RetrofitClientVideo;
import com.dragosholbann.androidfacedetection.ServerConnect.ServerConnect;
import com.dragosholbann.androidfacedetection.constants.ApplicationConstant;
import com.dragosholbann.androidfacedetection.utils.MyProgressDialog;
import com.dragosholbann.androidfacedetection.utils.Utils;
import com.dragosholbann.androidfacedetection.viewModels.CabTabDetailsViewHolder;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.MultiProcessor;
import com.google.android.gms.vision.Tracker;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.StringTokenizer;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.dragosholbann.androidfacedetection.GraphicFaceTracker.MISING_FLAG;
import static com.dragosholbann.androidfacedetection.GraphicFaceTracker.NO_DATA1;
import static com.dragosholbann.androidfacedetection.GraphicFaceTracker.screen_off_flag;
import static com.dragosholbann.androidfacedetection.SplashActivity.compName;
import static com.dragosholbann.androidfacedetection.SplashActivity.deviceManger;

public class VideoFaceDetectionActivity extends AppCompatActivity implements android.location.LocationListener,KeyListener, Serializable {
    int final_avg = 0;
    ImageView iv_gif;
    FrameLayout fl_gif;
    MyReceiver mReceiver;
    public static int video_played_count = 0;
    ArrayList<String> videoIdsDeletion = new ArrayList<>();
    String videoIds;
    Boolean return_flag = false;
    ArrayList<VideolistItem> videosListItem;
    Boolean previousDownload = false;
    String name_;
    ArrayList<String> videoListDBNames;
    Call<ResponseBody> call;
    RetrofitInterface downloadService2;
    int l = 0;
    long millisUntilFINISH = 0;
    ScratchFragment scratchFragment;
    FrameLayout rl_scratch_card;
    private static HashSet<String> allCombinations = new HashSet<>();
    private static HashSet<String> selectedCombinations = new HashSet<>();
    //===================================================
    SeekBar seekBarVolume, seekBarBrightness;
    Boolean filler = false, countDownTimerFlag = true, newDataFlag = false, videoLogDataToServerTimerFlag = false, offerLogDataToServerTimerFlag = false, face_id_flag = false,
            DebugFlag = true, countDownTimerFlagVideoDwnld = false;
    ArrayList<CampaignlistItem> videoListDB;
    ArrayList<Integer> videoListDB_download;
    CampaignlistItem videoListDBSingle;
    ActivityManager activityManager;
    int durationVideo = 0;
    public static int currentPosition = 0;
    int face__id = 1010;
    private FrameLayout.LayoutParams paramsNotFullscreen; //if you're using RelativeLatout

    //------------------------------------------------------------------------------
    public static CameraPreview mPreview;
    static public GraphicOverlay mGraphicOverlay;
    static public CameraSource mCameraSource = null;
    String mCurrentPhotoPath = null, mCurrentPhotoPath2 = null;
    private static final String API_KEY = "YskQd7XpEw7OwHEk1AVlo3MEP1OzquE_";
    private static final String API_KEY_BETAFACE = "d45fd466-51e2-4701-8da8-04351c872236";
    private static final String API_SECRET = "on7785sbWUygqo801QDcYMZPkeWK0Tzh";
    private static final String TAG = "VideoFaceDetection___";
    private static final int REQUEST_CAMERA_PERMISSION = 1;
    CompositeDisposable bag = new CompositeDisposable();
    private static final int RC_HANDLE_GMS = 2;
    public static MainActivity mainActivity;
    ImageView imageView, imageView1;
    FrameLayout rel;
    int firstCount = 0;
    public static MyFaceDetector myFaceDetector;
    Bitmap bmFrame = null;
    Boolean completionFlag = true;
    static Boolean api_hit = true, video_prepared = false;
    static Context mContext;
    static VideoView videoView;
    static Double longitude = 0.0;
    static Double lattitude = 0.0;
    static String age_ = "25";
    static String gender_ = "male";
    /**
     * Constant used in the location settings dialog.
     */
    protected static final int REQUEST_CHECK_SETTINGS = 50;
    LocationManager lm;
    Activity activity;
    public static MediaPlayer mediaPlayer;
    int difference_global = 0, my_map_key = 111;
    Map<Integer, String> myMap = new HashMap<Integer, String>();

    private boolean ScratchTimerFLAG = false;
    private static int sWidth, sHeight;
    private Display display;
    private Point size;
    private final static int MAX_VOLUME = 100;
    AudioManager audioManager;
    TextView tv_volume;
    int filler_id = 0;
    String ip = "0.0.0.0";
    static MyDB myDB;
    static List<Integer> timeDiff;
    Date fromDate, toDate;
    int durationFeedbackTimer = 0;
    PopupWindow popupWindow;
    String video_review = "None";
    public static CountDownTimer countDownTimerScratchFACEOFF = null;
    CountDownTimer countDownTimerVideoDwnld, countDownTimer, countDownTimer2, countDownTimerVideoLogData, countDownTimerOfferLogData, countDownTimerScratch;
    CabTabDetailsViewHolder cabTabDetailsViewHolder;
    int count = 0;
    ArrayList<CampaignlistItem> campaignListItem, campaignListItemFiller, campaignListItemAdv;
    ArrayList<CampaignlistItem> campaignListItemSemiFinal, campaignListItemSemiFinal2, campaignListItemFinal, campaignListItemFinal2;

    ArrayList<CampaignlistOfferItem> campaignListItem_, campaignListItemOffer;
    ArrayList<CampaignlistOfferItem> campaignListItemOfferSemiFinal, campaignListItemOfferSemiFinal2, campaignListItemOfferFinal, campaignListItemOfferFinal2;

    int[] newCount, newCountOffer;
    LinkedHashMap<Integer, CampaignlistItem> campMap = new LinkedHashMap<>();
    LinkedHashMap<Integer, CampaignlistItem> campMap2 = new LinkedHashMap<>();

    LinkedHashMap<Integer, CampaignlistOfferItem> campMap2Offer = new LinkedHashMap<>();

    ArrayList<Integer> final_items_combiOne = new ArrayList<>();
    ArrayList<Integer> final_items_combiTwo = new ArrayList<>();
    ArrayList<CampaignlistItem> CombiOne = new ArrayList<>();
    ArrayList<CampaignlistItem> CombiTwo = new ArrayList<>();

    ArrayList<Integer> final_items_combiOneOffer = new ArrayList<>();
    ArrayList<Integer> final_items_combiTwoOffer = new ArrayList<>();

    Thread thread;
    String postalCode = SplashActivity.postalCode;
    ;
    int i = 0, cabDetailsId;
    private static HashSet<String> allSubsets = new HashSet<>();
    private static final String token = " ";

    //======================================================================================
//Variable to store brightness value
    private int brightness;
    //Content resolver used as a handle to the system's settings
    private ContentResolver cResolver;
    //Window object, that will store a reference to the current window
    private Window window;

    TextView tv_timer_count;
    PackageManager mPackageManager;
    public static boolean power_video_flag = false;
    //protected MyApplication app;
    static final int RESULT_ENABLE = 55;


    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            // Get the battery status indicator integer value
            int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);

            // Determine the battery charging status
            boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING
                    ||
                    status == BatteryManager.BATTERY_STATUS_FULL;

            // If battery is in charging states, then get the charging method
            if (isCharging) {

                int chargePlug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);


                boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
                if (usbCharge) {
                    // Toast.makeText(context, "Charging : USB Charging", Toast.LENGTH_SHORT).show();
                    // mTextView.setText(mTextView.getText()+"\nUSB Charging");
                }


                boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;
                if (acCharge) {
                    //  Toast.makeText(context, "Charging : AC Charging", Toast.LENGTH_SHORT).show();
                    // mTextView.setText(mTextView.getText()+"\nAC Charging");
                }


                boolean wirelessCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_WIRELESS;
                if (wirelessCharge) {
                    // Toast.makeText(context, "Charging : Wireless Charging", Toast.LENGTH_SHORT).show();
                    //  mTextView.setText(mTextView.getText()+"\nWireless Charging");
                }
            } else {
                // Display the battery charging state
                //   mTextView.setText("Charging : No.");
                //  Toast.makeText(context, "Charging : No", Toast.LENGTH_SHORT).show();
            }
        }
    };
    private boolean NO_DATA_FLAG;

    private void BrightnessInit() {
        //Get the content resolver
        cResolver = getContentResolver();
        //Get the current window
        window = getWindow();
        try {
            // To handle the auto
            Settings.System.putInt(cResolver,
                    Settings.System.SCREEN_BRIGHTNESS_MODE, Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
            //Get the current system brightness
            brightness = Settings.System.getInt(cResolver, Settings.System.SCREEN_BRIGHTNESS);

            seekBarBrightness.setMax(255);
            seekBarBrightness.setProgress(brightness);
        } catch (Settings.SettingNotFoundException e) {
            //Throw an error case it couldn't be retrieved
            Log.e("Error", "Cannot access system brightness");
            e.printStackTrace();
        }
    }

    @SuppressLint({"MissingPermission", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_face_detection);

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.flags |= WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
        params.screenBrightness = 0.9f;
        getWindow().setAttributes(params);

        getWindow().addFlags ((int) WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_FULL);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        activityManager = (ActivityManager) getSystemService(
                Context.ACTIVITY_SERVICE);
        mContext = getApplicationContext();
        initViewHolder();
        postalCode = SplashActivity.postalCode;
        postalCode = postalCode.replaceAll(" ","");



        // Get the application instance
        // app = (MyApplication)getApplication();

        // Initialize a new IntentFilter instance
        IntentFilter iFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);

        // Register the broadcast receiver
        mContext.registerReceiver(mBroadcastReceiver, iFilter);
        // Hide status bar

        Intent intent = new Intent(DevicePolicyManager
                .ACTION_ADD_DEVICE_ADMIN);
        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN,
                compName);
        intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,
                "Additional text explaining why this needs to be added.");
        startActivityForResult(intent, RESULT_ENABLE);

        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_video_face_detection);
        getScreenSize();
        mainActivity = new MainActivity();
        mPreview = findViewById(R.id.preview);
        imageView = findViewById(R.id.imageView);
        imageView1 = findViewById(R.id.imageView1);
        mGraphicOverlay = findViewById(R.id.faceOverlay);
        rel = findViewById(R.id.rel);
        videoView = findViewById(R.id.videoView1);
        if (sHeight < 850) {
            videoView.setLayoutParams(new FrameLayout.LayoutParams(
                    1280,
                    1080));
        } else {
            videoView.setLayoutParams(new FrameLayout.LayoutParams(
                    1280,
                    1080));
        }


        tv_volume = findViewById(R.id.tv_volume);
        tv_timer_count = findViewById(R.id.timer_count);
        seekBarVolume = findViewById(R.id.seekBarVolume);
        iv_gif = findViewById(R.id.iv_gif);
        fl_gif = findViewById(R.id.fl_gif);

        iv_gif.setVisibility(View.VISIBLE);
        fl_gif.setVisibility(View.VISIBLE);
        Glide.with(mContext)
                .load(R.drawable.logo_madads)
                .into(iv_gif);

        seekBarVolume.setMax(20);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            seekBarVolume.setMin(0);
        }
        seekBarVolume.setProgress(10);
        seekBarBrightness = findViewById(R.id.seekBarBrigtness);

        seekBarVolume.animate().alpha(0.2f).setDuration(100);
        seekBarBrightness.animate().alpha(0.2f).setDuration(100);
        mContext = VideoFaceDetectionActivity.this;
        myDB = new MyDB(mContext);

        /*getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);*/

        Display display = null;
        activity = VideoFaceDetectionActivity.this;
        audioManager = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
        audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC); //Max Volume 15
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            display = ((WindowManager) Objects.requireNonNull(getSystemService(WINDOW_SERVICE)))
                    .getDefaultDisplay();

            int orientation = Objects.requireNonNull(display).getRotation();
            if (orientation == Surface.ROTATION_90
                    || orientation == Surface.ROTATION_270) {
                if (DebugFlag)
                    Log.e(TAG, "Landscape ====================================================== ");
            }
        }

        cabDetailsId = myDB.getCabDetailsId();
        //runtimePermissions();
        createCameraSource();

        if (deviceManger.isAdminActive(compName)) {
            Toast.makeText(mContext, "Admin Active", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(mContext, "Admin NOT Active", Toast.LENGTH_SHORT).show();
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.System.canWrite(mContext)) {
                if (DebugFlag) Log.e("Else", "__________________Settings.System.canWrite(mContext");

                Intent intent2 = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                intent2.setData(Uri.parse("package:" + mContext.getPackageName()));
                intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent2);
            }
        }

        boolean active = deviceManger.isAdminActive(compName);
        if (active) {

            videoView.post(new Runnable() {
                @Override
                public void run() {
                    BrightnessInit();
                    rel.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            switch (event.getAction()) {
                                case MotionEvent.ACTION_DOWN:

                                    /*//touch is start
                                    downX = event.getX();
                                    downY = event.getY();
                                    if (event.getX() < (sWidth / 2)) {

                                        //here check touch is screen left or right side
                                        intLeft = true;
                                        intRight = false;

                                    } else if (event.getX() > (sWidth / 2)) {

                                        //here check touch is screen left or right side
                                        intLeft = false;
                                        intRight = true;
                                    }*/
                                    break;

                                case MotionEvent.ACTION_UP:

                                case MotionEvent.ACTION_MOVE:

                                   /* //finger move to screen
                                    float x2 = event.getX();
                                    float y2 = event.getY();

                                    diffX = (long) (Math.ceil(event.getX() - downX));
                                    diffY = (long) (Math.ceil(event.getY() - downY));

                                    if (Math.abs(diffY) > Math.abs(diffX)) {
                                        if (intLeft) {
                                            //if left its for brightness

                                            if (downY < y2) {
                                                //down swipe brightness decrease
                                            } else if (downY > y2) {
                                                //up  swipe brightness increase
                                            }

                                        } else if (intRight) {

                                            //if right its for audio
                                            if (downY < y2) {
                                                if(mediaPlayer != null){
                                                    float volume = (float) (1 - (Math.log(MAX_VOLUME - downY) / Math.log(MAX_VOLUME)));
                                                    // mediaPlayer.setVolume(20f, 20f);
                                                    audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

                                                    enableSound(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)-1,mediaPlayer);
                                                    if(DebugFlag) Log.e("onTouch"," ^^^^^^^^^^^^^^^^^^ down swipe volume decreasez ^^^^^^^^^^^^^^^^^^");
                                                    tv_volume.setVisibility(View.VISIBLE);
                                                    tv_volume.postDelayed(new Runnable() {
                                                        public void run() {
                                                            tv_volume.setVisibility(View.GONE);
                                                        }
                                                    }, 1000);
                                                }

                                                //down swipe volume decrease
                                                //mAudioManager.adjustVolume(AudioManager.ADJUST_LOWER, AudioManager.FLAG_PLAY_SOUND);

                                            } else if (downY > y2) {
                                                if(mediaPlayer != null) {
                                                    float volume = (float) (1 - (Math.log(MAX_VOLUME - downY) / Math.log(MAX_VOLUME)));
                                                    enableSound(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC) + 1, mediaPlayer);
                                                    if(DebugFlag) Log.e("onTouch", " ^^^^^^^^^^^^^^^^^^ up swipe volume increasez ^^^^^^^^^^^^^^^^^^");
                                                    tv_volume.setVisibility(View.VISIBLE);
                                                    tv_volume.postDelayed(new Runnable() {
                                                        public void run() {
                                                            tv_volume.setVisibility(View.GONE);
                                                        }
                                                    }, 1000);
                                                }
                                                //up  swipe volume increase
                                                //mAudioManager.adjustVolume(AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND);
                                            }
                                        }
                                    }*/
                            }
                            return true;
                        }
                    });

                }
            });

            seekBarVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                    if (mediaPlayer != null) {


                        float volume = (float) (1 + (Math.log(MAX_VOLUME - progress) / Math.log(progress)));
                        /*if(String.valueOf(volume).equals("Infinity"));
                        volume = 100;*/
                        Log.e("volume", " = " + volume + "                   progress = " + progress);

                        // mediaPlayer.setVolume(20f, 20f);
                        audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

                        enableSound((progress + 1), mediaPlayer);
                        //if(DebugFlag) Log.e("onTouch"," ^^^^^^^^^^^^^^^^^^ down swipe volume decreasez ^^^^^^^^^^^^^^^^^^");
                        /*tv_volume.setVisibility(View.VISIBLE);
                        tv_volume.postDelayed(new Runnable() {
                            public void run() {
                                tv_volume.setVisibility(View.GONE);
                            }
                        }, 1000);*/
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                    seekBarVolume.animate().alpha(0.9f).setDuration(100);
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    seekBarVolume.animate().alpha(0.2f).setDuration(1000);
                }
            });

            seekBarBrightness.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                    //Log.e("seekBarBrightness"," = "+progress);
                    //Set the system brightness using the brightness variable value
                    Settings.System.putInt(cResolver, Settings.System.SCREEN_BRIGHTNESS, progress);
                    //Get the current window attributes
                    WindowManager.LayoutParams layoutpars = window.getAttributes();
                    //Set the brightness of this window
                    layoutpars.screenBrightness = progress / (float) 255;
                    //Apply attribute changes to this window
                    window.setAttributes(layoutpars);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                    seekBarBrightness.animate().alpha(0.9f).setDuration(100);

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    seekBarBrightness.animate().alpha(0.2f).setDuration(1000);

                }
            });
        }
        // createHandler();

        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.SCREEN_ON");
        filter.addAction("android.intent.action.SCREEN_OFF");
        filter.setPriority(999);

        mReceiver = new MyReceiver(this);
        registerReceiver(mReceiver, filter);
        //claimOffer();
        // deactivateLTM();

        //addingCredentials();
        //  removingCredentials();
    }


   /* private void claimOffer(){

        claim_offer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDownTimerScratch.cancel();

                if (countDownTimerScratchFACEOFF == null) {
                    countDownTimerScratchFACEOFF = new CountDownTimer(10000, 10000) {
                        @Override
                        public void onTick(long millisUntilFinished) {

                        }

                        @Override
                        public void onFinish() {
                            Log.e(TAG, "countDownTimerScratchFACEOFF .......... onFinish");
                            MISING_FLAG = true;
                        }
                    };
                    countDownTimerScratchFACEOFF.start();
                }

                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mContext);
                // ...Irrelevant code for customizing the buttons and title
                LayoutInflater inflater = VideoFaceDetectionActivity.this.getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.pop_up_offer_consumed, null);
                dialogBuilder.setView(dialogView);

                final EditText editText = dialogView.findViewById(R.id.edt_mob);
                Button submit = dialogView.findViewById(R.id.buttonSubmit);
                Button cancel = dialogView.findViewById(R.id.buttonCancel);
                final AlertDialog alertDialog = dialogBuilder.create();
                alertDialog.setCanceledOnTouchOutside(false);
                //alertDialog.setMessage("AVAIL OFFER");

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        offerInsertLog();

                        claim_offer.setVisibility(View.GONE);

                        tv_timer_count.setText("");
                        tv_timer_count.setBackground(getResources().getDrawable(R.color.black));
                        millisUntilFINISH = 0;
                        videoPlayback();
                        if (popupWindow != null && popupWindow.isShowing())
                            popupWindow.dismiss();
                        countDownTimerScratch.cancel();
                        alertDialog.cancel();
                    }
                });

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        offerInsertLog();
                        claim_offer.setVisibility(View.GONE);
                        tv_timer_count.setText("");
                        tv_timer_count.setBackground(getResources().getDrawable(R.color.black));
                        millisUntilFINISH = 0;
                        videoPlayback();
                        if (popupWindow != null && popupWindow.isShowing())
                            popupWindow.dismiss();
                        countDownTimerScratch.cancel();
                        alertDialog.cancel();
                    }
                });

                alertDialog.show();
                Log.e("ScratchFragment____", "millisUntilFINISH -  " + ((int) millisUntilFINISH % 1000));
                final int[] a = {(10 + ((int) millisUntilFINISH / 1000))};
                countDownTimerScratch = new CountDownTimer(9800 + millisUntilFINISH, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        ScratchTimerFLAG = true;
                        tv_timer_count.setText("" + a[0]);
                        a[0] = a[0] - 1;
                    }

                    @Override
                    public void onFinish() {
                        offerInsertLog();

                        claim_offer.setVisibility(View.GONE);
                        ScratchTimerFLAG = false;
                        tv_timer_count.setText("");
                        tv_timer_count.setBackground(getResources().getDrawable(R.color.black));
                        millisUntilFINISH = 0;
                        videoPlayback();
                        if (popupWindow != null && popupWindow.isShowing())
                            popupWindow.dismiss();

                        MISING_FLAG = true;

                        alertDialog.cancel();
                    }
                };
                countDownTimerScratch.start();
            }
        });

    }*/

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);


        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) //To fullscreen
        {
            paramsNotFullscreen = (FrameLayout.LayoutParams) videoView.getLayoutParams();
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(paramsNotFullscreen);
            params.setMargins(0, 0, 0, 0);
            params.height = ViewGroup.LayoutParams.MATCH_PARENT;
            params.width = ViewGroup.LayoutParams.MATCH_PARENT;
            videoView.setLayoutParams(params);

        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            paramsNotFullscreen = (FrameLayout.LayoutParams) videoView.getLayoutParams();
            videoView.setLayoutParams(paramsNotFullscreen);
        }
    }

    public void homeApp(View view) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


    @TargetApi(19)
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        super.onWindowFocusChanged(hasFocus);
        if (!hasFocus) {
            // Close every kind of system dialog
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            sendBroadcast(new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS));
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            //if(intent.getAction().equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS))
            //   Toast.makeText(mContext, "Your LongPress Power Button", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        // Here we can detect long press of power button
        if (keyCode == KeyEvent.KEYCODE_POWER) {
            //  Log.e(TAG,"759 -   Power     :::::    Power    ::::::    Power");

           /* getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            sendBroadcast(new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS));
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);*/
            return true;
        }
        return super.onKeyLongPress(keyCode, event);
    }


    private void initViewHolder() {
        cabTabDetailsViewHolder = ViewModelProviders.of(this).get(CabTabDetailsViewHolder.class);
    }

    public void enableSound(int sound, MediaPlayer mp) {
        float f = (float) sound;
        // if(DebugFlag) Log.e("checkingsounds","&&&&&   "+f);
        mp.setVolume(f, f);
        //mp.setLooping(true);
        //this will return current volume.
        //here you can set custom volume.
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, sound, AudioManager.FLAG_PLAY_SOUND);
    }

    ///THIS METHOD FOR GET SCREEN SIZE
    /// USE FOR DETECT SCREEN SIDE

    private void getScreenSize() {
        display = getWindowManager().getDefaultDisplay();
        size = new Point();
        display.getSize(size);
        sWidth = 1280;
        sHeight = 1080;
        Log.e(TAG, "Width = " + sWidth + ", Height = " + sHeight);
    }

    public static void unlockScreen() {

        //Log.e( TAG, "Turning on screen ... " );

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
                //   if(LogFlag)  Log.e(TAG, "Unable to start camera source.", e);
                mCameraSource.release();
                mCameraSource = null;
            }
        } else
            Toast.makeText(mContext, "Camera Null Null", Toast.LENGTH_SHORT).show();
    }

    //
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int keyCode = event.getKeyCode();
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_UP:
                // Ignore
                return false;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                // Ignore
                return false;
            case KeyEvent.KEYCODE_POWER:
                //      Log.e(TAG," 836 -  Power     :::::    Power    ::::::    Power");

                /*KeyguardManager km = (KeyguardManager) mContext.getSystemService(Context.KEYGUARD_SERVICE);
                final KeyguardManager.KeyguardLock kl = km .newKeyguardLock("MyKeyguardLock");
                kl.disableKeyguard();

                PowerManager pm = (PowerManager) mContext.getSystemService(Context.POWER_SERVICE);
                @SuppressLint("InvalidWakeLockTag") PowerManager.WakeLock wakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK
                        | PowerManager.ACQUIRE_CAUSES_WAKEUP
                        | PowerManager.ON_AFTER_RELEASE, "MyWakeLock");
                wakeLock.acquire(30*60*1000L );//5 Mins
                Toast.makeText(mainActivity, "KEYCODE_POWER", Toast.LENGTH_SHORT).show();*/
                /*Intent closeDialog = new Intent(Intent.ACTION_SCREEN_ON);
                sendBroadcast(closeDialog);*/

                // Toast.makeText(mainActivity, "Powerr Keyy", Toast.LENGTH_SHORT).show();
                    /*Intent closeDialogg = new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
                    sendBroadcast(closeDialogg);*/
                return false;
            /*case KeyEvent.KEYCODE_ALL_APPS:
                // Ignore
                return true;*/
            default:
                return false;
        }
    }

    private void runtimePermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_SETTINGS)
                != PackageManager.PERMISSION_GRANTED) {
            // permission not granted, initiate request
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.WRITE_SETTINGS},
                    REQUEST_CAMERA_PERMISSION);
        } else {
            createCameraSource();
        }
    }

    @SuppressLint("MissingPermission")
    private void getLoc(Double lattitude, Double longitude) {
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(mContext, Locale.getDefault());
        if (lattitude != 0 && longitude != 0) {

            try {
                addresses = geocoder.getFromLocation(lattitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                if (addresses != null) {
                    String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                    String city = addresses.get(0).getLocality();
                    String state = addresses.get(0).getAdminArea();
                    String country = addresses.get(0).getCountryName();
//                    postalCode = addresses.get(0).getPostalCode();
//                    postalCode = postalCode.replaceAll(" ","");
//                    postalCode = postalCode.replaceAll("\\s+", "");
//                    String knownName = addresses.get(0).getFeatureName();



                    if (DebugFlag)
                        Log.e(TAG, "PostalCode ======================================== " + postalCode);
                }
            } catch (IOException e) {
                if (DebugFlag)
                    Log.e(TAG, "Exception  getLoc =========================================== " + e);
            }
        }
    }


    private void displayLocationSettingsRequest(final Context mContext) {
        GoogleApiClient googleApiClient = new GoogleApiClient.Builder(mContext)
                .addApi(LocationServices.API).build();
        googleApiClient.connect();

        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        locationRequest.setInterval(120000);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);

        PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());
        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult result) {
                final Status status = result.getStatus();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        if (DebugFlag) Log.e(TAG, "All location settings are satisfied.");

                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        if (DebugFlag)
                            Log.e(TAG, "Location settings are not satisfied. Show the user a dialog to upgrade location settings ");

                        try {
                            // Show the dialog by calling startResolutionForResult(), and check the result
                            // in onActivityResult().
                            status.startResolutionForResult(VideoFaceDetectionActivity.this, REQUEST_CHECK_SETTINGS);
                        } catch (IntentSender.SendIntentException e) {
                            if (DebugFlag) Log.e(TAG, "PendingIntent unable to execute request.");
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        if (DebugFlag)
                            Log.e(TAG, "Location settings are inadequate, and cannot be fixed here. Dialog not created.");
                        break;
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CAMERA_PERMISSION && resultCode == RESULT_OK) {
            createCameraSource();
        } else {
            runtimePermissions();
        }

    }

    @SuppressLint("MissingPermission")
    private void createCameraSource() {
     //   lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
       // assert lm != null;
       // lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000 * 60 * 5, 1000, this);

        final Context mContext = getApplicationContext();
        FaceDetector detector = new FaceDetector.Builder(mContext)
                .setProminentFaceOnly(false)
                .setMinFaceSize(1.0f)
                .build();

        // detector.setProcessor(new MultiProcessor.Builder<>(new GraphicFaceTrackerFactory()).build());
        myFaceDetector = new MyFaceDetector(detector);

        // You can use your own processor
        myFaceDetector.setProcessor(
                new MultiProcessor.Builder<>(new GraphicFaceTrackerFactory())
                        .build());

        // You can use your own settings for CameraSource
        mCameraSource = new CameraSource.Builder(mContext, myFaceDetector)
                .setRequestedPreviewSize(1280, 720)
                .setAutoFocusEnabled(false)
                .setFacing(CameraSource.CAMERA_FACING_FRONT)
                .setRequestedFps(1.0f)
                .build();
        startCameraSource();
    }

    /**
     * Restarts the camera.
     */
    @Override
    protected void onResume() {
        super.onResume();
        //  if(DebugFlag) Log.e(TAG,"====== power_video_flag = " +power_video_flag+"=================== < < onResume > >");
        if (power_video_flag) {
            //  Log.e(TAG,"====onResume============ Current Position = "+currentPosition);
            //currentPosition = videoView.getCurrentPosition();
            //currentPosition = currentPosition+1800;
            videoView.seekTo(currentPosition + 1800);
            videoView.start();
            power_video_flag = false;
            //currentPosition = 0;
        }
    }

    /**
     * Stops the camera.
     */
    @Override
    protected void onPause() {
        super.onPause();
        /*if(power_video_flag)
        {
            Log.e(TAG,"====onPause============= Current Position = "+videoView.getCurrentPosition());

            currentPosition =  videoView.getCurrentPosition();
            videoView.pause();

        }*/
        if (GraphicFaceTracker.screen_off_flag) {
            if (DebugFlag)
                Log.e(TAG, "============ screen_off_flag >> TRUE ======= < < onPause > >");
            GraphicFaceTracker.startTimerFlag = false;
        } else {
            if (DebugFlag) Log.e(TAG, "========================================== < < onPause > >");
        }

        //mPreview.stop();
    }

    /**
     * Releases the resources associated with the camera source, the associated detector, and the
     * rest of the processing pipeline.
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCameraSource != null) {
            mCameraSource.release();
        }
        GraphicFaceTracker.startTimerFlag = false;
        GraphicFaceTracker.faceDetectFlag = null;
        GraphicFaceTracker.screen_off_flag = false;
        GraphicFaceTracker.videoPaused = false;

        unregisterReceiver(mReceiver);
        if (popupWindow != null)
            popupWindow.dismiss();

        popupWindow = null;

    }

    private void startCameraSource() {
        // check that the device has play services available.
        int code = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(getApplicationContext());
        if (code != ConnectionResult.SUCCESS) {
            Dialog dlg = GoogleApiAvailability.getInstance().getErrorDialog(this, code, RC_HANDLE_GMS);
            dlg.show();
        }

        if (mCameraSource != null) {
            try {
                mPreview.start(mCameraSource, mGraphicOverlay);
            } catch (IOException e) {
                if (DebugFlag) Log.e(TAG, "Unable to start camera source.", e);
                mCameraSource.release();
                mCameraSource = null;
            }
        } else
            Toast.makeText(mContext, "1374 - Camera Null Null", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            longitude = location.getLongitude();
            lattitude = location.getLatitude();
            //Toast.makeText(mContext, + lattitude + ", " + longitude, Toast.LENGTH_SHORT).show();
            if (DebugFlag)
                Log.e(TAG, "Latttitude =================== " + lattitude + ",Longitude ================ " + longitude);
            getLoc(lattitude, longitude);
        }
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {
        displayLocationSettingsRequest(mContext);

    }

    @Override
    public int getInputType() {
        return 0;
    }

    @Override
    public boolean onKeyDown(View view, Editable text, int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public boolean onKeyUp(View view, Editable text, int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public boolean onKeyOther(View view, Editable text, KeyEvent event) {
        return false;
    }

    @Override
    public void clearMetaKeyState(View view, Editable content, int states) {

    }

    public void activateLTM(View view) {
        activateLTM();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void activateLTM() {
        mPackageManager = this.getPackageManager();

        if (deviceManger.isDeviceOwnerApp(

                getApplicationContext().getPackageName())) {


            mPackageManager.setComponentEnabledSetting(
                    new ComponentName(getApplicationContext(),
                            VideoFaceDetectionActivity.class),
                    PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                    PackageManager.DONT_KILL_APP);

            // start lock task mode if its not already active
            if (deviceManger.isLockTaskPermitted(this.getPackageName())) {
                ActivityManager am = (ActivityManager) getSystemService(
                        Context.ACTIVITY_SERVICE);
                if (am.getLockTaskModeState() ==
                        ActivityManager.LOCK_TASK_MODE_NONE) {
                    startLockTask();
                }
            }

            // Set Default COSU policy
            deviceManger = (DevicePolicyManager) getSystemService(
                    Context.DEVICE_POLICY_SERVICE);
            mPackageManager = getPackageManager();
            if (deviceManger.isDeviceOwnerApp(getPackageName())) {
                setDefaultCosuPolicies(true);
            } else {
                Toast.makeText(getApplicationContext(),
                        R.string.not_device_owner, Toast.LENGTH_SHORT)
                        .show();
            }
        } else {
            Toast.makeText(getApplicationContext(),
                    "Device OWNER not active.", Toast.LENGTH_SHORT)
                    .show();
        }
    }

    public void deactivateLTM(View view) {
        deactivateLTM();

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void deactivateLTM() {
        ActivityManager am = (ActivityManager) getSystemService(
                Context.ACTIVITY_SERVICE);

        if (am.getLockTaskModeState() ==
                ActivityManager.LOCK_TASK_MODE_LOCKED) {
            stopLockTask();
        }

        setDefaultCosuPolicies(false);
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setDefaultCosuPolicies(boolean active) {
        // set user restrictions
        setUserRestriction(UserManager.DISALLOW_SAFE_BOOT, active);
        setUserRestriction(UserManager.DISALLOW_FACTORY_RESET, active);
        setUserRestriction(UserManager.DISALLOW_ADD_USER, active);
        setUserRestriction(UserManager.DISALLOW_MOUNT_PHYSICAL_MEDIA, active);
        setUserRestriction(UserManager.DISALLOW_CREATE_WINDOWS, active);

        // disable keyguard and status bar
        if (active)
            deviceManger.setKeyguardDisabled(compName, active);

        deviceManger.setStatusBarDisabled(compName, active);

        // enable STAY_ON_WHILE_PLUGGED_IN
        enableStayOnWhilePluggedIn(active);

        // set system update policy
        if (active) {
            deviceManger.setSystemUpdatePolicy(compName,
                    SystemUpdatePolicy.createWindowedInstallPolicy(60, 120));

            //deviceManger.setLockTaskFeatures(compName,LOCK_TASK_FEATURE_GLOBAL_ACTIONS);
        } else {
            deviceManger.setSystemUpdatePolicy(compName,
                    null);
        }

        // set this Activity as a lock task package

        deviceManger.setLockTaskPackages(compName,
                active ? new String[]{getPackageName()} : new String[]{});

        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_MAIN);
        intentFilter.addCategory(Intent.CATEGORY_HOME);
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);

        if (active) {
            // set Cosu activity as home intent receiver so that it is started
            // on reboot
            deviceManger.addPersistentPreferredActivity(
                    compName, intentFilter, new ComponentName(
                            getPackageName(), VideoFaceDetectionActivity.class.getName()));
        } else {
            deviceManger.clearPackagePersistentPreferredActivities(
                    compName, getPackageName());
        }
    }


    private void setUserRestriction(String restriction, boolean disallow) {
        if (disallow) {
            deviceManger.addUserRestriction(compName, restriction);
        } else {
            deviceManger.clearUserRestriction(compName,
                    restriction);
        }
    }

    private void enableStayOnWhilePluggedIn(boolean enabled) {
        if (enabled) {
            deviceManger.setGlobalSetting(
                    compName,
                    Settings.Global.STAY_ON_WHILE_PLUGGED_IN,
                    Integer.toString(BatteryManager.BATTERY_PLUGGED_AC
                            | BatteryManager.BATTERY_PLUGGED_USB
                            | BatteryManager.BATTERY_PLUGGED_WIRELESS));
        } else {
            deviceManger.setGlobalSetting(
                    compName,
                    Settings.Global.STAY_ON_WHILE_PLUGGED_IN,
                    "0"
            );
        }
    }


    /**
     * Factory for creating a face tracker to be associated with a new face.  The multiprocessor
     * uses this factory to create face trackers as needed -- one for each individual.
     */
    private class GraphicFaceTrackerFactory implements MultiProcessor.Factory<Face> {
        @Override
        public Tracker<Face> create(Face face) {

            if (!ScratchTimerFLAG) {
                if (!videoView.isPlaying() && GraphicFaceTracker.play_flag) {
                    videoPlayback();
                }

                if (face__id == 1010)
                    face__id = face.getId();

                if (api_hit && face__id == face.getId()) {
                    if (!face_id_flag) {
                        // Log.e("Tracker<Face>","__________________________________________________"+face.getId());
                        if (mCurrentPhotoPath2 == null)
                            bitmapMethod();
                    }
                    face_id_flag = true;
                } else {
                    if (video_prepared) {
                        // Log.e("Api Hit False","__________________________________________________videoPrepared");

                        if (GraphicFaceTracker.videoPaused) {
                            //  if(DebugFlag) Log.e("Api Hit False","__________________________________________________videoPaused");
                            if (GraphicFaceTracker.stopPosition != 0) {
                                //     if(DebugFlag) Log.e("Api Hit False","__________________________________________________stopPosition");
                                videoView.seekTo(GraphicFaceTracker.stopPosition);
                                videoView.start();
                            }
                        }
                    }
                }
            }
            return new GraphicFaceTracker(mGraphicOverlay, mContext, activity);
        }
    }


    public void savebitmapMethodCount() {
        VideoFaceDetectionActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (popupWindow != null && popupWindow.isShowing())
                    popupWindow.dismiss();

                countDownTimerVideoLogData = new CountDownTimer(300000, 300000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                    }

                    @Override
                    public void onFinish() {
                        Log.e("TIMER120se  >>> >>> ", " savebitmapMethod  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                        savebitmapMethod();
                    }
                };

                countDownTimerVideoLogData.start();
            }
        });
    }


    public void savebitmapMethod() {
        bmFrame = myFaceDetector.getbit();
        if (savebitmap(bmFrame) && mCurrentPhotoPath2 != null) {
            if (firstCount == 0) {
                firstCount = 1;
                if (postalCode == null) {
                    getCampaignListFromPincode(postalCode, Integer.parseInt(age_), gender_);
                    getCampaignListOfferFromPincode(postalCode, Integer.parseInt(age_), gender_);
                } else {
                    getCampaignListFromPincode(postalCode, Integer.parseInt(age_), gender_);
                    getCampaignListOfferFromPincode(postalCode, Integer.parseInt(age_), gender_);
                }
            }
            RetrofitCall();
        } else {
            savebitmapMethod();
        }
    }

    public void bitmapMethod() {
        savebitmapMethod();
    }


    public void RetrofitCall() {
        if (api_hit) {
            api_hit = false;
            //----------------------------  BETAFACE  -------------------------------------------------------
            OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
            httpClientBuilder.addInterceptor(new Interceptor() {
                @Override
                public okhttp3.Response intercept(Chain chain) throws IOException {
                    Request.Builder requestBuilder = chain.request().newBuilder();
                    requestBuilder.header("Content-Type", "application/json");
                    requestBuilder.header("Accept", "application/json");
                    return chain.proceed(requestBuilder.build());
                }
            });

            OkHttpClient httpClient = httpClientBuilder.build();

            final Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ServerConnect.URL_BETAFACE)
                    .addConverterFactory(GsonConverterFactory.create())
                    //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(httpClient)
                    .build();

            //    Log.e("Retrofit  >>> >>> ", " >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> mCurrentPhotoPath2  ===== " + mCurrentPhotoPath2);
            File file = new File(mCurrentPhotoPath2);

            String convert_File_2String = String.valueOf(file);
            String fileNAme = convert_File_2String.substring(convert_File_2String.lastIndexOf("/") + 1);

            RequestBody requestFile =
                    RequestBody.create(MediaType.parse("multipart/form-data"), file);

            // MultipartBody.Part is used to send also the actual file name
            MultipartBody.Part body =
                    MultipartBody.Part.createFormData("file", file.getName(), requestFile);

            // add another part within the multipart request
            RequestBody api_key =
                    RequestBody.create(MediaType.parse("multipart/form-data"), API_KEY_BETAFACE);

            RequestBody detection_flags =
                    RequestBody.create(MediaType.parse("multipart/form-data"), "bestface,classifiers");

            RequestBody api_key_ = RequestBody.create(MediaType.parse("multipart/form-data"), API_KEY_BETAFACE);
            RequestBody detection_flags_ = RequestBody.create(MediaType.parse("multipart/form-data"), "bestface,classifiers");
            Map<String, RequestBody> map = new HashMap<>();

            map.put("api_key", api_key_);
            map.put("file", requestFile);
            map.put("detection_flags", detection_flags_);

            //--------------------------------------------------------------------------------------------

            RetrofitInterface request = retrofit.create(RetrofitInterface.class);

            Call<ImageInfo> imageResponseCall = request.getImageDataBetaFace2(api_key, body, detection_flags);
            imageResponseCall.enqueue(new Callback<ImageInfo>() {
                @Override
                public void onResponse(@NonNull Call<ImageInfo> call, @NonNull Response<ImageInfo> response) {
                    ImageInfo jsonResponse = response.body();

                    if (jsonResponse != null) {
                        Media media = jsonResponse.getMedia();

                        List<FacesItem> facesItem = media.getFaces();
                        if (facesItem != null) {
                            List<TagsItem> tagsItem = facesItem.get(0).getTags();

                            age_ = tagsItem.get(1).getValue();
                            gender_ = tagsItem.get(18).getValue();
                            Log.e("1059 -  onSuccess ", "Retrofit___________________________________________________________________  Age = " + age_ + " ... Gender = " + gender_);
                            Toast.makeText(mContext, "1042  -  " + gender_ + " ... " + age_ + "  ...  " + postalCode, Toast.LENGTH_LONG).show();
                            getCampaignListFromPincode(postalCode, Integer.parseInt(age_), gender_);
                        } else {

                            Toast.makeText(mContext, "1051 - Retrofit No Age No Gender from BetaFace API", Toast.LENGTH_LONG).show();
                            getCampaignListFromPincode(postalCode, Integer.parseInt(age_), gender_);
                        }
                    }
                }

                @Override
                public void onFailure(@NonNull Call<ImageInfo> call, @NonNull Throwable t) {
                    Log.e("1072 -  onFailure ", "Retrofit Throwable === " + t);
                    Toast.makeText(mContext, "1051 - Retrofit Age Gender Connection Failure to BetaFace API", Toast.LENGTH_LONG).show();
                }
            });
        } else {
            api_hit = true;
            Log.e("1078 - API HIT FALKSE", " Retrofit");
        }
    }

    public void deleteImage() {
        File fdelete = new File(mCurrentPhotoPath);
        File fdelete2 = new File(mCurrentPhotoPath2);
        if (fdelete.exists()) {
            if (fdelete.delete()) {
                if (DebugFlag) Log.e("-->", "file Deleted :" + mCurrentPhotoPath);
            } else {
                if (DebugFlag) Log.e("-->", "file not Deleted :" + mCurrentPhotoPath);
            }
        }

        if (fdelete2.exists()) {
            if (fdelete2.delete()) {
                if (DebugFlag) Log.e("-->", "file Deleted :" + mCurrentPhotoPath2);
            } else {
                if (DebugFlag) Log.e("-->", "file not Deleted :" + mCurrentPhotoPath2);
            }
        }
    }

    public void callBroadCast() {
        if (Build.VERSION.SDK_INT >= 14) {
            if (DebugFlag) Log.e("-->", " >= 14");
            MediaScannerConnection.scanFile(this, new String[]{Environment.getExternalStorageDirectory().toString()}, null, new MediaScannerConnection.OnScanCompletedListener() {
                /*
                 *   (non-Javadoc)
                 * @see android.media.MediaScannerConnection.OnScanCompletedListener#onScanCompleted(java.lang.String, android.net.Uri)
                 */
                public void onScanCompleted(String path, Uri uri) {
                    // if(DebugFlag) Log.e("ExternalStorage", "Scanned " + path + ":");
                    //  if(DebugFlag) Log.e("ExternalStorage", "-> uri=" + uri);
                }
            });
        } else {
            if (DebugFlag) Log.e("-->", " < 14");
            sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED,
                    Uri.parse("file://" + Environment.getExternalStorageDirectory())));
        }
    }

    public void videoPlayback() {
        savebitmapMethodCount();
        VideoFaceDetectionActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                try {
                    //Video Background Download Timer
                    if (!countDownTimerFlagVideoDwnld) {
                        videoIds = "";
                        l = 0;
                        Log.e(TAG, "Video Background Download Timer Timer Started .......... Started ......... Started");
                        // Toast.makeText(VideoFaceDetectionActivity.this, "New Video Timer started...........", Toast.LENGTH_SHORT).show();
                        countDownTimerFlagVideoDwnld = true;
                        countDownTimerVideoDwnld = new CountDownTimer(90000, 90000) {
                            @Override
                            public void onTick(long millisUntilFinished) {

                            }

                            @Override
                            public void onFinish() {
                                countDownTimerFlagVideoDwnld = false;
                                /*int max = 36;
                                int min = 10;
                                Random rand = new Random();
                                int randomNum = rand.nextInt((max - min) + 1) + min;
                                postalCode = "4400"+randomNum;*/
                                getDataFromPincode("440013");
                                Log.e(TAG, "Video Background Download Timer Timer VideoDwnld .......... onFinish"+postalCode);
                            }
                        };
                        countDownTimerVideoDwnld.start();
                    }
                    //Face Detection Timer
                    if (countDownTimerFlag) {
                        countDownTimerFlag = false;
                        countDownTimer = new CountDownTimer(150000, 150000) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                            }

                            @Override
                            public void onFinish() {
                                api_hit = true;
                                countDownTimerFlag = true;
                                Log.e("TIMER  >>> >>> ", " Face Detection Timer  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ");
                                VideoLogDataToServerTimer();
                            }
                        };

                        countDownTimer.start();
                    }
                    if (!DebugFlag)
                        Log.e(TAG, "_____________________________________ completionFlag && !video_prepared");
                    completionFlag = false;
                    String path;

                    videoListDB = new ArrayList<>();
                    videoListDB = myDB.getNewAllVideosNames("adv");
                    if(videoListDB.size() != 0){

                    path = videoListDB.get(0).getVideoName();

                    videoListDBSingle = videoListDB.get(0);

                    Log.e(TAG, "VideoName           =           " + path + "______ videosCount = " + video_played_count);

                    //}

                    Uri data = Uri.parse(Environment.getExternalStorageDirectory() + "/Madads/" + path);
                    if (data == null) {
                        if (myDB.deleteCampaignItemById())
                            Log.e(TAG, "deleteCampaignItemById  ADV __________ data == null  SUCCESS __________ ");
                        else
                            Log.e(TAG, "deleteCampaignItemById  ADV ?????????? data == null  ERRORR ???????????? ");

                        videoPlayback();
                    } else {
                        videoView.setVideoURI(data);
                        MediaController mediaController = new MediaController(mContext);
                        durationVideo = 0;
                        currentPosition = 0;
                        mediaController.requestFitSystemWindows();
                        mediaController.setAnchorView(videoView);
                        videoView.setMediaController(null);
                        videoView.start();

                        videoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
                            @Override
                            public boolean onInfo(MediaPlayer mp, int what, int extra) {

                                if (what == MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START) {

                                    if (rl_scratch_card != null && rl_scratch_card.getVisibility() == View.VISIBLE) {
                                        rl_scratch_card.setVisibility(View.GONE);
                                        FragmentManager manager = getSupportFragmentManager();
                                        FragmentTransaction trans = manager.beginTransaction();
                                        trans.remove(scratchFragment);
                                        trans.commit();
                                        manager.popBackStack();
                                        scratchFragment = null;
                                    }
                                    if (iv_gif.getVisibility() == View.VISIBLE) {
                                        fl_gif.setVisibility(View.GONE);

                                        iv_gif.setVisibility(View.GONE);
                                    }

                                    return true;
                                }
                                return false;
                            }
                        });

                        videoView.setOnPreparedListener(new
                                                                MediaPlayer.OnPreparedListener() {
                                                                    @Override
                                                                    public void onPrepared(MediaPlayer mp) {

                                                                        if (popupWindow != null && popupWindow.isShowing())
                                                                            popupWindow.dismiss();

                                                                        durationVideo = videoView.getDuration();
                                                                        if (durationVideo > 11000)
                                                                            durationFeedbackTimer = durationVideo - 6000;
                                                                        DurationChecker();

                                                                        // Log.e(TAG, "videoListDBSingle = " + videoListDBSingle);

                                                                        videoView.setBackgroundColor(Color.TRANSPARENT);
                                                                        video_prepared = true;
                                                                        face_id_flag = false;
                                                                        face__id = 1010;
                                                                        mediaPlayer = mp;
                                                                        //if (DebugFlag)
                                                                        // Log.e(TAG, "Duration = " + videoView.getDuration());

                                                                    }
                                                                });

                        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                if (NO_DATA_FLAG) {
                                    fl_gif.setVisibility(View.VISIBLE);
                                    iv_gif.setVisibility(View.VISIBLE);
                                    Glide.with(mContext)
                                            .load(R.drawable.logo_madads)
                                            .into(iv_gif);
                                } else if (MISING_FLAG) {
                                    fl_gif.setVisibility(View.VISIBLE);
                                    iv_gif.setVisibility(View.VISIBLE);
                                    Glide.with(mContext)
                                            .load(R.drawable.logo_madads)
                                            .into(iv_gif);
                                } else {
                                    iv_gif.setVisibility(View.GONE);
                                    fl_gif.setVisibility(View.GONE);
                                }


                                video_played_count = video_played_count + 1;
                                if (popupWindow != null && popupWindow.isShowing())
                                    popupWindow.dismiss();

                                int VideoDur = (int) Math.round(durationVideo / 1000);

                                try {
                                    Calendar c = Calendar.getInstance();
                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                    timeDiff = getTimeDiff();
                                    if (timeDiff != null) {
                                        c.add(Calendar.DATE, -timeDiff.get(0));
                                        c.add(Calendar.HOUR, -timeDiff.get(1));
                                        c.add(Calendar.MINUTE, -timeDiff.get(2));
                                        c.add(Calendar.SECOND, -timeDiff.get(3));
                                    }
                                    String currentDate = sdf.format(c.getTime());
                                    ArrayList<Pincode> pincode = new ArrayList<>();
                                    ArrayList<City> city = new ArrayList<>();
                                    pincode = myDB.getAllPincode(postalCode);
                                    // Log.e(TAG, "PINCODE  = " + pincode.get(0).toString());
                                    int video_amount_charged = 0;
                                    try {
                                        city = myDB.getAllCity(pincode.get(0).getCityId());
                                        if (pincode != null && pincode.size() != 0) {
                                            if (videoListDBSingle.getCampaignIsPrime().equalsIgnoreCase("yes")) {

                                                // Log.e(TAG, "CITY  = " + city.get(0).toString());

                                                String[] time = currentDate.split(" ");
                                                if (time[1].compareTo("09:00:00") >= 0 && time[1].compareTo("12:00:00") <= 0 || time[1].compareTo("17:00:00") >= 0 && time[1].compareTo("20:00:00") <= 0) {
                                                    video_amount_charged = VideoDur * Integer.parseInt(city.get(0).getPrimeAmount());
                                                } else {
                                                    video_amount_charged = VideoDur * Integer.parseInt(city.get(0).getNonPrimeAmount());
                                                }
                                            } else {
                                                video_amount_charged = VideoDur * Integer.parseInt(city.get(0).getNonPrimeAmount());
                                            }
                                            int VideoDurAdv = (int) Math.round(durationVideo / 1000);
                                            myDB.insertVideoLogList(Integer.parseInt(videoListDBSingle.getVideoListId()),
                                                    Integer.parseInt(videoListDBSingle.getCampaignId()),
                                                    Integer.parseInt(videoListDBSingle.getAudienceId()), myDB.getCabId(), pincode.
                                                            get(0).getCountryId(), pincode.get(0).getStateId(), pincode.get(0).getCityId(),
                                                    pincode.get(0).getPincode(), gender_, age_, video_review,
                                                    currentDate, getLocalIpAddress(), 1,
                                                    String.valueOf(lattitude), String.valueOf(longitude), String.valueOf(video_amount_charged),
                                                    cabDetailsId, VideoDurAdv);
                                        } else {
                                            video_amount_charged = VideoDur * 10;

                                            int VideoDurAdv = (int) Math.round(durationVideo / 1000);
                                            myDB.insertVideoLogList(Integer.parseInt(videoListDBSingle.getVideoListId()),
                                                    Integer.parseInt(videoListDBSingle.getCampaignId()),
                                                    Integer.parseInt(videoListDBSingle.getAudienceId()), myDB.getCabId(), pincode.
                                                            get(0).getCountryId(), pincode.get(0).getStateId(), pincode.get(0).getCityId(),
                                                    pincode.get(0).getPincode(), gender_, age_, video_review,
                                                    currentDate, getLocalIpAddress(), 1,
                                                    String.valueOf(lattitude), String.valueOf(longitude), String.valueOf(video_amount_charged),
                                                    cabDetailsId, VideoDurAdv);
                                        }

                                    } catch (Exception e) {
                                        Log.e(TAG, "onCompletion ADV  1617 _________________________________________________ " + e);
                                    }

                                    if (myDB.deleteCampaignItemById())
                                        Log.e(TAG, "deleteCampaignItemById  ADV __________ SUCCESS __________ ");
                                    else
                                        Log.e(TAG, "deleteCampaignItemById  ADV ?????????? ERRORR ???????????? ");

                                    video_prepared = false;

                                    filler = true;
                                } catch (Exception e) {
                                    if (DebugFlag)
                                        Log.e(TAG, "onCompletion ADV  setScreenOnWhilePlaying_________________________________________________ " + e);
                                }
                                Log.e(TAG, "onCompletion ****1703*****  MISING_FLAG =" + MISING_FLAG + "___________________________ ");

                                if (MISING_FLAG) {
                                    if (countDownTimerScratchFACEOFF != null) {
                                        countDownTimerScratchFACEOFF.cancel();
                                        countDownTimerScratchFACEOFF = null;
                                    }

                                    if (popupWindow != null && popupWindow.isShowing())
                                        popupWindow.dismiss();

                                    popupWindow.dismiss();

                                    if (CheckNetwork.isInternetAvailable(mContext)) {
                                        ArrayList<VideoLogItem> obj = new ArrayList<>();
                                        obj = myDB.getVideoLogObject();
                                        if (obj != null) {
                                            if (obj.size() != 0) {
                                                Gson gson = new Gson();
                                                String json = gson.toJson(obj); //convert
                                                Log.e(TAG, "  gson.toJson  = " + json);
                                                if (!json.equals("[]") || json != null) {
                                                    Log.e(TAG, "1721 -   setVideoLogDataJSON(json);               setVideoLogDataJSON(json);");
                                                    setVideoLogDataJSON(json);
                                                }
                                            }
                                        }

                                        offerLogDataToServerTimerFlag = true;
                                        ArrayList<OfferLogItem> obj2 = new ArrayList<>();
                                        obj2 = myDB.getOfferLogObject();
                                        if (obj2 != null) {
                                            if (obj2.size() != 0) {
                                                Gson gson = new Gson();
                                                String json = gson.toJson(obj2); //convert
                                                Log.e(TAG, "  gson.toJson  = " + json);
                                                if (!json.equals("[]") || json != null) {
                                                    setOfferLogDataJSON(json);
                                                }
                                            }
                                        }
                                    } else {
                                        Toast.makeText(activity, "No Internet Connection", Toast.LENGTH_SHORT).show();

                                        api_hit = true;
                                        if (deviceManger.isAdminActive(compName)) {
                                            Log.e(TAG, " 1840 -   lockNow    |||||||||||      lockNow   ||||||||||||||||||     lockNow   ||||||||||||||||||||||   ");

                                            deviceManger.lockNow();
                                            screen_off_flag = true;
                                            video_played_count = 0;
                                /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                    deviceManger.setStatusBarDisabled(compName,true);
                                }*/
                                        }
                                    }

                                } else {
                                    if(imageView!=null)
                                    imageView.setVisibility(View.GONE);
                                    if (popupWindow != null && popupWindow.isShowing())
                                        popupWindow.dismiss();
                                    if (video_played_count == 3) {

                                        //=============================================================
                                        scratchFragment = new ScratchFragment();
                                        LinearLayout frameLayout = findViewById(R.id.container);

                                        getSupportFragmentManager().beginTransaction().replace(frameLayout.getId(),
                                                scratchFragment).addToBackStack(null).commit();

                                        rl_scratch_card = findViewById(R.id.rl_scratch_card);
                                        rl_scratch_card.setLayoutParams(new FrameLayout.LayoutParams(
                                                sWidth,
                                                sHeight));
                                        tv_timer_count.setBackground(getResources().getDrawable(R.drawable.background_circle_solid));
                                        Log.e("onCompletion", " ______________ MISING_FLAG = " + MISING_FLAG + " ________________________________onMissing");

                                        final int[] a = {11};

                                        countDownTimerScratch = new CountDownTimer(9800, 1000) {
                                            @Override
                                            public void onTick(long millisUntilFinished) {
                                                ScratchTimerFLAG = true;

                                                millisUntilFINISH = millisUntilFinished;
                                                a[0] = a[0] - 1;
                                                tv_timer_count.setText("" + a[0]);

                                            }

                                            @Override
                                            public void onFinish() {

                                                offerInsertLog();
                                                //claim_offer.setVisibility(View.GONE);
                                                ScratchTimerFLAG = false;

                                                tv_timer_count.setText("");
                                                millisUntilFINISH = 0;
                                                videoPlayback();
                                                if (popupWindow != null && popupWindow.isShowing())
                                                    popupWindow.dismiss();

                                                popupWindow.dismiss();

                                                if (countDownTimerScratchFACEOFF == null) {
                                                    Log.e(TAG, "1912 - countDownTimerScratchFACEOFF  NULL......NULL....NULL ");

                                                    countDownTimerScratchFACEOFF = new CountDownTimer(10000, 10000) {
                                                        @Override
                                                        public void onTick(long millisUntilFinished) {

                                                        }

                                                        @Override
                                                        public void onFinish() {
                                                            Log.e(TAG, "1921 - onFinish   countDownTimerScratchFACEOFF  ......50000.... ");

                                                            MISING_FLAG = true;
                                                        }
                                                    };
                                                    countDownTimerScratchFACEOFF.start();
                                                }
                                            }
                                        };
                                        countDownTimerScratch.start();
                                        rl_scratch_card.setVisibility(View.VISIBLE);
                                        rl_scratch_card.setBackgroundResource(R.color.black);
                                        tv_timer_count.setBackground(getResources().getDrawable(R.color.black));
                                        video_played_count = 0;
                                    } else {
                                        videoPlayback();
                                    }

                                }
                                if (NO_DATA_FLAG) {
                                    fl_gif.setVisibility(View.VISIBLE);
                                    iv_gif.setVisibility(View.VISIBLE);
                                    Glide.with(mContext)
                                            .load(R.drawable.logo_madads)
                                            .into(iv_gif);
                                }
                            }
                        });
                    }
                    }else {
                      //  claim_offer.setVisibility(View.GONE);
                        ScratchTimerFLAG = false;
                        tv_timer_count.setText("");
                        tv_timer_count.setBackground(getResources().getDrawable(R.color.black));
                        millisUntilFINISH = 0;
                        myDB.deleteCampaignList();
                        NO_DATA_FLAG=true;

                        fl_gif.setVisibility(View.VISIBLE);
                        iv_gif.setVisibility(View.VISIBLE);
                        Glide.with(mContext)
                                .load(R.drawable.logo_madads)
                                .into(iv_gif);

                        nodata();
                    }
                } catch (Exception e) {
                    Log.e(TAG, "1459 - VideoPlayback OUTER EXCEPTION >>> " + e);
                }

                if (video_played_count > 3)
                    video_played_count = 0;
            }
        });
    }


    private void offerInsertLog() {
        ArrayList<Pincode> pincode = new ArrayList<>();
        ArrayList<City> city = new ArrayList<>();
        pincode = myDB.getAllPincode(postalCode);
        ArrayList<CampaignlistOfferItem> campaignlistOfferItems = myDB.getNewAllOffers();
        if (campaignlistOfferItems != null) {
            if (campaignlistOfferItems.size() != 0) {
                CampaignlistOfferItem campaignlistOfferItem = campaignlistOfferItems.get(0);
                if (pincode != null && pincode.size() != 0) {
                    city = myDB.getAllCity(pincode.get(0).getCityId());
                    myDB.insertOfferLogList(Integer.parseInt(campaignlistOfferItem.getOfferId()),
                            Integer.parseInt(campaignlistOfferItem.getCampaignId()),
                            Integer.parseInt(campaignlistOfferItem.getAudienceId()), myDB.getCabId(), cabDetailsId,
                            10, 1, pincode.get(0).getCountryId(),
                            pincode.get(0).getStateId(), pincode.get(0).getCityId(),
                            pincode.get(0).getPincode(), String.valueOf(lattitude), String.valueOf(longitude),
                            gender_, age_, "100", "wow", getLocalIpAddress());
                }
            }

        }
    }


    public boolean savebitmap(final Bitmap bmp) {

        try {
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            final int min = 20;
            final int max = 8000;
            final int random = new Random().nextInt((max - min) + 1) + min;

            bmp.compress(Bitmap.CompressFormat.JPEG, 100, bytes);

            File f = new File(Environment.getExternalStorageDirectory() + "/MadadsImages/"
                    + File.separator + "Image__" + random + ".jpg");

            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);

            fo.write(bytes.toByteArray());
            fo.close();
            mCurrentPhotoPath = f.getAbsolutePath();
            //======================================================================================
            ByteArrayOutputStream bytes_2 = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, bytes_2);

            File f_2 = new File(Environment.getExternalStorageDirectory() + "/MadadsImages/"
                    + File.separator + "BetaFaceImage__" + random + ".jpg");

            if (!f_2.exists()) f_2.createNewFile();
            FileOutputStream fo_2 = new FileOutputStream(f_2);

            fo_2.write(bytes_2.toByteArray());
            fo_2.close();

            mCurrentPhotoPath2 = f_2.getAbsolutePath();

            if (mCurrentPhotoPath2 != null)
                return true;
            else
                return false;
        } catch (IOException e) {
            return false;
        }
    }


    @Override
    public void onBackPressed() {
        api_hit = true;
    }

    public String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        ip = Formatter.formatIpAddress(inetAddress.hashCode());
                        return ip;
                    }
                }
            }
        } catch (SocketException ex) {
            Log.e("SocketException", " = " + ex);
        }
        return ip;
    }

    private void getTimeDiffer() {
        //if(subscriptionList.size()!=0) {
        if (Utils.isConnectingToInternet(mContext)) {
            CabTabDetailsViewHolder cabTabDetailsViewHolder;
            cabTabDetailsViewHolder = ViewModelProviders.of(this).get(CabTabDetailsViewHolder.class);
            cabTabDetailsViewHolder.getTime()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(getTime);
        } else {
            Toast.makeText(mContext, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
        // }
    }

    SingleObserver<Map> getTime = new SingleObserver<Map>() {
        @Override
        public void onSubscribe(Disposable d) {
            bag.add(d);
            //MyProgressDialog.dismiss();
        }

        @Override
        public void onSuccess(Map timeD) {
            Map s = new HashMap();
            s = timeD;
            if (s != null) {
                String serverDate = String.valueOf(s.get("servercurrentdatetime"));
                Calendar c = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String currentTime = sdf.format(c.getTime());
                try {
                    fromDate = sdf.parse(serverDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                try {
                    toDate = sdf.parse(currentTime);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                long diff = toDate.getTime() - fromDate.getTime();
                String dateFormat = "duration: ";
                int diffDays = (int) (diff / (24 * 60 * 60 * 1000));
                if (diffDays > 0) {
                    dateFormat += diffDays + " day ";
                }
                diff -= diffDays * (24 * 60 * 60 * 1000);

                int diffhours = (int) (diff / (60 * 60 * 1000));
                if (diffhours > 0) {
                    dateFormat += diffhours + " hour ";
                }
                diff -= diffhours * (60 * 60 * 1000);

                int diffmin = (int) (diff / (60 * 1000));
                if (diffmin > 0) {
                    dateFormat += diffmin + " min ";
                }
                diff -= diffmin * (60 * 1000);

                int diffsec = (int) (diff / (1000));
                if (diffsec > 0) {
                    dateFormat += diffsec + " sec";
                }
                System.out.println(dateFormat);
                c.add(Calendar.DATE, -diffDays);
                c.add(Calendar.HOUR, -diffhours);
                c.add(Calendar.MINUTE, -diffmin);
                c.add(Calendar.SECOND, -diffsec);


                timeDiff = new ArrayList<Integer>();
                timeDiff.add(0, diffDays);
                timeDiff.add(1, diffhours);
                timeDiff.add(2, diffmin);
                timeDiff.add(3, diffsec);
            }

        }

        @Override
        public void onError(Throwable e) {
            Log.e(TAG, "1626 - onError: getCampaignListBytime >> " + e.toString());
            MyProgressDialog.dismiss();

            Toast.makeText(mContext, "1626 - " + ApplicationConstant.ANYTHING_WRONG, Toast.LENGTH_SHORT).show();
        }
    };

    public List<Integer> getTimeDiff() {
        return timeDiff;
    }


    private void showPopupFeedback(final Activity mContext) {

        VideoFaceDetectionActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // Inflate the popup_layout.xml
                FrameLayout viewGroup = mContext.findViewById(R.id.feedback_layout);
                LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View layout = layoutInflater.inflate(R.layout.pop_up_layout, viewGroup);

                // Creating the PopupWindow
                if (popupWindow != null && popupWindow.isShowing())
                    popupWindow.dismiss();

                popupWindow = new PopupWindow(mContext);
                popupWindow.setContentView(layout);
                popupWindow.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
                popupWindow.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
                popupWindow.setFocusable(true);
                popupWindow.showAtLocation(layout, Gravity.BOTTOM, 0, 0);

                //popupWindow.setAnimationStyle(R.anim.bottom_up);

                popupWindow.setBackgroundDrawable(getResources().getDrawable(R.color.white));

                // Getting a reference to Close button, and close the popup when clicked.
                ImageButton likeBtn = layout.findViewById(R.id.like_btn);
                likeBtn.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        video_review = "Yes";
                        popupWindow.dismiss();
                        Toast.makeText(mContext, "Feedback Submitted", Toast.LENGTH_SHORT).show();
                    }
                });

                ImageButton dislikeBtn = layout.findViewById(R.id.dislike_btn);
                dislikeBtn.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        video_review = "No";
                        popupWindow.dismiss();
                        Toast.makeText(mContext, "Feedback Submitted", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    public void DurationChecker() {
        if (DebugFlag)
            Log.e(TAG, "MISING_FLAG = " + MISING_FLAG + ",   durationFeedbackTimer ______________________   " + durationFeedbackTimer);

        VideoFaceDetectionActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {


                countDownTimer2 = new CountDownTimer(durationFeedbackTimer, durationFeedbackTimer) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
                        if (DebugFlag)
                            Log.e(TAG, "durationFeedbackTimer ______________________onFinish  >>>   ");
                        video_review = "None";
                        showPopupFeedback(VideoFaceDetectionActivity.this);
                    }
                };

                countDownTimer2.start();
            }
        });

    }

    public void VideoLogDataToServerTimer() {
        VideoFaceDetectionActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (popupWindow != null && popupWindow.isShowing())
                    popupWindow.dismiss();

                countDownTimerVideoLogData = new CountDownTimer(1000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                    }

                    @Override
                    public void onFinish() {
                        if (DebugFlag)
                            Log.e(TAG, "VideoLogDataToServerTimer ______________________onFinish  >>>   ");
                        videoLogDataToServerTimerFlag = true;
                        ArrayList<VideoLogItem> obj = new ArrayList<>();
                        obj = myDB.getVideoLogObject();
                        if (obj != null) {
                            if (obj.size() != 0) {
                                Gson gson = new Gson();
                                String json = gson.toJson(obj); //convert
                                Log.e(TAG, "  gson.toJson  = " + json);
                                if (!json.equals("[]") || json != null) {
                                    Log.e(TAG, "2119 -   setVideoLogDataJSON(json);               setVideoLogDataJSON(json);");
                                    setVideoLogDataJSON(json);
                                }
                            }
                        }
                    }
                };

                countDownTimerVideoLogData.start();
                countDownTimerOfferLogData = new CountDownTimer(3000, 3000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                    }

                    @Override
                    public void onFinish() {
                        if (DebugFlag)
                            Log.e(TAG, "OfferLogDataToServerTimer ______________________onFinish  >>>   ");
                        offerLogDataToServerTimerFlag = true;
                        ArrayList<OfferLogItem> obj = new ArrayList<>();
                        obj = myDB.getOfferLogObject();
                        if (obj != null) {
                            if (obj.size() != 0) {
                                Gson gson = new Gson();
                                String json = gson.toJson(obj); //convert
                                Log.e(TAG, "  gson.toJson  = " + json);
                                if (!json.equals("[]") || json != null) {
                                    setOfferLogDataJSON(json);
                                }
                            }
                        }
                    }
                };
                countDownTimerOfferLogData.start();
            }
        });
    }

    private void setVideoLogDataJSON(String json) {
        if (Utils.isConnectingToInternet(mContext)) {
            cabTabDetailsViewHolder.setVideoLogJson(json)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(setVideoLogData);
        } else {
            Toast.makeText(mContext, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }

    SingleObserver<VideoLogResponse> setVideoLogData = new SingleObserver<VideoLogResponse>() {
        @Override
        public void onSubscribe(Disposable d) {
            bag.add(d);
        }

        @Override
        public void onSuccess(VideoLogResponse videoLog) {
            Log.e(TAG, "onSuccess: setVideoLogData  _________________ MISING_FLAG =" + MISING_FLAG + " _________________________________________ ");

            if (("Success").equalsIgnoreCase(videoLog.getStatus())) {
                if (myDB.deleteVideoLogData()) //*** Most Imp Step
                {
                    if (DebugFlag)
                        Log.e(TAG, "VideoLog OfferLog DELETED :   ....  SUCCESS   .... ");
                    api_hit = true;
                    if (MISING_FLAG) {
                        if (scratchFragment == null || !videoView.isPlaying()) {
                            Log.e(TAG, " 2031 -   !videoView.isPlaying()   |||||||||||||||||||||||||||||||||||||||||||||||||||   scratchFragment==null ");

                            boolean active = deviceManger.isAdminActive(compName);
                            if (active) {
                                Log.e(TAG, " 2191 -   lockNow    |||||||||||      lockNow   ||||||||||||||||||     lockNow   ||||||||||||||||||||||   ");

                                deviceManger.lockNow();
                                screen_off_flag = true;
                                video_played_count = 0;
                                /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                    deviceManger.setStatusBarDisabled(compName,true);
                                }*/
                            } else
                                Log.e(TAG, " 2199 -   ????? lockNow    |||||||||||      ???? lockNow   ||||||||||||||||||    ???? lockNow   ||||||||||||||||||||||   ");

                        }

                        if (GraphicFaceTracker.countDownTimer2 != null)
                            GraphicFaceTracker.countDownTimer2.cancel();
                        GraphicFaceTracker.startTimerFlag = false;
                    } else {




                    }
                } else if (DebugFlag) Log.e(TAG, "VideoLog DELETED :   ....  ERROR   .... ");
            } else {
                if (DebugFlag) Log.e(TAG, "VideoLogResponse :   ....  FAILURE   .... ");
            }
        }

        @Override
        public void onError(Throwable e) {
            Log.e(TAG, "1821 - onError: setVideoLogData  --  getCampaignListByPincodeVideo >> " + e.toString());
            MyProgressDialog.dismiss();
            Toast.makeText(mContext, "1821 - " + postalCode + " ... " + age_ + " ... " + ApplicationConstant.ANYTHING_WRONG, Toast.LENGTH_SHORT).show();
        }
    };

    //==============================================================================================

    private void setOfferLogDataJSON(String json) {
        if (Utils.isConnectingToInternet(mContext)) {
            cabTabDetailsViewHolder.setOfferLogJson(json)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(setOfferLogData);
        } else {
            Toast.makeText(mContext, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }

    SingleObserver<OfferLogResponse> setOfferLogData = new SingleObserver<OfferLogResponse>() {
        @Override
        public void onSubscribe(Disposable d) {
            bag.add(d);
        }

        @Override
        public void onSuccess(OfferLogResponse offerLog) {
            Log.e(TAG, "onSuccess: OfferLog __________________________________________________________ ");

            if (("Success").equalsIgnoreCase(offerLog.getStatus())) {
                if (DebugFlag) Log.e(TAG, "OfferLogResponse :   ....  SUCCESS   .... ");

                if (myDB.deleteOfferLogData()) {
                    if (DebugFlag) Log.e(TAG, "OfferLog DELETED :   ....  SUCCESS   .... ");
                } else if (DebugFlag) Log.e(TAG, "OfferLog DELETED :   ....  ERROR   .... ");

            } else {
                if (DebugFlag) Log.e(TAG, "VideoLogResponse :   ....  FAILURE   .... ");
            }
        }

        @Override
        public void onError(Throwable e) {
            Log.e(TAG, "1821 - onError: setVideoLogData  --  getCampaignListByPincodeVideo >> " + e.toString());
            MyProgressDialog.dismiss();
            Toast.makeText(mContext, "1821 - " + postalCode + " ... " + age_ + " ... " + ApplicationConstant.ANYTHING_WRONG, Toast.LENGTH_SHORT).show();
        }
    };

    //============================================================================================
    //region Get Campaign List Pincode
    private void getCampaignListFromPincode(String pincode, int age, String gender) {
        Log.e(TAG, "VIDEO FACEDETECT ACT getCampaignListFromPincode()()()" + pincode + "," + age + "," + gender);

                if (Utils.isConnectingToInternet(mContext)) {
                    NO_DATA1 = false;
                    cabTabDetailsViewHolder.getCampaignDataPincode(pincode, age, gender)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(getCampaignListByPincode);
                } else {
                    Toast.makeText(mContext, "No Internet Connection", Toast.LENGTH_SHORT).show();
                }
    }



    SingleObserver<Campaign> getCampaignListByPincode = new SingleObserver<Campaign>() {
        @Override
        public void onSubscribe(Disposable d) {
            bag.add(d);
        }

        @Override
        public void onSuccess(Campaign campaign) {

            if (campaign.getStatus().equalsIgnoreCase("Success")) {
                iv_gif.setVisibility(View.GONE);
                fl_gif.setVisibility(View.GONE);
                Log.e(TAG, "onSuccess: SUCCESS getVideoListByPincode _______________ NEW CAMPAIGN LIST ___________ ");
                deleteImage();
                campaignListItem = new ArrayList<>();
                campaignListItemAdv = new ArrayList<>();
                final_items_combiTwo = new ArrayList<>();
                final_items_combiOne = new ArrayList<>();
                campaignListItem = campaign.getCampaignlist();
             //   campaignListItemFinal = new ArrayList<>();

                //````````````````````Start of Old Code`````````````````````````````
//                //System.out.println(video.getRandomElement(campaignListItem.size()));
//                int x = campaignListItem.size();
//                String vidId;
//                Log.e(TAG, "Sorted LIST ::::::::::::::     diff = start" );
//                int rand = 0;
//                int k= 0;
//                for (int i = 0; i<300; i++) {
//                    rand = new Random().nextInt(x);
//                    vidId = (campaignListItem.get(rand).getVideoListId());
//                    //for( k=0 ; k < Math.ceil(campaignListItemFinal.size()/3); k++ ){
//
//                   // }
//                    //rndNo(vidId,x,rand);
//                    campaignListItemFinal.add(campaignListItem.get(rand));
//                    Log.e(TAG, "Sorted LIST ::::::::::::::     diff = " + campaignListItem.get(rand).getCampaignName());
//                   // Log.e(TAG, "onSuccess: " );
//                }

//                ````````````````````Start of Old Code`````````````````````````````

                //
                // Sorting campaignListItem in ascending order so as to find out ratio
                Collections.sort(campaignListItem, new Comparator<CampaignlistItem>() {
                    @Override
                    public int compare(CampaignlistItem lhs, CampaignlistItem rhs) {
                        return Integer.valueOf(rhs.getCount()).compareTo(Integer.valueOf(lhs.getCount()));
                    }
                });
                float diff = 1;
                if (campaignListItem.size() > 2) {
                    //Now find ratio here
                    diff = (campaignListItem.get(0).getCount() / (float) campaignListItem.get(1).getCount());
                    Log.e(TAG, "Sorted LIST ::::::::::::::     diff = " + diff);

                    for (CampaignlistItem c : campaignListItem) {
                        Log.e(TAG, "" + c.getCampaignName());
                    }
                }

                if (diff > 2.20) {
                    //==============================================================================
                    ArrayList<Integer> countList = new ArrayList<>();
                    campaignListItemFiller = new ArrayList<>();
                    newCount = new int[campaignListItem.size()];
                    // int[] n ={2,7,6,2,4,3};
                    int count_sum = 0;
                    for (i = 0; i < campaignListItem.size(); i++) {
                        if (campaignListItem.get(i).getCampaignType().equals("adv")) {
                            campaignListItemAdv.add(campaignListItem.get(i));
                            newCount[i] = campaignListItem.get(i).getCount();
                            Log.e("count CAMPAIGN ______ ", "____________ " + campaignListItem.get(i).getCount());
                            final_items_combiTwo.add(Integer.parseInt(campaignListItem.get(i).getCampaignId()));
                            campMap.put(i, campaignListItem.get(i));
                            count_sum = count_sum + campaignListItem.get(i).getCount();
                        } else {
                            campaignListItemFiller.add(campaignListItem.get(i));
                        }
                    }
                    //==============================================================================================
                    int counter = 1;
                    Log.e("count_sum _________ ", "____________ " + count_sum);

                    int avg = count_sum / 2;
                    final_avg = avg;

                    //int avg = count_sum/2;
                    Log.e("AVERAGE _________ ", "____________ " + avg);
                    String[] items = new String[newCount.length];
                    findTargetSumSubsets(campaignListItemAdv, avg, "", 0);

                    /**
                     * Loop for finding the combination closest to average
                     */
                    for (String str : allSubsets) {
                    /*if(counter==1)
                    {
                         items = str.split(" ");
                    }*/
                        // Log.e("AllSubsets _________ ","____________ "+str);
                        allCombinations.add(str);
                        //  Log.e("Itmes _________ ","____________ "+items[0]);
                        //System.out.println(counter + ") " + str);
                        counter++;
                    }

                    /**
                     * Loop for finding each count value from combination which is selected as closest to average
                     */
                    for (String count : allCombinations) {
                        HashSet<String> allCombinationsSeperated = new HashSet<>();
                        allCombinationsSeperated.add(count);

                        for (String c : allCombinationsSeperated) {
                            if (c != null) {
                                items = c.split(" ");
                                int s = 0;
                                for (String item : items) {
                                    item = item.trim();
                                    assert item != null;
                                    if (!item.equals("")) {
                                        for (int k = 0; k < campaignListItem.size(); k++) {
                                            if (campaignListItem.get(k).getCampaignId().equals(item)) {
                                                s = s + campaignListItem.get(k).getCount();
                                            }
                                        }
                                        //  Log.e("SEPERATED ____ ", "IN  FOR ____________ " + item);
                                    }
                                }
                                int difference = Math.abs(final_avg - s);

                                if (myMap.isEmpty()) {
                                    myMap.put(my_map_key, c);
                                    selectedCombinations.add(c);
                                    difference_global = difference;
                                } else {
                                    if (difference < difference_global) {
                                        myMap.put(my_map_key, c);
                                        selectedCombinations.add(c);
                                        difference_global = difference;
                                    }
                                }
                                Log.e("SELECTED COMBI ____ ", "______________ " + c);
                            }
                        }
                    }

                    Log.e("FINAL COMBI ____ ", "______________ " + myMap.get(my_map_key));
                    String f = myMap.get(my_map_key);
                    String[] final_items = f.split(" ");
                    ArrayList<Integer> final_items_combiOne = new ArrayList<>();
                    for (String item : final_items) {
                        final_items_combiOne.add(Integer.parseInt(item));
                    }

                    Collection<Integer> listOne = new ArrayList(final_items_combiOne);
                    //final_items_combiTwo = new ArrayList<>() ;
                    final_items_combiTwo.removeAll(listOne);

                    Log.e("combiOne _________ ", "____________ " + final_items_combiOne);
                    Log.e("combiTwo _________ ", "____________ " + final_items_combiTwo);
                    final ArrayList<Integer> final_items_combiOneFinal = final_items_combiOne;
                    final int count_sum_final = count_sum;
                    CombiOne = new ArrayList<>();
                    CombiTwo = new ArrayList<>();

                    for (int item : final_items_combiOneFinal) {
                        for (int q = 0; q < campaignListItem.size(); q++) {
                            if (item == Integer.parseInt(campaignListItem.get(q).getCampaignId()))
                                CombiOne.add(campaignListItem.get(q));
                        }

                    }

                    for (int item : final_items_combiTwo) {
                        for (int q = 0; q < campaignListItem.size(); q++) {
                            if (item == Integer.parseInt(campaignListItem.get(q).getCampaignId()))
                                CombiTwo.add(campaignListItem.get(q));
                        }

                    }
                    campaignListItemFinal = new ArrayList<>();

                    for (int p = 0; p < count_sum_final; p++) {

                        if (p % 2 == 0) {
                            //even
                            float sumC1 = Float.valueOf(0);
                            float avgC1 = Float.valueOf(0);
                            for (int l = 0; l < CombiOne.size(); l++) {
                                sumC1 = sumC1 + CombiOne.get(l).getCount();
                            }
                            avgC1 = sumC1 / CombiOne.size();
                            for (int l = 0; l < CombiOne.size(); l++) {
                                if (CombiOne.get(l).getCount() >= avgC1) {
                                    Log.e(TAG, "CombiOne.get(l) = " + CombiOne.get(l).getCampaignName());
                                    campaignListItemFinal.add(CombiOne.get(l));
                                    int a1 = CombiOne.get(l).getCount();
                                    a1 = a1 - 1;
                                    CombiOne.get(l).setCount(a1);
                                    break;
                                }
                            }


                        } else {
                            //odd
                            float sumC1 = Float.valueOf(0);
                            float avgC1 = Float.valueOf(0);
                            for (int l = 0; l < CombiTwo.size(); l++) {
                                sumC1 = sumC1 + CombiTwo.get(l).getCount();
                            }
                            avgC1 = sumC1 / CombiTwo.size();
                            for (int l = 0; l < CombiTwo.size(); l++) {
                                if (CombiTwo.get(l).getCount() >= avgC1) {
                                    Log.e(TAG, "CombiTwo.get(l) = __________________" + CombiTwo.get(l).getCampaignName());
                                    campaignListItemFinal.add(CombiTwo.get(l));
                                    int a1 = CombiTwo.get(l).getCount();
                                    a1 = a1 - 1;
                                    CombiTwo.get(l).setCount(a1);
                                    break;
                                }
                            }
                        }
                    }

                    Log.e(TAG, "CLIF ___ = " + campaignListItemFinal.toString());

                    //==============================================================================

                } else {

                    ArrayList<Integer> countList = new ArrayList<>();
                    campaignListItemFiller = new ArrayList<>();
                    newCount = new int[campaignListItem.size()];
                    // int[] n ={2,7,6,2,4,3};
                    int count_sum = 0, count_sum2 = 0;
                    for (i = 0; i < campaignListItem.size(); i++) {
                        if (campaignListItem.get(i).getCampaignType().equals("adv")) {
                            campaignListItemAdv.add(campaignListItem.get(i));
                            newCount[i] = campaignListItem.get(i).getCount();
                            if (DebugFlag)
                                Log.e("count CAMPAIGN ", "ADV ______ ____________ " + campaignListItem.get(i).getCount());
                            final_items_combiTwo.add(Integer.parseInt(campaignListItem.get(i).getCampaignId()));
                            campMap.put(i, campaignListItem.get(i));
                            count_sum = count_sum + campaignListItem.get(i).getCount();
                        } else {
                            campaignListItemFiller.add(campaignListItem.get(i));
                            //=====================================================
                            if (DebugFlag)
                                Log.e("count CAMPAIGN ", "FILLER ______ ____________ " + campaignListItem.get(i).getCount());
                            final_items_combiOne.add(Integer.parseInt(campaignListItem.get(i).getCampaignId()));
                            campMap2.put(i, campaignListItem.get(i));
                            count_sum2 = count_sum2 + campaignListItem.get(i).getCount();
                        }
                    }

                    campaignListItemSemiFinal = new ArrayList<>();
                    campaignListItemSemiFinal2 = new ArrayList<>();

                    for (i = 0; i < campaignListItem.size(); i++) {
                        if (campaignListItem.get(i).getCampaignType().equals("adv")) {
                            if (campaignListItem.get(i).getCount() != 0) {
                                int a = (int) (Math.ceil(count_sum / campaignListItem.get(i).getCount()));
                                campaignListItem.get(i).setPriority(a);
                                campaignListItemSemiFinal.add(campaignListItem.get(i));
                            }
                        } else {
                            if (campaignListItem.get(i).getCount() != 0) {
                                int a = (int) (Math.ceil(count_sum2 / campaignListItem.get(i).getCount()));
                                campaignListItem.get(i).setPriority(a);
                                campaignListItemSemiFinal2.add(campaignListItem.get(i));
                            }
                        }
                    }

                    Collections.sort(campaignListItemSemiFinal, new Comparator<CampaignlistItem>() {
                        public int compare(CampaignlistItem obj1, CampaignlistItem obj2) {
                            return Integer.valueOf(obj1.getPriority()).compareTo(Integer.valueOf(obj2.getPriority())); // To compare integer values
                        }
                    });

                    Collections.sort(campaignListItemSemiFinal2, new Comparator<CampaignlistItem>() {
                        public int compare(CampaignlistItem obj1, CampaignlistItem obj2) {

                            return Integer.valueOf(obj1.getPriority()).compareTo(Integer.valueOf(obj2.getPriority())); // To compare integer values
                        }
                    });

                    campaignListItemFinal = new ArrayList<>();
                    String id = "";
                    for (int p = 0; p < count_sum; p++) {
                        int u = 0;

                        for (int j = 0; j < campaignListItemSemiFinal.size(); j++) {
                            int x = 0, y = 0;
                            for (int k = 0; k < campaignListItemFinal.size(); k++) {
                                if (campaignListItemFinal.get(k).getCampaignId().equalsIgnoreCase(campaignListItemSemiFinal.get(j).getCampaignId())) {
                                    x = k;
                                    y = 1;
                                }
                            }
                            if (campaignListItemFinal.size() - x >= campaignListItemSemiFinal.get(j).getPriority() || (x == 0 && y == 0)) {
                                if (campaignListItemSemiFinal.get(j).getTempCount() < campaignListItemSemiFinal.get(j).getCount()) {
                                    campaignListItemSemiFinal.get(j).setTempCount(campaignListItemSemiFinal.get(j).getTempCount() + 1);
                                    campaignListItemFinal.add(campaignListItemSemiFinal.get(j));
                                    u = u + 1;
                                    break;
                                }
                            }
                        }

                        if (u == 0) {
                            for (int l = 0; l < campaignListItemSemiFinal.size(); l++) {

                                if (campaignListItemSemiFinal.get(l).getTempCount() < campaignListItemSemiFinal.get(l).getCount()) {
                                    if (!id.equalsIgnoreCase(campaignListItemSemiFinal.get(l).getCampaignId())) {
                                        campaignListItemSemiFinal.get(l).setTempCount(campaignListItemSemiFinal.get(l).getTempCount() + 1);
                                        campaignListItemFinal.add(campaignListItemSemiFinal.get(l));
                                        id = campaignListItemSemiFinal.get(l).getCampaignId();
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }

                filler_id = 0;
                thread = new Thread() {
                    public void run() {
                        if (myDB.deleteCampaignList()) {
                            if (DebugFlag)
                                Log.e(TAG, "??????  SUCCESS DELETING CampaignList  ____+++++++++++++++ " + CombiOne);

                            if (myDB.createCampaignList(campaignListItemFinal))
                                Log.e(TAG, "2388  SUCCESS createCampaignList  ____+++++++++++++++ ");
                            else
                                Log.e(TAG, "2388  ERROR createCampaignList  ______ - - - - - - - -");


                            if (!MISING_FLAG) {
                                if (!videoView.isPlaying()) {
                                    if (!ScratchTimerFLAG) {
                                        videoPlayback();
                                    }
                                }
                            }

                        } else {
                            if (DebugFlag)
                                Log.e(TAG, "??????  ERROR DELETING CampaignList  ____- - - - - - - - -  " + CombiOne);
                        }
                    }
                };
                thread.start();
            } else {
                if (("Failed").equalsIgnoreCase(campaign.getStatus())) {
                    if(("No Data").equalsIgnoreCase(campaign.getMessage())){
                        //claim_offer.setVisibility(View.GONE);
                        ScratchTimerFLAG = false;
                        tv_timer_count.setText("");
                        tv_timer_count.setBackground(getResources().getDrawable(R.color.black));
                        millisUntilFINISH = 0;
                        myDB.deleteCampaignList();
                        NO_DATA_FLAG=true;

                        fl_gif.setVisibility(View.VISIBLE);
                        iv_gif.setVisibility(View.VISIBLE);
                        Glide.with(mContext)
                                    .load(R.drawable.logo_madads)
                                    .into(iv_gif);

                        nodata();
                    }
                }
                api_hit = true;
                Log.e(TAG, "onSuccess: FAILED getVideoListByPincode _______________ NEW CAMPAIGN LIST ___________ ");

                Toast.makeText(mContext, campaign.getMessage(), Toast.LENGTH_SHORT).show();
                MyProgressDialog.dismiss();
            }
        }

        @Override
        public void onError(Throwable e) {
            api_hit = true;
            Log.e(TAG, "1994 - onError: getCampaignListByPincodCampaign >> " + e.toString());
            MyProgressDialog.dismiss();
            Toast.makeText(mContext, "1994 - " + ApplicationConstant.ANYTHING_WRONG, Toast.LENGTH_SHORT).show();
        }
    };


    private void nodata(){

        countDownTimerOfferLogData = new CountDownTimer(60000, 60000) {
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {

                if (NO_DATA_FLAG) {
                    NO_DATA_FLAG = false;


                    if (!MISING_FLAG) {
                        if (!videoView.isPlaying()) {
                            if (!ScratchTimerFLAG) {
                                savebitmapMethodCount();
                            }
                        }
                    }
                }

            }
        };
        countDownTimerOfferLogData.start();
    }
    //==============================================================================================

    //region Get Campaign List ""OFFER"" Pincode
    private void getCampaignListOfferFromPincode(String pincode, int age, String gender) {
        Log.e(TAG, "getCampaignListOfferFromPincode()()()" + pincode + "," + age + "," + gender);

        if (Utils.isConnectingToInternet(mContext)) {
            cabTabDetailsViewHolder.getCampaignDataOfferPincode(pincode, age, gender)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(getCampaignListOfferByPincode);
        } else {
            Toast.makeText(mContext, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }

    SingleObserver<CampaignOffer> getCampaignListOfferByPincode = new SingleObserver<CampaignOffer>() {
        @Override
        public void onSubscribe(Disposable d) {
            bag.add(d);
        }

        @Override
        public void onSuccess(CampaignOffer campaign) {

            if (campaign.getStatus().equalsIgnoreCase("Success")) {
                Log.e(TAG, "onSuccess: SUCCESS getCampaignListOfferByPincode _______________ NEW CAMPAIGN LIST ___________ ");

                deleteImage();
                campaignListItem_ = new ArrayList<>();
                campaignListItemOffer = new ArrayList<>();
                final_items_combiTwoOffer = new ArrayList<>();
                final_items_combiOneOffer = new ArrayList<>();
                campaignListItem_ = campaign.getCampaignlistOffer();

//
//                campaignListItemOfferFinal = new ArrayList<>();
//                //System.out.println(video.getRandomElement(campaignListItem.size()));
//                int x = campaignListItem_.size();
//                String vidId;
//                Log.e(TAG, "Sorted LIST ::::::::::::::     diff = start" );
//                int rand = 0;
//                int k= 0;
//                for (int i = 0; i<80; i++) {
//                    rand = new Random().nextInt(x);
//                    vidId = (campaignListItem_.get(rand).getOfferId());
//                    //for( k=0 ; k < Math.ceil(campaignListItemFinal.size()/3); k++ ){
//
//                    // }
//                    //rndNo(vidId,x,rand);
//                    campaignListItemOfferFinal.add(campaignListItem_.get(rand));
//                    Log.e(TAG, "Sorted LIST ::::::::::::::     diff = " + campaignListItem_.get(rand).getCampaignName());
//                    // Log.e(TAG, "onSuccess: " );
//                }

//
                Log.e(TAG, "2325 - " + campaignListItem_.toString());
                ArrayList<Integer> countList = new ArrayList<>();
                newCountOffer = new int[campaignListItem_.size()];
                int count_sum = 0, count_sum2 = 0;
                for (i = 0; i < campaignListItem_.size(); i++) {
                    if (campaignListItem_.get(i).getOfferData() != null) {
                        campaignListItemOffer.add(campaignListItem_.get(i));
                        newCountOffer[i] = campaignListItem_.get(i).getCount();
                        if (DebugFlag)
                            Log.e("count CAMPAIGN ", "OFFER ______ ____________ " + campaignListItem_.get(i).getCount());
                        final_items_combiTwoOffer.add(Integer.parseInt(campaignListItem_.get(i).getCampaignId()));
                        campMap2Offer.put(i, campaignListItem_.get(i));
                        count_sum = count_sum + campaignListItem_.get(i).getCount();
                    }
                }

                campaignListItemOfferSemiFinal = new ArrayList<>();
                campaignListItemOfferSemiFinal2 = new ArrayList<>();

                for (i = 0; i < campaignListItem_.size(); i++) {
                    if (campaignListItem_.get(i).getOfferData() != null) {
                        if (campaignListItem_.get(i).getCount() != 0) {
                            int a = (int) (Math.ceil(count_sum / campaignListItem_.get(i).getCount()));
                            campaignListItem_.get(i).setPriority(a);
                            campaignListItemOfferSemiFinal.add(campaignListItem_.get(i));
                        }
                    }
                }

                Collections.sort(campaignListItemOfferSemiFinal, new Comparator<CampaignlistOfferItem>() {
                    public int compare(CampaignlistOfferItem obj1, CampaignlistOfferItem obj2) {

                        return Integer.valueOf(obj1.getPriority()).compareTo(Integer.valueOf(obj2.getPriority())); // To compare integer values
                    }
                });

                campaignListItemOfferFinal = new ArrayList<>();
                String id = "";
                for (int p = 0; p < count_sum; p++) {
                    int u = 0;

                    for (int j = 0; j < campaignListItemOfferSemiFinal.size(); j++) {
                        int x = 0, y = 0;
                        for (int k = 0; k < campaignListItemOfferFinal.size(); k++) {
                            if (campaignListItemOfferFinal.get(k).getCampaignId().equalsIgnoreCase(campaignListItemOfferSemiFinal.get(j).getCampaignId())) {
                                x = k;
                                y = 1;
                            }
                        }
                        if (campaignListItemOfferFinal.size() - x >= campaignListItemOfferSemiFinal.get(j).getPriority() || (x == 0 && y == 0)) {
                            if (campaignListItemOfferSemiFinal.get(j).getTempCount() < campaignListItemOfferSemiFinal.get(j).getCount()) {
                                campaignListItemOfferSemiFinal.get(j).setTempCount(campaignListItemOfferSemiFinal.get(j).getTempCount() + 1);
                                campaignListItemOfferFinal.add(campaignListItemOfferSemiFinal.get(j));
                                u = u + 1;
                                break;
                            }
                        }
                    }

                    if (u == 0) {
                        for (int l = 0; l < campaignListItemOfferSemiFinal.size(); l++) {

                            if (campaignListItemOfferSemiFinal.get(l).getTempCount() < campaignListItemOfferSemiFinal.get(l).getCount()) {
                                if (!id.equalsIgnoreCase(campaignListItemOfferSemiFinal.get(l).getCampaignId())) {
                                    campaignListItemOfferSemiFinal.get(l).setTempCount(campaignListItemOfferSemiFinal.get(l).getTempCount() + 1);
                                    campaignListItemOfferFinal.add(campaignListItemOfferSemiFinal.get(l));
                                    id = campaignListItemOfferSemiFinal.get(l).getCampaignId();
                                    break;
                                }
                            }
                        }
                    }
                }

//=================================================================================================================================================
                Log.e(TAG, "2428 - " + campaignListItemOfferFinal.toString());
                filler_id = 0;
                thread = new Thread() {
                    public void run() {
                        if (myDB.deleteCampaignListOffer()) {
                            myDB.createCampaignListOffer(campaignListItemOfferFinal);
                            if (videoLogDataToServerTimerFlag) {

                                video_review = "None";
                                api_hit = false;
                                videoLogDataToServerTimerFlag = false;
                                VideoLogDataToServerTimer();
                            } else {
                                if (countDownTimerScratchFACEOFF == null)
                                    videoPlayback();
                            }
                        } else {
                            if (DebugFlag)
                                Log.e(TAG, " 2453 ??????  ERROR DELETING CampaignList  ____ " + CombiOne);
                        }
                    }
                };
                thread.start();

            } else {
                api_hit = true;
                Log.e(TAG, "2463 - onSuccess: FAILED getCampaignListOfferByPincode _______________ NEW CAMPAIGN LIST ___________ ");
                Toast.makeText(mContext, campaign.getMessage(), Toast.LENGTH_SHORT).show();
                MyProgressDialog.dismiss();
            }
        }

        @Override
        public void onError(Throwable e) {
            api_hit = true;
            Log.e(TAG, "2473 - onError: getCampaignListOfferByPincode >> " + e.toString());
            MyProgressDialog.dismiss();
            Toast.makeText(mContext, "2473 - " + ApplicationConstant.ANYTHING_WRONG, Toast.LENGTH_SHORT).show();
        }
    };

    //==============================================================================================
    //region Get Campaign List Filler Pincode
    private void CampaignListFillerFromPincode(String pincode) {
        if (myDB.deleteCampaignListFiller()) {
            myDB.createCampaignListFiller(campaignListItemFinal2);

            video_review = "None";
            api_hit = false;

            VideoLogDataToServerTimer();

            if (!newDataFlag) {
                newDataFlag = true;
                videoPlayback();
            } else if (videoView.isPlaying()) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        int pos = videoView.getDuration() - videoView.getCurrentPosition();
                        countDownTimer = new CountDownTimer(pos, pos) {
                            @Override
                            public void onTick(long millisUntilFinished) {

                            }

                            @Override
                            public void onFinish() {
                                videoPlayback();
                            }
                        };

                        countDownTimer.start();
                    }
                });

            }
        } else {
            Log.e(TAG, "??????  ERROR DELETING CampaignListFiller  ____ " + CombiOne);
        }
    }

    ;


    /**
     * A helper Method for calculating the sum from a string of integers
     *
     * @param intString the string subset
     * @return the sum of the string subset
     */
    private static int getSum(String intString) {
        int sum = 0;
        StringTokenizer sTokens = new StringTokenizer(intString, token);
        int counter = 0;
        while (sTokens.hasMoreElements()) {
            counter++;
            sum += Integer.parseInt((String) sTokens.nextElement());
        }
        int ssum = 0;
        if (counter == 0)
            ssum = 0;
        else
            ssum = sum / counter;
        return ssum;
    }


    /**
     * The method for finding the subsets that sum to a target.
     *
     * @param input  The input array to be processed for subset with particular sum
     * @param target The target sum we are looking for
     * @param ramp   The Temporary String to be beefed up during recursive iterations(By default value an empty String)
     * @param index  The index used to traverse the array during recursive calls
     */
    public void findTargetSumSubsets(ArrayList<CampaignlistItem> input, int target, String ramp, int index) {

        if (index > (input.size() - 1)) {
            allSubsets.add(ramp);
            return;
        }

        //First recursive call going ahead selecting the int at the currenct index value
        findTargetSumSubsets(input, target, ramp + input.get(index).getCampaignId() + token, index + 1);
        //Second recursive call going ahead WITHOUT selecting the int at the currenct index value
        findTargetSumSubsets(input, target, ramp, index + 1);
    }

    //================================================================================================

    private void calfunction(final RetrofitInterface downloadService2, final int count1) {

        Log.e(TAG, " call videoListDB.size()==0 ______________  i = " + count1);

        String urll = videosListItem.get(count1).getVideoUrl();

        if (!dontDownloadExistingVideos(urll)) {
            urll = "http://madads.in/maddy/assets/uploaded-video/" + urll;
            Log.e(TAG, "URL = " + urll);
            call = downloadService2.downloadFileWithDynamicUrlAsync(urll);

            //Log.e(TAG,"i ==================== call ================================== "+i);
            final int finalI = count1;
            this.call.enqueue(new Callback<ResponseBody>() {
                @SuppressLint("StaticFieldLeak")
                @Override
                public void onResponse(@NonNull final Call<ResponseBody> call, @NonNull final Response<ResponseBody> response) {

                    if (response.isSuccessful()) {
                        new AsyncTask<Void, Void, Void>() {
                            @Override
                            protected Void doInBackground(Void... voids) {

                                assert response.body() != null;

                                if (finalI < (videosListItem.size())) {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(VideoFaceDetectionActivity.this, "Video Downloading .........", Toast.LENGTH_LONG).show();
                                        }
                                    });
                                    Log.e(TAG, "doInBackground j = " + finalI);
                                    boolean writtenToDisk = writeResponseBodyToDisk(response.body(), finalI);
                                    if (writtenToDisk) {
                                        Log.e(TAG, "server  file download was a success ? " + writtenToDisk);
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(VideoFaceDetectionActivity.this, "Video Downloaded SUCCESS", Toast.LENGTH_LONG).show();
                                            }
                                        });
                                        myDB.createRecordsVideoList(Integer.parseInt(videosListItem.get(finalI).getVideoListId()),
                                                Integer.parseInt(videosListItem.get(finalI).getCustomerId()), videosListItem.get(finalI).getVideoName(),
                                                videosListItem.get(finalI).getVideoUrl(), videosListItem.get(finalI).getVideoDuration(),
                                                videosListItem.get(finalI).getVideoFormat(), videosListItem.get(finalI).getVideoType(), videosListItem.get(finalI).getVideoAdded(),videosListItem.get(finalI).getVideoSize());
                                        if (finalI < videosListItem.size() - 1) {
                                            l = finalI;
                                            l++;
                                            if (videosListItem.size() > l) {
                                                calfunction(downloadService2, l);
                                            }
                                        } else {
                                        }
                                    } else {
                                        calfunction(downloadService2, finalI);
                                    }
                                }
                                return null;
                            }

                            @Override
                            protected void onProgressUpdate(Void... values) {
                            }

                            @Override
                            protected void onPostExecute(Void aVoid) {
                                Log.e(TAG, "size = 0 >>>>>>>>>>>>>>>> onPostExecute j = " + finalI);
                            }
                        }.execute();

                    } else {

                        Log.e(TAG, "callFunction server contact failed");
                        l = l + 1;
                        if (videosListItem.size() > l) {
                            calfunction(downloadService2, l);
                        }
                    }
                }

                @Override
                public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                    Log.e(TAG, "Server  error");
                }
            });
        } else {
            videoIds = videoIds + videosListItem.get(count1).getVideoListId() + " , ";
//            myDB.createRecordsVideoList(Integer.parseInt(videosListItem.get(count1).getVideoListId()),
//                    Integer.parseInt(videosListItem.get(count1).getCustomerId()), videosListItem.get(count1).getVideoName(),
//                    videosListItem.get(count1).getVideoUrl(), videosListItem.get(count1).getVideoDuration(),
//                    videosListItem.get(count1).getVideoFormat(), videosListItem.get(count1).getVideoType(),
//                    videosListItem.get(count1).getVideoAdded());

            l = l + 1;
            if (l < videosListItem.size()) {
                calfunction(downloadService2, l);
            } else {
                delelteVideos();
            }
        }
    }

    private void delelteVideos() {

        String path = Environment.getExternalStorageDirectory().toString() + "/Madads/";
        Log.e("Files", "Path: " + path);
        File directory = new File(path);
        File[] files = directory.listFiles();
        ArrayList<String> filesArrayList = new ArrayList();
        ArrayList<String> tempArrayList = new ArrayList();
        Log.e("Files", "Size: " + files.length);
        for (int i = 0; i < files.length; i++) {
            filesArrayList.add(files[i].getName());
            Log.e("Files", "FileName:" + files[i].getName());
        }
        //videoListDB_download = myDB.getAllVideos();
        videoIdsDeletion = new ArrayList<>();
        videoListDBNames = myDB.getCabVideosListNames();
        videoListDB_download = myDB.getAllVideos();
        for (int p = 0; p < videoListDB_download.size(); p++) {
            String urll = videoListDBNames.get(p);
            videoIdsDeletion.add(/*Environment.getExternalStorageDirectory()+ "/Madads/"  +*/urll);
        }

        for (String item : filesArrayList) {
            if (!videoIdsDeletion.contains(item)) {
                tempArrayList.add(item);
            }
        }
        Log.e(TAG, "" + tempArrayList.toString());
        for (int i = 0; i < tempArrayList.size(); i++) {
            String path2 = tempArrayList.get(i);
            File fdelete = new File(Environment.getExternalStorageDirectory()
                    + "/Madads", File.separator + path2);
            if (fdelete.exists()) {
                if (fdelete.delete()) {
                    Log.e("-->", "__________ file Deleted :" + path2);
                } else {
                    Log.e("-->", "__________ file not Deleted :" + path2);
                }
            }
        }
        videoIds = videoIds.substring(0, videoIds.lastIndexOf(","));
        Log.e(TAG, "2881 - videoIds = " + videoIds);

        videoIds = "";
    }

    private boolean writeResponseBodyToDisk(ResponseBody body, int j) {

        try {
            final int min = 20;
            final int max = 8000;
            final int random = new Random().nextInt((max - min) + 1) + min;
            String folder_main = "Madads";

            if (j < videosListItem.size()) {
                // todo change the file location/name according to your needs
                File imageFile = null;

                //Localhost
                String urll = videosListItem.get(j).getVideoUrl();
                urll = urll.replace("http://madads.in/maddy/assets/uploaded-video/", "");

                imageFile = new File(Environment.getExternalStorageDirectory()
                        + "/" + folder_main, File.separator + urll/*"MadAds"+random+".mp4"*/);
                Log.e(TAG, "videosListItem [ " + j + " ] ==================== " + urll);
                mCurrentPhotoPath = imageFile.getAbsolutePath();

                InputStream inputStream = null;
                OutputStream outputStream = null;

                try {
                    byte[] fileReader = new byte[4096];

                    long fileSize = body.contentLength();

                    long fileSizeDownloaded = 0;

                    inputStream = body.byteStream();
                    outputStream = new FileOutputStream(imageFile);
                    Log.e(TAG, "imageFile [ " + j + " ] ==================== " + imageFile.getName());

                    while (true && j < videosListItem.size()) {
                        int read = inputStream.read(fileReader);

                        if (read == -1) {
                            break;
                        }

                        outputStream.write(fileReader, 0, read);

                        fileSizeDownloaded += read;
                        final long finalFileSizeDownloaded = fileSizeDownloaded;
                    }
                    Log.e(TAG, "file download: " + imageFile.getName() + " >>> " + fileSizeDownloaded + " of " + fileSize + "duration");
                    if (fileSize == fileSizeDownloaded) {
                        previousDownload = true;
                        Log.e(TAG, "videosListItem.get(" + j + ").getVideoUrl() ::::  " + videosListItem.get(j).getVideoUrl());

                        name_ = imageFile.getAbsolutePath();

                        return_flag = false;
                        imageFile = null;
                        return true;
                    } else {
                        File fdelete = new File(mCurrentPhotoPath);
                        if (fdelete.exists()) {
                            if (fdelete.delete()) {
                                Log.e("-->", "file Deleted :" + mCurrentPhotoPath);
                            } else {
                                Log.e("-->", "file not Deleted :" + mCurrentPhotoPath);
                            }
                        }
                        calfunction(downloadService2, j);
                        return false;
                    }

                } catch (IOException e) {
                    File fdelete = new File(mCurrentPhotoPath);
                    if (fdelete.exists()) {
                        if (fdelete.delete()) {
                            Log.e("-->", "file Deleted :" + mCurrentPhotoPath);
                        } else {
                            Log.e("-->", "file not Deleted :" + mCurrentPhotoPath);
                        }
                    }
                    Log.e(TAG, "Inner  IOException ????????????  " + e);
                    if (videosListItem.size() > l) {
                        calfunction(downloadService2, j);
                    }

                    return false;
                } finally {
                    if (inputStream != null) {
                        inputStream.close();
                    }

                    if (outputStream != null) {
                        outputStream.close();
                    }
                }
            } else {
                return false;
            }
        } catch (IOException e) {
            File fdelete = new File(mCurrentPhotoPath);
            if (fdelete.exists()) {
                if (fdelete.delete()) {
                    Log.e("-->", "file Deleted :" + mCurrentPhotoPath);
                } else {
                    Log.e("-->", "file not Deleted :" + mCurrentPhotoPath);
                }
            }
            Log.e(TAG, "Outer  IOException ????????????  " + e);
            if (videosListItem.size() > l) {
                calfunction(downloadService2, l);
            }
            return false;
        }
    }

    public boolean dontDownloadExistingVideos(String video_path) {
        boolean flag = false;
        String path = Environment.getExternalStorageDirectory().toString() + "/Madads/";
        Log.e("Files", "Path: " + path);
        File directory = new File(path);
        File[] files = directory.listFiles();
        ArrayList<String> filesArrayList = new ArrayList();
        ArrayList<String> tempArrayList = new ArrayList();
        Log.e("Files", "Size: " + files.length);
        for (int i = 0; i < files.length; i++) {
            String absolutePath = files[i].getAbsolutePath();
            absolutePath = absolutePath.replace("/storage/emulated/0/Madads/", "");
            filesArrayList.add(absolutePath);
            Log.e("Files", "FileName:" + absolutePath);
        }

        for (String item : filesArrayList) {
            if (video_path.contains(item)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    //region Get Product Response
    private void getDataFromPincode(String pincode) {
        if (Utils.isConnectingToInternet(mContext)) {
            cabTabDetailsViewHolder.addDataPincode(pincode)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(getVideoListByPincode);
        } else {
            Toast.makeText(mContext, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }

    SingleObserver<Video> getVideoListByPincode = new SingleObserver<Video>() {
        @Override
        public void onSubscribe(Disposable d) {
            bag.add(d);
        }

        @Override
        public void onSuccess(Video video) {
            Log.e(TAG, "onSuccess: getVideoListByPincode __________________________________________________________ ");
            videoIds = "";
            if (video.getStatus().equalsIgnoreCase("Success")) {
                videosListItem = new ArrayList<>();
                videosListItem = video.getVideolist();
                Log.e(TAG, "videosListItem Size  = ____________________________________________ " + videosListItem.size());

                downloadVideos();

            } else {
                Toast.makeText(mContext, "Splash - 470 - " + video.getMessage(), Toast.LENGTH_SHORT).show();
                MyProgressDialog.dismiss();
            }
        }

        @Override
        public void onError(Throwable e) {
            Log.e(TAG, "onError: getVideoListByPincode >> " + e.toString());
            MyProgressDialog.dismiss();
            Toast.makeText(mContext, ApplicationConstant.ANYTHING_WRONG, Toast.LENGTH_SHORT).show();
        }
    };


    //endregion
    public void downloadVideos() {
        ArrayList<String> videoIdsDeletion = new ArrayList<>();
        if (CheckNetwork.isInternetAvailable(mContext)) {
            Thread thread = new Thread() {
                public void run() {

                    Looper.prepare();

                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // Do Work
                            Retrofit retrofit2 = RetrofitClientVideo.getInstance();
                            RetrofitInterface downloadService2 =
                                    retrofit2.create(RetrofitInterface.class);
                            videoListDB_download = new ArrayList<>();

                            if (videosListItem != null) {

                                videoListDB_download = myDB.getAllVideos();
                                Log.e(TAG, "videoListDB_download = " + videoListDB_download);
                                calfunction(downloadService2, 0);

                            }
                        }
                    }, 0);

                    Looper.loop();
                }
            };
            thread.start();
        } else
            Toast.makeText(mContext, "No Internet Connection", Toast.LENGTH_SHORT).show();
    }

}
