package AsynRequestFrame.Response;

/**
 * Created by alexpang on 2015/4/15.
 */
public class Response {

    protected int resultCode;

    protected String resultMsg;

    protected Object RspData;

    public class ENUM_RESULT_CODE{
        public final static int SUCCESS = 1;
        public final static int FAIL = 2;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public void setRspData(Object rspData) {
        RspData = rspData;
    }

    public Object getRspData() {
        return RspData;
    }

    public Response(int resultCode,String resultMsg,Object RspData){
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
        this.RspData = RspData;
    }
}
