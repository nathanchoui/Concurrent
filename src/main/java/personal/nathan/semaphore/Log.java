package personal.nathan.semaphore;

/**
 * Description:
 * <p>
 * Created by nathan.z on 2018/1/26.
 */
public class Log {
    public static void println(String s) {
        System.out.println(Thread.currentThread().getName() + ": " + s);
    }
}
