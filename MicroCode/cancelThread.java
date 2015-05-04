package com.example.InitDemo;

/**
 * Created by pangbolike on 2015/2/27.
 */
public class cancelThread implements Runnable{
    private boolean isStop = false;
    public void stop(){
        //Can cancel do something
        isStop = true;
    }
    public boolean isStop(){
        return isStop;
    }
    @Override
    public void run() {
        if (!isStop){
                /*
                    Do something
                */
        }
    }
}