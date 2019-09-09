package personal.nathan.jdkbase.base.Sync;

import java.util.ArrayList;

/**
 * Description:
 * <p>
 * Created by zhangwei on 2018/4/28.
 */
public class ArrayListMulti {

    private static ArrayList<Integer> list = new ArrayList<>();

    public static class AddThread implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 100000; i++) {
                list.add(i);
            }
        }
    }

    /**
     * 未同步的情况下有一定概率抛出
     * ArrayIndexOutOfBoundsException 异常
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main (String[] args) throws InterruptedException {
        Runnable addThread = new AddThread();
        Thread t1 = new Thread(addThread);
        Thread t2 = new Thread(addThread);

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(list.size());
    }
}
