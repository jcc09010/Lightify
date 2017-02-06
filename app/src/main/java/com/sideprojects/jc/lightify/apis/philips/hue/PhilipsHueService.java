package com.sideprojects.jc.lightify.apis.philips.hue;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by justin.chu on 2/5/17.
 */

public class PhilipsHueService {

    private static final String BASE_URL = "http://%s/";
    private static final String PATH_API = "api";

    private static String getBridgeURL(String bridgeIp){
        return String.format(BASE_URL, bridgeIp);
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     *
     *                    Bridge Discovery API
     *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    private static final String URL_N_UPNP_BROKER = "https://www.meethue.com/api/";
    private static final String PATH_N_UPNP_DISCOVERY = "nupnp";

    public static BridgeDiscoveryApi discoveryService(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_N_UPNP_BROKER)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build();
        return retrofit.create(BridgeDiscoveryApi.class);
    }


    public interface BridgeDiscoveryApi {
        @GET(PATH_N_UPNP_DISCOVERY)
        Observable<List<Bridge>> discoverBridges();
    }

    /**
     * Response Objects
     */
    public static class Bridge implements Parcelable {

        public static final String TAG = Bridge.class.getSimpleName();

        public static final String KEY_ID = "id";
        public static final String KEY_LOCAL_IP = "internalipaddress";
        public static final String KEY_MAC = "macaddress";
        public static final String KEY_BRIDGE_NAME = "name";

        @SerializedName(KEY_ID)
        @Expose
        private @NonNull String id;
        @SerializedName(KEY_LOCAL_IP)
        @Expose
        private @NonNull String ip;
        @SerializedName(KEY_MAC)
        @Expose
        private @Nullable String mac;
        @SerializedName(KEY_BRIDGE_NAME)
        @Expose
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

        public Bridge() {
        }

        protected Bridge(Parcel in) {
            this.id = in.readString();
            this.ip = in.readString();
            this.mac = in.readString();
            this.name = in.readString();
        }

        public static final Parcelable.Creator<Bridge> CREATOR = new Parcelable.Creator<Bridge>() {
            @Override
            public Bridge createFromParcel(Parcel source) {
                return new Bridge(source);
            }

            @Override
            public Bridge[] newArray(int size) {
                return new Bridge[size];
            }
        };
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     *
     *                    Onboarding API
     *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    private static final String KEY_DEVICE_TYPE = "devicetype";

    public interface OnboardingApi {
        @POST(PATH_API)
        Observable<List<OnboardingResponse>> onboard(@Body RequestBody body);
    }

    private static OnboardingApi onboardingService(@NonNull String ip){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getBridgeURL(ip))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build();
        return retrofit.create(OnboardingApi.class);
    }

    public static @NonNull Observable<List<OnboardingResponse>> onboardBridge(
            @NonNull Bridge bridge,
            @NonNull String userName){
        StringBuilder builder = new StringBuilder()
                .append("{\"")
                .append(KEY_DEVICE_TYPE).append("\":\"").append(userName)
                .append("\"}");
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), builder.toString());
        return onboardingService(bridge.ip).onboard(body);
    }

    /**
     * Response Objects
     */
    public static class OnboardingResponse {

        public static final String KEY_SUCCESS = "success";
        public static final String KEY_ERROR = "error";

        @SerializedName(KEY_SUCCESS)
        @Expose
        private @Nullable Success success;
        @SerializedName(KEY_ERROR)
        @Expose
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
            @Expose
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
            @Expose
            private String errorType;
            @SerializedName(KEY_ADDRESS)
            @Expose
            private String address;
            @SerializedName(KEY_MESSAGE)
            @Expose
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


    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     *
     *                    Light Control API
     *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    private static final String PATH_LIGHTS = "lights";
    private static final String PATH_STATE = "state";

    public interface LightApi{
        @GET(PATH_LIGHTS)
        Observable<List<LightItem>> getLights();

        @PUT(PATH_LIGHTS + "/{id}/" + PATH_STATE)
        Observable<List<LightStateResponse>> setLightState(@Path("id") String id, @Body LightItem.StateRequest request);
    }

    private static LightApi mSharedLightService;

    public static LightApi lightService(@NonNull String ip, @NonNull String userId){
        Uri uri = Uri.parse(getBridgeURL(ip)).buildUpon()
                .appendPath(PATH_API)
                .appendPath(userId)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(uri.toString() + "/")
                .addConverterFactory(GsonConverterFactory.create(gsonForLightItems()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build();
        return retrofit.create(LightApi.class);
    }

    public static LightApi lightService(){
        return mSharedLightService;
    }

    public static LightApi setupSharedLightService(@NonNull String ip, @NonNull String userId){
        mSharedLightService = lightService(ip, userId);
        return mSharedLightService;
    }

    public static void clearSharedLightService(){
        mSharedLightService = null;
    }

    /**
     * Response Objects
     */
    public static class LightStateResponse{

        public static final String KEY_SUCCESS = "success";
        public static final String KEY_ERROR = "error";

        @SerializedName(KEY_SUCCESS)
        @Expose
        private @Nullable Map<String, Object> success;

        @SerializedName(KEY_ERROR)
        @Expose
        private @Nullable Map<String, Object> error;

        public Map<String, Object> response() {
            return success;
        }

        public Map<String, Object> error(){
            return error;
        }
    }

    /**
     * Returns a GSON object with custom Light Item deserializer.
     * @return
     */
    private static Gson gsonForLightItems(){
        Type listType = new TypeToken<List<LightItem>>(){}.getType();
        return new GsonBuilder()
                .registerTypeAdapter(listType, new LightItemGSONDeserializer())
                .create();
    }

    /**
     * A custom Deserializer written for {@link LightItem}.
     * The JSON response from Philips Hue Bridge uses "key" as the ID of light item.
     * A custom Gson deserializer is needed to parse light item correctly.
     */
    private static class LightItemGSONDeserializer implements JsonDeserializer<List<LightItem>>{
        @Override
        public List<LightItem> deserialize(JsonElement element, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            List<LightItem> items = new ArrayList<>();
            for(Map.Entry<String, JsonElement> entry : element.getAsJsonObject().entrySet()){
                LightItem item = context.deserialize(entry.getValue(), LightItem.class);
                item.setId(entry.getKey());
                items.add(item);
            }
            return items;
        }
    }
}
