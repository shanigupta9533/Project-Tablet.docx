package com.dragosholbann.androidfacedetection;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.PendingIntent;
import android.app.admin.DevicePolicyManager;
import android.app.admin.SystemUpdatePolicy;
import android.arch.lifecycle.ViewModelProviders;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.pm.PackageInfo;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.media.MediaMetadataRetriever;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.UserManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dragosholbann.androidfacedetection.ApiModels.City;
import com.dragosholbann.androidfacedetection.ApiModels.CityModel;
import com.dragosholbann.androidfacedetection.ApiModels.Country;
import com.dragosholbann.androidfacedetection.ApiModels.CountryModel;
import com.dragosholbann.androidfacedetection.ApiModels.Pincode;
import com.dragosholbann.androidfacedetection.ApiModels.PincodeModel;
import com.dragosholbann.androidfacedetection.ApiModels.State;
import com.dragosholbann.androidfacedetection.ApiModels.StateModel;
import com.dragosholbann.androidfacedetection.DBHelper.MyDB;
import com.dragosholbann.androidfacedetection.Interfaces.RetrofitInterface;
import com.dragosholbann.androidfacedetection.ModelLayer.Entities.CabDetails;
import com.dragosholbann.androidfacedetection.ModelLayer.Entities.Cabtabdetail;
import com.dragosholbann.androidfacedetection.ModelLayer.Entities.CampaignlistItem;
import com.dragosholbann.androidfacedetection.ModelLayer.Entities.Video;
import com.dragosholbann.androidfacedetection.ModelLayer.Entities.VideolistItem;
import com.dragosholbann.androidfacedetection.NetworkCheck.CheckNetwork;
import com.dragosholbann.androidfacedetection.RetrofitClients.RetrofitClientVideo;
import com.dragosholbann.androidfacedetection.constants.ApplicationConstant;
import com.dragosholbann.androidfacedetection.utils.MyProgressDialog;
import com.dragosholbann.androidfacedetection.utils.Utils;
import com.dragosholbann.androidfacedetection.viewModels.CabTabDetailsViewHolder;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.InstallState;
import com.google.android.play.core.install.InstallStateUpdatedListener;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.InstallStatus;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.tasks.Task;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import static android.view.View.VISIBLE;
import static com.google.firebase.crash.FirebaseCrash.log;

public class SplashActivity extends AppCompatActivity implements android.location.LocationListener
{
    public static final String ACTION_INSTALL_COMPLETE
            = "com.ensivosolutions.inappupdatedemo.INSTALL_COMPLETE";
    protected static final int REQUEST_CHECK_SETTINGS = 50;
    protected static final int MY_REQUEST_CODE = 77;
    LocationManager lm;
    ArrayList<String> videoIdsDeletion = new ArrayList<>();
    MyDB myDB;
    boolean onceSetPincode = false;
    Context mContext;
    ProgressBar progressBar;
    Retrofit retrofit;
    private static final String TAG = "SPLASH ACTIVITY ___";
    CompositeDisposable bag = new CompositeDisposable();
    CabTabDetailsViewHolder cabTabDetailsViewHolder ;
    //CabTabDetailsViewHolder cabTabDetailsViewHolder ;

    List<Cabtabdetail> cabTabDetailsList ;
    List<CabDetails> cabDetails ;
    ArrayList<VideolistItem> videosListItem  ;
    String videoIds = "";

    Boolean return_flag = false , temp_flag = false,db_flag=false;
    String[] new_urls;
    Boolean previousDownload = false;
    String name_;
    String mCurrentPhotoPath=null;
    int z=0;
    int i = 0;
    String tempPostalCode = "0";
    ArrayList<Integer>  videoListDB;
    ArrayList<String>  videoListDBNames;
    Call<ResponseBody> call;
    Retrofit retrofit2;
    RetrofitInterface downloadService2;
    int l=0;
    int[] newCount;

    /**
     * The collection for storing the unique sets that sum to a target.
     */
    private static HashSet<String> allSubsets = new HashSet<>();

    /**
     * The String token
     */
    private static final String token = " ";
    static int pincount=0;
    int difference_global=0,my_map_key=111;
    public static String postalCode=null;
    static String postalCodenew=null;

    private static HashSet<String> allCombinations = new HashSet<>();
    private static HashSet<String> selectedCombinations = new HashSet<>();
    Map<Integer, String> myMap = new HashMap<Integer, String>();
    LinkedHashMap<Integer, CampaignlistItem> campMap = new LinkedHashMap<>();
    ArrayList<Integer> temp_items_combiTwo = new ArrayList<>() ;
    ArrayList<Integer> final_items_combiTwo = new ArrayList<>() ;
    ArrayList<CampaignlistItem> CombiOne = new ArrayList<>() ;
    ArrayList<CampaignlistItem> DBCampaignList ;

    ArrayList<CampaignlistItem> CombiTwo = new ArrayList<>() ;
    Thread thread;
    public final static int REQUEST_CODE = 5463;
    public final static int REQUEST_APP_UPDATE = 5544;
    static DevicePolicyManager deviceManger;
    static ComponentName compName;
    PackageManager mPackageManager;
    CountDownTimer countDownTimer;
    ImageView iv_splash;

    @SuppressLint("MissingPermission")
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        deviceManger = (DevicePolicyManager)getSystemService(
                Context.DEVICE_POLICY_SERVICE);
        compName = new ComponentName(this, MyAdmin.class);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        hideSystemUI(getWindow());
        setContentView(R.layout.activity_splash);

        View decorView = getWindow().getDecorView();
        // Hide both the navigation bar and the status bar.
        // SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
        // a general rule, you should design your app to hide the status bar whenever you
        // hide the navigation bar.
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        initViews();

        mContext = SplashActivity.this;
        myDB = new MyDB(mContext);
        iv_splash = findViewById(R.id.imageViewSplash);

        Glide.with(mContext)
                .load(R.drawable.logo_madads)

                .into(iv_splash);


        if (!Settings.canDrawOverlays(this)) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, 0);
        }
        //checkrootcommand();


        initViewHolder();
        runtimePermissions();

    }





    public static void hideSystemUI(Window window) {
        window.getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LOW_PROFILE
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        );


    }


    @Override
    public void onBackPressed() {



    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {


        }
        else if (event.getKeyCode() == KeyEvent.KEYCODE_HOME) {


        }
        else if (event.getKeyCode() == KeyEvent.KEYCODE_SOFT_LEFT) {


        }
        else if (event.getKeyCode() == KeyEvent.KEYCODE_SOFT_RIGHT) {


        }
        else if (event.getKeyCode() == KeyEvent.KEYCODE_SOFT_SLEEP) {


        }
        else if (event.getKeyCode() == KeyEvent.KEYCODE_SYSTEM_NAVIGATION_DOWN) {


        }
        else if (event.getKeyCode() == KeyEvent.KEYCODE_SYSTEM_NAVIGATION_LEFT) {


        }
        else if (event.getKeyCode() == KeyEvent.KEYCODE_SYSTEM_NAVIGATION_RIGHT) {


        }
        else if (event.getKeyCode() == KeyEvent.KEYCODE_SYSTEM_NAVIGATION_UP) {


        }
        else if (event.getKeyCode() == KeyEvent.KEYCODE_STB_POWER) {


        }
        else if (event.getKeyCode() == KeyEvent.KEYCODE_ALL_APPS) {


        }
        else if (event.getKeyCode() == KeyEvent.KEYCODE_APP_SWITCH) {


        }
        else if (event.getKeyCode() == KeyEvent.KEYCODE_POWER) {


        }
        else if (event.getKeyCode() == KeyEvent.KEYCODE_AVR_POWER) {


        }


        return true;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            if (!hasFocus) {
                hideSystemUI(getWindow());
                Intent closeDialog = new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
                sendBroadcast(closeDialog);


                // Method that handles loss of window focus
                new BlockStatusBar(this,false).collapseNow();


            }
        }
    }

/*

    private boolean videoFileIsCorrupted(String path){

        MediaMetadataRetriever retriever = new MediaMetadataRetriever();

        try {
            retriever.setDataSource(mContext, Uri.parse(path));
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

        String hasVideo = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_HAS_VIDEO);
       if("yes".equals(hasVideo))
           Log.e(TAG, path+" -------////////----- NOT CORRUPTED NOT");
       else
           Log.e(TAG, path+" -------////////----- CORRUPTED CORRUPTED CORRUPTED");

        return "yes".equals(hasVideo);
    }
*/

    private int getVideoSize(String path){

        // String filepath = Environment.getExternalStorageDirectory() + "/file.mp4";

        if(path!=null){
            File file = new File(path);
            int length =(int) file.length();
            length = length/1024;
            return  length;
        }
        else
            return 0;

    }


    public int checkrootcommand() {
        // TODO Auto-generated method stub
        Process exec = null;
        try {
            Log.e(TAG, " ... inside ...     checkrootcommand");

            exec = Runtime.getRuntime().exec(new String[]{"su","-c","pm grant com.dragosholbann.androidfacedetection android.permission.WAKE_LOCK"});
            final OutputStreamWriter out = new OutputStreamWriter(exec.getOutputStream());
            out.write("exit");
            out.flush();
            exec.waitFor();

            Log.e(TAG, "su command executed successfully");
            //runtimePermissions();
            return 0; // returns zero when the command is executed successfully
        } catch (IOException | InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (exec != null) {
                try {
                    exec.destroy();
                } catch (Exception ignored) {
                }
            }
        }
        return 1; //returns one when the command execution fails
    }

    //----------------------------------------------------------------------------------------------
    private void addingCredentials()
    {
        if(deviceManger.isAdminActive(compName))
        {
            Toast.makeText(mContext, "Admin ACTIVE", Toast.LENGTH_SHORT).show();
            if(!deviceManger.isDeviceOwnerApp(getPackageName()))
                setDeviceOwnerByRootAccess();
            else
                Toast.makeText(mContext, "DEVICE OWNER NOT SET", Toast.LENGTH_SHORT).show();
        }
        else
            setDeviceADMINbyRootAccess();
    }
    private void removingCredentials()
    {
        Log.e(TAG,"removingCredentials    ... inside ...    ");
        if(deviceManger.isAdminActive(compName))
            clearDeviceAdmin();
        else
            Toast.makeText(mContext, "Device admin - not active", Toast.LENGTH_SHORT).show();
    }
    // Clears Device Admin & Owner Programatically
    private void clearDeviceAdmin() {
        Log.e(TAG,"clearDeviceAdmin    ... inside ...    ");
        deviceManger.removeActiveAdmin(compName);
        clearDeviceOwner();
    }

    private void clearDeviceOwner() {
        if(deviceManger.isDeviceOwnerApp("com.dragosholbann.androidfacedetection"))
        {
            Log.e(TAG,"clearDeviceOwner    ... inside ...    ");
            deviceManger.clearDeviceOwnerApp(mContext.getPackageName());
            switchToApp();
        }
        else
        {
            Toast.makeText(mContext, "865 - Device Owner not set to current app", Toast.LENGTH_SHORT).show();
        }
    }

    //Uninstall application by root access
    public  void uninstallApp ()
    {
        try{
            Process su = Runtime.getRuntime().exec("su");
            DataOutputStream outputStream = new DataOutputStream(su.getOutputStream());

            outputStream.writeBytes("pm uninstall com.dragosholbann.androidfacedetection\n");
            outputStream.flush();

            outputStream.writeBytes("exit\n");
            outputStream.flush();
            su.waitFor();
        }catch(IOException e){
            Log.e("uninstallApp",""+e);
        }catch(InterruptedException e){
            Log.e("uninstallApp",""+e);
        }
    }

    //Set Device Owner by root access
    public  void setDeviceOwnerByRootAccess ()
    {
        Log.e("setDeviceOwnerRoot","   ...Inside...   ");

        try{
            String command;
            command = "dpm set-device-owner com.dragosholbann.androidfacedetection/.MyAdmin";
            Log.e("COMMAND:", command);
            Process su = Runtime.getRuntime().exec(new String[]{"su", "-c",  command});

            su.waitFor();

            Log.e("setDeviceOwnerRoot","TRY");
            activateLTM();
        }catch(IOException e){
            Log.e("setDeviceOwnerRoot",""+e);
        }catch(InterruptedException e){
            Log.e("setDeviceOwnerRoot",""+e);
        }
    }

    //Set Device Owner by root access
    public  void setDeviceADMINbyRootAccess ()
    {
        Log.e("setDeviceADMINRoot","   ...Inside...   ");
        try{
            String command;
            command = "dpm set-active-admin --user current com.dragosholbann.androidfacedetection/.MyAdmin";
            Log.e("COMMAND:", command);
            Process su = Runtime.getRuntime().exec(new String[]{"su", "-c",  command});

            su.waitFor();
            Log.e("setDeviceADMINRoot","TRY");

            if(deviceManger.isAdminActive(compName))
            {
                if(!deviceManger.isDeviceOwnerApp(getPackageName()))
                    setDeviceOwnerByRootAccess();
            }
        }catch(IOException e){
            Log.e("setDeviceADMINRoot",""+e);
        }catch(InterruptedException e){
            Log.e("setDeviceADMINRoot",""+e);
        }
    }

    private void switchToApp()
    {
        Log.e(TAG,"switchToApp    ... inside ...    ");
        PackageManager pm = mContext.getPackageManager();
        Intent intent = pm.getLaunchIntentForPackage("com.ensivosolutions.inappupdatedemos");
        if (intent != null) {
            Log.e(TAG,"switchToApp    ... inside startActivity ...    ");
            mContext.startActivity(intent);
        }
        finish();
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    public void activateLTM()
    {
        mPackageManager = this.getPackageManager();

        if ( deviceManger.isDeviceOwnerApp(
                getApplicationContext().getPackageName())) {
            mPackageManager.setComponentEnabledSetting(
                    new ComponentName(getApplicationContext(),
                            TempClass.class),
                    //VideoFaceDetectionActivity.class),
                    PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                    PackageManager.DONT_KILL_APP);

            // start lock task mode if its not already active
            if(deviceManger.isLockTaskPermitted(this.getPackageName())){
                ActivityManager am = (ActivityManager) getSystemService(
                        Context.ACTIVITY_SERVICE);
                if(am.getLockTaskModeState() ==
                        ActivityManager.LOCK_TASK_MODE_NONE) {
                    startLockTask();
                }
            }

            // Set Default COSU policy
            deviceManger = (DevicePolicyManager) getSystemService(
                    Context.DEVICE_POLICY_SERVICE);
            mPackageManager = getPackageManager();
            if(deviceManger.isDeviceOwnerApp(getPackageName())){
                setDefaultCosuPolicies(true);
            }
            else {
                Toast.makeText(getApplicationContext(),
                        R.string.not_device_owner,Toast.LENGTH_SHORT)
                        .show();
            }
        } else {
            Toast.makeText(getApplicationContext(),
                    "Device OWNER not active.",Toast.LENGTH_SHORT)
                    .show();
        }
    }

    public void deactivateLTM(View view) {

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
    private void setDefaultCosuPolicies(boolean active){
        // set user restrictions
        setUserRestriction(UserManager.DISALLOW_SAFE_BOOT, active);
        setUserRestriction(UserManager.DISALLOW_FACTORY_RESET, active);
        setUserRestriction(UserManager.DISALLOW_ADD_USER, active);
        setUserRestriction(UserManager.DISALLOW_MOUNT_PHYSICAL_MEDIA, active);
        setUserRestriction(UserManager.DISALLOW_CREATE_WINDOWS, active);

        // disable keyguard and status bar
        if(active)
            deviceManger.setKeyguardDisabled(compName, active);

        deviceManger.setStatusBarDisabled(compName, active);

        // enable STAY_ON_WHILE_PLUGGED_IN
        enableStayOnWhilePluggedIn(active);

        // set system update policy
        if (active){
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
                            getPackageName(), TempClass.class.getName()));
            //  getPackageName(), VideoFaceDetectionActivity.class.getName()));
        } else {
            deviceManger.clearPackagePersistentPreferredActivities(
                    compName, getPackageName());
        }
    }

    private void setUserRestriction(String restriction, boolean disallow){
        if (disallow) {
            deviceManger.addUserRestriction(compName,
                    restriction);
        } else {
            deviceManger.clearUserRestriction(compName,
                    restriction);
        }
    }

    private void enableStayOnWhilePluggedIn(boolean enabled){
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

    @Override
    protected void onResume() {
        super.onResume();

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public boolean checkDrawOverlayPermission() {
        /** check if we already  have permission to draw over other apps */
        if (!Settings.canDrawOverlays(mContext)) {
            /** if not construct intent to request permission */
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName()));
            /** request permission via start activity for result */
            startActivityForResult(intent, REQUEST_CODE);
            return  false;
        }
        else
            return true;

    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        hideSystemUI(getWindow());
        /** check if received result code
         is equal our requested code for draw permission  */
        if (requestCode == REQUEST_CODE) {
            if (Settings.canDrawOverlays(this)) {
                // continue here - permission was granted

            }
        }

        if (requestCode == MY_REQUEST_CODE) {
            if (resultCode != RESULT_OK) {
                log("Update flow failed! Result code: " + resultCode);
                // If the update is cancelled or fails,
                // you can request to start the update again.
            }
        }
    }

    /*@TargetApi(19)
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }*/

    private  void runtimePermissions()
    {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
                &&     ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED  &&     ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED  &&     ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED  &&     ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED  &&     ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_SETTINGS)
                != PackageManager.PERMISSION_GRANTED
        ) {

            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.CAMERA,Manifest.permission.WRITE_SETTINGS},REQUEST_CHECK_SETTINGS);
            /*try{
                Log.e("setRuntimePermiRoOt"," ... inside ... ");

                Process su = Runtime.getRuntime().exec(new String[]{"su", "-c",
                                "pm grant com.dragosholbann.androidfacedetection android.permission.WRITE_EXTERNAL_STORAGE;" +
                                "pm grant com.dragosholbann.androidfacedetection android.permission.READ_EXTERNAL_STORAGE;" +
                                "pm grant com.dragosholbann.androidfacedetection android.permission.WRITE_SETTINGS;" +
                                "pm grant com.dragosholbann.androidfacedetection android.permission.CAMERA;" +
                                *//*"pm grant com.dragosholbann.androidfacedetection android.hardware.camera;" +
                                "pm grant com.dragosholbann.androidfacedetection android.hardware.camera.autofocus;" +*//*
                                "pm grant com.dragosholbann.androidfacedetection android.permission.ACCESS_COARSE_LOCATION;" +
                                "pm grant com.dragosholbann.androidfacedetection android.permission.ACCESS_FINE_LOCATION;"+
                                "pm grant com.dragosholbann.androidfacedetection android.permission.RECEIVE_SMS;"+
                                "pm grant com.dragosholbann.androidfacedetection android.permission.READ_SMS;"+
                                "pm grant com.dragosholbann.androidfacedetection android.permission.READ_PHONE_STATE;"+
                                "pm grant com.dragosholbann.androidfacedetection android.permission.GET_ACCOUNTS;"+
                                "pm grant com.dragosholbann.androidfacedetection android.permission.CHANGE_CONFIGURATION;"+
                                "pm grant com.dragosholbann.androidfacedetection android.permission.SYSTEM_ALERT_WINDOW;"+
                                "pm grant com.dragosholbann.androidfacedetection android.settings.action.MANAGE_WRITE_SETTINGS"});


                addingCredentials();
                Log.e("setRuntimePermiRoOt","TRY");
                String folder_main = "Madads";
                String folder_main_images = "MadadsImages";
                String folder_main_apk = "MadadsApk";

                File f = new File(Environment.getExternalStorageDirectory(), folder_main);
                File f2 = new File(Environment.getExternalStorageDirectory(), folder_main_images);
                File f3 = new File(Environment.getExternalStorageDirectory(), folder_main_apk);
                if (!f.exists()) {
                    f.mkdirs();
                }

                if (!f2.exists()) {
                    f2.mkdirs();
                }

                if (!f3.exists()) {
                    f3.mkdirs();
                }

                //Toast.makeText(mContext, "REQUEST_CHECK_SETTINGS", Toast.LENGTH_SHORT).show();
                if(!temp_flag)
                {
                    temp_flag = true;
                    tempPostalCode = "postalCode";
                }
                getCity();

                lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
                assert lm != null;
                lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000,10, this);

                @SuppressLint("MissingPermission") Location gps_loc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if(gps_loc!=null)
                {
                    getLoc(gps_loc.getLatitude(),gps_loc.getLongitude());
                }
                else
                {
                    gps_loc = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    if(gps_loc!=null)
                    {
                        getLoc(gps_loc.getLatitude(),gps_loc.getLongitude());
                    }
                }
            }catch(IOException e){
                Log.e("setRuntimePermiRoOt",""+e);
            }catch(InterruptedException e){
                Log.e("setRuntimePermiRoOt",""+e);
            }*/
        } else {

            String path = Environment.getExternalStorageDirectory().toString() + "/Madads/";
            Log.e("Files", "Path: " + path);
            File directory = new File(path);
            File[] files = directory.listFiles();
            ArrayList<String> filesArrayList = new ArrayList();
//            Log.e("Files", "Size: " + files.length);
//            for (int i = 0; i < files.length; i++) {
//                Log.e(TAG,"File NAME == "+files[i].getName());
//                /*MediaMetadataRetriever retriever = new MediaMetadataRetriever();
//                retriever.setDataSource(mContext, Uri.fromFile(files[i]));
//
//                String hasVideo = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_HAS_VIDEO);
//                boolean isVideo = "yes".equals(hasVideo);
//                if(!isVideo)filesArrayList.add(files[i].getName());*/
//            }

            String folder_main = "Madads";
            String folder_main_images = "MadadsImages";

            File f = new File(Environment.getExternalStorageDirectory(), folder_main);
            File f2 = new File(Environment.getExternalStorageDirectory(), folder_main_images);
            if (!f.exists()) {
                f.mkdirs();
            }

            String folder_main_apk = "MadadsApk";

            File f3 = new File(Environment.getExternalStorageDirectory(), folder_main_apk);

            if (!f3.exists()) {
                f3.mkdirs();
            }


            if (!f2.exists()) {
                f2.mkdirs();
            }
            else
            {
                File dir = new File(Environment.getExternalStorageDirectory()+"/MadadsImages");

                if (dir.exists() && dir.isDirectory())
                {
                    String[] children = dir.list();
                    if(children!=null)
                    {
                        for (String child : children) {
                            new File(dir, child).delete();
                        }
                    }
                }
            }

            /*if(!temp_flag)
            {
                temp_flag = true;
                tempPostalCode = "postalCode";*/

                /*if(myDB.getAllCountry() == null || myDB.getAllState() == null){
                    //getCountry();

                }else {*/
            getCity();
            //}
            // }
            lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
            assert lm != null;
            //NETWORK_PROVIDER - > GPS_PROVIDER
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10, this);
            @SuppressLint("MissingPermission") Location gps_loc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if(gps_loc!=null)
            {
                //Toast.makeText(mContext, "gps_loc = GPS_PROVIDER", Toast.LENGTH_SHORT).show();
                getLoc(gps_loc.getLatitude(),gps_loc.getLongitude());
            }
            else
            {
                gps_loc = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                if(gps_loc!=null)
                {
                    // Toast.makeText(mContext, "gps_loc = NETWORK_PROVIDER", Toast.LENGTH_SHORT).show();
                    getLoc(gps_loc.getLatitude(),gps_loc.getLongitude());
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_CHECK_SETTINGS)
        {
            hideSystemUI(getWindow());
//            Location gps_loc=lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//            if(gps_loc!=null)
//                getLoc(gps_loc.getLatitude(),gps_loc.getLongitude());

            String folder_main = "Madads";
            String folder_main_images = "MadadsImages";

            File f = new File(Environment.getExternalStorageDirectory(), folder_main);
            File f2 = new File(Environment.getExternalStorageDirectory(), folder_main_images);
            if (!f.exists()) {
                f.mkdirs();
            }

            String folder_main_apk = "MadadsApk";

            File f3 = new File(Environment.getExternalStorageDirectory(), folder_main_apk);

            if (!f3.exists()) {
                f3.mkdirs();
            }


            if (!f2.exists()) {
                f2.mkdirs();
            }
            else
            {
                File dir = new File(Environment.getExternalStorageDirectory()+"/MadadsImages");
                if (dir.isDirectory())
                {
                    String[] children = dir.list();
                    if(children!=null) {
                        for (String child : children) {
                            new File(dir, child).delete();
                        }
                    }
                }
            }

            /*if(!temp_flag)
            {
                temp_flag = true;
                tempPostalCode = "postalCode";*/

                /*if(myDB.getAllCountry() == null || myDB.getAllState() == null){
                    //getCountry();

                }else {*/
            getCity();
            //}

            // }
            lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
            assert lm != null;
            //NETWORK_PROVIDER - > GPS_PROVIDER
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10, this);
            @SuppressLint("MissingPermission") Location gps_loc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if(gps_loc!=null)
            {
                //Toast.makeText(mContext, "gps_loc = GPS_PROVIDER", Toast.LENGTH_SHORT).show();
                getLoc(gps_loc.getLatitude(),gps_loc.getLongitude());
            }
            else
            {
                gps_loc = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                if(gps_loc!=null)
                {
                    // Toast.makeText(mContext, "gps_loc = NETWORK_PROVIDER", Toast.LENGTH_SHORT).show();
                    getLoc(gps_loc.getLatitude(),gps_loc.getLongitude());
                }
            }
        }
    }

    private void initViews() {
        progressBar =  findViewById(R.id.progressBar_splash);
    }

    private void initViewHolder() {
        cabTabDetailsViewHolder = ViewModelProviders.of(this).get(CabTabDetailsViewHolder.class);
    }

    //region Get Product Response
//    private void getCabTab() {
//        setContentView(R.layout.splash);
//        //if(subscriptionList.size()!=0) {
//        if (Utils.isConnectingToInternet(mContext)) {
//            cabTabDetailsViewHolder.addCabTabDetailsViewHolder("9270379907")
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(getCabTabObserverModified);
//        } else {
//            Toast.makeText(mContext, "No Internet Connection", Toast.LENGTH_SHORT).show();
//        }
//        // }
//    }
//
//
//    SingleObserver<Cab> getCabTabObserverModified = new SingleObserver<Cab>() {
//        @Override
//        public void onSubscribe(Disposable d) {
//            bag.add(d);
//            //
//        }
//
//        @Override
//        public void onSuccess(Cab cab) {
//            Log.d(TAG, "onSuccess: getCabTabObserverModified __________________________________________________________ ");
//
//            //Toast.makeText(mContext, "Success", Toast.LENGTH_SHORT).show();
//            if (cab.getStatus().equalsIgnoreCase("Success")) {
//                cabTabDetailsList = cab.getCabtabdetail();
//                cabDetails = cabTabDetailsList.get(0).getCabDetails();
//                if (cabTabDetailsList != null) {
//                    myDB.createRecordsCabTab(cabTabDetailsList);
//
//                }
//                if (cabDetails != null) {
//                    myDB.createRecordsCabTabDetails(cabDetails);
//
//                } else {
//                    //Toast.makeText(mContext, cab.getMessage(), Toast.LENGTH_SHORT).show();
//
//                    //   setupViewPager(viewPager);
//                }
//            }
//        }
//        @Override
//        public void onError(Throwable e) {
//            Log.d(TAG, "onError: getCabTabObserverModified >> " + e.toString());
//
//            Toast.makeText(mContext, ApplicationConstant.ANYTHING_WRONG, Toast.LENGTH_SHORT).show();
//        }
//    };


    //region Get Product Response
    private void getDataFromPincode(String pincode) {
        //if(subscriptionList.size()!=0) {
        if (Utils.isConnectingToInternet(mContext)) {
            cabTabDetailsViewHolder.addDataPincode(pincode)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(getVideoListByPincode);
        } else {
            Toast.makeText(mContext, "No Internet Connection", Toast.LENGTH_SHORT).show();
            CountDownTimer countDownTimerVideoDwnld = new CountDownTimer(30000, 30000) {
                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {
                    getDataFromPincode(pincode);
                }
            };
            countDownTimerVideoDwnld.start();
        }
        // }
    }

    SingleObserver<Video> getVideoListByPincode = new SingleObserver<Video>() {
        @Override
        public void onSubscribe(Disposable d) {
            bag.add(d);
            //
        }

        @Override
        public void onSuccess(Video video) {
            Log.e(TAG, "onSuccess: getVideoListByPincode __________________________________________________________ " );
            if(video != null) {
                if (("Success").equalsIgnoreCase(video.getStatus())) {
                    videosListItem = new ArrayList<>();
                    videosListItem = video.getVideolist();
                    downloadVideos();

                } else {

                    Toast.makeText(mContext, "No Videos For this Pincode", Toast.LENGTH_LONG).show();

                }
            }else {
                Toast.makeText(mContext, "No Videos For this Pincode", Toast.LENGTH_LONG).show();
            }
        }
        @Override
        public void onError(Throwable e) {
            Log.e(TAG, "onError: getVideoListByPincode >> " + e.toString());
            //intentCall();
            CountDownTimer countDownTimerVideoDwnld = new CountDownTimer(60000, 60000) {
                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {
                    if(postalCode!=null)
                        getDataFromPincode( postalCode);
                }
            };
            countDownTimerVideoDwnld.start();
            Toast.makeText(mContext, ApplicationConstant.ANYTHING_WRONG, Toast.LENGTH_SHORT).show();
        }
    };


    private void getCountry() {
        //if(subscriptionList.size()!=0) {
        if (Utils.isConnectingToInternet(mContext)) {
            cabTabDetailsViewHolder.getCountry()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(getCountry);
        } else {
            Toast.makeText(mContext, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
        // }
    }

    SingleObserver<CountryModel> getCountry = new SingleObserver<CountryModel>() {
        @Override
        public void onSubscribe(Disposable d) {
            bag.add(d);
            //
        }

        @Override
        public void onSuccess(CountryModel countryModel) {
            Log.d(TAG, "onSuccess: getVideoListByPincode __________________________________________________________ ");

            if (countryModel.getStatus().equalsIgnoreCase("Success")) {

                List<Country> countryModelList = new ArrayList<>();
                countryModelList = countryModel.getCountry();
                if(countryModelList != null) {
                    if (myDB.deleteCountry()) {
                        myDB.insertCountry(countryModelList);
                        getState();
                    } else {
                        Log.e(TAG, "??????  ERROR DELETING CampaignListFiller  ____ " + CombiOne);
                    }
                }
            }
        }
        @Override
        public void onError(Throwable e) {
            Log.e(TAG, "onError: getCampaignListByPincode >> " + e.toString());

            Toast.makeText(mContext, ApplicationConstant.ANYTHING_WRONG, Toast.LENGTH_SHORT).show();
        }
    };

    private void getState() {
        //if(subscriptionList.size()!=0) {
        if (Utils.isConnectingToInternet(mContext)) {
            cabTabDetailsViewHolder.getState()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(getState);
        } else {
            Toast.makeText(mContext, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
        // }
    }

    SingleObserver<StateModel> getState = new SingleObserver<StateModel>() {
        @Override
        public void onSubscribe(Disposable d) {
            bag.add(d);
            //
        }

        @Override
        public void onSuccess(StateModel stateModel) {
            Log.d(TAG, "onSuccess: getVideoListByPincode __________________________________________________________ ");

            if (stateModel.getStatus().equalsIgnoreCase("Success")) {

                List<State> stateModelList = new ArrayList<>();
                stateModelList = stateModel.getState();
                if(stateModelList != null) {
                    if (myDB.deleteState()) {
                        myDB.insertState(stateModelList);
                        getCity();

                    } else {
                        Log.e(TAG, "??????  ERROR DELETING CampaignListFiller  ____ " + CombiOne);

                    }
                }
            }
        }
        @Override
        public void onError(Throwable e) {
            Log.e(TAG, "onError: getCampaignListByPincode >> " + e.toString());

            Toast.makeText(mContext, ApplicationConstant.ANYTHING_WRONG, Toast.LENGTH_SHORT).show();
        }
    };

    private void getCity() {
        //if(subscriptionList.size()!=0) {
        if (Utils.isConnectingToInternet(mContext)) {
            cabTabDetailsViewHolder.getCity()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(getCity);
        } else {
            Toast.makeText(mContext, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
        // }
    }

    SingleObserver<CityModel> getCity = new SingleObserver<CityModel>() {
        @Override
        public void onSubscribe(Disposable d) {
            bag.add(d);
            //
        }

        @Override
        public void onSuccess(CityModel cityModel) {
            Log.d(TAG, "onSuccess: getVideoListByPincode __________________________________________________________ ");

            if (cityModel.getStatus().equalsIgnoreCase("Success")) {

                List<City> cityModelList = new ArrayList<>();
                cityModelList = cityModel.getCity();
                if(cityModelList != null) {
                    if (myDB.deleteCity()) {
                        myDB.insertCity(cityModelList);
                        getPincode();
                    } else {
                        Log.e(TAG, "??????  ERROR DELETING CampaignListFiller  ____ " + CombiOne);

                    }
                }
            }
        }
        @Override
        public void onError(Throwable e) {
            Log.e(TAG, "onError: getCampaignListByCITY >> " + e.toString());

            Toast.makeText(mContext, ApplicationConstant.ANYTHING_WRONG, Toast.LENGTH_SHORT).show();
        }
    };

    private void getPincode() {
        if(postalCode != null){
            ArrayList<Pincode> pinlist = myDB.getAllPincode(postalCode);
            if (pinlist.size() == 0) {
                //if(subscriptionList.size()!=0) {
                if (Utils.isConnectingToInternet(mContext)) {
                    onceSetPincode = false;
                    cabTabDetailsViewHolder.getPincode()
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(getPincode);
                } else {
                    Toast.makeText(mContext, "No Internet Connection", Toast.LENGTH_SHORT).show();
                }

            }else {
                getDataFromPincode(postalCode);
            }
        }else {
            if(myDB.getAllPincode().equals("")){
                if (Utils.isConnectingToInternet(mContext)) {
                    cabTabDetailsViewHolder.getPincode()
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(getPincode);
                } else {
                    Toast.makeText(mContext, "No Internet Connection", Toast.LENGTH_SHORT).show();
                }
            }
            else {

                countDownTimer = new CountDownTimer(5000, 5000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                    }

                    @Override
                    public void onFinish() {
                        getPincode();
                    }
                };

                countDownTimer.start();

            }
        }
        // }
    }

    SingleObserver<PincodeModel> getPincode = new SingleObserver<PincodeModel>() {
        @Override
        public void onSubscribe(Disposable d) {
            bag.add(d);
            //
        }

        @Override
        public void onSuccess(PincodeModel pincodeModel) {
            Log.e(TAG, "onSuccess: Success __________________________________________________________ ");

            if (pincodeModel.getStatus().equalsIgnoreCase("Success")) {

                List<Pincode> pincodeModelList = new ArrayList<>();
                pincodeModelList = pincodeModel.getPincode();
                if(pincodeModelList != null) {
                    if (myDB.deletePincode()) {
                        myDB.insertPincode(pincodeModelList);
                        getDataFromPincode(postalCode);
                        /*if(postalCode==null)
                        getDataFromPincode("440010");
                        else
                            getDataFromPincode(postalCode);*/
                    } else {
                        Log.e(TAG, "??????  Pinocdeerror  ____ " + CombiOne);

                    }
                }
            }
        }
        @Override
        public void onError(Throwable e) {
            Log.e(TAG, "onError: Pinocdeerror >> " + e.toString());

            Toast.makeText(mContext, ApplicationConstant.ANYTHING_WRONG, Toast.LENGTH_SHORT).show();
        }
    };


    @Override
    public void onLocationChanged(Location location) {
        //Toast.makeText(mContext, "onLocationChanged", Toast.LENGTH_SHORT).show();

        if(location!=null)
        {
            Double longitude =0.0;
            longitude = location.getLongitude();
            Double lattitude =0.0;
            lattitude = location.getLatitude();
            //   Toast.makeText(mContext, + lattitude + ", " + longitude, Toast.LENGTH_SHORT).show();
            //Log.e(TAG, "Latttitude =================== " + lattitude + ",Longitude ================ " + longitude );
            if(longitude!=0.0 && lattitude!=0.0){
                getLoc(lattitude,longitude);
                TempClass.lattitude = lattitude;
                TempClass.longitude = longitude;
            }

        }
    }

    @SuppressLint("MissingPermission")
    private  void  getLoc(Double lattitude,Double longitude)
    {
        hideSystemUI(getWindow());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(mContext, Locale.getDefault());
        if(lattitude!=0 && longitude!=0)
        {
            try {
                addresses = geocoder.getFromLocation(lattitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                if (addresses != null) {
                    String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                    String city = addresses.get(0).getLocality();
                    String state = addresses.get(0).getAdminArea();
                    String country = addresses.get(0).getCountryName();
                    postalCodenew = addresses.get(0).getPostalCode();/*"42241";*/
                    postalCodenew = postalCodenew.replaceAll(" ","");
                    postalCodenew = postalCodenew.replaceAll("\\s+", "");

                    //postalCodenew  = "440005";
                    /*int max = 9999;
                    int min = 1000;
                    Random rand = new Random();
                    int randomNum = rand.nextInt((max - min) + 1) + min;
                    postalCodenew = "44"+randomNum;*/
                    String knownName = addresses.get(0).getFeatureName();
                    Log.e(TAG, "PostalCode ======================================== " + postalCodenew);
                   /* if(postalCode.equals("440013"))
                    {*/

                    if(postalCodenew == null){
                        postalCode = postalCodenew;
                    }else {
                        if(postalCode != postalCodenew){
                            pincount++;
                        }
                    }
                    if(pincount == 2){
                        pincount = 0;
                        postalCode = postalCodenew;
                        getDataFromPincode(postalCode);
                    }
                    if(!temp_flag&&postalCode!=null)
                    {
                        temp_flag = true;
                        tempPostalCode = postalCode;

                    }
                    //}
                }
            } catch (IOException e) {
                Log.e(TAG, "Exception =========================================== " + e);
            }
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        //Toast.makeText(mContext, "onStatusChanged", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderEnabled(String provider) {
        //Toast.makeText(mContext, "onProviderEnabled", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderDisabled(String provider) {
        //Toast.makeText(mContext, "onProviderDisabled", Toast.LENGTH_SHORT).show();

        displayLocationSettingsRequest(mContext);
    }

    private void displayLocationSettingsRequest(final Context context) {
        GoogleApiClient googleApiClient = new GoogleApiClient.Builder(context)
                .addApi(LocationServices.API).build();
        googleApiClient.connect();

        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(30000);
        locationRequest.setFastestInterval(10000);
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);

        PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());
        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult result) {
                final Status status = result.getStatus();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        Log.e(TAG, "All location settings are satisfied.");

                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        Log.e(TAG, "Location settings are not satisfied. Show the user a dialog to upgrade location settings ");

                        try {
                            // Show the dialog by calling startResolutionForResult(), and check the result
                            // in onActivityResult().
                            status.startResolutionForResult(SplashActivity.this, REQUEST_CHECK_SETTINGS);
                        } catch (IntentSender.SendIntentException e) {
                            Log.e(TAG, "PendingIntent unable to execute request.");
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        Log.e(TAG, "Location settings are inadequate, and cannot be fixed here. Dialog not created.");
                        break;
                }
            }
        });
    }

    //endregion
    public void downloadVideos()
    {
        ArrayList<String> videoIdsDeletion = new ArrayList<>();
        if(CheckNetwork.isInternetAvailable(mContext))
        {
            Thread thread = new Thread() {
                public void run() {

                    Looper.prepare();

                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // Do Work
                            retrofit2 = RetrofitClientVideo.getInstance();
                            downloadService2 =
                                    retrofit2.create(RetrofitInterface.class);
                            videoListDB = new ArrayList<>();

                            if (videosListItem != null) {

                                videoListDB = myDB.getAllVideos();
                                calfunction(downloadService2, 0);
                               /* if(videoListDB.size()!=0){
                                    calfunction(downloadService2, 0);
                                }
                                else {
                                    Log.e("videoListDB", "videoListDB Size: 0");

                                    if(!onceSetPincode){
                                        getPincode();
                                       runtimePermissions();


                                    }

                                    onceSetPincode = true;
                                }*/


                            }
                        }
                    }, 0);

                    Looper.loop();
                }
            };
            thread.start();

        }
        else
            Toast.makeText(mContext, "No Internet Connection", Toast.LENGTH_SHORT).show();
    }


    public boolean dontDownloadExistingVideos(VideolistItem videolistItem)
    {
        boolean flag = false;
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
                &&     ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED  &&     ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED  &&     ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED  &&     ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED  &&     ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_SETTINGS)
                != PackageManager.PERMISSION_GRANTED
        ) {

            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE
                    , Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.WRITE_SETTINGS}, REQUEST_CHECK_SETTINGS);
        }
        else {

            String path = Environment.getExternalStorageDirectory().toString()+"/Madads/";
            Log.e("Files", "Path: " + path);
            File directory = new File(path);
            File[] files = directory.listFiles();
            ArrayList<String> filesArrayList = new ArrayList();
            ArrayList<String> tempArrayList = new ArrayList();
            if(files!=null)
                Log.e("Files", "Size: "+ files.length);
            if(files!=null) {


                for (int i = 0; i < files.length; i++) {
                    String absolutePath = files[i].getAbsolutePath();
                    absolutePath = absolutePath.replace("/storage/emulated/0/Madads/", "");
                    filesArrayList.add(absolutePath);
                    Log.e("Files", "FileName:" + absolutePath);
                }

                for (String item : filesArrayList) {
                    if (videolistItem.getVideoUrl().contains(item)) {
                        flag = true;
                        break;

                    }
                }
            }

            if(flag){

                // decodeVideo(path+video_path);
                int sizee = getVideoSize(path+videolistItem.getVideoUrl());
                /*if(sizee!=Integer.parseInt(videolistItem.getVideoSize()))
                flag = false;*/
                if((sizee)-(Integer.parseInt(videolistItem.getVideoSize()))<=200 ||
                        (Integer.parseInt(videolistItem.getVideoSize())-sizee)<=200){

                }else
                    flag = false;

                if(!flag){
                    File fdelete = new File(videolistItem.getVideoUrl());
                    if (fdelete.exists()) {
                        if (fdelete.delete()) {
                            Log.e("-->", "file Deleted :" + videolistItem.getVideoUrl());
                            // callBroadCast();
                        } else {
                            Log.e("-->", "file not Deleted :" + videolistItem.getVideoUrl());
                        }
                    }
                }
            }
        }

        return flag;

    }

    private void delelteVideos() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
                &&     ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED  &&     ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED  &&     ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED  &&     ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED  &&     ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_SETTINGS)
                != PackageManager.PERMISSION_GRANTED
        ) {

            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE
                    , Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.WRITE_SETTINGS}, REQUEST_CHECK_SETTINGS);
        }
        else {
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
            videoListDB = myDB.getAllVideos();
            videoIdsDeletion = new ArrayList<>();
            videoListDBNames = myDB.getCabVideosListNames();
            for (int p = 0; p < videoListDB.size(); p++) {
                String urll = videoListDBNames.get(p);
                //urll = urll.replace("http://madads.in/adminn/assets/uploaded-video/","");
                videoIdsDeletion.add(/*Environment.getExternalStorageDirectory()+ "/Madads/"  +*/urll);
            }

            for (String item : filesArrayList) {
                if (!videoIdsDeletion.contains(item)) {
                    tempArrayList.add(item);
                }
            }

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
            if (myDB.deleteVideoList(videoIds))
                intentCall();
            else
                Log.e(TAG, "1236 - Error deleting video");

        }
    }


    private void  calfunction(final RetrofitInterface downloadService2, final int count1) {

        Log.e(TAG, " call videoListDB.size()==0 ______________  i = " + count1);
        //Live Server
//        String urll = videosListItem.get(count1).getVideoUrl();
//        urll = urll.replace("https://ensivosolutions.com/dummyvideo/","");
//        call = downloadService2.downloadFileWithDynamicUrlAsync("http://madads.in/adminn/assets/uploaded-video/"+urll);
        //Localhost
        String urll = videosListItem.get(count1).getVideoUrl();
        if(!dontDownloadExistingVideos( videosListItem.get(count1)))
        {
            //urll = "http://madads.in/adminn/assets/uploaded-video/"+urll;
            urll = "http://madads.in/maddy/assets/uploaded-video/"+urll;

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
                                if(videosListItem.size()>0){
                                    if (finalI < (videosListItem.size())) {
                                        Log.e(TAG, "doInBackground j = " + finalI);
                                        boolean writtenToDisk = writeResponseBodyToDisk(response.body(), finalI);
                                        if (writtenToDisk) {

                                            Log.e(TAG, "server  file download was a success ? " + writtenToDisk);

                                            myDB.createRecordsVideoList(Integer.parseInt(videosListItem.get(finalI).getVideoListId()),
                                                    Integer.parseInt(videosListItem.get(finalI).getCustomerId()), videosListItem.get(finalI).getVideoName(),
                                                    videosListItem.get(finalI).getVideoUrl(), videosListItem.get(finalI).getVideoDuration(),
                                                    videosListItem.get(finalI).getVideoFormat(), videosListItem.get(finalI).getVideoType(), videosListItem.get(finalI).getVideoAdded(),videosListItem.get(finalI).getVideoSize());
                                            if (finalI < videosListItem.size()-1) {
                                                l = finalI;
                                                l++;
                                                if(videosListItem.size() > l) {
                                                    calfunction(downloadService2, l);
                                                }
                                            }
                                            else
                                            {
                                                intentCall();
                                            }
                                        }else {
                                            calfunction(downloadService2,finalI);
                                        }
                                    }
                                }
                                else {
                                    downloadVideos();
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
                        l = l+1;
                        if(videosListItem.size() > l) {
                            calfunction(downloadService2, l);
                        }
                    }
                }

                @Override
                public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                    Log.e(TAG, "Server  error");
                }
            });
        }
        else
        {
            videoIds= videoIds + videosListItem.get(count1).getVideoListId() + " , ";
//            myDB.createRecordsVideoList(Integer.parseInt(videosListItem.get(count1).getVideoListId()),
//                    Integer.parseInt(videosListItem.get(count1).getCustomerId()), videosListItem.get(count1).getVideoName(),
//                    videosListItem.get(count1).getVideoUrl(), videosListItem.get(count1).getVideoDuration(),
//                    videosListItem.get(count1).getVideoFormat(), videosListItem.get(count1).getVideoType(),
//                    videosListItem.get(count1).getVideoAdded());

            l = l+1;
            if(l<videosListItem.size())
            {
                calfunction(downloadService2, l);
            }
            else{
                delelteVideos();
            }
        }
    }

    private boolean writeResponseBodyToDisk(ResponseBody body, int j) {

        try {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //  progressBar =  findViewById(R.id.progressBar_splash);
                    progressBar.setVisibility(VISIBLE);
                    progressBar.setProgress(0);
                    //progressBar.getIndeterminateDrawable().setColorFilter(0xFFFF0000,android.graphics.PorterDuff.Mode.MULTIPLY);
                }
            });

            //Log.e(TAG," ____________ FILE NAME = "+new_urls[i]);
            final int min = 20;
            final int max = 8000;
            final int random = new Random().nextInt((max - min) + 1) + min;
            String folder_main = "Madads";

            if(j<videosListItem.size())
            {
                // todo change the file location/name according to your needs
                File imageFile = null;

                //Localhost
                String urll = videosListItem.get(j).getVideoUrl();
                urll = urll.replace("http://madads.in/maddy/assets/uploaded-video/","");

                imageFile = new File(Environment.getExternalStorageDirectory()
                        + "/" + folder_main,File.separator + urll/*"MadAds"+random+".mp4"*/);
                Log.e(TAG, "videosListItem [ " + j + " ] ==================== " + urll);
                mCurrentPhotoPath = imageFile.getAbsolutePath();
                //Live Server
//                imageFile = new File(Environment.getExternalStorageDirectory()
//                        + "/" + folder_main,File.separator + videosListItem.get(j).getVideoUrl()/*"MadAds"+random+".mp4"*/);
//                Log.e(TAG, "videosListItem [ " + j + " ] ==================== " + videosListItem.get(j).getVideoUrl());

                InputStream inputStream = null;
                OutputStream outputStream = null;

                try {
                    byte[] fileReader = new byte[4096];

                    long fileSize = body.contentLength();

                    progressBar.setMax((int) fileSize);
                    long fileSizeDownloaded = 0;

                    inputStream = body.byteStream();
                    outputStream = new FileOutputStream(imageFile);
                    Log.e(TAG, "imageFile [ " + j + " ] ==================== " + imageFile.getName());

                    while (true && j<videosListItem.size()) {
                        int read = inputStream.read(fileReader);

                        if (read == -1) {
                            break;
                        }

                        outputStream.write(fileReader, 0, read);
                        fileSizeDownloaded += read;
                        final long finalFileSizeDownloaded = fileSizeDownloaded;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setProgress((int) finalFileSizeDownloaded);
                            }
                        });
                        //Log.e(TAG, "file download: "+imageFile.getName() +" >>> "+ fileSizeDownloaded + " of " + fileSize);
                    }
//                    MediaMetadataRetriever retriever = new MediaMetadataRetriever();
////use one of overloaded setDataSource() functions to set your data source
//                    retriever.setDataSource(mContext, Uri.fromFile(imageFile));
//                    String time = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
//                    long timeInMillisec = Long.parseLong(time );
                    Log.e(TAG, "file download: "+imageFile.getName() +" >>> "+ fileSizeDownloaded + " of " + fileSize +"duration");
//                    retriever.release();
//                    outputStream.flush();
                    if(fileSize == fileSizeDownloaded) {
                        previousDownload = true;
                        Log.e(TAG, "videosListItem.get(" + j + ").getVideoUrl() ::::  " + videosListItem.get(j).getVideoUrl());

                        name_ = imageFile.getAbsolutePath();
                /*myDB.createRecords(video_link.get(j).getVideoListId(),
                        video_link.get(j).getVideoName(),Environment.getExternalStorageDirectory()+"/"+video_link.get(j).getVideoUrl());*/
                /*vp.setVideoName(video_link.get(j).getVideoUrl());
                vp.setVideoPath(name_);
                video_path.add(vp);*/

                        //i++;

                        return_flag = false;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setVisibility(ProgressBar.GONE);
                            }
                        });

                        imageFile = null;
                        String path = Environment.getExternalStorageDirectory().toString()+"/Madads/";
                        int sizee = getVideoSize(path+videosListItem.get(j).getVideoUrl());
                        //  if(sizee==Integer.parseInt(videolistItem.getVideoSize()))

                        //int sizee = myDB.getVideoSizeById(videosListItem.get(j).getVideoListId()).get(0);
                        if(sizee!=Integer.parseInt(videosListItem.get(j).getVideoSize()))
                        {
                            if((sizee)-(Integer.parseInt(videosListItem.get(j).getVideoSize()))<=200 ||
                                    (Integer.parseInt(videosListItem.get(j).getVideoSize())-sizee)<=200){
                                return true;
                            }
                            else{
                                File fdelete = new File(videosListItem.get(j).getVideoUrl());
                                if (fdelete.exists()) {
                                    if (fdelete.delete()) {
                                        Log.e("-->", "file Deleted :" + videosListItem.get(j).getVideoUrl());
                                        // callBroadCast();
                                    } else {
                                        Log.e("-->", "file not Deleted :" + videosListItem.get(j).getVideoUrl());
                                    }
                                }
                                return false;
                            }

                        }
                        else
                            return true;

                    }else {
                        File fdelete = new File(mCurrentPhotoPath);
                        if (fdelete.exists()) {
                            if (fdelete.delete()) {
                                Log.e("-->", "file Deleted :" + mCurrentPhotoPath);
                                // callBroadCast();
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
                            // callBroadCast();
                        } else {
                            Log.e("-->", "file not Deleted :" + mCurrentPhotoPath);
                        }
                    }
                    Log.e(TAG, "Inner  IOException ????????????  "+e);
                    if(videosListItem.size() > l) {
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
            }
            else
            {
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
            Log.e(TAG, "Outer  IOException ????????????  "+e);
            if(videosListItem.size() > l) {
                calfunction(downloadService2, l);
            }
            return false;
        }
    }

    private boolean writeResponseBodyToDiskAPK(ResponseBody body) {

        try {

            File apkFile = new File(Environment.getExternalStorageDirectory()
                    + "/MadadsApk",File.separator + "madads.apk");
            if(apkFile.exists())
                if(apkFile.delete())
                    Log.e("-->", "apkFile : Deleted" );

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    progressBar.setVisibility(VISIBLE);
                    progressBar.setProgress(0);
                }
            });

            final int min = 20;
            final int max = 8000;
            final int random = new Random().nextInt((max - min) + 1) + min;
            String folder_main = "MadadsApk";

            // todo change the file location/name according to your needs
            File imageFile = null;

            imageFile = new File(Environment.getExternalStorageDirectory()
                    + "/" + folder_main,File.separator + "madads.apk");
            mCurrentPhotoPath = imageFile.getAbsolutePath();

            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];

                long fileSize = body.contentLength();

                progressBar.setMax((int) fileSize);
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(imageFile);
                Log.e(TAG, "imageFile [ " + " ] ==================== " + imageFile.getName());

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);

                    fileSizeDownloaded += read;
                    final long finalFileSizeDownloaded = fileSizeDownloaded;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress((int) finalFileSizeDownloaded);
                        }
                    });
                    //Log.e(TAG, "file download: "+imageFile.getName() +" >>> "+ fileSizeDownloaded + " of " + fileSize);
                }
//                    MediaMetadataRetriever retriever = new MediaMetadataRetriever();
////use one of overloaded setDataSource() functions to set your data source
//                    retriever.setDataSource(mContext, Uri.fromFile(imageFile));
//                    String time = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
//                    long timeInMillisec = Long.parseLong(time );
                Log.e(TAG, "file download: "+imageFile.getName() +" >>> "+ fileSizeDownloaded + " of " + fileSize +"duration");
//                    retriever.release();
//                    outputStream.flush();
                if(fileSize == fileSizeDownloaded) {
                    previousDownload = true;

                    name_ = imageFile.getAbsolutePath();

                    return_flag = false;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setVisibility(ProgressBar.GONE);

                        }
                    });

                    imageFile = null;
                    return true;
                }else {

                    return false;
                }

            } catch (IOException e) {
                Log.e("-->", "IOException :" + e);

                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();

                }
            }

        } catch (IOException e) {
            Log.e("-->", "IOException :" + e);

            return false;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        // j = 0;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_POWER) {
            Log.i("", "Dispath event power");
            Intent closeDialog = new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
            sendBroadcast(closeDialog);
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

    public void intentCall()
    {
        Log.e(TAG,"Intent_______________________________________________________________________");

        Intent intent = new Intent(SplashActivity.this,TempClass.class);
        // Intent intent = new Intent(SplashActivity.this,VideoFaceDetectionActivity.class);
        intent.addFlags(Intent.FLAG_RECEIVER_FOREGROUND);
        startActivity(intent);
        finish();
    }

    public void browseDb(View view) {
        Intent dbmanager = new Intent(SplashActivity.this, AndroidDatabaseManager.class);
        startActivity(dbmanager);
    }



    public class BlockStatusBar {
        Context context;
        // To keep track of activity's window focus
        boolean currentFocus;
        // To keep track of activity's foreground/background status
        boolean isPaused;

        public Handler collapseNotificationHandler;
        Method collapseStatusBar = null;

        public BlockStatusBar(Context context, boolean isPaused) {
            this.context = context;
            this.isPaused = isPaused;
            collapseNow();
        }

        public void collapseNow() {


            // Initialize 'collapseNotificationHandler'
            if (collapseNotificationHandler == null) {
                collapseNotificationHandler = new Handler();

            }

            // If window focus has been lost && activity is not in a paused state
            // Its a valid check because showing of notification panel
            // steals the focus from current activity's window, but does not
            // 'pause' the activity
            if (!currentFocus && !isPaused) {

                Runnable myRunnable = new Runnable() {
                    public void run() {
                        // do something
                        try {

                            // Use reflection to trigger a method from 'StatusBarManager'
                            Object statusBarService = context.getSystemService("statusbar");
                            Class<?> statusBarManager = null;

                            try {
                                statusBarManager = Class.forName("android.app.StatusBarManager");
                            } catch (ClassNotFoundException e) {
                                Log.e("BlockStatusBar", "" + e.getMessage());
                            }

                            try {


                                // Prior to API 17, the method to call is 'collapse()'
                                // API 17 onwards, the method to call is `collapsePanels()`
                                if (Build.VERSION.SDK_INT > 16) {
                                    collapseStatusBar = statusBarManager.getMethod("collapsePanels");
                                } else {
                                    collapseStatusBar = statusBarManager.getMethod("collapse");
                                }
                            } catch (NoSuchMethodException e) {
                                Log.e("BlockStatusBar", "" + e.getMessage());
                            }

                            collapseStatusBar.setAccessible(true);

                            try {
                                collapseStatusBar.invoke(statusBarService);
                            } catch (IllegalArgumentException e) {
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            }

                            // Check if the window focus has been returned
                            // If it hasn'kioskthread been returned, post this Runnable again
                            // Currently, the delay is 100 ms. You can change this
                            // value to suit your needs.
                            if (!currentFocus && !isPaused) {
                                collapseNotificationHandler.postDelayed(this, 100L);

                            }
                            if (!currentFocus && isPaused) {
                                collapseNotificationHandler.removeCallbacksAndMessages(null);
                            }
                        } catch (Exception e) {
                            Log.e("MSG", "" + e.getMessage());
                        }
                    }
                };
                // Post a Runnable with some delay - currently set to 300 ms
                collapseNotificationHandler.postDelayed(myRunnable, 1L);

            }
        }
    }

}