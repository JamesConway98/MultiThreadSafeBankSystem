public class Main {

    public static void main(String[] args) {
        Bank bank = new Bank();

        bank.addCustomer("james", "password");
        bank.addAccount("james", 'c', 1234);

        bank.addCustomer("bill", "password");
        bank.addAccount("bill", 'c', 5678);

        bank.withdraw("james", 100, 1234);
        bank.deposit("james", 100, 1234);
        bank.withdraw("james", 100, 1234);
        bank.deposit("james", 100, 1234);
        bank.deposit("james", 100, 1234);
        bank.withdraw("james", 100, 1234);
        bank.deposit("james", 100, 1234);
        bank.deposit("james", 100, 1234);

        bank.customerTransfer("james", "bill", 100, 1234, 5678);
        bank.employeeTransfer("james", "bill", 100, 1234, 5678);

        System.out.println(bank.getCustomerByName("james").getBalance(1234));

    }
}