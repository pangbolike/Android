package AsynRequestFrame.Interface;


import AsynRequestFrame.Response.Response;
import AsynRequestFrame.Task.Task;

/**
 * Created by alexpang on 2015/4/15.
 */
public interface ServiceCallBack {
    public void OnServiceResult(Task task,Response rsp);

}
