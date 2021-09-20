package banking.entitie;

import banking.controller.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    public void newAccount(SQLiteDatabase sqLiteDatabase) {

        Account account = new Account();
        while (this.accounts.indexOf(account) > 0) {
            account = new Account();
        }
        this.accounts.add(account);
        sqLiteDatabase.addToDatabase(account.getCard());
        System.out.println("Your card has been created");
        account.print();
        System.out.println();

    }

    public int loggingIn() {
        Scanner scanner = new Scanner(System.in);

        boolean successfullyLogged = false;
        System.out.println("Enter your card number:");

        String cardNumber = scanner.nextLine();
        System.out.println("Enter your PIN:");
        String pinCode = scanner.nextLine();
        Account account = new Account();

        for (Account a: this.getAccounts()) {
            if (a.getCard().getCardNumber().equals(cardNumber) && a.getCard().getPinCode().equals(pinCode)) {
                successfullyLogged = true;
                account = a;
                break;
            }
        }
        if (successfullyLogged) {
            System.out.println("You have successfully logged in!");
            System.out.println();


            for (;;) {
                this.showAccountMenu();
                switch (scanner.nextInt()) {
                    case 1:
                        account.showBalance();
                        break;
                    case 2:
                        account.logOut();
                        return 1;
                    case 0:
                        return 0;
                }
            }

        } else {
            System.out.println();
            System.out.println("Wrong card number or PIN!");
            System.out.println();
        }
    return 1;
    }

    public void showStartMenu ()  {
        System.out.println("1. Create an account");
        System.out.println("2. Log into account");
        System.out.println("0. Exit");
        System.out.println();
    }

    public void showAccountMenu ()  {
        System.out.println("1. Balance");
        System.out.println("2. Log out");
        System.out.println("0. Exit");
        System.out.println();
    }
}
