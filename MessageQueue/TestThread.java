import sun.awt.windows.ThemeReader;

/**
 * Created by alexpang on 2015/3/10.
 */
public class TestThread extends Thread{
    private  int time = 0;
    public TestThread(int t)
    {
        time = t;
    }
    public void run()
    {
        for (int i = 0 ;i < 100 ;i++)
        {
            ThreadPoolManager.newInstance().addLogMsg("i = " + i + "worker" + time);
            try{
                Thread.sleep(1);
            }catch ( Exception e){
                e.printStackTrace();
            }
        }
    }
}
