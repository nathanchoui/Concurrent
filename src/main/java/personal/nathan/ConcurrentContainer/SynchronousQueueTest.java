package personal.nathan.ConcurrentContainer;

import java.util.concurrent.SynchronousQueue;

/**
 * Description:
 *
 * Created by zhangwei on 2018/5/21.
 */
public class SynchronousQueueTest {


    public static void main(String[] args) throws InterruptedException {
        SynchronousQueue<Integer> queue = new SynchronousQueue<>();

        Thread t1 = new Thread(() -> {

            try {
                for (int i = 0; i < 10; i ++) {
                    System.out.println(Thread.currentThread().getName() + " put:" + i);
                    queue.put(i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "put thread");

        Thread t2 = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i ++) {
                    System.out.println(Thread.currentThread().getName() + " taked: " + queue.take());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "take thread");

        /**
         * 这种情况下，t1由于没有线程去take,会一直等待，t2始终没有获得执行take的机会
         *
         * */
//        t1.start();
//        t1.join();
//        t2.start();
        /* ------ */

        t1.start();
        t2.start();

    }
}
