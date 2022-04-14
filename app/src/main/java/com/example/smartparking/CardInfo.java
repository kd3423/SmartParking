package com.example.smartparking;

public class CardInfo {
    int cardNumber;
    int securityCode;
    String nameOnCard;
    int postalCode;
    boolean check=false;

    //  constructor
    public CardInfo(int cardNumber, int securityCode, String nameOnCard, int postalCode){
        this.cardNumber = cardNumber;
        this.securityCode = securityCode;
        this.nameOnCard = nameOnCard;
        this.postalCode = postalCode;
    }

    public boolean authenticatePayment(CardInfo card){
        return this.check;
    }
}
