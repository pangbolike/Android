package AsynRequestFrame.Task;


import AsynRequestFrame.Http.HttpAgent;
import AsynRequestFrame.Interface.ActivityCallBack;
import AsynRequestFrame.Interface.ServiceCallBack;
import AsynRequestFrame.Interface.TaskCallBack;
import AsynRequestFrame.Request.HttpRequest;
import AsynRequestFrame.Request.Request;
import AsynRequestFrame.Response.Response;
import AsynRequestFrame.Service.TaskQueueService;

/**
 * Created by alexpang on 2015/4/15.
 */
public class Task implements TaskCallBack {
    public ServiceCallBack serviceCallBack = null;
    protected Request request = null;
    public ActivityCallBack listner;
    public Task(Request request,ServiceCallBack callback,ActivityCallBack listener){
        this.request = request;
        serviceCallBack = callback;
        this.listner = listener;
    }
    public void run(){
        if (request == null) return ;
        request.setTaskCallBack(this);
        if (request instanceof HttpRequest)
        {
            HttpAgent.getInstance().sendRequest((HttpRequest)request);
        }
    }
    @Override
    public void OnTaskSuccess(Response rsp){
        TaskQueueService.getInstance().getLooper().completeTask(this,rsp);
    }

    @Override
    public void OnTaskFailed(int resultCode,String resultMsg){
        TaskQueueService.getInstance().getLooper().completeTask(this,new Response(resultCode,resultMsg,null));
    }
}
