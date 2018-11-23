public class WithdrawRunnable implements Runnable {

    private static final int DELAY = 1;
    private double amount;
    private int accountNumber;
    private Manager manager;

    public WithdrawRunnable(double a, int ac, Manager manager) {
        amount = a;
        accountNumber = ac;
        this.manager = manager;
    }

    public void run() {
        try {
            manager.getAccountByNumber(accountNumber).withdraw(amount);
            Thread.sleep(DELAY);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }
}
