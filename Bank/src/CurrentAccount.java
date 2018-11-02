import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CurrentAccount extends Account {

    private char type = 'c';

    public CurrentAccount(int an) {
        super(an);
    }

    @Override
    public char getType() {
        return type;
    }

    @Override
    public void withdraw(double amount) {

        boolean stillWaiting = true;
        fundsLock.lock();

        try{
            while(balance < amount){
                if(!stillWaiting)
                    Thread.currentThread().interrupt();

                try {
                    stillWaiting = enoughFundsCondition.await(10, TimeUnit.SECONDS);
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }

            System.out.println("Thread with id " + Thread.currentThread().getId() + ", withdrawing " + amount);
            balance = balance - amount;
            System.out.println("Thread with id " + Thread.currentThread().getId() + ", current balance: " + balance);

        }
        finally{
            fundsLock.unlock();
        }

    }


}
