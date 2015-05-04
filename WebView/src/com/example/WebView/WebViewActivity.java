package com.example.WebView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by pangbolike on 15/4/12.
 */
public class WebViewActivity extends BaseWebViewActivity{
    private final static String TAG = "WebViewActivity";
    public final static String INTENT_URL = "IntentUrl";
    public final static String INTENT_TITLE = "IntentTitle";
    protected String url;
    private ViewGroup webContentView;
    private TextView backBtn;
    private TextView forwardBtn;
    private int colorNotCanClick = 0xffE3E3E3;
    private int colorCanClick = 0xff63B8FF;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view);
        initTitleBar();
        initView();
        url = getIntent().getStringExtra(INTENT_URL);
        if (url == null) {
            return;
        }
        configWebView(webView);
        webView.loadUrl(url);
    }

    protected void initTitleBar() {
        setTitle(getIntent().getStringExtra(INTENT_TITLE));
    }
    private void initView() {
        createWebView();
        webContentView = ((ViewGroup) findViewById(R.id.web_content));
        webContentView.addView(webView);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (WebViewActivity.this.isFinishing() || webView == null) {
                    Log.w(TAG, "webview do onPageFinished while activity is destory!");
                    return;
                }
                updateBtn();
            }
        });

        webView.setWebChromeClient(new WebChromeClient());
        forwardBtn = (TextView) findViewById(R.id.forWard);
        backBtn = (TextView) findViewById(R.id.backWard);

        updateBtn();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            webView.removeJavascriptInterface("searchBoxJavaBridge_");
        }
    }

    protected ViewGroup getWebViewContent() {
        return webContentView;
    }
    public final static void setIntent(Intent intent, String viewUrl, String title) {
        intent.putExtra(INTENT_URL, viewUrl);
        intent.putExtra(INTENT_TITLE, title);
    }
    protected boolean onBackBtnClick() {
        setResult(RESULT_OK);
        finish();
        return true;
    }
    private void updateBtn()
    {
        if (webView.canGoBack()) {
            backBtn.setTextColor(colorCanClick);
            backBtn.setClickable(true);
        } else {
            backBtn.setTextColor(colorNotCanClick);
            backBtn.setClickable(false);
        }
        if (webView.canGoForward()) {
            forwardBtn.setTextColor(colorCanClick);
            forwardBtn.setClickable(true);
        } else {
            forwardBtn.setTextColor(colorNotCanClick);
            forwardBtn.setClickable(false);
        }
    }
    public void onClickBackWard(View v) {
        webView.goBack();
        updateBtn();
    }
    public void onClickForWard(View v){
        webView.goForward();
        updateBtn();
    }
    public void onClickRefresh(View v){
        webView.reload();
    }

    @Override
    public void onBackPressed() {
        onBackBtnClick();
        super.onBackPressed();
    }

}
