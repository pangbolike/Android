package AsynRequestFrame.Http;


import AsynRequestFrame.Request.HttpRequest;
import AsynRequestFrame.Request.RequestConst;
import AsynRequestFrame.Response.Response;

/**
 * Created by asus on 2015/4/16.
 */
public class HttpTask extends ThreadTask{
    HttpRequest request;
    public HttpTask(HttpRequest request){
        this.request = request;
    }
    @Override
    public void run(){
        if (request.getRequestMethod() == RequestConst.REQUEST_METHOD_GET)
        {
            String ans = HttpUtils.sendGet(request.getRequestUrl(),HttpUtils.getParamsStr(request.getParams()));
            request.OnRequestSuccess(Response.ENUM_RESULT_CODE.SUCCESS,"success",ans);
        }
    }
}
