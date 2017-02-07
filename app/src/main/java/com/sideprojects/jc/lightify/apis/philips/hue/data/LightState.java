package com.sideprojects.jc.lightify.apis.philips.hue.data;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;

import com.google.gson.annotations.SerializedName;

/**
 * Created by {@author justin.chu} on 2/6/17.
 */
public class LightState implements Parcelable {

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

    @IntRange(from=Constants.Brightness.MIN, to=Constants.Brightness.MAX)
    public int brightness() {
        return brightness;
    }

    @IntRange(from=Constants.Hue.MIN, to=Constants.Hue.MAX)
    public int hue() {
        return hue;
    }

    @IntRange(from=Constants.Saturation.MIN, to=Constants.Saturation.MAX)
    public int saturation() {
        return saturation;
    }

    public String effect() {
        return effect;
    }

    public float[] xy() {
        return xy;
    }

    @FloatRange(from=Constants.Color.X_MIN, to=Constants.Color.X_MAX)
    public float x() {
        return xy[0];
    }

    @FloatRange(from=Constants.Color.Y_MIN, to=Constants.Color.Y_MAX)
    public float y() {
        return xy[1];
    }

    @IntRange(from=Constants.Color.TEMP_MIN, to=Constants.Color.TEMP_MAX)
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.isOn ? (byte) 1 : (byte) 0);
        dest.writeInt(this.brightness);
        dest.writeInt(this.hue);
        dest.writeInt(this.saturation);
        dest.writeString(this.effect);
        dest.writeFloatArray(this.xy);
        dest.writeInt(this.colorTemperature);
        dest.writeString(this.alert);
        dest.writeString(this.colorMode);
        dest.writeByte(this.reachable ? (byte) 1 : (byte) 0);
    }

    protected LightState(Parcel in) {
        this.isOn = in.readByte() != 0;
        this.brightness = in.readInt();
        this.hue = in.readInt();
        this.saturation = in.readInt();
        this.effect = in.readString();
        this.xy = in.createFloatArray();
        this.colorTemperature = in.readInt();
        this.alert = in.readString();
        this.colorMode = in.readString();
        this.reachable = in.readByte() != 0;
    }

    public static final Creator<LightState> CREATOR = new Creator<LightState>() {
        @Override
        public LightState createFromParcel(Parcel source) {
            return new LightState(source);
        }

        @Override
        public LightState[] newArray(int size) {
            return new LightState[size];
        }
    };
}
