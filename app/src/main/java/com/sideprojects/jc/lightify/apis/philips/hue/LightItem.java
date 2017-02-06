package com.sideprojects.jc.lightify.apis.philips.hue;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.annotations.Expose;
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
    @Expose
    private @NonNull String type;

    @SerializedName(KEY_NAME)
    @Expose
    private @NonNull String name;

    @SerializedName(KEY_MODEL_ID)
    @Expose
    private @NonNull String modelId;

    @SerializedName(KEY_MANUFACTURER)
    @Expose
    private @NonNull String manufacturer;

    @SerializedName(KEY_UID)
    @Expose
    private @NonNull String uid;

    @SerializedName(KEY_SW_VERSION)
    @Expose
    private @NonNull String softwareVersion;

    @SerializedName(KEY_SW_CONFIG)
    @Expose
    private @Nullable String softwareConfig;

    @SerializedName(KEY_PRODUCT_ID)
    @Expose
    private @Nullable String productId;

    @SerializedName(KEY_STATE)
    @Expose
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

        @SerializedName(KEY_ON)
        @Expose
        private boolean isOn;

        @SerializedName(KEY_BRIGHTNESS)
        @Expose
        private int brightness;

        @SerializedName(KEY_HUE)
        @Expose
        private int hue;

        @SerializedName(KEY_SATURATION)
        @Expose
        private int saturation;

        @SerializedName(KEY_EFFECT)
        @Expose
        private String effect;

        @SerializedName(KEY_XY)
        @Expose
        private int[] xy;

        @SerializedName(KEY_COLOR_TEMP)
        @Expose
        private int colorTemperature;

        @SerializedName(KEY_ALERT)
        @Expose
        private String alert;

        @SerializedName(KEY_COLOR_MODE)
        @Expose
        private String colorMode;

        @SerializedName(KEY_REACHABLE)
        @Expose
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

        public int[] xy() {
            return xy;
        }

        public int x(){
            return xy[0];
        }

        public int y(){
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
