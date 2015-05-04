package com.example.WebView;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.webkit.WebView;
import android.widget.Toast;

import java.io.File;

/**
 * Created by pangbolike on 15/4/12.
 */
public class BaseWebViewActivity extends Activity implements DownloadListener{
    private final static String TAG = "BaseWebViewActivity";
    protected WebView webView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    protected void onPause() {
        super.onPause();
        webView.onPause();
    }
    @Override
    protected void onResume() {
        super.onResume();
        webView.onResume();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        webView.setVisibility(View.GONE);
        getWebViewContent().removeView(webView);
        webView.removeAllViews();
        webView.destroy();
        webView = null;

    }
    protected ViewGroup getWebViewContent() {
        return null;
    }
    protected void createWebView() {
        webView = new SafeWebView(this);
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.setDownloadListener(this);
        webView.setVisibility(View.VISIBLE);

    }
    protected void configWebView(WebView webView) {
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setJavaScriptEnabled(true);

        /*if (Build.VERSION.SDK_INT > Build.VERSION_CODES.GINGERBREAD_MR1 && Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            webView.removeJavascriptInterface("searchBoxJavaBridge_");
        }*/
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setDatabaseEnabled(true);


    }

    @Override
    public void onDownloadStart(String url, String userAgent,
                                String contentDisposition, String mimetype, long contentLength) {
        boolean useDownloadManager = false;
        try {
            Uri uri = Uri.parse(url);

            DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
            DownloadManager.Request request = new DownloadManager.Request(uri);
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            File destDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            if (destDir != null && destDir.exists()) {
                String subPath = uri.getLastPathSegment();
                if (subPath == null || subPath.equals("")) {
                    subPath = "file_" + System.currentTimeMillis();
                }
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, subPath);
                downloadManager.enqueue(request);
                useDownloadManager = true;

            }
            onDownloadFile(url, useDownloadManager);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
    protected void onDownloadFile(String url, boolean useDownloadManager) {
        if (useDownloadManager) {
            toast(this.getResources().getString(R.string.url_have_download));
        }
    }
    public void toast(String msg){
        Toast toast = Toast.makeText(getBaseContext(),
                msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

}
