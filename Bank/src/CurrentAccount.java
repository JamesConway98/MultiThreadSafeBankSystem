public class CurrentAccount extends Account {

    public CurrentAccount(int an) {
        super(an);
    }

    @Override
    public boolean withdraw(double amount) {
        if (balance > amount) {
            balance = balance - amount;
            System.out.println("Your new balance is " + balance);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean transfer(double amount, Account account) {
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
