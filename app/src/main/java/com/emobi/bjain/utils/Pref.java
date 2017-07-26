package com.emobi.bjain.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.emobi.bjain.pojo.featurelist.FeaturePOJO;
import com.google.gson.Gson;

import java.io.File;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by sunil on 29-04-2017.
 */

public class Pref {
    private static final String PrefDB = "booklings.txt";


    public static final String FCM_REGISTRATION_TOKEN = "fcm_registration_token";
    public static final String FEATURE_LIST_DATA = "feature_list_data";
    public static final String CURRENCY = "currency";


    public static void SetStringPref(Context context, String KEY, String Value) {
        SharedPreferences sp = context.getSharedPreferences(PrefDB, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(KEY, Value);
        editor.commit();
    }

    public static String GetStringPref(Context context, String KEY, String defValue) {
        SharedPreferences sp = context.getSharedPreferences(PrefDB, MODE_PRIVATE);
        return sp.getString(KEY, defValue);
    }

    public static void SetBooleanPref(Context context, String KEY, boolean Value) {
        SharedPreferences sp = context.getSharedPreferences(PrefDB, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(KEY, Value);
        editor.commit();
    }

    public static boolean GetBooleanPref(Context context, String KEY, boolean defValue) {
        SharedPreferences sp = context.getSharedPreferences(PrefDB, MODE_PRIVATE);
        return sp.getBoolean(KEY, defValue);
    }

    public static void clearSharedPreference(Context context) {
        SharedPreferences sp = context.getSharedPreferences(PrefDB, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();
    }

    public static void clearALLSharedPreferences(Context context) {
        File sharedPreferenceFile = new File("/data/data/" + context.getPackageName() + "/shared_prefs/");
        File[] listFiles = sharedPreferenceFile.listFiles();
        for (File file : listFiles) {
            file.delete();
        }
    }

    public static void SetCurrency(Context context, String Value) {
        SharedPreferences sp = context.getSharedPreferences("currencybookling.txt", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(CURRENCY, Value);
        editor.commit();
    }

    public static String GetCurrency(Context context) {
        SharedPreferences sp = context.getSharedPreferences("currencybookling.txt", MODE_PRIVATE);
        return sp.getString(CURRENCY, "INR");
    }

    public static String GetNondefCurrency(Context context) {
        SharedPreferences sp = context.getSharedPreferences("currencybookling.txt", MODE_PRIVATE);
        return sp.getString(CURRENCY, "");
    }


    public static void SetDeviceToken(Context context, String Value) {
        SharedPreferences sp = context.getSharedPreferences("devicebookling.txt", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(FCM_REGISTRATION_TOKEN, Value);
        editor.commit();
    }

    public static String GetDeviceToken(Context context, String defValue) {
        SharedPreferences sp = context.getSharedPreferences("devicebookling.txt", MODE_PRIVATE);
        return sp.getString(FCM_REGISTRATION_TOKEN, defValue);
    }


    public static <T> void SavePOJO(Context context, String KEY, T object) {
        SharedPreferences mPrefs = context.getSharedPreferences(Pref.PrefDB, MODE_PRIVATE);
        SharedPreferences.Editor editor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(object);
        editor.putString(KEY, json);
        editor.commit();
    }

    public static final String TAG = Pref.class.getSimpleName();

    public static FeaturePOJO GetPOJO(Context context, String KEY) {
        try {
            SharedPreferences mPrefs = context.getSharedPreferences(Pref.PrefDB, MODE_PRIVATE);
            Gson gson = new Gson();
            String json = mPrefs.getString(KEY, "");
            Log.d(TAG, "feature response:-" + json);

            return gson.fromJson(json, FeaturePOJO.class);
        } catch (Exception e) {
            return null;
        }
    }


}
