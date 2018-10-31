public class transferRunnable implements Runnable {

    private static final int DELAY = 1;
    private Customer customer1, customer2;
    private double amount;

    public transferRunnable(Customer c1, Customer c2, double a) {
        customer1 = c1;
        customer2 = c2;
        amount = a;
    }

    public void run() {
        try
        {
            customer1.withdraw(amount);
            customer2.deposit(amount);
        } catch (InterruptedException ie) {
            
        }
    }


}
