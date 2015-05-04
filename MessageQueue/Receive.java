/**
 * Created by alexpang on 2015/3/10.
 */
public class Receive extends Thread{
    public void run()
    {
        while(true)
        {
            Object msg = null;
            while(true) {
                try {

                        msg = MsgQueue.getInstance().recv();
                        System.out.println("msg = " + msg);


                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
            }
        }

    }
}


