
import java.util.ArrayList;
import java.util.List;


public class Customer extends User{

    List<Integer> myAccountList = new ArrayList<>();
    String username;

    public Customer(String username) {
        this.username = username;
    }

    public void addAccount(int accountNumber) {
        myAccountList.add(accountNumber);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public boolean checkMyAccounts(int account) {
        for (int acnt: myAccountList){
            if (acnt == account)
                return true;
        }
        return false;
    }

}

