import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bank {

    List<Customer> customerList = new ArrayList<>();

    public Customer getCustomerByName(String username) {
        for (Customer customer: customerList) {
            if (customer.getUsername().equals(username)) {
                return customer;
            }
        }
        System.out.println("No such login available");
        return null;
    }


     public void logIn() {
         Scanner sc = new Scanner();

        System.out.println("Enter your Username");
        String username = sc.nextLine();

        System.out.println("Enter your Password");
        String password = sc.nextLine();


        if (username == null || password == null) {
            System.out.println("Please make sure to enter both fields ");
        }
        else {
            System.out.println("Welcome " + username);
        }

        public void transfer(Customer customer1, Customer customer2) {
            transferRunnable tr = new transferRunnable(customer1, customer2);
         }

    }
}
