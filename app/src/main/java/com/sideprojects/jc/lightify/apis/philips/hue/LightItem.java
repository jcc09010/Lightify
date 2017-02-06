package com.sideprojects.jc.lightify.apis.philips.hue;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

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
        public static final String KEY_TRANSITION_TIME = "transitiontime";

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

    public static class StateRequest {
        @SerializedName(State.KEY_ON)
        public Boolean on;

        @SerializedName(State.KEY_BRIGHTNESS)
        public Integer brightness;

        @SerializedName(State.KEY_HUE)
        public Integer hue;

        @SerializedName(State.KEY_SATURATION)
        public Integer saturation;

        @SerializedName(State.KEY_ALERT)
        public String alert;

        @SerializedName(State.KEY_EFFECT)
        public String effect;

        @SerializedName(State.KEY_COLOR_TEMP)
        public Integer colorTemp;

        @SerializedName(State.KEY_TRANSITION_TIME)
        public Integer transitionTime;
    }

    public static class RequestBuilder {

        private StateRequest request;

        public static RequestBuilder start(){
            return new RequestBuilder();
        }

        public RequestBuilder(){
            request = new StateRequest();
        }

        public RequestBuilder setOn(boolean on){
            request.on = on;
            return this;
        }

        public RequestBuilder setBrightness(@IntRange(from=-254,to=254) int brightness){
            request.brightness = brightness;
            return this;
        }

        public RequestBuilder setHue(@IntRange(from=-65534,to=65534) int hue){
            request.hue = hue;
            return this;
        }

        public RequestBuilder setSaturation(@IntRange(from=-254,to=254) int saturation){
            request.saturation = saturation;
            return this;
        }

        public RequestBuilder setAlert(String alert){
            request.alert = alert;
            return this;
        }

        public RequestBuilder setEffect(String effect){
            request.effect = effect;
            return this;
        }

        public RequestBuilder setColorTemperature(@IntRange(from=-65534,to=65534) int temp){
            request.colorTemp = temp;
            return this;
        }

        public RequestBuilder setTransitionTime(int deciseconds){
            request.transitionTime = deciseconds;
            return this;
        }

        public StateRequest build(){
            return request;
        }
    }
}
