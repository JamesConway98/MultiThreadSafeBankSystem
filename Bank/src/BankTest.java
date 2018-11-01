
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class BankTest {

    Bank bank = new Bank();

    @Test
    public void testDeposit() {
        bank.addCustomer("james", "password");
        bank.addAccount("james", 'c', 1234);

        bank.addCustomer("jams", "password");
        bank.addAccount("jams", 'c', 5678);

        bank.deposit("james", 100, 1234);
        bank.deposit("jams", 50, 5678);

        assertEquals(bank.getCustomerByName("james").getAccountByNo(1234).getBalance(), 100, 0.0);
        assertEquals(bank.getCustomerByName("jams").getAccountByNo(5678).getBalance(), 50, 0.0);
    }

    @Test
    public void testWithdraw() {
        bank.addCustomer("james", "password");
        bank.addAccount("james", 'c', 1234);

        bank.addCustomer("jams", "password");
        bank.addAccount("jams", 'c', 5678);

        bank.deposit("james", 100, 1234);
        bank.deposit("jams", 50, 5678);

        bank.withdraw("james", 50, 1234);
        bank.withdraw("jams", 50, 5678);

        assertEquals(bank.getCustomerByName("james").getAccountByNo(1234).getBalance(), 50, 0.0);
        assertEquals(bank.getCustomerByName("jams").getAccountByNo(5678).getBalance(), 0, 0.0);
    }

    @Test
    public void testTransfer() {
        bank.addCustomer("james", "password");
        bank.addAccount("james", 'c', 1234);

        bank.addCustomer("jams", "password");
        bank.addAccount("jams", 'c', 5678);

        bank.deposit("james", 100, 1234);
        bank.deposit("jams", 50, 5678);

        bank.transfer("james", "jams", 50, 1234, 5678);

        assertEquals(bank.getCustomerByName("james").getAccountByNo(1234).getBalance(), 50, 0.0);
        assertEquals(bank.getCustomerByName("jams").getAccountByNo(5678).getBalance(), 100, 0.0);
    }

    @Test
    public void testKids() {
        bank.addCustomer("james", "password");
        bank.addAccount("james", 'k', 1234);

        bank.addCustomer("jams", "password");
        bank.addAccount("jams", 'c', 5678);

        bank.deposit("james", 100, 1234);

        bank.withdraw("james", 50, 1234);

        assertEquals(bank.getCustomerByName("james").getAccountByNo(1234).getBalance(), 100, 0.0);

        bank.transfer("james", "jams", 50, 1234, 5678);

        assertEquals(bank.getCustomerByName("james").getAccountByNo(1234).getBalance(), 100, 0.0);
        assertEquals(bank.getCustomerByName("jams").getAccountByNo(5678).getBalance(), 0, 0.0);
    }

    @Test
    public void testSavings() {

    }
}
