package personal.nathan.jdkbase.ConcurrentContainer;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.omg.PortableServer.THREAD_POLICY_ID;

import java.lang.ref.Reference;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Description:
 * <p>
 * Created by nathan.z on 2018/5/20.
 */
public class AtomicReferenceTest {

    static AtomicReference<Integer> bank = new AtomicReference<>();

    static final int time = 100;

    static class Producer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < time; i++) {
                Random random = new Random();
                int balance = bank.get();
                if (balance <= 20) {

                    while (true) {
                        if (bank.compareAndSet(balance, balance + random.nextInt(100) + 1)) {
                            System.out.println("deposit money successfully!");
                            break;
                        }
                    }
                }
            }
        }
    }

    static class Consumer implements Runnable {
        @Override
        public void run() {
            Random random = new Random();
            for (int i = 0; i < time; i++) {
                int balance = bank.get();
                if (balance >= 20) {
                    while (true) {
                        if (bank.compareAndSet(balance, balance - 20)) {
                            System.out.println("withdraw money successfully!");
                            break;
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        bank.set(19);

        Thread t1 = new Thread(new Producer());

        Thread t2 = new Thread(new Consumer());
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(bank.get());
        //bank.set(19);

    }
}
