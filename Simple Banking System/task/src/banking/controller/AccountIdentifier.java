package banking.controller;

import java.util.Random;

public class AccountIdentifier {

    public static String generate () {
        String pinCode = new String();
        Random random = new Random();
        for (int i = 0; i < 9; i++) {
            pinCode += String.valueOf(random.nextInt(10));
        }
        return pinCode;
    }

}
