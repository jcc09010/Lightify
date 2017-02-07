package com.sideprojects.jc.lightify.apis.philips.hue.messeging;

import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;

import com.google.gson.annotations.SerializedName;
import com.sideprojects.jc.lightify.apis.philips.hue.data.LightItem;

/**
 * Created by {@author justin.chu} on 2/6/17.
 */
public class LightControlRequest {
    @SerializedName(LightItem.State.KEY_ON)
    public Boolean on;

    @SerializedName(LightItem.State.KEY_BRIGHTNESS)
    public Integer brightness;

    @SerializedName(LightItem.State.KEY_HUE)
    public Integer hue;

    @SerializedName(LightItem.State.KEY_SATURATION)
    public Integer saturation;

    @SerializedName(LightItem.State.KEY_XY)
    public Float[] xy;

    @SerializedName(LightItem.State.KEY_ALERT)
    public String alert;

    @SerializedName(LightItem.State.KEY_EFFECT)
    public String effect;

    @SerializedName(LightItem.State.KEY_COLOR_TEMP)
    public Integer colorTemp;

    @SerializedName(LightItem.State.KEY_TRANSITION_TIME)
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

        public Builder setBrightness(@IntRange(from=-254,to=254) int brightness){
            request.brightness = brightness;
            return this;
        }

        public Builder setHue(@IntRange(from=-65534,to=65534) int hue){
            request.hue = hue;
            return this;
        }

        public Builder setSaturation(@IntRange(from=-254,to=254) int saturation){
            request.saturation = saturation;
            return this;
        }

        public Builder setXY(@FloatRange(from=0.0f,to=1.0f) float x,
                             @FloatRange(from=0.0f,to=1.0f) float y){
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

        public Builder setColorTemperature(@IntRange(from=-65534,to=65534) int temp){
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
