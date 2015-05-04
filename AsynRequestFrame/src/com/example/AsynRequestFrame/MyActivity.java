package com.example.AsynRequestFrame;

import AsynRequestFrame.Interface.ActivityCallBack;
import AsynRequestFrame.Response.Response;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

public class MyActivity extends Activity implements ActivityCallBack,Handler.Callback{
    /**
     * Called when the activity is first created.
     */
    TextView txt;
    Handler handler;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        handler = new Handler(getMainLooper(),this);
        txt = (TextView)findViewById(R.id.text);
        DemoService.getInstance().GetNowService(this);

    }
    @Override
    public void OnResult(Response rsp){
        Message msg = Message.obtain();
        msg.what = 1;
        msg.obj = rsp.getRspData();
        handler.sendMessage(msg);
    }
    @Override
    public boolean handleMessage(Message msg){
        txt.setText(msg.obj.toString());
        return false;
    }
    @Override
    public void onBackPressed() {
        System.exit(0);
    }
}
