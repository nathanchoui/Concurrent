package personal.nathan.base.lock;

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
    private static ReentrantLock reentrantLock = new ReentrantLock();
    // 读写锁
    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();


}
