public class CurrentAccount extends Account {

    private char type = 'c';

    public CurrentAccount(int an) {
        super(an);
    }

    @Override
    public char getType() {
        return type;
    }

    @Override
    public void withdraw(double amount) {
            balance = balance - amount;
    }

}
