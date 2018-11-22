import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bank {

    List<Customer> customerList = new ArrayList<>();
    Employee employee;

    public Bank(){
        employee = new Employee();
    }

    public void bankWait(int duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
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

    public boolean addAccount(String username, char accountType, int accountNum) {
        Customer customer = getCustomerByName(username);
        return customer.addAccount(accountType, accountNum);
    }

    public void addCustomer(String username, String password) {
        Customer customer = new Customer(username, password);
        customerList.add(customer);
        employee.addCustomer(customer);
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
        bankWait(50);
    }

    public void withdraw(String customer, double amount, int accountNumber) {
        Customer cust = this.getCustomerByName(customer);
        bankWait(50);
        if (cust.getBalance(accountNumber) < amount) {
            System.out.println("Insufficient funds");
        } else {
            WithdrawRunnable wr = new WithdrawRunnable(cust, amount, accountNumber);
            Thread withdraw = new Thread(wr);
            withdraw.start();
            bankWait(50);
        }
    }

    public void customerTransfer(String customer1, String customer2, double amount, int accountNum1, int accountNum2) {
        Customer cust1 = getCustomerByName(customer1);
        bankWait(50);

        if (cust1.getBalance(accountNum1) == null) {
            System.out.println("You have no such account");
            return;
        } else if (cust1.getBalance(accountNum1) < amount) {
            System.out.println("Insufficient funds");
            return;
        } else if (cust1.getAccountByNo(accountNum1).getType() == 'k') {
            System.out.println("You cannot transfer from a kids' account");
            return;
        }

        Customer cust2 = getCustomerByName(customer2);

        TransferRunnable tr = new TransferRunnable(cust1, cust2, amount, accountNum1, accountNum2);
        Thread transfer = new Thread(tr);
        transfer.start();
       bankWait(50);
    }

    public void employeeTransfer(String customer1, String customer2, double amount, int accountNum1, int accountNum2) {
        Customer cust1 = getCustomerByName(customer1);
        bankWait(50);

        if (cust1.getBalance(accountNum1) == null) {
            System.out.println("You have no such account");
            return;
        } else if (cust1.getBalance(accountNum1) < amount) {
            System.out.println("Insufficient funds");
            return;
        } else if (cust1.getAccountByNo(accountNum1).getType() == 'k') {
            System.out.println("You cannot transfer from a kids' account");
            return;
        }

        Customer cust2 = getCustomerByName(customer2);

        TransferRunnable tr = new TransferRunnable(employee, cust1, cust2, amount, accountNum1, accountNum2);
        Thread transfer = new Thread(tr);
        transfer.start();
        bankWait(50);
    }
}
