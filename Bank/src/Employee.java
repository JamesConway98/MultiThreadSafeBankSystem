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

    public void updateCustomers(List<Customer> customers) {
        this.customerList = customers;
    }

    public int getId(){
        return id;
    }
}
