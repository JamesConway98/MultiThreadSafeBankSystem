package CS313.Banks;
public class SavingsAccount extends Account {

    /*
    NO withdrawals
     */

    @Override
    public boolean withdraw(int amount) {
        System.out.println("Cannot withdraw from savings' accounts.");
        return false;
    }

    @Override
    public boolean transfer(int amount, Account account) {
        if (balance > amount) {
            balance = balance - amount;
            account.deposit(amount);
            //add money to other account
            System.out.println("Your new balance is " + balance);
            return true;
        } else {
            return false;
        }
    }
}