package personal.nathan.jdkbase.base.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 * 线程工作完成latch.countDown();
 * latch.await();等待全部线程完成。
 * <p>
 * Created by nathan.z on 2018/5/5.
 */
public class CountDownLatchDemo {

    private final static CountDownLatch latch = new CountDownLatch(10);

    public static class CountDown implements Runnable {
        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName());
                latch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {

            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 10; i > 0; i --) {
            Thread t = new Thread(new CountDown(), String.valueOf(i));
            t.start();
            t.join();
        }
        latch.await();
        System.out.println("fire!!!");
    }
}
