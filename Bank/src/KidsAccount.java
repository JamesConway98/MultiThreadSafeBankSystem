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
    public char getType() {
        return type;
    }

    @Override
    public void withdraw(double amount) {
        System.out.println("Cannot withdraw from kids' accounts.");
    }
}
