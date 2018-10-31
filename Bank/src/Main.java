public class Main {

    public static void main(String[] args) {
        Bank bank = new Bank();
        bank.addCustomer("james", "password");
        bank.addAccount("james", 'k', 1234);
        bank.deposit("james", 100, 1234);

        bank.getCustomerByName("james").getBalance(1234);
    }

}