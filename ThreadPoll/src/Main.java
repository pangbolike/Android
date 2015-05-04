/**
 * Created by alexpang on 2015/4/1.
 */
public class Main {
    public static void main(String args[])
    {

        for (int i = 0 ;i < 2000 ; i++){
            ThreadPool.getInstance().execute(new ThreadTask("task = " + i, 1000 - i));
        }
    }
}
