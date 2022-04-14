package com.example.smartparking;

public class ChargePayment {
    Users u1;
    double bill;
    boolean status;

    public ChargePayment(Users u1, double bill){
        this.u1 = u1;
        this.bill = bill;
    }

    public void processPayment(){
        CardInfo c1 = u1.getCard();
        this.status = c1.authenticatePayment();
        if(status) {
            u1.addBill("Bill processed successfully for $" + Double.toString(bill));
        }
        else{
            u1.addBill("Unable to process for $" + Double.toString(bill)+", update card info!");
        }
    }

    public boolean getPaymentStatus(){
        return status;
    }
}
