package com.pangbolike.imageutils;

import android.app.Application;

/**
 * Created by alexpang on 2016/6/22.
 */
public class RealApplication extends Application{

    private static Application app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }

    public static Application getApp(){
        return app;
    }
}
