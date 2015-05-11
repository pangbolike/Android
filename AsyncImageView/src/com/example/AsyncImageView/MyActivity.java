package com.example.AsyncImageView;

import android.app.Activity;
import android.os.Bundle;
import image.AsyncImageView;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        AsyncImageView image = (AsyncImageView) findViewById(R.id.image);
        image.setUri("http://imgsrc.baidu.com/forum/w%3D580%3B/sign=a6582d41319b033b2c88fcd225f537d3/7dd98d1001e93901534dbd7b7eec54e736d19623.jpg");
    }
}
