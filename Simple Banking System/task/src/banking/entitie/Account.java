package banking.entitie;

import java.util.Scanner;

public class Account {

    private Card card;

    public void showBalance () {
        System.out.printf("Balance: %s", this.card.getBalance());
        System.out.println();
    }

    public void addIncome () {
        System.out.println("Enter income:");
        Scanner scanner = new Scanner(System.in);
        this.card.setBalance(this.card.getBalance() + scanner.nextInt());
        System.out.println("Income was added!");
        System.out.println();
    }

    public void logOut () {
        System.out.println("You have successfully logged out!");
        System.out.println();
    }

    public Card getCard() {
        return card;
    }

    public void setCard( Card card ) {
        this.card = card;
    }

    public Account() {
        this.card = new Card();
    }

    public void print() {
        System.out.println("Your card number:");
        System.out.println(this.card.getCardNumber());
        System.out.println("Your card PIN:");
        System.out.println(this.card.getPinCode());
    }

}
