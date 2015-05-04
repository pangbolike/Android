package AsynRequestFrame.Service;

/**
 * Created by alexpang on 2015/4/15.
 */
public class TaskQueueService {
    private TaskLooper looper;
    private static TaskQueueService instance;
    public static TaskQueueService getInstance(){
        if (instance == null){
            instance = new TaskQueueService();
        }
        return instance;
    }
    public TaskLooper getLooper(){
        if (looper == null){
            looper = new TaskLooper();
        }
        return looper;
    }
}
