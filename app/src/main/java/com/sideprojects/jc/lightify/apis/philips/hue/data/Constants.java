package com.sideprojects.jc.lightify.apis.philips.hue.data;

/**
 * Created by {@author justin.chu} on 2/6/17.
 */

public final class Constants {

    public static final class Brightness{
        public static final int MIN = -254;
        public static final int MAX = 254;
    }

    public static final class Hue{
        public static final int MIN = -65534;
        public static final int MAX = 65534;
    }

    public static final class Saturation{
        public static final int MIN = -254;
        public static final int MAX = 254;
    }

    public static final class Color{
        public static final float X_MIN = 0f;
        public static final float X_MAX = 1f;
        public static final float Y_MIN = 0f;
        public static final float Y_MAX = 1f;
        public static final int TEMP_MIN = -65534;
        public static final int TEMP_MAX = 65534;
    }
}
