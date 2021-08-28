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
           if (acc.cardNumber.equals(account.cardNumber)) {
               return false;
           }
        }
        this.accounts.add(account);
        return true;
    }
}
