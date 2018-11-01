import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bank {

    List<Customer> customerList = new ArrayList<>();

    public void bankWait(int duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

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
        bankWait(200);
    }

    public void withdraw(String customer, double amount, int accountNumber) {
        Customer cust = this.getCustomerByName(customer);
        if (cust.getBalance(accountNumber) < amount) {
            System.out.println("Insufficient funds");
        } else {
            WithdrawRunnable wr = new WithdrawRunnable(cust, amount, accountNumber);
            Thread withdraw = new Thread(wr);
            withdraw.start();
            bankWait(200);
        }
    }

    public void transfer(String customer1, String customer2, double amount, int accountNum1, int accountNum2) {
        Customer cust1 = getCustomerByName(customer1);

        if (cust1.getBalance(accountNum1) == null) {
            System.out.println("You have no such account");
            return;
        } else if (cust1.getBalance(accountNum1) < amount) {
            System.out.println("Insufficient funds");
            return;
        }

        Customer cust2 = getCustomerByName(customer2);

        TransferRunnable tr = new TransferRunnable(cust1, cust2, amount, accountNum1, accountNum2);
        Thread transfer = new Thread(tr);
        transfer.start();
        bankWait(200);
    }
}
