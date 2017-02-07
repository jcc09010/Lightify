package com.sideprojects.jc.lightify.apis.philips.hue.data;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by justin.chu on 2/5/17.
 */

public class LightItem implements Parcelable {

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
    private @NonNull
    LightState state;

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
    public LightState state() {
        return state;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.type);
        dest.writeString(this.name);
        dest.writeString(this.modelId);
        dest.writeString(this.manufacturer);
        dest.writeString(this.uid);
        dest.writeString(this.softwareVersion);
        dest.writeString(this.softwareConfig);
        dest.writeString(this.productId);
        dest.writeParcelable(this.state, flags);
    }

    protected LightItem(Parcel in) {
        this.id = in.readString();
        this.type = in.readString();
        this.name = in.readString();
        this.modelId = in.readString();
        this.manufacturer = in.readString();
        this.uid = in.readString();
        this.softwareVersion = in.readString();
        this.softwareConfig = in.readString();
        this.productId = in.readString();
        this.state = in.readParcelable(LightState.class.getClassLoader());
    }

    public static final Parcelable.Creator<LightItem> CREATOR = new Parcelable.Creator<LightItem>() {
        @Override
        public LightItem createFromParcel(Parcel source) {
            return new LightItem(source);
        }

        @Override
        public LightItem[] newArray(int size) {
            return new LightItem[size];
        }
    };
}
