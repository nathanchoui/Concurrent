package personal.nathan.jdkbase.base.LockSupport;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * 可以阻塞线程，不会出现suspend()和resume()永久挂起。
 * park()会查看 一个许可标志，当许可为可用才会挂起，unpark()会将许可置为可用。
 * 不必放在synchronized中，和Object的wait()和notify()的一个区别。
 *
 * Created by zhangwei on 2018/5/7.
 */
public class LockSupportDemo {

    static final Object o = new Object();

    static class Park implements Runnable {
        @Override
        public void run() {
            //LockSupport.park();
            LockSupport.park(o);
            if (Thread.interrupted()) {
                System.out.println(Thread.currentThread().getName() + "被中断了");

            }
            System.out.println(Thread.currentThread().getName() + "执行结束");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Park park = new Park();
        Thread t1 = new Thread(park);
        Thread t2 = new Thread(park);
        t1.start();
        TimeUnit.MILLISECONDS.sleep(100);
        t2.start();
        t1.interrupt();
        LockSupport.unpark(t2);
    }

}
