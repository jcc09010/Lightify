package com.sideprojects.jc.lightify.apis.philips.hue.messeging;

import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;

import com.google.gson.annotations.SerializedName;
import com.sideprojects.jc.lightify.apis.philips.hue.data.Constants;
import com.sideprojects.jc.lightify.apis.philips.hue.data.LightState;

/**
 * Created by {@author justin.chu} on 2/6/17.
 */
public class LightControlRequest {
    @SerializedName(LightState.KEY_ON)
    public Boolean on;

    @SerializedName(LightState.KEY_BRIGHTNESS)
    public Integer brightness;

    @SerializedName(LightState.KEY_HUE)
    public Integer hue;

    @SerializedName(LightState.KEY_SATURATION)
    public Integer saturation;

    @SerializedName(LightState.KEY_XY)
    public Float[] xy;

    @SerializedName(LightState.KEY_ALERT)
    public String alert;

    @SerializedName(LightState.KEY_EFFECT)
    public String effect;

    @SerializedName(LightState.KEY_COLOR_TEMP)
    public Integer colorTemp;

    @SerializedName(LightState.KEY_TRANSITION_TIME)
    public Integer transitionTime;

    public static class Builder {

        private LightControlRequest request;

        public static Builder start(){
            return new Builder();
        }

        public Builder(){
            request = new LightControlRequest();
        }

        public Builder setOn(boolean on){
            request.on = on;
            return this;
        }

        public Builder setBrightness(@IntRange(from=Constants.Brightness.MIN,to=Constants.Brightness.MAX)
                                             int brightness){
            request.brightness = brightness;
            return this;
        }

        public Builder setHue(@IntRange(from=Constants.Hue.MIN,to=Constants.Hue.MAX)
                                      int hue){
            request.hue = hue;
            return this;
        }

        public Builder setSaturation(@IntRange(from=Constants.Saturation.MIN,to=Constants.Saturation.MAX)
                                             int saturation){
            request.saturation = saturation;
            return this;
        }

        public Builder setXY(@FloatRange(from=Constants.Color.X_MIN,
                                        to=Constants.Color.X_MAX) float x,
                             @FloatRange(from=Constants.Color.Y_MIN,
                                        to=Constants.Color.Y_MAX) float y){
            request.xy = new Float[]{x, y};
            return this;
        }

        public Builder setAlert(String alert){
            request.alert = alert;
            return this;
        }

        public Builder setEffect(String effect){
            request.effect = effect;
            return this;
        }

        public Builder setColorTemperature(@IntRange(from=Constants.Color.TEMP_MIN,to=Constants.Color.TEMP_MAX)
                                                   int temp){
            request.colorTemp = temp;
            return this;
        }

        public Builder setTransitionTime(int deciseconds){
            request.transitionTime = deciseconds;
            return this;
        }

        public LightControlRequest build(){
            return request;
        }
    }
}
