package personal.nathan.jdkbase.base.WaitAndNotify;

import java.util.concurrent.TimeUnit;

/**
 * wait()、notify()、notifyAll() 一定得在synchronized块中使用
 *
 * <p>
 * Created by zhangwei on 2018/4/25.
 */
public class SimpleWN {

    private final static Object obj = new Object();

    public static void main(String[] args) {

        Thread waitThread = new Thread(() -> {
            synchronized (obj) {
                System.out.println(System.currentTimeMillis()
                        + ": " + Thread.currentThread().getName() + " started!");
                try {
                    System.out.println(System.currentTimeMillis()
                            + ": " + Thread.currentThread().getName() + " wait!");
                    // 等待会释放对Obj的锁，停止执行，直到有其它线程调用notify()和notifyAll()
                    obj.wait();
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis()
                        + ": " + Thread.currentThread().getName() + " end!");
            }
        });

        Thread notifyThread = new Thread(() -> {
            synchronized (obj) {
                System.out.println(System.currentTimeMillis()
                        + ": " + Thread.currentThread().getName() + " started! and notify some threads");
                // 调用了notify()后，此线程还是会争抢obj资源，wait()的线程不一定会马上得到执行。
                obj.notify();
                try {
                    TimeUnit.SECONDS.sleep(3);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis()
                        + ": " + Thread.currentThread().getName() + " end!");
            }
        });

        waitThread.start();
        notifyThread.start();
    }
}
