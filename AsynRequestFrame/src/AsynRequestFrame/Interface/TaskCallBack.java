package AsynRequestFrame.Interface;


import AsynRequestFrame.Response.Response;

/**
 * Created by alexpang on 2015/4/15.
 */
public interface TaskCallBack {
    public void OnTaskSuccess(Response rsp);
    public void OnTaskFailed(int resultCode,String resultMsg);
}
