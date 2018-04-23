package personal.nathan.atomic;

import java.util.concurrent.TimeUnit;

/**
 * Description:
 * <p>
 * Created by zhangwei on 2017/11/29.
 */
public class AtomicStudy {

    private double dNum;

    private long lNum;

    private int a;

    public double getdNum() {
        return dNum;
    }

    public void setdNum(double dNum) {
        this.dNum = dNum;
    }

    public long getlNum() {
        return lNum;
    }

    public void setlNum(long lNum) {
        this.lNum = lNum;
    }

    public class SetNum implements Runnable {
        public void run() {
            a = 10;
            dNum = 1.2;
            lNum = 100;
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public class GetNum  implements Runnable {
        public void run() {
            System.out.println("dNum:" + dNum + " lNum:" + lNum + " a:" + a);
        }
    }

    public static void main(String[] agrs) throws InterruptedException {
        AtomicStudy atomicStudy = new AtomicStudy();
        Thread t1 = new Thread(atomicStudy.new SetNum());
        Thread t2 = new Thread(atomicStudy.new GetNum());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
