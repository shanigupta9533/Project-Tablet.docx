package com.dragosholbann.androidfacedetection.SharedPref;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.dragosholbann.androidfacedetection.ApiModels.Videos;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class SessionManager
{
    public void setPreferences(Context context, String key, String value)
    {
        SharedPreferences.Editor editor = context.getSharedPreferences("status", Context.MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.apply();
    }

    public  String getPreferences(Context context, String key)
    {
        SharedPreferences prefs = context.getSharedPreferences("status",	Context.MODE_PRIVATE);
        return prefs.getString(key, "");
    }

    public  void deletePreferences(Context context, String key)
    {
        SharedPreferences prefs = context.getSharedPreferences("status",	Context.MODE_PRIVATE);
        prefs.edit().remove(key).apply();
    }

    public void setPreferencesArrayList(Context context, String key, Object arrayList)
    {
        SharedPreferences.Editor editor = context.getSharedPreferences("status", Context.MODE_PRIVATE).edit();
        Gson gson = new Gson();
        String json = gson.toJson(arrayList);
        editor.putString(key, json);
        editor.apply();

    }

    public  List<Videos> getPreferencesArrayList(Context context, String key)
    {
        SharedPreferences prefs = context.getSharedPreferences("status",	Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString(key, "");
        Type type = new TypeToken<List<Videos>>() {}.getType();
        List<Videos> arrayList = gson.fromJson(json, type);

        return arrayList;
    }





}