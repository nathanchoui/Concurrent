package personal.nathan.jdkbase.base.CyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description:
 * <p>
 * Created by nathan.z on 2018/5/6.
 */
public class CyclicBarrierCalc {

    static CyclicBarrier cb = new CyclicBarrier(10, new Thread(new MainCalc()));

    static final int[] nums = new int[1000];

    static final int[] res = new int[10];

    static final int length = 100;

    static class MainCalc implements Runnable {
        @Override
        public void run() {
            int sum = 0;
            for (int i: res) {
                sum += i;
            }
            System.out.println("Multi Thread sum res: " + sum);
        }
    }

    static class Calc implements Runnable {

        private int index;


        public Calc(int index) {
            this.index = index;
        }

        @Override
        public void run() {
            int sum = 0;
            for (int i = index * length; i < (index + 1) * length; i ++) {
                sum += nums[i];
            }
            res[index] = sum;
            // 计算完成等待
            try {
                cb.await();
            }
            catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        int expectedSum = 0;
        for (int i = 0; i < 1000; i ++) {
            nums[i] = 100;
            expectedSum += nums[i];
        }
        System.out.println("ExpectedSum: " + expectedSum);

        ExecutorService es = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i ++) {
            es.submit(new Calc(i));
        }
        es.shutdown();

    }
}
