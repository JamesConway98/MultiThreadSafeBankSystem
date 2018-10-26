package CS313.Banks;
public class KidsAccount extends  Account {
    /*
    Can't transfer out of kids accounts, can transfer in
    No withdrawals
     */

    @Override
    public boolean withdraw(int amount) {
        System.out.println("Cannot withdraw from kids' accounts.");
        return false;
    }

    @Override
    public boolean transfer(int amount, Account account) {
        System.out.println("Cannot transfer from kids' accounts.");
        return false;
    }
}
