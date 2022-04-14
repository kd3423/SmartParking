package com.example.smartparking;

import java.util.Date;

public class CostEstimator {
    Date startTime;
    Date currenTime;
    boolean exitCheck;
    double billAmount;
    double pricePerHour1 = 10;
    double pricePerHour2 = 5;

    public CostEstimator(Date startTime, boolean exitCheck){
        this.startTime = startTime;
        this.currenTime = new Date();
        this.exitCheck = exitCheck;
        this.billAmount = 0.0;
        this.estimateBill();
    }

    private void estimateBill() {
        if(this.exitCheck){
            double timeDiff = (double) (this.currenTime.getTime() - this.startTime.getTime());
            timeDiff = (timeDiff/1000)%60; //time difference in seconds.
            timeDiff = Math.ceil(timeDiff/3600.0);
            this.billAmount = timeDiff * pricePerHour1;
            if(timeDiff>24) {
                this.billAmount = (timeDiff - 24) * pricePerHour2;
            }
        }
    }

    public double getBillAmount(){
        return this.billAmount;
    }


}
