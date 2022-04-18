package com.example.smartparking;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CostEstimator {
    Date startTime;
    Date currenTime;
    boolean exitCheck;
    double billAmount;
    double pricePerHour1 = 10;
    double pricePerHour2 = 5;
    int mins =0;

    public CostEstimator(String startTime, boolean exitCheck){
        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        try {
            this.startTime = formatter.parse(startTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.currenTime = new Date();
        this.exitCheck = exitCheck;
        this.billAmount = 0.0;
        this.estimateBill();
    }

    private void estimateBill() {
        if(this.exitCheck){
            double timeDiff = (double) (this.currenTime.getTime() - this.startTime.getTime());
            timeDiff = (timeDiff / (1000*60*60)) % 24;
            this.mins = (int) Math.ceil(timeDiff*60);
            this.billAmount = timeDiff * pricePerHour1;
            if(timeDiff>24) {
                this.billAmount = (timeDiff - 24) * pricePerHour2;
            }
        }
    }

    public double getBillAmount(){
        return this.billAmount;
    }

    public int getMins(){return this.mins;}


}
