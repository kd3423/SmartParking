package com.example.smartparking;

public class CardInfo {
    int cardNumber;
    int securityCode;
    String nameOnCard;
    int postalCode;
    boolean validateInfo=false;

    //  constructor
    public CardInfo(int cardNumber, int securityCode, String nameOnCard, int postalCode){
        boolean c1 = false,c2 = false;

        if((int)(Math.log10(cardNumber)+1) == 16){
            c1 = true;
        }

        if((int)(Math.log10(securityCode)+1) == 3){
            c2 = true;
        }
        if(c1 && c2){
            this.cardNumber = cardNumber;
            this.securityCode = securityCode;
            this.nameOnCard = nameOnCard;
            this.postalCode = postalCode;
            this.validateInfo = true;
        }

    }

    public boolean authenticatePayment(){
        return this.validateInfo;
    }
}
