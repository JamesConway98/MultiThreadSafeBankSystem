package Banks;
public abstract class Account {

    int accountNum;
    double balance;

    public double getBalance() {
        return balance;
    }

    public int getAccountNum() {
        return accountNum;
    }

    public boolean deposit(int amount) {
        if (amount > balance) {
            return false;
        }
        balance += amount;
        return true;
    }

    public abstract boolean withdraw(int amount);

    public abstract boolean transfer(int amount, Account account);

}
