import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Customer implements Comparable<Customer> {

    String username;
    String password;
    private int id;

    List<Account> myAccountList = new ArrayList<>();

    public Customer(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    private String getPassword() {
        return password;
    }

    public int getID() {
        return id;
    }

    public void editName(String newName){
        username = newName;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password){
        this.password = password;

    }

    private boolean checkUsername(String username) {
        if (this.username == username) {
            return true;
        }
        return false;
    }

    private boolean checkPassword(String password){
        if (this.password == password) {
            return true;
        }
        return false;
    }

    /*public boolean createAccounts (char accountType) {
        switch (accountType) {
            case ('C'):
                Account currentAccount = new CurrentAccount();
                myAccountList.add(currentAccount);
                break;
            case ('K'):
                Account kidsAccount = new KidsAccount();
                myAccountList.add(kidsAccount);
                break;
            case ('S'):
                Account savingsAccount = new SavingsAccount();
                myAccountList.add(savingsAccount);
                break;
            default:
                return false;
        }
        addToMap();
        return true;
    }*/

    /*public void addToMap() {
        if (!accountMap.containsKey(this))
            accountMap.put(this, myAccountList);
        else {
            accountMap.replace(this, myAccountList);
        }
    }*/


    public List<Account> getAccountList(){
        return myAccountList;
    }



    /*public void logIn(String username, String password) {
        System.out.println("Enter your Username");
        username = sc.nextLine();

        System.out.println("Enter your Password");
        password = sc.nextLine();


        if (username == null || password == null) {
            System.out.println("Please make sure to enter both fields ");
        }
        else {
            System.out.println("Welcome " + username);
        }

    }*/

    public double getBalance(int accountNo) {

        myAccountList.add(new SavingsAccount());

        String result = readAccount(accountNo);

        if (!result.isEmpty()) {
            return Double.parseDouble(result);
        }
        return -1;
    }

    public void printBalance(int accountNo) {
        if(getBalance(accountNo) != -1) {
            System.out.println(getBalance(accountNo));
        }
    }

    public boolean deposit(int amount, int accountNo) { 
        double result = getBalance(accountNo);

        return true;
    }

    public boolean withdraw(int amount, Account account) {
        return account.withdraw(amount);
    }

    /*public boolean transfer(int amount, Customer customer, int transfereeNum, Account thisAccount) {
        List<Account> transfereeList = accountMap.get(customer);
        for (Account account:transfereeList) {
            if (account.getAccountNum() == transfereeNum) {
                return thisAccount.transfer(amount, account);
            }
        }
        return false;
    }*/

    public String readAccount(int accountNo) {
        for (Account account : myAccountList) {
            if (accountNo == account.getAccountNum()) {
                try {
                    File file = new File("/home/ckb16190/Bank/accounts");

                    String st;

                    BufferedReader br = new BufferedReader(new FileReader(file));

                    while ((st = br.readLine()) != null) {
                        StringTokenizer strtok = new StringTokenizer(st, ",");
                        if (Integer.parseInt(strtok.nextToken()) == accountNo)
                            return strtok.nextToken();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("STOP, THIEF!");
        return "";
    }

    @Override
    public int compareTo(Customer customer) {
        if (this.id == customer.getID()) {
            return 0;
        } else {
            return -1;
        }
    }


}
