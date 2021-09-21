package banking.controller;
import banking.entitie.Account;

import java.util.List;
import java.util.Scanner;

public class Bank {

    List<Account> accounts;
    SQLiteDatabase sqLiteDatabase;
    public List<Account> getAccounts() {
        return accounts;
    }

    public Bank(SQLiteDatabase sqLiteDatabase) {
        this.accounts = sqLiteDatabase.selectAllAccounts();
        this.sqLiteDatabase = sqLiteDatabase;
    }

    public void newAccount() {

        Account account = new Account();
        while (this.accounts.indexOf(account) > 0) {
            account = new Account();
        }
        this.accounts.add(account);
        this.sqLiteDatabase.addToDatabase(account.getCard());
        System.out.println("Your card has been created");
        account.print();
        System.out.println();

    }

    public void updateBalance (Account account) {
        this.sqLiteDatabase.updateBalance(account.getCard());
    }

    public void transfer (Account accountFrom) {
        System.out.println("Transfer");
        System.out.println("Enter card number:");
        Scanner scanner = new Scanner(System.in);
        String cardTo = scanner.nextLine();

        if (!String.valueOf(cardTo.charAt(cardTo.length() - 1))
                .equals(Checksum.generate(cardTo.substring(0,cardTo.length() - 1))) )  {
            System.out.println("Probably you made a mistake in the card number. Please try again!");
            System.out.println();
            return;
        }

        Account accountTo = null;

        for (Account a: this.getAccounts()) {
            if (a.getCard().getCardNumber().equals(cardTo)) {
                accountTo = a;
                break;
            }
        }

        if (accountTo == null) {
            System.out.println("Such a card does not exist.");
            System.out.println();
            return;
        }

        if (accountFrom.getCard().getCardNumber().equals(accountTo.getCard().getCardNumber())) {
            System.out.println("You can't transfer money to the same account!");
            System.out.println();
            return;
        }

        System.out.println("Enter how much money you want to transfer:");
        int money = scanner.nextInt();
        if (money > accountFrom.getCard().getBalance()) {
            System.out.println("Not enough money!");
            System.out.println();
            return;
        }

        accountFrom.getCard().setBalance(accountFrom.getCard().getBalance() - money);
        accountTo.getCard().setBalance(accountTo.getCard().getBalance() + money);
        sqLiteDatabase.transfer(accountFrom.getCard(), accountTo.getCard());
        System.out.println("Success!");
        System.out.println();

    }

    public void deleteAccount (Account account) {
        sqLiteDatabase.deleteCard(account.getCard());
        this.accounts.remove(account);
        System.out.println("The account has been closed!");
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
                        account.addIncome();
                        updateBalance(account);
                        break;
                    case 3:
                        transfer(account);
                        break;
                    case 4:
                        deleteAccount(account);
                        break;
                    case 5:
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
        System.out.println("2. Add income");
        System.out.println("3. Do transfer");
        System.out.println("4. Close account");
        System.out.println("5. Log out");
        System.out.println("0. Exit");
        System.out.println();
    }
}
