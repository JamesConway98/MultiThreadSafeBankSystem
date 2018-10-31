public class TransferRunnable implements Runnable {

    private static final int DELAY = 1;
    private Customer customer1, customer2;
    private double amount;

    public TransferRunnable(Customer c1, Customer c2, double a) {
        customer1 = c1;
        customer2 = c2;
        amount = a;
    }

    public void run() {
       /* try
        {
            WithdrawRunnable wr = new WithdrawRunnable();
            Thread withdraw = new Thread(wr);
            DepositRunnable dr = new DepositRunnable();
            Thread deposit = new Thread(dr);

            withdraw.start();
            try {
                withdraw.join();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
            deposit.run();
        } catch (InterruptedException ie) {

            ie.printStackTrace();
            
        }*/
    }


}
