package banking.entitie;

import java.math.BigDecimal;
import java.util.Random;

public class Account {
    private String accountIdentifier;
    private BigDecimal balance;
    private Card card;

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

    public Card getCard() {
        return card;
    }

    public void setCard( Card card ) {
        this.card = card;
    }

    public Account() {

        int upperId = 999999999;
        int lowerId = 100000000;
        Random random = new Random();
        //this.accountIdentifier = String.valueOf(random.nextInt(upperId - lowerId + 1) + lowerId);
        this.balance = BigDecimal.valueOf(0);
        this.card = new Card();

    }

    public void print() {
        System.out.println("Your card number:");
        System.out.println(this.card.getCardNumber());
        System.out.println("Your card PIN:");
        System.out.println(this.card.getPinCode());
    }

}
