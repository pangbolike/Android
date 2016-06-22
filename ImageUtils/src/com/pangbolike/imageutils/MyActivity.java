package com.pangbolike.imageutils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.widget.ImageView;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    Handler handler;
    HandlerThread handlerThread;
    Handler uiHandler = new Handler();
    private ImageView image;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        handlerThread = new HandlerThread("111");
        handlerThread.start();
        handler = new Handler(handlerThread.getLooper());

        image = (ImageView) findViewById(R.id.img);
        handler.post(new Runnable() {
            @Override
            public void run() {
                Bitmap bmp = BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher);
                final Bitmap bmp1 = ImageUtils.getCenterCircleBitMap(bmp,null);
                uiHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        image.setImageBitmap(bmp1);
                    }
                });
            }
        });
    }
}
