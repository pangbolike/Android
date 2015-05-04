/**
 * Created by alexpang on 2015/4/1.
 */
public  class ThreadTask implements Runnable{
    String data = null;
    int time;
    @Override
    public void run(){
        System.out.println(data);

    }
    public ThreadTask(String name,int t){
        data = name;
        time = t;
    }
}
