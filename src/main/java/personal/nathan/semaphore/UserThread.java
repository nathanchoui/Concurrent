package personal.nathan.semaphore;

import java.util.Random;

/**
 * Description:
 * <p>
 * Created by nathan.z on 2018/1/26.
 */
public class UserThread extends Thread {
    private final static Random random = new Random(26535);
    private final BoundedResource resource;

    public UserThread(BoundedResource resource) {
        this.resource = resource;
    }

    public void run() {
        try {
            while (true) {
                resource.use();
                Thread.sleep(random.nextInt(3000));
            }
        } catch (InterruptedException e) {
        }
    }
}
