package banking;


import banking.controller.SQLiteDatabase;
import banking.controller.Bank;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (args[0].equals("-fileName")) {

            SQLiteDatabase sqLiteDatabase = new SQLiteDatabase(args[1]);
            sqLiteDatabase.connectionToDatabase();
            Bank bank = new Bank(sqLiteDatabase);
            Scanner scanner = new Scanner(System.in);

            for (;;) {
                bank.showStartMenu();
                int menu = scanner.nextInt();
                switch (menu) {
                    case 1:
                        bank.newAccount();
                        break;
                    case 2:
                        if (bank.loggingIn() == 0) {
                            System.out.println("Bye!");
                            sqLiteDatabase.closeConnection();
                            return;
                        }
                        break;
                    case 0:
                        System.out.println("Bye!");
                        sqLiteDatabase.closeConnection();
                        return;

                }

            }
        }
    }
}