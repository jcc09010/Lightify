package com.sideprojects.jc.lightify.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by justin.chu on 2/5/17.
 */

public class PreferencesUtil {

    public static final String PREFERENCE_BRIDGE_IP = "bridge_ip";
    public static final String PREFERENCE_USER_ID = "hue_user_id";

    public static @Nullable String getBridgeIP(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(PREFERENCE_BRIDGE_IP, null);
    }

    public static void setBridgeIp(Context context, @NonNull String ip){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putString(PREFERENCE_BRIDGE_IP, ip).apply();
    }

    public static @Nullable String getUserId(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(PREFERENCE_USER_ID, null);
    }

    public static void setUserId(Context context, @NonNull String id){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putString(PREFERENCE_USER_ID, id).apply();
    }
}
