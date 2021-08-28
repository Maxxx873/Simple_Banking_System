package banking;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        Bank bank = new Bank();

        Scanner scanner = new Scanner(System.in);
        for (;;) {
            System.out.println("1. Create an account");
            System.out.println("2. Log into account");
            System.out.println("0. Exit");
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
                    scanner.nextLine();
                    break;
                case 0:
                    System.out.println(bank.getAccounts());
                    return;

            }

        }

    }
}