package challenges;

import banking.Account;
import banking.AccountType;
import banking.WithdrawException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Challenge2Test {

    @Test
    public void testWithdrawal() throws WithdrawException {

        Account account = new Account(AccountType.SAVINGS);
        account.deposit(100);
        account.withdraw(50);
        Assertions.assertEquals(50, account.getBalance());
    }

    @Test
    public void testSomeOtherWithdrawal() throws WithdrawException {

        Account account = new Account(AccountType.SAVINGS);
        account.deposit(100);
        account.withdraw(99);
        Assertions.assertEquals(1, account.getBalance());
    }

    @Test
    public void testAnotherWithdrawal() throws WithdrawException {

        Account account = new Account(AccountType.SAVINGS);
        account.deposit(100);
        account.withdraw(100);
        Assertions.assertEquals(0, account.getBalance());
    }

    @Test
    public void testYetAnotherWithdrawal() throws WithdrawException {

        Account account = new Account(AccountType.CHECKING);
        account.deposit(100);
        account.withdraw(150);
        Assertions.assertEquals(-50, account.getBalance());
    }

    @Test
    public void testYetYetAnotherWithdrawal() {

        Account account = new Account(AccountType.SAVINGS);
        account.deposit(100);
        Assertions.assertThrows(WithdrawException.class, () -> account.withdraw(150));
    }
}
