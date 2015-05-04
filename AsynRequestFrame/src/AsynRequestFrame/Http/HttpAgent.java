package AsynRequestFrame.Http;


import AsynRequestFrame.Request.HttpRequest;

/**
 * Created by alexpang on 2015/4/15.
 */
public class HttpAgent {
    private static HttpAgent instance;
    public static HttpAgent getInstance(){
        if (instance == null){
            instance = new HttpAgent();
        }
        return instance;
    }
    public boolean sendRequest(HttpRequest request){
        if (request instanceof HttpRequest){
            //execute
            ThreadPool.getInstance().execute(new HttpTask(request));
        }
        return false;
    }
}
