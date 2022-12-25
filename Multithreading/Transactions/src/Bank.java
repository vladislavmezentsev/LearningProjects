import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class Bank {

    private Map<String, Account> accounts;
    private Map<String, Account> blockedAccounts;
    private final Random random = new Random();

    public Map<String, Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Map<String, Account> accounts) {
        this.accounts = accounts;
    }

    public Map<String, Account> getBlockedAccounts() {
        return blockedAccounts;
    }

    public void setBlockedAccounts(Map<String, Account> blockedAccounts) {
        this.blockedAccounts = blockedAccounts;
    }

    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
            throws InterruptedException {
        Thread.sleep(1000);
        return random.nextBoolean();
    }

    /**
     * TODO: реализовать метод. Метод переводит деньги между счетами. Если сумма транзакции > 50000,
     * то после совершения транзакции, она отправляется на проверку Службе Безопасности – вызывается
     * метод isFraud. Если возвращается true, то делается блокировка счетов (как – на ваше
     * усмотрение)
     */

    public void transfer(String fromAccountNum, String toAccountNum, long amount) {
        if (amount > 50000) {
            try {
                if (isFraud(fromAccountNum, toAccountNum, amount)) {
                    blockedAccounts.put(fromAccountNum, accounts.get(fromAccountNum));
                    blockedAccounts.put(toAccountNum, accounts.get(toAccountNum));
                    accounts.remove(fromAccountNum);
                    accounts.remove(toAccountNum);
                    System.out.println("Обнаружены нарушения. Счета заблокированы.");
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        if (accounts.get(fromAccountNum).getAccNumber().compareTo(accounts.get(toAccountNum).getAccNumber()) < 0) {
            synchronized (accounts.get(fromAccountNum)) {
                synchronized (accounts.get(toAccountNum)) {
                    accounts.get(toAccountNum).setMoney(accounts.get(toAccountNum).getMoney() + amount);
                    accounts.get(fromAccountNum).setMoney(accounts.get(fromAccountNum).getMoney() - amount);
                }
            }
        } else {
            synchronized (accounts.get(fromAccountNum)) {
                synchronized (accounts.get(toAccountNum)) {
                    accounts.get(toAccountNum).setMoney(accounts.get(toAccountNum).getMoney() + amount);
                    accounts.get(fromAccountNum).setMoney(accounts.get(fromAccountNum).getMoney() - amount);
                }
            }
        }
    }

    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */
    public long getBalance(String accountNum) {
        long balance = accounts.get(accountNum).getMoney();
        return balance;
    }

    public long getSumAllAccounts() {
        AtomicLong sumOfAllAccounts = new AtomicLong();
        accounts.forEach((s, account) -> {
            sumOfAllAccounts.addAndGet(account.getMoney());
        });

        return sumOfAllAccounts.get();
    }
}
