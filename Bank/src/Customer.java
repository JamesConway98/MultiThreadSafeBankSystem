
import java.util.ArrayList;
import java.util.List;


public class Customer {

    String username;
    String password;
    private int id;

    List<Account> myAccountList = new ArrayList<>();

    public Customer(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public Account getAccountByNo(int accountNo) {
        for (Account account : myAccountList) {
            if (account.getAccountNum() == accountNo) {
                return account;
            }
        }
        return null;
    }

    public boolean addAccount(char accountType, int accountNumber) {
        Account newAccount;
        switch (accountType) {
            case ('k'):
                newAccount = new KidsAccount(accountNumber);
                break;
            case ('s'):
                newAccount = new SavingsAccount(accountNumber);
                break;
            case ('c'):
                newAccount = new CurrentAccount(accountNumber);
                break;
            default:
                System.out.println("Error");
                return false;
        }
        myAccountList.add(newAccount);
        return true;
    }

    public Double getBalance(int accountNo) {
        Account account = getAccountByNo(accountNo);
        if (account != null) {
            return getAccountByNo(accountNo).getBalance();
        } else {
            return null;
        }
    }

    public void deposit(double amount, int accountNo) {
        getAccountByNo(accountNo).deposit(amount);
    }

    public void withdraw(double amount, int accountNo) {
       getAccountByNo(accountNo).withdraw(amount);
    }

    public void transfer(double amount, Customer customer, int transfereeNum, int accountNo) {
        this.getAccountByNo(accountNo).transfer(amount, customer, transfereeNum);
    }


}

