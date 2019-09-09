package personal.nathan.jdkbase.semaphore;

/**
 * Description:
 * <p>
 * Created by nathan.z on 2018/1/26.
 */
public class Main {

    public static void main(String[] args) {
        // 设置3个资源
        BoundedResource resource = new BoundedResource(3);

        // 10个线程使用资源
        for (int i = 0; i < 10; i++) {
            new UserThread(resource).start();
        }
    }
}
