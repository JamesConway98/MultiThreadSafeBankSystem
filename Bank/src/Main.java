public class Main {

    public static void main(String[] args) {
        Bank bank = new Bank();

        bank.addCustomer("james", "password");
        bank.addAccount("james", 'c', 1234);

        WithdrawRunnable withdraw = new WithdrawRunnable(bank.getCustomerByName("james"), 100, 1234);
        DepositRunnable deposit = new DepositRunnable(bank.getCustomerByName("james"), 100, 1234);

        Thread deposit1 = new Thread(deposit);
        Thread withdraw1 = new Thread(withdraw);
        Thread deposit2 = new Thread(deposit);
        Thread deposit3 = new Thread(deposit);

        deposit1.start();
        withdraw1.start();
        deposit2.start();
        deposit3.start();
    }
}