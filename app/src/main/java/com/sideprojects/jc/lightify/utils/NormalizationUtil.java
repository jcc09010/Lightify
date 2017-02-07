package com.sideprojects.jc.lightify.utils;

import com.sideprojects.jc.lightify.apis.philips.hue.data.Constants;

/**
 * Created by {@author justin.chu} on 2/6/17.
 */

public class NormalizationUtil {

    private static float normalize(float originalMin, float originalMax,
                                  float newMin, float newMax,
                                  float input){
        float delta = input - originalMin;
        float range = originalMax - originalMin;
        float newRange = newMax - newMin;
        return newMin + (delta / range * newRange);
    }

    private static int normalize(int originalMin, int originalMax,
                                       int newMin, int newMax,
                                       int input){
        return Math.round(normalize(
                (float) originalMin, (float) originalMax,
                (float) newMin, (float) newMax,
                (float)input));
    }

    /**
     * Brightness
     */

    public static int denormalizeBrightness(int input){
        return normalize(0, 100, Constants.Brightness.MIN, Constants.Brightness.MAX, input);
    }

    public static int normalizeBrightness(int input){
        return normalize(Constants.Brightness.MIN, Constants.Brightness.MAX, 0, 100, input);
    }

    /**
     * Hue
     */

    public static int denormalizeHue(int input){
        return normalize(0, 100, Constants.Hue.MIN, Constants.Hue.MAX, input);
    }

    public static int normalizeHue(int input){
        return normalize(Constants.Hue.MIN, Constants.Hue.MAX, 0, 100, input);
    }

    /**
     * Saturation
     */

    public static int denormalizeSaturation(int input){
        return normalize(0, 100, Constants.Saturation.MIN, Constants.Saturation.MAX, input);
    }

    public static int normalizeSaturation(int input){
        return normalize(Constants.Saturation.MIN, Constants.Saturation.MAX, 0, 100, input);
    }

    /**
     * Color
     */

    public static int denormalizeColorTemp(int input){
        return normalize(0, 100, Constants.Color.TEMP_MIN, Constants.Color.TEMP_MAX, input);
    }

    public static int normalizeColorTemp(int input){
        return normalize(Constants.Color.TEMP_MIN, Constants.Color.TEMP_MAX, 0, 100, input);
    }
}
