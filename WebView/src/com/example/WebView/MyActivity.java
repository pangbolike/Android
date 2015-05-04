package com.example.WebView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    private final static String TAG = "MyActivity";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Intent intent = new Intent();
        intent.setClass(this,WebViewActivity.class);
        WebViewActivity.setIntent(intent, "http://www.qq.com/", "webview");
        startActivityForResult(intent,-1);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.v(TAG, "resultCode = " + requestCode);
    }
}
