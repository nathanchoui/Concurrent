package personal.nathan.ConcurrentContainer;

/**
 * Description:
 * <p>
 * Created by nathan.z on 2018/5/19.
 */
public class ThreadLocalTest {

    public static void main(String[] args) {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("A");

    }
}
