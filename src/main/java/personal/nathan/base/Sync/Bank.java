package personal.nathan.base.Sync;

/**
 * Description: java 核心技术 660
 * <p>
 * Created by zhangwei on 2018/4/23.
 */
public class Bank {

    private final Account[] accounts;

    public Bank(int n, double initialBalance) {
        accounts = new Account[n];
        for (int i = 0; i < n; i ++) {
            Account account = new Account();
            account.setAccountId(i + 1);
            account.setBalance(initialBalance);
            accounts[i] = account;
        }
    }

    public void transfer(Account from, Account to, double amount) {

    }

}
