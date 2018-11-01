public class WithdrawRunnable implements Runnable {

    private static final int DELAY = 1;
    private Customer customer;
    private double amount;
    private int accountNumber;

    public WithdrawRunnable(Customer c, double a, int ac) {
        customer = c;
        amount = a;
        accountNumber = ac;
    }

    public void run() {
        try {
            customer.withdraw(amount, accountNumber);
            Thread.sleep(DELAY);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }
}
