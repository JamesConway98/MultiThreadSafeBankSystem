public class Main {

    public static void main(String[] args) {
        Bank bank = new Bank();
        bank.addCustomer("james", "password");
        bank.addAccount("james", 'c', 1234);

        bank.addCustomer("jams", "password");
        bank.addAccount("jams", 'c', 5678);

        bank.deposit("james", 100, 1234);
        bank.getCustomerByName("james").printBalance(1234);

        bank.transfer("james", "jams", 100, 1234, 5678);

        bank.getCustomerByName("james").printBalance(1234);
        bank.getCustomerByName("jams").printBalance(5678);
    }
}