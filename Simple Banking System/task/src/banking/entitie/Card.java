package banking.entitie;

import banking.controller.AccountIdentifier;
import banking.controller.Checksum;
import banking.controller.PinCode;

public class Card {
    final String BIN = "400000";
    private String checksum;
    private String cardNumber;
    private String pinCode;
    private String accountIdentifier;
    private int balance;


    public Card() {
        this.pinCode = PinCode.generate();
        this.accountIdentifier = AccountIdentifier.generate();
        this.checksum = Checksum.generate(this.BIN + this.accountIdentifier);
        this.cardNumber = this.BIN + this.accountIdentifier + this.checksum;
        this.balance = 0;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance( int balance ) {
        this.balance = balance;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getPinCode() {
        return pinCode;
    }



}
