package personal.nathan.DeadLock;

import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * Created by zhangwei on 2018/5/21.
 */
public class DiningPhilosopherProblem {

    private static Object knife = new Object();

    private static Object fork = new Object();

    static class Dining implements Runnable {

        private Object tool = null;

        public Dining(Object tool) {
            this.tool = tool;
        }

        @Override
        public void run() {
            if (tool == knife) {
                synchronized (knife) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(500);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (fork) {
                        System.out.println(Thread.currentThread().getName() + " start eating!");
                    }
                }
            }
            else {
                synchronized (fork) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(500);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (knife) {
                        System.out.println(Thread.currentThread().getName() + " start eating!");
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Runnable runnable = new Dining(knife);
        //Thread t1 = new Thread()
        // 180
    }

}
