package com.dragosholbann.androidfacedetection;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.PendingIntent;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dragosholbann.androidfacedetection.DBHelper.MyDB;
import com.dragosholbann.androidfacedetection.Interfaces.OnSmsCatchListener;
import com.dragosholbann.androidfacedetection.ModelLayer.Entities.Cab;
import com.dragosholbann.androidfacedetection.ModelLayer.Entities.CabDetails;
import com.dragosholbann.androidfacedetection.ModelLayer.Entities.Cabtabdetail;
import com.dragosholbann.androidfacedetection.ModelLayer.Entities.VideoLogResponse;
import com.dragosholbann.androidfacedetection.constants.ApplicationConstant;
import com.dragosholbann.androidfacedetection.utils.MyProgressDialog;
import com.dragosholbann.androidfacedetection.utils.Utils;
import com.dragosholbann.androidfacedetection.viewModels.CabTabDetailsViewHolder;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.HintRequest;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SmsActivity extends AppCompatActivity  {
    GoogleApiClient mCredentialsApiClient;
    private static final int PHONE_NUMBER_HINT = 100;
    private final int PERMISSION_REQ_CODE = 200;
    private SmsVerifyCatcher smsVerifyCatcher;
    Context mContext;
    private static final int MY_PERMISSIONS_REQUEST_READ_PHONE_STATE = 0;
    CabTabDetailsViewHolder cabTabDetailsViewHolder ;
    CompositeDisposable bag = new CompositeDisposable();
    List<Cabtabdetail> cabTabDetailsList ;
    List<CabDetails> cabDetails ;
    private static final String TAG = "SMS ACTIVITY ___";
    MyDB myDB;
    String IMEINumber,  IMEINumber1;
    int x = 0;
    int y = 0;
    EditText et_code;
    Button btn_verify;
    String ip = "0.0.0.0";
    TextView tv_no_internet;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sms);

        mContext = SmsActivity.this;
        myDB = new MyDB(mContext);
        tv_no_internet = findViewById(R.id.tv_no_internet);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_FULLSCREEN
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        );

        /*getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
       *//* WifiManager wifiManager = (WifiManager)this.mContext.getSystemService(Context.WIFI_SERVICE);
        wifiManager.setWifiEnabled(true);*//*
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);*/
        mainConn();
//        //init views
//        final EditText etCode = (EditText) findViewById(R.id.et_code);
//        final Button btnVerify = (Button) findViewById(R.id.btn_verify);

//        Thread.setDefaultUncaughtExceptionHandler(new MyException(SmsActivity.this));
//        if (getIntent().getBooleanExtra("crash", false)) {
//            Toast.makeText(this, "App restarted after crash", Toast.LENGTH_SHORT).show();
//
//        }
//
//        if (getIntent().getExtras() != null) {
//            for (String key : getIntent().getExtras().keySet()) {
//                String value = getIntent().getExtras().getString(key);
//                if (key.equals("AnotherActivity") && value.equals("True")) {
////                    Intent intent = new Intent(SmsActivity.this, SmsActivity.class);
////                    startActivity(intent);
////                    finish();
//                }
//            }
//        }



    }

    public void mainConn(){
        if (Utils.isConnectingToInternet(mContext)) {
            if(tv_no_internet!=null)
            tv_no_internet.setVisibility(View.GONE);
            initViewHolder();
            et_code = findViewById(R.id.et_code);
            btn_verify = findViewById(R.id.btn_verify);
            getCabTab("865781042487453");
            btn_verify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String code = et_code.getText().toString().trim();
                    if(!("").equalsIgnoreCase(code)){
                        getCabTab(code);
                    }else {
                        Toast.makeText(mContext, "Please Enter IMEI", Toast.LENGTH_SHORT).show();
                    }
                }
            });
          //  loadIMEI();
        } else {
            if(tv_no_internet!=null)
            tv_no_internet.setVisibility(View.VISIBLE);
                CountDownTimer countDownTimerVideoDwnld = new CountDownTimer(30000, 30000) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
                        mainConn();
                    }
                };
                countDownTimerVideoDwnld.start();
        }


    }
    @Override
    public void onBackPressed() {
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == android.view.KeyEvent.KEYCODE_BACK) {


        }
        else if (event.getKeyCode() == android.view.KeyEvent.KEYCODE_HOME) {


        }
        else if (event.getKeyCode() == android.view.KeyEvent.KEYCODE_SOFT_LEFT) {


        }
        else if (event.getKeyCode() == android.view.KeyEvent.KEYCODE_SOFT_RIGHT) {


        }
        else if (event.getKeyCode() == android.view.KeyEvent.KEYCODE_SOFT_SLEEP) {


        }
        else if (event.getKeyCode() == android.view.KeyEvent.KEYCODE_SYSTEM_NAVIGATION_DOWN) {


        }
        else if (event.getKeyCode() == android.view.KeyEvent.KEYCODE_SYSTEM_NAVIGATION_LEFT) {


        }
        else if (event.getKeyCode() == android.view.KeyEvent.KEYCODE_SYSTEM_NAVIGATION_RIGHT) {


        }
        else if (event.getKeyCode() == android.view.KeyEvent.KEYCODE_SYSTEM_NAVIGATION_UP) {


        }
        else if (event.getKeyCode() == android.view.KeyEvent.KEYCODE_STB_POWER) {


        }
        else if (event.getKeyCode() == android.view.KeyEvent.KEYCODE_ALL_APPS) {


        }
        else if (event.getKeyCode() == android.view.KeyEvent.KEYCODE_APP_SWITCH) {


        }
        else if (event.getKeyCode() == android.view.KeyEvent.KEYCODE_POWER) {


        }
        else if (event.getKeyCode() == android.view.KeyEvent.KEYCODE_AVR_POWER) {


        }


        return true;
    }


    private void initViewHolder() {
        cabTabDetailsViewHolder = ViewModelProviders.of(this).get(CabTabDetailsViewHolder.class);
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    public void loadIMEI() {
        // Check if the READ_PHONE_STATE permission is already available.
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            // READ_PHONE_STATE permission has not been granted.
            requestReadPhoneStatePermission();
        } else {
            // READ_PHONE_STATE permission is already been granted.
            doPermissionGrantedStuffs();
        }
    }


    /**
     * Requests the READ_PHONE_STATE permission.
     * If the permission has been denied previously, a dialog will prompt the user to grant the
     * permission, otherwise it is requested directly.
     */
    private void requestReadPhoneStatePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.READ_PHONE_STATE)) {
            // Provide an additional rationale to the user if the permission was not granted
            // and the user would benefit from additional context for the use of the permission.
            // For example if the user has previously denied the permission.
            new AlertDialog.Builder(SmsActivity.this)
                    .setTitle("Permission Request")
                    .setMessage(("anything"))
                    .setCancelable(false)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //re-request
                            ActivityCompat.requestPermissions(SmsActivity.this,
                                    new String[]{Manifest.permission.READ_PHONE_STATE},
                                    MY_PERMISSIONS_REQUEST_READ_PHONE_STATE);
                        }
                    })
                    .show();
        } else {
            // READ_PHONE_STATE permission has not been granted yet. Request it directly.
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE},
                    MY_PERMISSIONS_REQUEST_READ_PHONE_STATE);
        }
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        if (requestCode == MY_PERMISSIONS_REQUEST_READ_PHONE_STATE) {
            // Received permission result for READ_PHONE_STATE permission.est.");
            // Check if the only required permission has been granted
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // READ_PHONE_STATE permission has been granted, proceed with displaying IMEI Number
                //alertAlert(getString(R.string.permision_available_read_phone_state));
                doPermissionGrantedStuffs();
            } else {
                alertAlert("nonthing");
            }
        }
    }

    private void alertAlert(String msg) {
        new AlertDialog.Builder(SmsActivity.this)
                .setTitle("Permission Request")
                .setMessage(msg)
                .setCancelable(false)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do somthing here
                    }
                })
                .show();
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    public void doPermissionGrantedStuffs() {
        //Have an  object of TelephonyManager
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        //Get IMEI Number of Phone  //////////////// for this example i only need the IMEI
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
         IMEINumber = tm.getDeviceId(0);
         IMEINumber1 = tm.getDeviceId(1);

        getCabTab(IMEINumber);
    }



        private void InternetConnectionChecker(String IMEINumber){
            if(tv_no_internet!=null)
            tv_no_internet.setVisibility(View.VISIBLE);
            CountDownTimer countDownTimerVideoDwnld = new CountDownTimer(30000, 30000) {
                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {
                    getCabTab(IMEINumber);
                }
            };
            countDownTimerVideoDwnld.start();
        }

    private void getCabTab(String IMEINumber) {

        if (Utils.isConnectingToInternet(mContext)) {
            if(tv_no_internet!=null)
            tv_no_internet.setVisibility(View.GONE);
            cabTabDetailsViewHolder.getCabTabByDriverImei("865781042487453")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(getCabTabObserverModified);
        } else {

            Toast.makeText(mContext, "No Internet Connection", Toast.LENGTH_SHORT).show();
            InternetConnectionChecker(IMEINumber);

        }
        // }
    }


    SingleObserver<Cab> getCabTabObserverModified = new SingleObserver<Cab>() {
        @Override
        public void onSubscribe(Disposable d) {
            bag.add(d);
            //MyProgressDialog.dismiss();
        }

        @Override
        public void onSuccess(Cab cab) {
            Log.d(TAG, "onSuccess: getCabTabObserverModified __________________________________________________________ ");
            y++;
            //Toast.makeText(mContext, "Success", Toast.LENGTH_SHORT).show();
            if (cab.getStatus().equalsIgnoreCase("Success")) {
                cabTabDetailsList = cab.getCabtabdetail();
                cabDetails = cabTabDetailsList.get(0).getCabDetails();
                if (cabTabDetailsList != null && cabDetails != null) {
                    myDB.createRecordsCabTab(cabTabDetailsList);
                    myDB.createRecordsCabTabDetails(cabDetails);
                   // Toast.makeText(mContext, "Login Successful", Toast.LENGTH_SHORT).show();
                    Log.e(TAG,"Intent");
                    Intent intent = new Intent(SmsActivity.this,SplashActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    if(y == 1){
                        getCabTab("865781042487453");
                    }else {
                        Toast.makeText(mContext, "Cab Login Not Found, Please Contact Administrator", Toast.LENGTH_SHORT).show();
                    }
                }

            }else{
                Toast.makeText(mContext, "Cab Login Not Found, Please Contact Administrator", Toast.LENGTH_SHORT).show();
            }
        }
        @Override
        public void onError(Throwable e) {
            InternetConnectionChecker(IMEINumber);
            Log.d(TAG, "onError: getCabTabObserverModified >> " + e.toString());
           // MyProgressDialog.dismiss();
           // Utils.showDialog(getApplicationContext(), ApplicationConstant.ANYTHING_WRONG);
        }
    };



    public void ApiCallEmailErrorLog(String error, StringBuffer errorReport) {
        myDB = new MyDB(mContext);
        String cab_tab_id = String.valueOf(myDB.getCabId());
        String error_message = "Error";
        String error_file = "VideoFaceDetectionActivity";
        String error_line = error;
        String error_context = String.valueOf(errorReport);
        String error_level = "High";
        String ip_address = getLocalIpAddress();
        String platform = "Android";
        String error_status = "Open";

        AddErrorLog(cab_tab_id, error_message, error_file, error_line, error_context, error_level, ip_address, platform, error_status);
    }

    private void AddErrorLog(String cab_tab_id, String error_message, String error_file, String error_line, String error_context, String error_level, String ip_address, String platform, String error_status) {
        if (Utils.isConnectingToInternet(mContext)) {
            cabTabDetailsViewHolder.addErrorLog(cab_tab_id, error_message, error_file, error_line, error_context, error_level, ip_address, platform, error_status)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(addErrorLog);
        } else {
            Toast.makeText(mContext, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }

    SingleObserver<VideoLogResponse> addErrorLog = new SingleObserver<VideoLogResponse>() {
        @Override
        public void onSubscribe(Disposable d) {
            bag.add(d);
        }

        @Override
        public void onSuccess(VideoLogResponse video) {
            Log.e(TAG, "onSuccess: getVideoListByPincode __________________________________________________________ ");
            if (("Success").equalsIgnoreCase(video.getStatus())) {

                Toast.makeText(mContext, "Error Log Updated", Toast.LENGTH_SHORT).show();


            } else {
                Toast.makeText(mContext, "Failed Error Log", Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        public void onError(Throwable e) {
            Log.e(TAG, "onError: Failed Error Log >> " + e.toString());
            MyProgressDialog.dismiss();
            Toast.makeText(mContext, ApplicationConstant.ANYTHING_WRONG, Toast.LENGTH_SHORT).show();
        }
    };


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

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(mContext, "SMS ACTIVITY", Toast.LENGTH_SHORT).show();
    }


//        getCreadenticalApiClient();
//
//        //init SmsVerifyCatcher
//        smsVerifyCatcher = new SmsVerifyCatcher(this, new OnSmsCatchListener<String>() {
//            @Override
//            public void onSmsCatch(String message) {
//                String code = parseCode(message);//Parse verification code
//                etCode.setText(code);//set code in edit text
//                //then you can send verification code to server
//            }
//        });
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//         //   ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSION_WRITE_EXTERNAL_STORAGE);
//            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
//            //    ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);
//                // TODO: Consider calling
//                //    ActivityCompat#requestPermissions
//                // here to request the missing permissions, and then overriding
//                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                //                                          int[] grantResults)
//                // to handle the case where the user grants the permission. See the documentation
//                // for ActivityCompat#requestPermissions for more details.
//                return;
//            }
//            List<SubscriptionInfo> subscription = SubscriptionManager.from(context).getActiveSubscriptionInfoList();
//            for (int i = 0; i < subscription.size(); i++) {
//                SubscriptionInfo info = subscription.get(i);
//
//                Log.d("TAG", "number " + info.getNumber());
//                Log.d("TAG", "network name : " + info.getCarrierName());
//                Log.d("TAG", "country iso " + info.getCountryIso());
//            }
//        }
//        try {
//            TelephonyManager tMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
//            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
//                // TODO: Consider calling
//                //    ActivityCompat#requestPermissions
//                // here to request the missing permissions, and then overriding
//                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                //                                          int[] grantResults)
//                // to handle the case where the user grants the permission. See the documentation
//                // for ActivityCompat#requestPermissions for more details.
//                return;
//            }
//
////            AccountManager am = AccountManager.get(this);
////            Account[] accounts = am.getAccounts();
////
////            for (Account ac : accounts) {
////                String acname = ac.name;
////                String actype = ac.type;
////                // Take your time to look at all available accounts
////                System.out.println("Accounts : " + acname + ", " + actype);
////            }
//
////            tMgr.getSimSerialNumber();
////            String mPhoneNumber = tMgr.getLine1Number();
////            SmsManager smgr = SmsManager.getDefault();
//
//
//           // smgr.sendTextMessage(txtMobile.getText().toString(),null,txtMessage.getText().toString(),null,null);
//            Toast.makeText(SmsActivity.this, "SMS Sent Successfully", Toast.LENGTH_SHORT).show();
//        }
//        catch (Exception e){
//            Toast.makeText(SmsActivity.this, "SMS Failed to Send, Please try again", Toast.LENGTH_SHORT).show();
//        }
//
////        try{
////            Intent i = new Intent(Intent.ACTION_VIEW);
////            i.setData(Uri.parse("smsto:"));
////            i.setType("vnd.android-dir/mms-sms");
////            i.putExtra("address", new String(txtMobile.getText().toString()));
////            i.putExtra("sms_body",txtMessage.getText().toString());
////            startActivity(Intent.createChooser(i, "Send sms via:"));
////        }
////        catch(Exception e){
////            Toast.makeText(SmsActivity.this, "SMS Failed to Send, Please try again", Toast.LENGTH_SHORT).show();
////        }
////        Intent sInt = new Intent(Intent.ACTION_VIEW);
////        sInt.putExtra("address", new String[]{txtMobile.getText().toString()});
////        sInt.putExtra("sms_body",txtMessage.getText().toString());
////        sInt.setType("vnd.android-dir/mms-sms");
//        //set phone number filter if needed
//        smsVerifyCatcher.setPhoneNumberFilter("+918668456087");
//
//
//        //smsVerifyCatcher.setFilter("regexp");
//
//        //button for sending verification code manual
//        btnVerify.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//
//                Intent intent = new Intent(SmsActivity.this,SplashActivity.class);
//                startActivity(intent);
//                finish();
//                //send verification code to server
//            }
//        });
    }

    /**
     * Parse verification code
     *
     * @param message sms message
     * @return only four numbers from massage string
     */
//    private String parseCode(String message) {
//        Pattern p = Pattern.compile("\\b\\d{4}\\b");
//        Matcher m = p.matcher(message);
//        String code = "";
//        while (m.find()) {
//            code = m.group(0);
//        }
//        return code;
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        smsVerifyCatcher.onStart();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        smsVerifyCatcher.onStop();
//    }
//
//    /**
//     * need for Android 6 real time permissions
//     */
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        smsVerifyCatcher.onRequestPermissionsResult(requestCode, permissions, grantResults);
//    }
//
//    private void getCreadenticalApiClient() {
//        mCredentialsApiClient = new GoogleApiClient.Builder(getBaseContext())
//                .addConnectionCallbacks(this)
//                .enableAutoManage(SmsActivity.this, this)
//                .addApi(Auth.CREDENTIALS_API)
//                .build();
//
//
//    }
//
//
//    // Construct a request for phone numbers and show the picker
//    private void requestHint() {
//        final HintRequest hintRequest =
//                new HintRequest.Builder().setPhoneNumberIdentifierSupported(true).build();
//
//        try {
//            final GoogleApiClient googleApiClient =
//                    new GoogleApiClient.Builder(context).addApi(Auth.CREDENTIALS_API).build();
//
//            final PendingIntent pendingIntent =
//                    Auth.CredentialsApi.getHintPickerIntent(mCredentialsApiClient, hintRequest);
//            startIntentSenderForResult(pendingIntent.getIntentSender(), PHONE_NUMBER_HINT, null, 0, 0, 0);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }    }
//
//    // Obtain the phone number from the result
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == PHONE_NUMBER_HINT && resultCode == RESULT_OK) {
//            com.google.android.gms.auth.api.credentials.Credential credential = data.getParcelableExtra(com.google.android.gms.auth.api.credentials.Credential.EXTRA_KEY);
//            final String phoneNumber = credential.getId();
//        }
//    }
//
//    @Override
//    public void onConnected(@Nullable Bundle bundle) {
//        requestHint();
//    }
//
//    @Override
//    public void onConnectionSuspended(int i) {
//
//    }
//
//    @Override
//    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
//
//    }
//}
