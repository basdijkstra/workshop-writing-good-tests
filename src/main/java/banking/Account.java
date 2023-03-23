package banking;

public class Account {

    private AccountType type;
    private int id;
    private double balance;

    public Account(AccountType type) {
        this.type = type;
        this.id = 98765;
        this.balance = 0;
    }

    public void deposit(double amountToDeposit) {
        this.balance += amountToDeposit;
    }

    public void withdraw(double amountToWithdraw) throws WithdrawException {
        if (amountToWithdraw < 0) {
            throw new WithdrawException("You cannot withdraw a negative amount");
        }
        if (amountToWithdraw > this.balance && this.type.equals(AccountType.SAVINGS)) {
            throw new WithdrawException("You cannot overdraw on a savings account");
        }
        this.balance -= amountToWithdraw;
    }

    public void addInterest(double interestRate) {
        this.balance *= (1 + interestRate);
    }

    public double getBalance() {
        return this.balance;
    }
}
