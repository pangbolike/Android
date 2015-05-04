package AsynRequestFrame.Request;



import AsynRequestFrame.Response.Response;

import java.util.HashMap;

/**
 * Created by alexpang on 2015/4/15.
 */
public class HttpRequest extends Request{
    protected String requestUrl;
    protected HashMap<String,String> params = new HashMap<String, String>();
    protected int requestMethod;
    public HttpRequest(String reqUrl, int method){
        requestUrl = reqUrl;
        requestMethod = method;
    }
    public void addParam(String key,String value){
        if (params != null){
            params.put(key,value);
        }
    }
    public String getParam(String key){
        if (params != null){
            return params.get(key);
        }
        return null;
    }
    public String getRequestUrl() {
        return requestUrl;
    }

    public int getRequestMethod() {
        return requestMethod;
    }

    public HashMap<String, String> getParams() {
        return params;
    }

    public void setRequestMethod(int requestMethod) {
        this.requestMethod = requestMethod;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public void setParams(HashMap<String, String> params) {
        this.params = params;
    }
    @Override
    public void OnRequestSuccess(int resultCode,String resultMsg,Object resultData){
        if (taskCallBack == null) return ;
        Response rsp  = new Response(resultCode,resultMsg,resultData);
        taskCallBack.OnTaskSuccess(rsp);
    }
    @Override
    public void OnRequestFailed(int resultCode,String resultMsg){
        if (taskCallBack == null) return ;
        taskCallBack.OnTaskFailed(resultCode,resultMsg);
    }
}
