public class KidsAccount extends  Account {
    /*
    Can't transfer out of kids accounts, can transfer in
    No withdrawals
     */

    char type = 'k';

    public KidsAccount(int an) {
        super(an);
    }



    @Override
    public void withdraw(double amount) {
        System.out.println("Cannot withdraw from kids' accounts.");
    }

    @Override
    public boolean transfer(double amount, Account account) {
        System.out.println("Cannot transfer from kids' accounts.");
        return false;
    }
}
