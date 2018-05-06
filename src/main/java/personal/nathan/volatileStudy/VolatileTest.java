package personal.nathan.volatileStudy;

import java.util.concurrent.TimeUnit;

/**
 * Description:
 * <p>
 * Created by zhangwei on 2017/11/26.
 */
public class VolatileTest {

    private volatile long dNum = 0L;

    private volatile float fNum = 0;

    private int times = 10000;

    class AddDouble implements Runnable {

        public void run() {
            synchronized (this) {
                for (int i = 0; i < times; i ++) {
                    dNum ++;
                }
            }
        }
    }

    class AddFloat implements Runnable {
        public void run() {
            synchronized (this) {
                for (int i = 0; i < times; i ++) {
                    fNum ++;
                }
            }

        }
    }


    public static void main(String[] args) throws InterruptedException {
        VolatileTest volatileTest = new VolatileTest();
        AddDouble addDouble = volatileTest.new AddDouble();
        Thread add1 = new Thread(addDouble);
        Thread add2 = new Thread(addDouble);
        add1.start();
        add2.start();

        add1.join();
        add2.join();
        System.out.println(volatileTest.dNum);
        System.out.println(volatileTest.fNum);

    }
}
