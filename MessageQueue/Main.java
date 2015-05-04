
/**
 * Created by alexpang on 2015/3/10.
 */
public class Main {
    public static void main(String args[]){
        for (int i=0;i<100;i++)
        {
            new TestThread(i).start();
        }
    }
}
