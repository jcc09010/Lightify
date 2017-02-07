package com.sideprojects.jc.lightify.apis.philips.hue.messeging;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

/**
 * Response Objects
 */
public class OnboardingResponse {

    public static final String KEY_SUCCESS = "success";
    public static final String KEY_ERROR = "error";

    @SerializedName(KEY_SUCCESS)
    private @Nullable Success success;

    @SerializedName(KEY_ERROR)
    private @Nullable Error error;

    @Nullable
    public Success success() {
        return success;
    }

    @Nullable
    public Error error() {
        return error;
    }

    public static class Success {
        public static final String KEY_USER_ID = "username";

        @SerializedName(KEY_USER_ID)
        private String userId;

        public String userId() {
            return userId;
        }
    }

    public static class Error {
        public static final String KEY_ERROR_TYPE = "type";
        public static final String KEY_ADDRESS = "address";
        public static final String KEY_MESSAGE = "description";

        @SerializedName(KEY_ERROR_TYPE)
        private String errorType;

        @SerializedName(KEY_ADDRESS)
        private String address;

        @SerializedName(KEY_MESSAGE)
        private String errorMessage;

        public String errorType() {
            return errorType;
        }

        public String bridgeAddress() {
            return address;
        }

        public String errorMessage() {
            return errorMessage;
        }
    }
}
