package personal.nathan.DeadLock;

import java.util.concurrent.TimeUnit;

/**
 * 哲学家进餐问题，两个哲学家互相等待对方放下餐具来进餐，导致死锁，程序无法继续往下走。
 *
 * Created by zhangwei on 2018/5/21.
 */
public class DiningPhilosopherProblem {

    private static Object knife = new Object();

    private static Object fork = new Object();

    static class Dining extends Thread {

        private Object tool = null;

        public Dining(Object tool) {
            this.tool = tool;
        }

        @Override
        public void run() {
            if (tool == knife) {
                this.setName("Philosopher A");
                synchronized (knife) {
                    System.out.println(Thread.currentThread().getName() + " got the knife!");
                    try {
                        TimeUnit.MILLISECONDS.sleep(500);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " is waiting to use the fork!");
                    synchronized (fork) {
                        System.out.println(Thread.currentThread().getName() + " start eating!");
                    }
                }
            }
            else {
                this.setName("Philosopher B");
                synchronized (fork) {
                    System.out.println(Thread.currentThread().getName() + " got the fork!");
                    try {
                        TimeUnit.MILLISECONDS.sleep(500);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " is waiting to use the knife!");
                    synchronized (knife) {
                        System.out.println(Thread.currentThread().getName() + " start eating!");
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Dining(knife);
        Thread t2 = new Dining(fork);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

}
