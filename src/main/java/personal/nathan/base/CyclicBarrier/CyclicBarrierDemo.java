package personal.nathan.base.CyclicBarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 * <p>
 * Created by nathan.z on 2018/5/6.
 */
public class CyclicBarrierDemo {

    /**
     * 一定得有十个线程调用await()方法后，
     * await()方法后的程序才会得到执行
     *
     * 可用于合并计算
     *
     */
    private static final CyclicBarrier cb = new CyclicBarrier(10);

    public static void main(String[] args) {
        for (int i = 0; i < 10; i ++) {
            new Thread(() -> {
                try {
                    // 模拟处理耗时
                    TimeUnit.SECONDS.sleep(new Random().nextInt(3) + 1);
                    cb.await();
                    System.out.println(Thread.currentThread().getName());
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        System.out.println("done!");
    }
}
