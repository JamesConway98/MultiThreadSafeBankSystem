import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bank {

    List<Customer> customerList = new ArrayList<>();
    List<Employee> employeeList = new ArrayList<>();
    Manager manager;

    public Bank(){
        manager = new Manager();
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
        System.out.println("No such customer available");
        return null;
    }

    public Employee getEmployeeById(int id) {
        for (Employee employee: employeeList) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        System.out.println("No such employee available");
        return null;
    }


    public boolean addAccount(String username, char accountType, int accountNum) {
        Customer customer = getCustomerByName(username);
        for (Customer cust: customerList) {
             if (cust.getUsername().equals(customer.getUsername())) {
                 customer.addAccount(accountNum);
                 return manager.addAccount(accountType, accountNum);
             }
        }
        return false;
    }

    public void addEmployee(int id) {
        Employee employee = new Employee(id, customerList);
        employeeList.add(employee);
    }

    public void addCustomer(String username) {
        Customer customer = new Customer(username);
        customerList.add(customer);
        for (Employee employee : employeeList) {
             employee.addCustomer(customer);
        }
    }

    public void deposit(User user, double amount, int accountNumber) {
        bankWait(50);
        if(user instanceof Customer) {
            if (!((Customer) user).checkMyAccounts(accountNumber))
                return;
        }

        DepositRunnable dr = new DepositRunnable(amount, accountNumber, manager);
        Thread deposit = new Thread(dr);
        deposit.start();
        bankWait(50);
    }

    public void withdraw(User user, double amount, int accountNumber) {

        if (user instanceof Customer) {
            if (!((Customer) user).checkMyAccounts(accountNumber))
                return;
        }

        if (manager.getBalance(accountNumber) < amount) {
            System.out.println("Insufficient funds");

        } else if (manager.getType(accountNumber) == 's'){
            System.out.println("You cannot withdraw from a savings' account");
            return;
        }else if (manager.getType(accountNumber) == 'k') {
            System.out.println("You cannot withdraw from a kids' account");
            return;
        }else{
            WithdrawRunnable wr = new WithdrawRunnable(amount, accountNumber, manager);
            Thread withdraw = new Thread(wr);
            withdraw.start();
            bankWait(50);

        }
    }

    public void transfer(User user, double amount, int accountFrom, int accountTo) {

        if(user instanceof Customer) {
            if (!((Customer) user).checkMyAccounts(accountFrom))
                return;
        }

        if (manager.getBalance(accountFrom) < amount) {
            System.out.println("Insufficient funds");
            return;
        } else if (manager.getType(accountFrom) == 'k') {
            System.out.println("You cannot transfer from a kids' account");
            return;
        }

        TransferRunnable tr = new TransferRunnable(amount, accountFrom, accountTo, manager);
        Thread transfer = new Thread(tr);
        transfer.start();
        bankWait(50);


    }

    public double getBalance(User user, int accountNumber) {

        if(user instanceof Customer) {
            if (!((Customer) user).checkMyAccounts(accountNumber))
                return 0.0;
        }

        bankWait(50);
        return user.getBalance(accountNumber, manager);
    }


}
