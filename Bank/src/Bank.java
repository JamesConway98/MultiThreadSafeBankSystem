import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bank {

    List<Customer> customerList = new ArrayList<>();

    public void addCustomer(String username, String password) {
        Customer customer = new Customer(username, password);
        customerList.add(customer);
    }

    public Customer getCustomerByName(String username) {
        for (Customer customer: customerList) {
            if (customer.getUsername().equals(username)) {
                return customer;
            }
        }
        System.out.println("No such login available");
        return null;
    }

    public void addAccount(String username, char accountType, int accountNum) {
        Customer customer = getCustomerByName(username);
        customer.addAccount(accountType, accountNum);
    }


    /* public void logIn() {
         Scanner sc = new Scanner();

         System.out.println("Enter your Username");
         String username = sc.nextLine();

         System.out.println("Enter your Password");
         String password = sc.nextLine();


         if (username == null || password == null) {
             System.out.println("Please make sure to enter both fields ");
         } else {
             System.out.println("Welcome " + username);
         }
     }*/

    public void deposit(String customer, double amount, int accountNumber) {
        Customer cust = this.getCustomerByName(customer);
        DepositRunnable dr = new DepositRunnable(cust, amount, accountNumber);
        Thread deposit = new Thread(dr);
        deposit.start();
        System.out.println("deposit called in bank");
    }

    public void transfer(Customer customer1, Customer customer2, double amount) {
        TransferRunnable tr = new TransferRunnable(customer1, customer2, amount);
    }
}
