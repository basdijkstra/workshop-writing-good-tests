package solutions;

import banking.Account;
import banking.AccountType;
import banking.WithdrawException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class Solution2Test {

    @ParameterizedTest(name = "Withdrawing ${1} from $100 leaves ${2} for account of type {0}")
    @MethodSource("provideWithdrawalTestData")
    public void withdrawFromBalance_checkResultingBalance(
            AccountType type, double amountToWithdraw, double expectedResultingBalance
    ) throws WithdrawException {

        Account account = new Account(type);
        account.deposit(100);
        account.withdraw(amountToWithdraw);
        Assertions.assertEquals(expectedResultingBalance, account.getBalance());
    }

    @Test
    public void withdrawFromSavingsAccount_insufficientFunds_throwsWithdrawException() {

        Account account = new Account(AccountType.SAVINGS);
        account.deposit(100);
        Assertions.assertThrows(WithdrawException.class, () -> account.withdraw(150));
    }

    private static Stream<Arguments> provideWithdrawalTestData() {

        return Stream.of(
                Arguments.of(AccountType.SAVINGS, 50, 50),
                Arguments.of(AccountType.SAVINGS, 99, 1),
                Arguments.of(AccountType.SAVINGS, 100, 0),
                Arguments.of(AccountType.CHECKING, 150, -50)
        );
    }

}
