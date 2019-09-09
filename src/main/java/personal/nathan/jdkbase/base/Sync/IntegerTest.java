package personal.nathan.jdkbase.base.Sync;

/**
 * Description:
 * <p>
 * Created by zhangwei on 2018/5/2.
 */
public class IntegerTest {

    private static IntegerTest integerTest = new IntegerTest();

    private static Integer count = 0;

    public static class AddThread implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 100000; i++) {
                synchronized (integerTest) {
                    count ++;
                }
                // 这样无法保证数字的正确累加
//                synchronized (count) {
//                    count ++;
//                }
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
        Runnable addThread = new IntegerTest.AddThread();
        Thread t1 = new Thread(addThread);
        Thread t2 = new Thread(addThread);

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(count);
    }
}
