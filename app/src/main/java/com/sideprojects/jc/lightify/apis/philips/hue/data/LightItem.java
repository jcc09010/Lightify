package com.sideprojects.jc.lightify.apis.philips.hue.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by justin.chu on 2/5/17.
 */

public class LightItem {

    public static final String TAG = LightItem.class.getSimpleName();

    public static final String KEY_TYPE = "type";
    public static final String KEY_NAME = "name";
    public static final String KEY_MODEL_ID = "modelid";
    public static final String KEY_MANUFACTURER = "manufacturername";
    public static final String KEY_UID = "uniqueid";
    public static final String KEY_SW_VERSION = "swversion";
    public static final String KEY_SW_CONFIG = "swconfigid"; // Supported in V3
    public static final String KEY_PRODUCT_ID = "productid"; // Supported in V3

    public static final String KEY_STATE = "state";

    private String id;

    @SerializedName(KEY_TYPE)
    private @NonNull String type;

    @SerializedName(KEY_NAME)
    private @NonNull String name;

    @SerializedName(KEY_MODEL_ID)
    private @NonNull String modelId;

    @SerializedName(KEY_MANUFACTURER)
    private @NonNull String manufacturer;

    @SerializedName(KEY_UID)
    private @NonNull String uid;

    @SerializedName(KEY_SW_VERSION)
    private @NonNull String softwareVersion;

    @SerializedName(KEY_SW_CONFIG)
    private @Nullable String softwareConfig;

    @SerializedName(KEY_PRODUCT_ID)
    private @Nullable String productId;

    @SerializedName(KEY_STATE)
    private @NonNull State state;

    public void setId(String id){
        this.id = id;
    }

    public String id(){
        return id;
    }

    @NonNull
    public String type() {
        return type;
    }

    @NonNull
    public String name() {
        return name;
    }

    @NonNull
    public String modelId() {
        return modelId;
    }

    @NonNull
    public String manufacturer() {
        return manufacturer;
    }

    @NonNull
    public String uid() {
        return uid;
    }

    @NonNull
    public String softwareVersion() {
        return softwareVersion;
    }

    @Nullable
    public String softwareConfig() {
        return softwareConfig;
    }

    @Nullable
    public String productId() {
        return productId;
    }

    @NonNull
    public State state() {
        return state;
    }

    public static class State {

        public static final String KEY_ON = "on";
        public static final String KEY_BRIGHTNESS = "bri";
        public static final String KEY_HUE = "hue";
        public static final String KEY_SATURATION = "sat";
        public static final String KEY_EFFECT = "effect";
        public static final String KEY_XY = "xy";
        public static final String KEY_COLOR_TEMP = "ct";
        public static final String KEY_ALERT = "alert";
        public static final String KEY_COLOR_MODE = "colormode";
        public static final String KEY_REACHABLE = "reachable";
        public static final String KEY_TRANSITION_TIME = "transitiontime";

        @SerializedName(KEY_ON)
        private boolean isOn;

        @SerializedName(KEY_BRIGHTNESS)
        private int brightness;

        @SerializedName(KEY_HUE)
        private int hue;

        @SerializedName(KEY_SATURATION)
        private int saturation;

        @SerializedName(KEY_EFFECT)
        private String effect;

        @SerializedName(KEY_XY)
        private float[] xy;

        @SerializedName(KEY_COLOR_TEMP)
        private int colorTemperature;

        @SerializedName(KEY_ALERT)
        private String alert;

        @SerializedName(KEY_COLOR_MODE)
        private String colorMode;

        @SerializedName(KEY_REACHABLE)
        private boolean reachable;

        public boolean isOn() {
            return isOn;
        }

        public int brightness() {
            return brightness;
        }

        public int hue() {
            return hue;
        }

        public int saturation() {
            return saturation;
        }

        public String effect() {
            return effect;
        }

        public float[] xy() {
            return xy;
        }

        public float x(){
            return xy[0];
        }

        public float y(){
            return xy[1];
        }

        public int colorTemperature() {
            return colorTemperature;
        }

        public String alert() {
            return alert;
        }

        public String colorMode() {
            return colorMode;
        }

        public boolean isReachable() {
            return reachable;
        }
    }

}
