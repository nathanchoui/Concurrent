package personal.nathan.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * Created by zhangwei on 2018/5/9.
 */
public class ExtThreadPool {

    public static void main(String[] args) {
        ExecutorService es = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>()) {
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                super.beforeExecute(t, r);
                System.out.println(Thread.currentThread().getName() + " start. " + Thread.currentThread().getState());
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                super.afterExecute(r, t);
                System.out.println(Thread.currentThread().getName() + " end. " + Thread.currentThread().getState());
            }

            @Override
            protected void terminated() {
                super.terminated();
                System.out.println(Thread.currentThread().getName() + " over. " + Thread.currentThread().getState());
            }
        };

        for (int i = 0; i < 5; i ++) {
            es.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        es.shutdown();
    }

}
