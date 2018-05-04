package personal.nathan.base;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 信号量能控制同时访问资源的线程数
 *
 * <p>
 * Created by zhangwei on 2018/5/4.
 */
public class SemaphoreTest {

    final static Semaphore sema = new Semaphore(5,true);

    public static void main(String[] args) {
        Runnable r = () -> {
            try {
                sema.acquire();
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName() + " done");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                sema.release();
            }
        };
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 20; i ++) {
            executorService.submit(r);
        }
        executorService.shutdown();
    }
}
