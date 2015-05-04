package AsynRequestFrame.Service;

import AsynRequestFrame.Response.Response;
import AsynRequestFrame.Task.Task;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Pair;

/**
 * Created by alexpang on 2015/4/15.
 */
public class TaskLooper implements Handler.Callback{

    private Handler handler;

    private static HandlerThread handlerThread;

    private static final int RUN_TASK_MSG = 0;

    private static final int COMPLETE_TASK_MSG = 1;

    public TaskLooper(){
        handlerThread = HandlerThreadFactory.getHandlerThread(HandlerThreadFactory.TaskThread);
    }
    private Looper getLooper(){
        return handlerThread.getLooper();
    }

    public void runTask(Task task){
        if (handler == null){
            handler = new Handler(getLooper(),this);
        }
        Message msg = Message.obtain();
        msg.what = RUN_TASK_MSG;
        msg.obj = task;
        handler.sendMessage(msg);
    }
    public void completeTask(Task task,Response rsp){
        if (handler == null){
            handler = new Handler(getLooper(),this);
        }
        Message msg = Message.obtain();
        msg.what = COMPLETE_TASK_MSG;
        msg.obj = new Pair<Task,Response>(task,rsp);
        handler.sendMessage(msg);
    }
    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what){
            case RUN_TASK_MSG : {
                Task task = (Task) msg.obj;
                task.run();
            }
                break;
            case COMPLETE_TASK_MSG : {
                Pair<Task, Response> pair = (Pair<Task, Response>) msg.obj;
                Task task = pair.first;
                Response rsp = pair.second;
                if (task.serviceCallBack != null){
                    task.serviceCallBack.OnServiceResult(task,rsp);
                }
            }

                break;
            default:
                break;
        }
        return false;
    }

}
