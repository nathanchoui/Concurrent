package personal.nathan.jdkbase.basic;

import java.util.concurrent.TimeUnit;

/**
 * Description:
 * <p>
 * Created by zhangwei on 2017/11/4.
 */
public class Bank {

    // 余额
    private int balance;

    public Bank(int balance) {
        this.balance = balance;
    }

    // 存款
    public void deposit(int amount) throws InterruptedException {
        balance += amount;
        printBalance("deposit");
        //TimeUnit.SECONDS.sleep(1);

    }

    // 取钱
    public boolean withdraw(int amount) throws InterruptedException {
        if (balance >= amount) {
            balance -= amount;
            printBalance("withdraw");
            //TimeUnit.SECONDS.sleep(1);
            return true;
        }
        return false;
    }

    private void printBalance(String action) {
        System.out.println(action + "; current balance: " + balance);
    }
}
