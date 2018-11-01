public abstract class Account {

    int accountNum;
    double balance;
    char type = 'a';

    public Account(int an) {
        accountNum = an;
    }

    public char getType() {
        return type;
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

    public abstract void withdraw(double amount);

    public abstract boolean transfer(double amount, Account account);

}
