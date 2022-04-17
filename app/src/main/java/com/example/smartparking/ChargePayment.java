package com.example.smartparking;

import com.example.smartparking.data.model.Card;
import com.example.smartparking.data.model.GetUserResponse;

public class ChargePayment {
    GetUserResponse u1;
    double bill;
    boolean status;

    public ChargePayment(GetUserResponse u1, double bill){
        this.u1 = u1;
        this.bill = bill;
        processPayment();
    }

    public void processPayment(){
        Card c1 = u1.getCard();
        this.status = c1.authenticatePayment();
        if(status) {
            if (u1.getHistory() == null) {
                u1.setHistory("Bill paid at parkingLot: " + u1.getParkingLotId() + " for $" + Double.toString(bill)+"\n");
            }
            else{
                u1.setHistory(u1.getHistory()+"Bill paid at parkingLot: " + u1.getParkingLotId() + " for $" + Double.toString(bill)+"\n");
            }
        }
    }

    public boolean getPaymentStatus(){
        return status;
    }
}
