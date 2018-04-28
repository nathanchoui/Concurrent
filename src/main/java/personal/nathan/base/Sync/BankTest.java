package personal.nathan.base.Sync;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 * <p>
 * Created by zhangwei on 2018/4/24.
 */
public class BankTest {

    private static final int NACCOUNTS = 100;
    private static final double INITIAL_BALANCE = 1000;
    private static final double MAX_AMOUNT = 1000;
    private static final int DELAY = 10;

    public static void main(String[] args) {
        Bank bank = new Bank(NACCOUNTS, INITIAL_BALANCE);
        for (int i = 0; i < NACCOUNTS; i++) {
            int fromIndex = i;
            Runnable r = () -> {
                try {
                    while (true) {
                        Random random = new Random();
                        int toIndex = random.nextInt(bank.size());
                        double amount =  MAX_AMOUNT * random.nextDouble();
                        bank.transfer(fromIndex, toIndex, amount);
                        TimeUnit.MILLISECONDS.sleep( random.nextInt(DELAY));
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
            new Thread(r).start();
        }
        System.out.println(bank.getTotalBalance());
    }
}
