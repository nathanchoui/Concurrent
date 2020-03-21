package personal.nathan.jdkbase.base.condition;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 如同wait()和notify()只能在synchronized中使用一样，
 * Condition只能在lock中使用
 * <p>
 * Created by zhangwei on 2018/5/2.
 */
public class ConditionTest {

    private static ReentrantLock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();

    public static class Cond implements Runnable {
        @Override
        public void run() {
            try {
                lock.lock();
                condition.await();
                System.out.println("Thread is going");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Cond());
        t.start();
        TimeUnit.SECONDS.sleep(5);
        lock.lock();
        condition.signal();
        lock.unlock();
    }
}
