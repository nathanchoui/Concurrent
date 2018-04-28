package personal.nathan.base.Sync;

/**
 * Description:
 * <p>
 * Created by zhangwei on 2018/4/23.
 */
public class Bank {

    private final Account[] accounts;

    public Bank(int n, double initialBalance) {
        accounts = new Account[n];
        for (int i = 0; i < n; i++) {
            Account account = new Account();
            account.setAccountId(i + 1);
            account.setBalance(initialBalance);
            accounts[i] = account;
        }
    }

    /**
     * 转账
     *
     * @param from
     * @param to
     * @param amount
     */
    public void transfer(int from, int to, double amount) {
        Account accountFrom = accounts[from];
        Account accountTo = accounts[to];
        if (amount <= 0
                || accountFrom.getBalance() < amount) {
            return;
        }
        accountFrom.setBalance(accountFrom.getBalance() - amount);
        accountTo.setBalance(accountTo.getBalance() + amount);
    }

    public double getTotalBalance() {
        double sum = 0;
        for (Account account: accounts) {
            sum += account.getBalance();
        }
        return sum;
    }

    public int size() {
        return accounts.length;
    }
}
