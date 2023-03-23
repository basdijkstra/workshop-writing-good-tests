package solutions;

import banking.Account;
import banking.AccountType;
import banking.WithdrawException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Solution1Test {

    private Account account;

    @BeforeEach
    public void createAccountWithInitialBalance() {

        account = new Account(AccountType.SAVINGS);
        account.deposit(100);
    }

    @Test
    public void testDeposit_onSavingsAccount_balanceIsUpdated() {

        assertEquals(100, account.getBalance());
    }

    @Test
    public void testAddInterest_onSavingsAccount_balanceIsUpdated() {

        account.addInterest(0.05);
        assertEquals(105, account.getBalance());
    }

    @Test
    public void testWithdrawal_onSavingsAccount_balanceIsUpdated() throws WithdrawException {

        account.withdraw(50);
        assertEquals(50, account.getBalance());
    }

    @Test
    public void testOverdrawing_onSavingsAccount_throwsWithdrawException() {

        assertThrows(WithdrawException.class, () -> account.withdraw(150));
    }
}
