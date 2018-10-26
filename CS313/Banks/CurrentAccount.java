package Banks;
public class CurrentAccount extends Account {

    public CurrentAccount() {
    }

    @Override
    public boolean withdraw(int amount) {
        if (balance > amount) {
            balance = balance - amount;
            System.out.println("Your new balance is " + balance);
            return true;
        } else {
            return false;
        }
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
