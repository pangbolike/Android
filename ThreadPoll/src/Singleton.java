/**
 * Created by alexpang on 2015/4/23.
 */
public abstract class Singleton<T> {
    private T instance;
    protected abstract T create();

    public final T get()
    {
        synchronized (this)
        {
            if (instance == null)
            {
                instance = create();
            }
            return instance;
        }
    }
}
