package com.pangbolike.imageutils;

import android.content.Context;
import android.view.WindowManager;

/**
 * Created by alexpang on 2016/6/22.
 */
public class ScreenUtils {

    private static float DENSITY = 0;

    private static float SCALED_DENSITY = 0;

    private static int SCREEN_WIDTH = 320;

    private static int SCREEN_HEIGHT = 480;

    static {
        WindowManager wm = (WindowManager) RealApplication.getApp().getSystemService(Context.WINDOW_SERVICE);
        SCREEN_WIDTH = wm.getDefaultDisplay().getWidth();
        SCREEN_HEIGHT = wm.getDefaultDisplay().getHeight();
    }

    public static int getScreenWidth(){
        return SCREEN_WIDTH;
    }

    public static int getScreenHeight(){
        return SCREEN_HEIGHT;
    }

    public static int dp2px(float dp){
        return Math.round(dp * getDensity());
    }

    public static int px2dp(float px){
        return Math.round(px / getDensity());
    }

    public static float getDensity(){
        if (0 == DENSITY){
            DENSITY = RealApplication.getApp().getResources().getDisplayMetrics().density;
        }
        return DENSITY;
    }

    public static float getScaledDensity(){
        if (0 == SCALED_DENSITY){
            SCALED_DENSITY = RealApplication.getApp().getResources().getDisplayMetrics().scaledDensity;
        }
        return SCALED_DENSITY;
    }

    public static int sp2px(float sp) {
        return Math.round(sp * getScaledDensity());
    }

    public static int px2sp(float px) {
        return Math.round(px / getScaledDensity());
    }
}
