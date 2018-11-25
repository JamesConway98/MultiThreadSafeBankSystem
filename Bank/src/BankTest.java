import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

public class BankTest {

    Bank bank;

    @Before
    public void initialize() {

        bank = new Bank();

        bank.addEmployee(1);
        bank.addEmployee(2);

        bank.addCustomer("Robbie Dings");
        bank.addAccount("Robbie Dings", 'c', 1110);

        bank.addCustomer("Billy Stopman");
        bank.addAccount("Billy Stopman", 'c', 1110);
        bank.addAccount("Billy Stopman", 's', 1111);
        bank.addAccount("Billy Stopman", 'k', 1112);

        bank.deposit(bank.getCustomerByName("Robbie Dings"), 100, 1110);
        bank.deposit(bank.getCustomerByName("Billy Stopman"), 100, 1111);
        bank.deposit(bank.getCustomerByName("Billy Stopman"), 100, 1112);

    }

    @Test
    public void testDeposit() {

        bank.deposit(bank.getCustomerByName("Robbie Dings"), 100, 1110);
        bank.deposit(bank.getCustomerByName("Billy Stopman"), 50, 1111);
        bank.deposit(bank.getCustomerByName("Billy Stopman"), 150, 1112);

        assertEquals(bank.getBalance(bank.getCustomerByName("Robbie Dings"), 1110), 200, 0.0);
        assertEquals(bank.getBalance(bank.getCustomerByName("Billy Stopman"),1111), 150, 0.0);
        assertEquals(bank.getBalance(bank.getCustomerByName("Billy Stopman"),1112), 250, 0.0);
    }

   @Test
    public void testWithdraw() {

       bank.withdraw(bank.getCustomerByName("Robbie Dings"), 50, 1110);
       bank.withdraw(bank.getCustomerByName("Billy Stopman"), 100, 1111);
       bank.withdraw(bank.getCustomerByName("Billy Stopman"), 100, 1112);

       assertEquals(bank.getBalance(bank.getCustomerByName("Robbie Dings"), 1110), 50, 0.0);
       assertEquals(bank.getBalance(bank.getCustomerByName("Billy Stopman"),1111), 100, 0.0);
       assertEquals(bank.getBalance(bank.getCustomerByName("Billy Stopman"),1112), 100, 0.0);

       bank.withdraw(bank.getCustomerByName("Robbie Dings"), 100, 1110);

       assertEquals(bank.getBalance(bank.getCustomerByName("Robbie Dings"), 1110), 50, 0.0);
    }


    @Test
    public void testCheckBalance() {

        assertEquals(bank.getBalance(bank.getCustomerByName("Robbie Dings"), 1110), 100, 0.0);
        assertEquals(bank.getBalance(bank.getCustomerByName("Billy Stopman"), 1110), 100, 0.0);
        assertEquals(bank.getBalance(bank.getCustomerByName("Billy Stopman"), 1111), 100, 0.0);

    }

    @Test
    public void testTransfer() {

        bank.transfer(bank.getCustomerByName("Robbie Dings"), 50, 1110, 1111);
        bank.transfer(bank.getEmployeeById(1), 50, 1110, 1111);

        assertEquals(bank.getBalance(bank.getCustomerByName("Robbie Dings"), 1110), 0, 0.0);
        assertEquals(bank.getBalance(bank.getCustomerByName("Billy Stopman"), 1111), 200, 0.0);

        bank.transfer(bank.getCustomerByName("Robbie Dings"), 100, 1110, 1111);

        assertEquals(bank.getBalance(bank.getCustomerByName("Robbie Dings"), 1110), 0, 0.0);
        assertEquals(bank.getBalance(bank.getCustomerByName("Billy Stopman"), 1111), 200, 0.0);
    }


    @Test
    public void testKids() {

        bank.transfer(bank.getCustomerByName("Billy Stopman"), 25, 1112, 1110);
        bank.transfer(bank.getEmployeeById(1), 50, 1110, 1112);

        assertEquals(bank.getBalance(bank.getCustomerByName("Robbie Dings"), 1110), 50, 0.0);
        assertEquals(bank.getBalance(bank.getCustomerByName("Billy Stopman"), 1112), 150, 0.0);

        bank.withdraw(bank.getCustomerByName("Billy Stopman"), 100, 1112);
        bank.deposit(bank.getCustomerByName("Billy Stopman"), 100, 1112);

        assertEquals(bank.getBalance(bank.getCustomerByName("Billy Stopman"), 1112), 250, 0.0);
    }


    @Test
    public void testSavings() {
        bank.withdraw(bank.getCustomerByName("Billy Stopman"), 100, 1111);
        assertEquals(bank.getBalance(bank.getCustomerByName("Billy Stopman"), 1111), 100, 0.0);

        bank.transfer(bank.getCustomerByName("Billy Stopman"), 25, 1110, 1111);
        assertEquals(bank.getBalance(bank.getCustomerByName("Billy Stopman"), 1111), 125, 0.0);
        assertEquals(bank.getBalance(bank.getCustomerByName("Billy Stopman"), 1110), 75, 0.0);

        bank.transfer(bank.getEmployeeById(1), 25, 1111, 1110);
        assertEquals(bank.getBalance(bank.getCustomerByName("Billy Stopman"), 1111), 100, 0.0);
        assertEquals(bank.getBalance(bank.getCustomerByName("Billy Stopman"), 1110), 100, 0.0);

    }

    @Test
    public void testInvalidAccountType() {

        assertFalse(bank.addAccount("Billy Stopman", 't', 1112));
    }

    @Test
    public void testSimultaneousBalanceCheck() {
        assertEquals(bank.getBalance(bank.getCustomerByName("Robbie Dings"), 1110), 100, 0.0);
        assertEquals(bank.getBalance(bank.getCustomerByName("Billy Stopman"), 1110), 100, 0.0);
    }

    @Test
    public void testSimultaneousCheckAndDeposit() {
        bank.deposit(bank.getCustomerByName("Robbie Dings"), 100, 1110);
        assertEquals(bank.getBalance(bank.getCustomerByName("Billy Stopman"), 1110), 200, 0.0);
        assertEquals(bank.getBalance(bank.getCustomerByName("Robbie Dings"), 1110), 200, 0.0);
    }


    @Test
    public void testSimultaneousDepositWithdrawAndCheck() {

        bank.withdraw(bank.getCustomerByName("Robbie Dings"), 100, 1110);
        bank.deposit(bank.getCustomerByName("Billy Stopman"), 100, 1110);
        bank.withdraw(bank.getCustomerByName("Billy Stopman"), 100, 1110);
        bank.deposit(bank.getCustomerByName("Robbie Dings"), 100, 1110);
        bank.withdraw(bank.getCustomerByName("Billy Stopman"), 100, 1110);
        bank.withdraw(bank.getCustomerByName("Billy Stopman"), 100, 1110);
        bank.deposit(bank.getCustomerByName("Robbie Dings"), 100, 1110);
        bank.deposit(bank.getCustomerByName("Billy Stopman"), 100, 1110);
        bank.deposit(bank.getCustomerByName("Billy Stopman"), 100, 1110);
        bank.deposit(bank.getCustomerByName("Billy Stopman"), 100, 1110);
        bank.deposit(bank.getCustomerByName("Robbie Dings"), 100, 1110);

        assertEquals(bank.getBalance(bank.getCustomerByName("Robbie Dings"), 1110), 500, 0.0);
        assertEquals(bank.getBalance(bank.getCustomerByName("Billy Stopman"), 1110), 500, 0.0);
        assertEquals(bank.getBalance(bank.getCustomerByName("Billy Stopman"), 1111), 100, 0.0);

    }

    @Test
    public void testSimultaneousDepositStandingOrderAndCheck() {

    }

    @Test
    public void testSimultaneousAccountModification() {
        bank.alterCustomer(bank.getEmployeeById(1), bank.getCustomerByName("Robbie Dings"), "Dobbie Rings");
        bank.alterCustomer(bank.getEmployeeById(1), bank.getCustomerByName("Robbie Dings"), "Dobbie Rings");
        assertNotNull(bank.getCustomerByName("Dobbie Rings"));
        assertNull(bank.getCustomerByName("Robbie Dings"));
    }





}
