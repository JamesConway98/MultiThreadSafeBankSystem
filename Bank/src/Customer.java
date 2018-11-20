
import java.util.ArrayList;
import java.util.List;


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


    public int getID() {
        return id;
    }

    public void editName(String newName) {
        username = newName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Account getAccountByNo(int accountNo) {
        for (Account account : myAccountList) {
            if (account.getAccountNum() == accountNo) {
                return account;
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

    public Double getBalance(int accountNo) {
        Account account = getAccountByNo(accountNo);
        if (account != null) {
            return getAccountByNo(accountNo).getBalance();
        } else {
            return null;
        }
    }

    public void printBalance(int accountNo) {
       System.out.println(accountNo + ": " + getBalance(accountNo));
    }

    public void deposit(double amount, int accountNo) {
        getAccountByNo(accountNo).deposit(amount);
    }

    public void withdraw(double amount, int accountNo) {
       getAccountByNo(accountNo).withdraw(amount);
    }

    public void transfer(double amount, Customer customer, int transfereeNum, int accountNo) {
        this.getAccountByNo(accountNo).transfer(amount, customer, transfereeNum);
    }
/*
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

