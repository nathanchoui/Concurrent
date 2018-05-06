package personal.nathan.base.lock;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读-读 不互斥，不上锁
 *
 * 读-写、写-写 互斥
 * <p>
 * Created by zhangwei on 2018/5/4.
 */
public class ReadWriteLockTest {

    // 可重入锁
    private static ReentrantLock lock = new ReentrantLock();
    // 读写锁
    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private static Lock readLock = readWriteLock.readLock();

    private static Lock writeLock = readWriteLock.writeLock();

    private int value;

    public Object read(Lock lock) throws InterruptedException {
        try {
            lock.lock();
            TimeUnit.SECONDS.sleep(1);
            return value;
        }
        finally {
            lock.unlock();
        }
    }

    public void write(Lock lock, int value) throws  InterruptedException {
        try {
            lock.lock();
            TimeUnit.SECONDS.sleep(1);
            this.value = value;
        }
        finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        final ReadWriteLockTest test = new ReadWriteLockTest();

        Runnable writeR = new Runnable() {
            @Override
            public void run() {
                try {
                    test.write(writeLock, new Random().nextInt(100));
                    //test.write(lock, new Random().nextInt(100));
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable readR = new Runnable() {
            @Override
            public void run() {
                try {
                    test.read(readLock);
                    //test.read(lock);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 10; i ++) {
            new Thread(writeR).start();
        }

        for (int i = 0; i < 10; i ++) {
            new Thread(readR).start();
        }

        System.out.println("consumed: " + (System.currentTimeMillis() - startTime) + "ms");

    }

}
