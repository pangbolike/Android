package AsynRequestFrame.Http;

import AsynRequestFrame.Utils.Singleton;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/**
 * Created by alexpang on 2015/4/16.
 */
public class ThreadPool {
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int corePoolSize =  CPU_COUNT + 1;
    private static final int maximumPoolSize = CPU_COUNT * 2 + 1;
    private static final long keepAliveTime = 1;
    private static ThreadPoolExecutor threadPoolExecutor = null;
    private static final Singleton<ThreadPool> singleton = new Singleton<ThreadPool>() {
        @Override
        protected ThreadPool create() {
            return new ThreadPool();
        }
    };
    public static ThreadPool getInstance()
    {
        return singleton.get();
    }

    public ThreadPool()
    {
        threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime,
                TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(),new ThreadPoolExecutor.DiscardPolicy());
    }
    public void execute(Runnable task)
    {
        if (null != threadPoolExecutor)
        {
            threadPoolExecutor.execute(task);
        }
    }
}
