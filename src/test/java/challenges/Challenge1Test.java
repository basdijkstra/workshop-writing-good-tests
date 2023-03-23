package challenges;

import banking.Account;
import banking.AccountType;
import banking.WithdrawException;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class Challenge1Test {

    @Test
    public void testDepositsWithdrawalsAddingInterestAndOverdrawing() throws WithdrawException {

        Account account = new Account(AccountType.SAVINGS);
        account.deposit(100);
        assertEquals(100, account.getBalance());
        account.addInterest(0.05);
        assertEquals(105, account.getBalance());
        account.withdraw(100);
        assertEquals(0, account.getBalance());
        account.deposit(149);
        assertThrows(WithdrawException.class, () -> account.withdraw(150));
    }
}
