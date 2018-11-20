
public class TransferRunnable implements Runnable {

    private static final int DELAY = 1;

    private Employee employee;
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

    public TransferRunnable(Employee employee, Customer c1, Customer c2, double a, int c1an, int c2an) {
        this.employee = employee;
        customer1 = c1;
        customer2 = c2;
        amount = a;
        c1AccountNum = c1an;
        c2AccountNum = c2an;
    }

    public void run() {
        if(employee == null) {
            try {
                customer1.transfer(amount, customer2, c2AccountNum, c1AccountNum);
                Thread.sleep(DELAY);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
        else{
            try {
                employee.transfer(amount, customer1, customer2, c2AccountNum, c1AccountNum);
                Thread.sleep(DELAY);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }

    }

}
