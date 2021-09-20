package banking.entitie;

import banking.controller.AccountIdentifier;
import banking.controller.Checksum;
import banking.controller.PinCode;

import java.math.BigDecimal;
import java.util.Random;

public class Card {
    final String BIN = "400000";
    private String checksum;
    private String cardNumber;
    private String pinCode;
    private String accountIdentifier;
    private BigDecimal balance;


    public Card() {
        Random random = new Random();
        this.pinCode = PinCode.generate();
        this.accountIdentifier = AccountIdentifier.generate();
        this.checksum = Checksum.generate(this.BIN + this.accountIdentifier);
        this.cardNumber = this.BIN + this.accountIdentifier + this.checksum;
        this.balance = BigDecimal.valueOf(0);
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getPinCode() {
        return pinCode;
    }



}
