package AsynRequestFrame.Request;


import AsynRequestFrame.Interface.RequestCallBack;
import AsynRequestFrame.Interface.TaskCallBack;

/**
 * Created by alexpang on 2015/4/15.
 */
public abstract class Request implements RequestCallBack {
    protected TaskCallBack taskCallBack;

    public void setTaskCallBack(TaskCallBack taskCallBack) {
        this.taskCallBack = taskCallBack;
    }
}
