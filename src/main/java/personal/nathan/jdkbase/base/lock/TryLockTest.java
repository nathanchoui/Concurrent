package personal.nathan.jdkbase.base.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description:
 * <p>
 * Created by zhangwei on 2018/5/2.
 */
public class TryLockTest {

    public static ReentrantLock lock = new ReentrantLock();

    public static class TryLock implements Runnable {
        @Override
        public void run() {
            try {
                if (lock.tryLock(3, TimeUnit.SECONDS)) {
                    TimeUnit.SECONDS.sleep(5);
                }
                else {
                    System.out.println("try lock failed");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (lock.isHeldByCurrentThread()) {
                    lock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) {
        TryLock tryLock = new TryLock();
        // 第一次获取锁是成功的，并占用了该锁5秒
        Thread t1 = new Thread(tryLock);
        // 第二次获取锁的时候，由于第一个线程并未释放锁，并且只尝试3秒，所有获取锁失败。
        Thread t2 = new Thread(tryLock);

        t1.start();
        t2.start();
    }
}
