package personal.nathan.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * Created by zhangwei on 2018/5/8.
 */
public class ThreadPoolDemo {

    public static void main(String[] args) {
        /**
         * 固定线程数的线程池，有空闲线程则使用，如没有则等待。
         */
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
        /**
         *只有一个线程的线程池，任务会保存在一个任务队列，先入先出逐个执行。
         */
        ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
        /**
         *初始化一部分线程放入线程池，不够时再创建。
         */
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        /**
         * 具有延时执行或定时执行功能的线程，线程池大小为1
         */
        ScheduledExecutorService singleScheduledThreadPool = Executors
            .newSingleThreadScheduledExecutor();
        /**
         * 指定线程数量
         */
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);

        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(
                        System.currentTimeMillis() + ": " + Thread.currentThread().getName()
                            + " start executing");
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(
                        System.currentTimeMillis() + ": " + Thread.currentThread().getName()
                            + " completed");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        /*

        // 线程只有5个
        for (int i = 0; i < 10; i++) {
            fixedThreadPool.submit(r);
        }
        fixedThreadPool.shutdown();

        // 创建了10个线程
        for (int i = 0; i < 10; i++) {
            cachedThreadPool.submit(r);
        }
        cachedThreadPool.shutdown();

        // 线程池永远执行一个线程在执行，按顺序执行。
        for (int i = 0; i < 10; i++) {
            singleThreadPool.submit(r);
        }
        singleThreadPool.shutdown();

        */

        // 定时任务
        // 每个5秒执行，立即执行
//        scheduledThreadPool.scheduleAtFixedRate(r, 0, 5, TimeUnit.SECONDS);

        // 每次任务执行完后，间隔五秒再执行
//        scheduledThreadPool.scheduleWithFixedDelay(r, 0, 5, TimeUnit.SECONDS);

        // 延迟执行，不重复
        scheduledThreadPool.schedule(r, 5, TimeUnit.SECONDS);

    }

}
