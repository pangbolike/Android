package com.example.WebView;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

/**
 * Created by pangbolike on 15/4/12.
 */
public class SafeWebView extends WebView{
    public SafeWebView(Context context) {
        super(context);
        init();
    }

    public SafeWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SafeWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }
    private void init()
    {

    }

}
