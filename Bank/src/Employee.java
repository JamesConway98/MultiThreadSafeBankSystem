import java.util.ArrayList;
import java.util.List;

public class Employee {

    List<Customer> customerList = new ArrayList<>();

    public void addCustomer(Customer customer){
        customerList.add(customer);
    }

    public Account getAccountByNo(Customer customer, int accountNo) {
        for (Account account : customer.myAccountList) {
            if (account.getAccountNum() == accountNo) {
                return account;
            }
        }
        return null;
    }


    public void transfer(double amount, Customer customer1, Customer customer2, int transfereeNum, int accountNo) {
        Account transfererAccount = customer1.getAccountByNo(accountNo);
        transfererAccount.transfer(amount, customer2, transfereeNum);
    }

}
