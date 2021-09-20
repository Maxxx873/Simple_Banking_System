package banking.controller;

public class Checksum {

    public static String generate (String cardNumber) {
        int checksum = 0;

        for (int i = cardNumber.length() - 1 ;i >= 0; i--) {
            if (i % 2 > 0 ) {
                checksum +=Character.getNumericValue(cardNumber.charAt(i));
            } else {
                int multiply = Character.getNumericValue(cardNumber.charAt(i)) * 2;
                if (multiply > 9) {
                  checksum += multiply - 9;
                } else {
                    checksum += multiply;

                }
            }
        }
        checksum = (10 - checksum % 10) % 10;
        return String.valueOf(checksum);
    }
}
