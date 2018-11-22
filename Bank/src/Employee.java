import java.util.ArrayList;
import java.util.List;

public class Employee {

    List<Customer> customerList = new ArrayList<>();

    public void addCustomer(Customer customer){
        customerList.add(customer);
    }

    public void transfer(double amount, Customer customer1, Customer customer2, int transfereeNum, int accountNo) {
        Account transfererAccount = customer1.getAccountByNo(accountNo);
        transfererAccount.transfer(amount, customer2, transfereeNum);
    }

}
