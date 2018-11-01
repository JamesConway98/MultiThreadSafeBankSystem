public class CurrentAccount extends Account {

    public CurrentAccount(int an) {
        super(an);
    }

    @Override
    public void withdraw(double amount) {
            balance = balance - amount;
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
