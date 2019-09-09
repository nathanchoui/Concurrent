package personal.nathan.jdkbase.base.Sync;

/**
 * Synchronized用法：
 * 1. 直接加锁对象：对给定对象加锁，进入同步代码前要获得给定对象的锁。
 * 2. 直接作用于实例方法：相当于对当前实例加锁，进入同步代码前要获得当前实例的锁。
 * 3. 直接作用于类方法：相当于对当前类加锁，进入同步代码前要获得当前类的锁。
 * <p>
 * <p>
 * <p>
 * Created by zhangwei on 2018/4/28.
 */
public class AccountingSync {

    private static final int TIMES = 100000;


    public static class SyncCount1 implements Runnable {

        private Counter counter;

        public SyncCount1(Counter counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            // 将锁放在循环外面，不用每次累加都去做加锁和解锁的动作
            synchronized (counter) {
                for (int i = 0; i < TIMES; i++) {
                    counter.setNum(counter.getNum() + 1);
                }
            }
        }
    }

    public static class Counter {
        private int num;

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        counter.setNum(0);
        // 锁的对象必须是同一个对象,
        Runnable r = new SyncCount1(counter);
        //Runnable r2 = new SyncCount1(counter);
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        long startTime = System.currentTimeMillis();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("consumed :" + (System.currentTimeMillis() - startTime) + " ms");
        System.out.println(counter.getNum());
    }

}
