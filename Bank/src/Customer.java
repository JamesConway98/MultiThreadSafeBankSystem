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

    public Customer(String username, String password) {
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

    public void editName(String newName) {
        username = newName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;

    }

    private boolean checkUsername(String username) {
        if (this.username == username) {
            return true;
        }
        return false;
    }

    private boolean checkPassword(String password) {
        if (this.password == password) {
            return true;
        }
        return false;
    }

    public Account getAccountByNo(int accountNo) {
        for (Account account : myAccountList) {
            if (account.getAccountNum() == accountNo) {
                return account;
            } else {
                System.out.println("There is no account with number" + accountNo);
            }
        }
        return null;
    }

    public boolean addAccount(char accountType, int accountNumber) {
        Account newAccount;
        switch (accountType) {
            case ('k'):
                newAccount = new KidsAccount(accountNumber);
                break;
            case ('s'):
                newAccount = new SavingsAccount(accountNumber);
                break;
            case ('c'):
                newAccount = new CurrentAccount(accountNumber);
                break;
            default:
                System.out.println("Error");
                return false;
        }
        myAccountList.add(newAccount);
        return true;
    }

    /*public void addToMap() {
        if (!accountMap.containsKey(this))
            accountMap.put(this, myAccountList);
        else {
            accountMap.replace(this, myAccountList);
        }
    }*/


    public List<Account> getAccountList() {
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

    public void getBalance(int accountNo) {
        System.out.println(getAccountByNo(accountNo).getBalance());
    }

   /* public void printBalance(int accountNo) {
        if(getBalance(accountNo) != -1) {
            System.out.println(getBalance(accountNo));
        }
    }

    */public void deposit(double amount, int accountNo) {
        getAccountByNo(accountNo).deposit(amount);
    }/*

    public boolean withdraw(double amount, int accountNo) {
        double balance = Double.parseDouble(readAccount(accountNo));
        if (balance < amount) {
            System.out.println("You don't have enough money in your account.");
            return false;
        } else {
            System.out.println("Withdrew" + amount);
            getAccountByNo(accountNo).withdraw(amount);
            return true;
        }
    }

    public boolean transfer(double amount, Customer customer, int transfereeNum, int accountNo) {
        
    }

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
    }*/

    @Override
    public int compareTo(Customer customer) {
        if (this.id == customer.getID()) {
            return 0;
        } else {
            return -1;
        }
    }


}

