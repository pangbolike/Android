package AsynRequestFrame.Service;

import android.os.HandlerThread;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by alexpang on 2015/4/15.
 */
public class HandlerThreadFactory {
    public final static String TaskThread = "TaskThread";

    private static Map<String, HandlerThread> handlerMap = new HashMap<String, HandlerThread>();

    public static HandlerThread getHandlerThread(String type){
        HandlerThread handlerThread = handlerMap.get(type);
        if (handlerThread == null){
            handlerThread = new HandlerThread(type,getPriority(type));
            handlerThread.start();
            handlerMap.put(type,handlerThread);
        }
        else{
            if (!handlerThread.isAlive())
                handlerThread.start();
        }
        return handlerThread;
    }
    private static int getPriority(String type){
        if (type.equals(TaskThread))
            return android.os.Process.THREAD_PRIORITY_DEFAULT;
        return android.os.Process.THREAD_PRIORITY_DEFAULT;
    }
}
