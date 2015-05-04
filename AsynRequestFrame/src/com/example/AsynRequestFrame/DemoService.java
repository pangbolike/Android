package com.example.AsynRequestFrame;

import AsynRequestFrame.Interface.ActivityCallBack;
import AsynRequestFrame.Interface.ServiceCallBack;
import AsynRequestFrame.Request.HttpRequest;
import AsynRequestFrame.Request.RequestConst;
import AsynRequestFrame.Response.Response;
import AsynRequestFrame.Service.TaskQueueService;
import AsynRequestFrame.Task.Task;


/**
 * Created by alexpang on 2015/4/16.
 */
public class DemoService implements ServiceCallBack {
    private static DemoService instance;
    public static DemoService getInstance(){
        if (instance == null){
            instance = new DemoService();
        }
        return instance;
    }
    public void GetNowService(ActivityCallBack callBack){
        HttpRequest request = new HttpRequest("http://www.linfeng.im/cgi-bin/GetNow.php", RequestConst.REQUEST_METHOD_GET);
        Task task = new Task(request,this,callBack);
        TaskQueueService.getInstance().getLooper().runTask(task);
    }
    @Override
    public void OnServiceResult(Task task,Response rsp){
        task.listner.OnResult(rsp);
    }
}
