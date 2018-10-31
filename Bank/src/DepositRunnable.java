public class DepositRunnable implements Runnable {

    private static final int DELAY = 1;
    private Customer customer;
    private double amount;
    private int accountNumber;


    public DepositRunnable(Customer c, double a, int ac) {
        customer = c;
        amount = a;
        accountNumber = ac;
    }

    public void run() {
        try {
            customer.deposit(amount, accountNumber);
            System.out.println("depositrunnable running");
            Thread.sleep(DELAY);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }
}
