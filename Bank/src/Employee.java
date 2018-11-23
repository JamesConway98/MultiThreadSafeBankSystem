import java.util.ArrayList;
import java.util.List;

public class Employee extends User{

    List<Customer> customerList = new ArrayList<>();
    int id;

    public Employee(int id, List<Customer> customerList) {
        this.id = id;
        this.customerList = customerList;
    }

    public void addCustomer(Customer customer){
        customerList.add(customer);
    }

    public int getId(){
        return id;
    }

//    public void transfer(double amount, Customer customer1, Customer customer2, int transfereeNum, int accountNo) {
//        Account transfererAccount = customer1.getAccountByNo(accountNo);
//        transfererAccount.transfer(amount, customer2, transfereeNum);
//    }

}
