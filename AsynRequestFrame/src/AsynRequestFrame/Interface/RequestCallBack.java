package AsynRequestFrame.Interface;

/**
 * Created by alexpang on 2015/4/15.
 */
public interface RequestCallBack {
    public void OnRequestSuccess(int resultCode,String resultMsg,Object resultData);
    public void OnRequestFailed(int resultCode,String resultMsg);
}
