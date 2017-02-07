package com.sideprojects.jc.lightify.apis.philips.hue.messeging;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 * Created by {@author justin.chu} on 2/6/17.
 */
public class LightControlResponse {

    public static final String KEY_SUCCESS = "success";
    public static final String KEY_ERROR = "error";

    @SerializedName(KEY_SUCCESS)
    private @Nullable Map<String, Object> success;

    @SerializedName(KEY_ERROR)
    private @Nullable Map<String, Object> error;

    public Map<String, Object> response() {
        return success;
    }

    public Map<String, Object> error(){
        return error;
    }
}
