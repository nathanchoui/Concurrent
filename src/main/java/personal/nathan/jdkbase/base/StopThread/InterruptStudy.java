package personal.nathan.jdkbase.base.StopThread;

import java.util.concurrent.TimeUnit;

/**
 * Description:
 * <p>
 * Created by zhangwei on 2018/4/23.
 */
public class InterruptStudy {

    public static void main(String[] args) throws InterruptedException {
        // 测试1,因为t1没有任何中断处理逻辑，所以无法被中断。
        Thread t1 = new Thread(() -> {
            while (true) {
                Thread.yield();
            }
        });


//        t1.start();
//        TimeUnit.SECONDS.sleep(2);
//        t1.interrupt();
        // 测试2，增加中断判断后，就能正常中断线程了。
        Thread t2 = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.yield();
            }
            System.out.println("Interrupted Successful!");
        });
//        t2.start();
//        TimeUnit.SECONDS.sleep(2);
//        t2.interrupt();


        //
        Thread t3 = new Thread(() -> {
            int count = 0;
            while (!Thread.currentThread().isInterrupted()
                    && count < 1000) {
                count++;
                // 在500的时候中断线程
                // 如果一个线程被阻塞（调用sleep或者wait）上调用interrupt方法时
                // 会抛出InterruptionException异常中断。
                if (count == 500) {
                    // 中断线程，将中断状态置为true
                    Thread.currentThread().interrupt();
                    // 将线程的中断状态置为false, 导致中断失败
                    //System.out.println(Thread.interrupted());
                    try {
                        TimeUnit.MILLISECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        // InterruptedException 会清除中断标志位。
                        // 异常抛出后，无法进行下次循环。再次中断可以在下次正常结束。
                        // 也可以在catch处理中断失败的情况，以保证数据完整性或其它业务逻辑。
                        Thread.currentThread().interrupt();
                    }
                }
            }
            System.out.println(count);
        });

        t3.start();
//        t3.interrupt();
    }
}
