package com.pangbolike.imageutils;

import android.content.Context;
import android.view.WindowManager;

/**
 * Created by alexpang on 2016/6/22.
 */
public class ScreenUtils {

    private static float DENSITY = 0;

    public static int SCREEN_WIDTH = 320;

    public static int SCREEN_HIGHT = 480;

    static {
        WindowManager wm = (WindowManager) RealApplication.getApp().getSystemService(Context.WINDOW_SERVICE);
        SCREEN_WIDTH = wm.getDefaultDisplay().getWidth();
        SCREEN_HIGHT = wm.getDefaultDisplay().getHeight();
    }

    public static int dp2px(float dp){
        if (0 == DENSITY){
            DENSITY = RealApplication.getApp().getResources().getDisplayMetrics().density;
        }
        return (int)(Math.round(dp * DENSITY));
    }
}
