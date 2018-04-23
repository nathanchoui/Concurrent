package personal.nathan.base;

import java.util.concurrent.TimeUnit;

/**
 * Description:
 * <p>
 * Created by zhangwei on 2018/4/23.
 */
public class InterruptStudy {

    public static void main(String[] args) {
        new Thread(() -> {
            int count = 0;
            try {

                while (!Thread.currentThread().isInterrupted()
                        && count < 1000) {
                    count ++;
                    // 在500的时候中断线程
                    // 如果一个线程被阻塞（调用sleep或者wait）上调用interrupt方法时
                    // 会抛出InterruptionException异常中断。
                    if (count == 500) {
                        // 中断线程，将中断状态置为true
                        Thread.currentThread().interrupt();
                        // 将线程的中断状态置为false, 导致中断失败
                        System.out.println(Thread.interrupted());
                        TimeUnit.MILLISECONDS.sleep(10);
                    }
                }
                System.out.println(count);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();
    }
}
