package banking;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    List<Account> accounts;

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts( List<Account> accounts ) {
        this.accounts = accounts;
    }

    public Bank() {
        this.accounts = new ArrayList<>();
    }

    public boolean addAccount (Account account) {
        for (Account acc:this.accounts) {
           if (acc.getCardNumber().equals(account.getCardNumber())) {
               return false;
           }
        }
        this.accounts.add(account);
        return true;
    }

    public void showStartMenu ()  {
        System.out.println("1. Create an account");
        System.out.println("2. Log into account");
        System.out.println("0. Exit");
        System.out.println();
    }
}
