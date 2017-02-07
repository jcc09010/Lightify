package com.sideprojects.jc.lightify.apis.philips.hue;

import android.net.Uri;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.sideprojects.jc.lightify.apis.philips.hue.data.LightItem;
import com.sideprojects.jc.lightify.apis.philips.hue.data.LightItemGSONDeserializer;
import com.sideprojects.jc.lightify.apis.philips.hue.messeging.HueBridge;
import com.sideprojects.jc.lightify.apis.philips.hue.messeging.LightControlRequest;
import com.sideprojects.jc.lightify.apis.philips.hue.messeging.LightControlResponse;
import com.sideprojects.jc.lightify.apis.philips.hue.messeging.OnboardingResponse;

import java.lang.reflect.Type;
import java.util.List;

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
        Observable<List<HueBridge>> discoverBridges();
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
            @NonNull HueBridge bridge,
            @NonNull String userName){
        String bodyString = "{\"" + KEY_DEVICE_TYPE + "\":\"" + userName + "\"}";
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), bodyString);
        return onboardingService(bridge.ip()).onboard(body);
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

        @GET(PATH_LIGHTS + "/{id}")
        Observable<LightItem> getLight(@Path("id") String id);

        @PUT(PATH_LIGHTS + "/{id}/" + PATH_STATE)
        Observable<List<LightControlResponse>> setLightState(@Path("id") String id, @Body LightControlRequest request);
    }

    private static LightApi mSharedLightService;

    public static LightApi lightService(@NonNull String ip, @NonNull String userId){
        Type listType = new TypeToken<List<LightItem>>(){}.getType();
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(listType, new LightItemGSONDeserializer())
                .create();
        Uri uri = Uri.parse(getBridgeURL(ip)).buildUpon()
                .appendPath(PATH_API)
                .appendPath(userId)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(uri.toString() + "/")
                .addConverterFactory(GsonConverterFactory.create(gson))
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
}
