import java.util.ArrayList;
import java.util.List;

public abstract class User {

    private int id;

    public Double getBalance(int accountNo, Manager manager) {
        Account account = manager.getAccountByNumber(accountNo);
        if (account != null) {
            return account.getBalance();
        } else {
            return null;
        }
    }


    public void deposit(double amount, int accountNo, Manager manager) {
        manager.getAccountByNumber(accountNo).deposit(amount);
    }

    public void withdraw(double amount, int accountNo, Manager manager) {
        manager.getAccountByNumber(accountNo).withdraw(amount);
    }
}
