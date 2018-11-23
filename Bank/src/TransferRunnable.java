
public class TransferRunnable implements Runnable {

    private static final int DELAY = 1;
    private double amount;
    private int c1AccountNum, c2AccountNum;
    private Manager manager;


    public TransferRunnable(double a, int c1an, int c2an, Manager manager) {
        amount = a;
        c1AccountNum = c1an;
        c2AccountNum = c2an;
        this.manager = manager;
    }

   public void run() {

        try {
            manager.getAccountByNumber(c1AccountNum).withdraw(amount);
            manager.getAccountByNumber(c2AccountNum).deposit(amount);
            Thread.sleep(DELAY);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }

    }

}
