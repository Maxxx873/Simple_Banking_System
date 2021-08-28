package banking;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        Bank bank = new Bank();

        Scanner scanner = new Scanner(System.in);
        for (;;) {
            bank.showStartMenu();
            switch (scanner.nextInt()) {
                case 1:
                    Account account = new Account();
                    if (bank.getAccounts().size() == 0) {
                        bank.addAccount(account);
                    } else {
                        while (!bank.addAccount(account)) {
                            bank.addAccount(account);
                        }
                    }
                    System.out.println("Your card has been created");
                    account.print();
                    System.out.println();

                    break;
                case 2:
                    System.out.println("Enter your card number:");

                    for (Account account1: bank.getAccounts()) {
                        if (account1.getCardNumber().equals(scanner.nextLine())) {
                            System.out.println("You have successfully logged in!");
                        }
                    }
                    break;
                case 0:
                    System.out.println(bank.getAccounts());
                    return;

            }

        }

    }
}