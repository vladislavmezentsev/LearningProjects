import junit.framework.TestCase;

import java.util.*;

public class BankTest extends TestCase {

    Bank bankBefore = new Bank();
    Bank bankAfter = new Bank();
    Map<String, Account> accounts = new HashMap<>();

    @Override
    protected void setUp() throws Exception {
        for (int i = 0; i <= 500; i++) {
            accounts.put(String.valueOf(i), new Account((int) (Math.random() * 1_000_000), String.valueOf(i)));
        }
        bankBefore.setAccounts(accounts);
        bankAfter.setAccounts(accounts);
    }

    public synchronized void testTransfer() {
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            threads.add(new Thread(() -> {
                for (int j = 0; j <= 100; j++) {
                    try {
                        bankAfter.transfer(String.valueOf((int) (Math.random() * 500)),
                                String.valueOf((int) (Math.random() * 500)),
                                (int) (Math.random() * 1_000_000));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }));
        }
        threads.forEach(t -> t.start());

        long actual = bankAfter.getSumAllAccounts();
        long expected = bankBefore.getSumAllAccounts();
        assertEquals(expected, actual);
    }

    @Override
    protected void tearDown() throws Exception {

    }
}
