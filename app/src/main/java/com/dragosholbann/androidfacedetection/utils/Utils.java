package com.dragosholbann.androidfacedetection.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.dragosholbann.androidfacedetection.ModelLayer.Register;
import com.dragosholbann.androidfacedetection.ModelLayer.UserData;
import com.dragosholbann.androidfacedetection.R;
import com.dragosholbann.androidfacedetection.constants.ApplicationConstant;


public class Utils {


    public static Toolbar mToolbar;
    public static TextView toolbarTitle;
    public static ImageView nextButton;

    public static int convertDpToPixels(float dp, Context context) {
        Resources resources = context.getResources();
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                resources.getDisplayMetrics()
        );

    }



    public static boolean isConnectingToInternet(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
        }
        return false;
    }



    public static String getUserLogin(Context context) {

        SharedPreferences sharedPreferences = context.getSharedPreferences("PREF_USER", Context.MODE_PRIVATE);
        String userJson = sharedPreferences.getString("TAG_USER", "");

        if (ApplicationConstant.DEBUG) {
            Log.d("UserLogin",userJson);
        }

        if (!userJson.isEmpty()) {
            Register login = GlobalGson.get().fromJson(userJson, Register.class);
            return login.getStatus();
        } else {
            return "null";
        }
    }





    public static String getToken(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("PREF_USER", Context.MODE_PRIVATE);
        String userJson = sharedPreferences.getString("TAG_USER", "");

        if (ApplicationConstant.DEBUG) {
            Log.d("UserLogin",userJson);
        }

        if (!userJson.isEmpty()) {
            Register login = GlobalGson.get().fromJson(userJson, Register.class);
            return login.getUserData().getApiKey();


        } else {
            return null;
        }
    }

    public static String getUserId(Context context) {

        SharedPreferences sharedPreferences = context.getSharedPreferences("PREF_USER", Context.MODE_PRIVATE);
        String userJson = sharedPreferences.getString("TAG_USER", "");

        if (ApplicationConstant.DEBUG) {
            Log.d("UserLogin",userJson);
        }

        if (!userJson.isEmpty()) {
            Register login = GlobalGson.get().fromJson(userJson, Register.class);
            return login.getUserData().getUserId();
        } else {
            return null;
        }
    }







    public static UserData getUserDetail(Context context) {

        SharedPreferences sharedPreferences = context.getSharedPreferences("PREF_USER", Context.MODE_PRIVATE);
        String userJson = sharedPreferences.getString("TAG_USER", "");

        if (ApplicationConstant.DEBUG) {
            Log.d("UserLogin",userJson);
        }

        if (!userJson.isEmpty()) {
            Register login = GlobalGson.get().fromJson(userJson, Register.class);
            return login.getUserData();
        } else {
            return null;
        }
    }




    public static void saveUserFcmId(Context context, String fcmId) {
        SharedPreferences.Editor editor = context.getSharedPreferences("PREF_USER", Context.MODE_PRIVATE).edit();
        editor.putString("TAG_USER_FCM", fcmId);
        editor.apply();
    }

    public static String getUserFcmId(Context context) {

        SharedPreferences sharedPreferences = context.getSharedPreferences("PREF_USER", Context.MODE_PRIVATE);
        String fcmId = sharedPreferences.getString("TAG_USER_FCM", "");

        if (ApplicationConstant.DEBUG) {
            //.log(fcmId);
        }
        if (!fcmId.isEmpty()) {
            return fcmId;
        } else {
            return null;
        }
    }



    public static void hideSoftKeyboard(InputMethodManager inputMethodManager, View view) {
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    public static void hideKeyboard(AppCompatActivity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static boolean emailValidator(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isValidMobile(String phone) {
        return android.util.Patterns.PHONE.matcher(phone).matches();
    }

    public static @NonNull
    <T> T checkNotNull(final T reference) {
        if (reference == null) {
            throw new NullPointerException();
        }
        return reference;
    }

    public static String ifStringNullOrEmpty(final String reference, String emptyHolder) {
        if (reference == null) {
            return emptyHolder;
        } else {
            if (reference.trim().isEmpty()) {
                return emptyHolder;
            }
            return reference;
        }

    }

    public static void replaceFragment(@NonNull Context context, @NonNull Fragment fragment, Bundle bundle, int frameId, boolean addToBackStack) {
        checkNotNull(fragment);

        FragmentActivity fragmentActivity = (FragmentActivity) context;
        FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();

        if (bundle != null) {
            //bundle.putAll(fragment.getArguments());
            fragment.setArguments(bundle);
        }

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        Log.e("Replace Fragment: ", " -> " + fragment.getClass().getSimpleName());
        transaction.replace(frameId, fragment, "tag" + fragment.getClass().getSimpleName());

        if (addToBackStack) {
            transaction.addToBackStack("stack" + fragment.getClass().getSimpleName()); // <-- This makes magic!
        } else {
            transaction.disallowAddToBackStack();
        }

        transaction.commit();
    }

    /*public static String getRealPathFromUri(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.};
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }*/

    public static void removeFragment(@NonNull Context context, @NonNull Fragment fragment) {
        checkNotNull(fragment);

        FragmentActivity fragmentActivity = (FragmentActivity) context;
        FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();
        FragmentTransaction trans = fragmentManager.beginTransaction();
        trans.remove(fragment);
        trans.commit();

        fragmentManager.popBackStack();
    }

    public static Toolbar setupToolbar(final Activity activity, @Nullable String str, View.OnClickListener clickListener) {

        mToolbar =  activity.findViewById(R.id.toolbar);

        AppCompatActivity appCompatActivity = (AppCompatActivity) activity;

        appCompatActivity.setSupportActionBar(mToolbar);

        // Set onClickListener to customView
        toolbarTitle =  mToolbar.findViewById(R.id.txt_title);
        nextButton =  mToolbar.findViewById(R.id.img_privous);

        return mToolbar;
    }

    public static void setToolbarTitle(String toolbarTitle) {
        if (Utils.toolbarTitle != null) {
            Utils.toolbarTitle.setText(toolbarTitle);
         }
      }

      public static void showDialog(Context context, String message) {

        // todo: Uncomment
//        if (ApplicationConstant.DEBUG) {

        AlertDialog.Builder buidler = new AlertDialog.Builder(context);
        buidler.setMessage(message);
        buidler.setCancelable(false);
        buidler.setPositiveButton(context.getResources().getString(R.string.ok_label),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method
                    }
                });
        AlertDialog dialog = buidler.create();
        dialog.show();
//        }
    }


    public static void saveUserPreference(Context context, String userJson) {
        SharedPreferences.Editor editor = context.getSharedPreferences("PREF_USER", Context.MODE_PRIVATE).edit();
        editor.putString("TAG_USER", userJson);
        editor.apply();
    }
}
