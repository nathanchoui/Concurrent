package personal.nathan.jdkbase.basic;

import java.util.Random;

/**
 * Description:
 * <p
 * >
 * Created by zhangwei on 2017/11/4.
 */
public class BankMain {



    public static void main(String[] args) {
        Bank bank = new Bank(1000);
        WithdrawThread withdrawThread = new WithdrawThread(bank);
        Thread t1 = new Thread(withdrawThread);
        Thread t2 = new Thread(withdrawThread);
        t1.start();
        t2.start();

    }

    private static class WithdrawThread implements Runnable {

        Bank bank;

        public WithdrawThread(Bank bank) {
            this.bank = bank;
        }

        public void run() {
            while (true) {
                try {
                    bank.withdraw(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private static class DepositThread implements Runnable {

        Bank bank;

        public DepositThread(Bank bank) {
            this.bank = bank;
        }

        public void run() {
            while (true) {
                try {
                    bank.deposit(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
