import java.util.*;
/**
 * Created by alexpang on 2015/3/10.
 */
public class MsgQueue {
    private Vector queue = null;
    private  static MsgQueue instance = null;
    public MsgQueue(){
        queue = new   Vector();
    }
    public synchronized void send(Object o)
    {
        queue.addElement(o);
    }
    public synchronized Object recv()
    {
        if(queue.size()==0)
            return null;
        Object o = queue.firstElement();
        queue.removeElementAt(0);//or queue[0] = null can also work
        return o;
    }
    public static MsgQueue getInstance()
    {
        if (instance == null )
        {
            instance = new MsgQueue();
        }
        return instance;
    }
}
