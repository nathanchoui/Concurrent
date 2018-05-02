package personal.nathan.base.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description:
 * <p>
 * Created by zhangwei on 2018/5/2.
 */
public class LockTest {


    private static Lock lock = new ReentrantLock();

    private static Integer count = 0;

    public static class AddThread implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 100000; i++) {
                try {
                    lock.lock();
                    // 可重复加锁，但解锁次数必须和加锁次数相同。
                    //lock.lock();
                    count++;
                }
                finally {
                    lock.unlock();
                    //多次解锁抛出IllegalMonitorStateException
                    //lock.unlock();
                }
            }
        }
    }

    /**
     * 未同步的情况下有一定概率抛出
     * ArrayIndexOutOfBoundsException 异常
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Runnable addThread = new LockTest.AddThread();
        Thread t1 = new Thread(addThread);
        Thread t2 = new Thread(addThread);

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(count);
    }
}
