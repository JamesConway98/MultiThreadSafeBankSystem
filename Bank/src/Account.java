public abstract class Account {

    int accountNum;
    double balance;

    public Account(int an) {
        accountNum = an;
    }

    public double getBalance() {
        return balance;
    }

    public int getAccountNum() {
        return accountNum;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public abstract boolean withdraw(double amount);

    public abstract boolean transfer(double amount, Account account);

}
