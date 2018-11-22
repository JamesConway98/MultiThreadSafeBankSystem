
public class SavingsAccount extends Account {

    /*
    NO withdrawals
     */

    char type = 's';

    public SavingsAccount(int an) {
        super(an);
    }

    @Override
    public char getType() {
        return type;
    }

    @Override
    public void withdraw(double amount) {
        System.out.println("Cannot withdraw from savings' accounts.");
    }


}
