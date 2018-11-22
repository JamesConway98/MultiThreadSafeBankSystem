import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class BankTest {

    Bank bank;

    @Before
    public void initialize() {

        bank = new Bank();

        bank.addCustomer("Robbie Dings", "password");
        bank.addAccount("Robbie Dings", 'c', 1110);

        bank.addCustomer("Billy Stopman", "password");
        bank.addAccount("Billy Stopman", 's', 1111);
        bank.addAccount("Billy Stopman", 'k', 1112);

        bank.deposit("Robbie Dings", 100, 1110);
        bank.deposit("Billy Stopman", 100, 1111);
        bank.deposit("Billy Stopman", 100, 1112);

    }

    @Test
    public void testDeposit() {

        bank.deposit("Robbie Dings", 100, 1110);
        bank.deposit("Billy Stopman", 50, 1111);
        bank.deposit("Billy Stopman", 150, 1112);

        assertEquals(bank.getCustomerByName("Robbie Dings").getAccountByNo(1110).getBalance(), 200, 0.0);
        assertEquals(bank.getCustomerByName("Billy Stopman").getAccountByNo(1111).getBalance(), 150, 0.0);
        assertEquals(bank.getCustomerByName("Billy Stopman").getAccountByNo(1112).getBalance(), 250, 0.0);
    }

    @Test
    public void testWithdraw() {

        bank.withdraw("Robbie Dings", 50, 1110);
        bank.withdraw("Billy Stopman", 50, 1111);
        bank.withdraw("Billy Stopman", 150, 1112);

        assertEquals(bank.getCustomerByName("Robbie Dings").getAccountByNo(1110).getBalance(), 50, 0.0);
        assertEquals(bank.getCustomerByName("Billy Stopman").getAccountByNo(1111).getBalance(), 100, 0.0);
        assertEquals(bank.getCustomerByName("Billy Stopman").getAccountByNo(1112).getBalance(), 100, 0.0);

        bank.withdraw("Robbie Dings", 100, 1110);

        assertEquals(bank.getCustomerByName("Robbie Dings").getAccountByNo(1110).getBalance(), 50, 0.0);
    }

    @Test
    public void testTransfer() {
        bank.employeeTransfer("Robbie Dings", "Billy Stopman", 25, 1110, 1111);
        bank.customerTransfer("Robbie Dings", "Billy Stopman", 50, 1110, 1111);

        assertEquals(bank.getCustomerByName("Robbie Dings").getAccountByNo(1110).getBalance(), 25, 0.0);
        assertEquals(bank.getCustomerByName("Billy Stopman").getAccountByNo(1111).getBalance(), 175, 0.0);

        bank.customerTransfer("Robbie Dings", "Billy Stopman", 50, 1110, 1111);

        assertEquals(bank.getCustomerByName("Robbie Dings").getAccountByNo(1110).getBalance(), 25, 0.0);
        assertEquals(bank.getCustomerByName("Billy Stopman").getAccountByNo(1111).getBalance(), 175, 0.0);
    }

    @Test
    public void testKids() {
        bank.employeeTransfer("Billy Stopman", "Robbie Dings", 25, 1112, 1110);
        bank.customerTransfer("Robbie Dings", "Billy Stopman", 50, 1110, 1112);

        assertEquals(bank.getCustomerByName("Robbie Dings").getAccountByNo(1110).getBalance(), 50, 0.0);
        assertEquals(bank.getCustomerByName("Billy Stopman").getAccountByNo(1112).getBalance(), 150, 0.0);

        bank.withdraw("Billy Stopman", 100, 1112);
        bank.deposit("Billy Stopman", 100, 1112);

        assertEquals(bank.getCustomerByName("Billy Stopman").getAccountByNo(1112).getBalance(), 250, 0.0);
    }

    @Test
    public void testSavings() {
        bank.employeeTransfer("Billy Stopman", "Robbie Dings", 25, 1111, 1110);

        assertEquals(bank.getCustomerByName("Billy Stopman").getAccountByNo(1111).getBalance(), 75, 0.0);

    }

    @Test
    public void testInvalidAccountType() {

        assertFalse(bank.addAccount("Billy Stopman", 't', 1112));
    }

    @Test
    public void testwithdrawandDeposit() {

        bank.withdraw("Robbie Dings", 100, 1110);
        bank.deposit("Robbie Dings", 100, 1110);
        bank.withdraw("Robbie Dings", 100, 1110);
        bank.withdraw("Robbie Dings", 100, 1110);
        bank.withdraw("Robbie Dings", 100, 1110);
        bank.deposit("Robbie Dings", 100, 1110);
        bank.deposit("Robbie Dings", 100, 1110);
        bank.deposit("Robbie Dings", 100, 1110);
        bank.deposit("Robbie Dings", 100, 1110);
        bank.deposit("Robbie Dings", 100, 1110);

        assertEquals(bank.getCustomerByName("Robbie Dings").getAccountByNo(1110).getBalance(), 500, 0.0);

    }


}
