public class TransferRunnable implements Runnable {

    private static final int DELAY = 1;
    private Customer customer1, customer2;
    private double amount;
    private int c1AccountNum, c2AccountNum;

    public TransferRunnable(Customer c1, Customer c2, double a, int c1an, int c2an) {
        customer1 = c1;
        customer2 = c2;
        amount = a;
        c1AccountNum = c1an;
        c2AccountNum = c2an;
    }

    public void run() {
            WithdrawRunnable wr = new WithdrawRunnable(customer1, amount, c1AccountNum);
            Thread withdraw = new Thread(wr);
            DepositRunnable dr = new DepositRunnable(customer2, amount, c2AccountNum);
            Thread deposit = new Thread(dr);

            withdraw.start();
            try {
                withdraw.join();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
            deposit.run();
    }

}
