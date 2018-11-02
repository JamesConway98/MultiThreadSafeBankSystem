
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class Account {

    int accountNum;
    double balance;
    char type = 'a';
    Lock fundsLock;
    Condition enoughFundsCondition;


    public Account(int an) {
        accountNum = an;
        fundsLock = new ReentrantLock();
        enoughFundsCondition = fundsLock.newCondition();

    }

    public char getType() {
        return type;
    }

    public double getBalance() {
        return balance;
    }

    public int getAccountNum() {
        return accountNum;
    }

    public void deposit(double amount) {

        fundsLock.lock();

        try{
            System.out.println("Thread with id " + Thread.currentThread().getId() + ", depositing " + amount);
            balance += amount;
            System.out.println("Thread with id " + Thread.currentThread().getId() + ", current balance: " + balance);
            enoughFundsCondition.signalAll();
        }
        finally{
            fundsLock.unlock();
        }
    }

    public abstract void withdraw(double amount);

    public void transfer(double amount, Customer customer, int accountNum) {
        balance -= amount;
        customer.getAccountByNo(accountNum).deposit(amount);
    }

}
