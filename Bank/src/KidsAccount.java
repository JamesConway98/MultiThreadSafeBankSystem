public class KidsAccount extends  Account {
    /*
    No withdrawals or transfering out
     */

    char type = 'k';

    public KidsAccount(int an) {
        super(an);
    }

    @Override
    public char getType() {
        return type;
    }


    public void withdraw(double amount) {
        System.out.println("Can't withdraw from a kids' account");
    }

}
