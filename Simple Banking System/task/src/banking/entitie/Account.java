package banking.entitie;

import java.math.BigDecimal;
import java.util.Random;

public class Account {
    final String BIN = "400000";
    private String accountIdentifier;
    private int checksum;
    private String cardNumber;
    private String pinCode;
    private BigDecimal balance;

    public BigDecimal getBalance() {
        return balance;
    }

    public void showBalance () {
        System.out.printf("Balance: %s", this.balance.toString());
        System.out.println();
    }

    public void logOut () {
        System.out.println("You have successfully logged out!");
        System.out.println();
    }

    public void setBalance( BigDecimal balance ) {
        this.balance = balance;
    }

    public String getAccountIdentifier() {
        return accountIdentifier;
    }

    public void setAccountIdentifier( String accountIdentifier ) {
        this.accountIdentifier = accountIdentifier;
    }

    public int getChecksum() {
        return checksum;
    }

    public void setChecksum( int checksum ) {
        this.checksum = checksum;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber( String cardNumber ) {
        this.cardNumber = cardNumber;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode( String pinCode ) {
        this.pinCode = pinCode;
    }

    public Account() {
        int upperPin = 9999;
        int lowerPin = 1000;
        int upperId = 999999999;
        int lowerId = 100000000;
        Random random = new Random();
        this.pinCode = String.valueOf(random.nextInt(upperPin - lowerPin + 1) + lowerPin);
        this.accountIdentifier = String.valueOf(random.nextInt(upperId - lowerId + 1) + lowerId);
        this.checksum =random.nextInt(10);
        this.cardNumber = this.BIN + this.accountIdentifier + this.checksum;
        this.balance = BigDecimal.valueOf(0);

    }

    public void print() {
        System.out.println("Your card number:");
        System.out.println(this.cardNumber);
        System.out.println("Your card PIN:");
        System.out.println(this.pinCode);
    }

}
