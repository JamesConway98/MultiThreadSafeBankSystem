import java.util.ArrayList;
import java.util.List;

public class Manager {

    List<Account> allAccounts = new ArrayList<>();

    public boolean addAccount(char accountType, int accountNum) {

        Account newAccount;
        switch (accountType) {
            case ('k'):
                newAccount = new KidsAccount(accountNum);
                break;
            case ('s'):
                newAccount = new SavingsAccount(accountNum);
                break;
            case ('c'):
                newAccount = new CurrentAccount(accountNum);
                break;
            default:
                System.out.println("Error");
                return false;
        }

        allAccounts.add(newAccount);
        return true;
    }

    public Account getAccountByNumber(int accountNo){
        for(Account account: allAccounts){
            if(accountNo == account.getAccountNum()){
                return account;
            }
        }
        return null;
    }

    public char getType(int accountNo){
        Account account = getAccountByNumber(accountNo);
        if (account != null) {
            return account.getType();
        } else {
            return ' ';
        }
    }

    public double getBalance(int accountNo){
        Account account = getAccountByNumber(accountNo);
        return account.getBalance();
    }


}
