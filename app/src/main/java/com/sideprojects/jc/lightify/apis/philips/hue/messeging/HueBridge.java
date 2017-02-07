package com.sideprojects.jc.lightify.apis.philips.hue.messeging;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

/**
 * A class that represents a discovered hue bridge.
 * It's currently only used for onboarding.
 * Created by {@author justin.chu} on 2/6/17.
 */
public class HueBridge implements Parcelable {

    public static final String TAG = HueBridge.class.getSimpleName();

    public static final String KEY_ID = "id";
    public static final String KEY_LOCAL_IP = "internalipaddress";
    public static final String KEY_MAC = "macaddress";
    public static final String KEY_BRIDGE_NAME = "name";

    @SerializedName(KEY_ID)
    private @NonNull String id;

    @SerializedName(KEY_LOCAL_IP)
    private @NonNull String ip;

    @SerializedName(KEY_MAC)
    private @Nullable String mac;

    @SerializedName(KEY_BRIDGE_NAME)
    private @Nullable String name;

    @NonNull
    public String id() {
        return id;
    }

    @NonNull
    public String ip() {
        return ip;
    }

    @Nullable
    public String mac() {
        return mac;
    }

    @Nullable
    public String name() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.ip);
        dest.writeString(this.mac);
        dest.writeString(this.name);
    }

    protected HueBridge(Parcel in) {
        this.id = in.readString();
        this.ip = in.readString();
        this.mac = in.readString();
        this.name = in.readString();
    }

    public static final Creator<HueBridge> CREATOR = new Creator<HueBridge>() {
        @Override
        public HueBridge createFromParcel(Parcel source) {
            return new HueBridge(source);
        }

        @Override
        public HueBridge[] newArray(int size) {
            return new HueBridge[size];
        }
    };
}
