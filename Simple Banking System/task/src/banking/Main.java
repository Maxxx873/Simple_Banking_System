package banking;

import banking.entitie.Account;
import banking.entitie.Bank;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Bank bank = new Bank();
        Scanner scanner = new Scanner(System.in);

        for (;;) {
            bank.showStartMenu();
            int menu = scanner.nextInt();
            switch (menu) {
                case 1:
                    bank.newAccount();
                    break;
                case 2:
                    bank.loggingIn();
                    break;
                case 0:
                    System.out.println("Bye!");
                    return;

            }

        }

    }
}