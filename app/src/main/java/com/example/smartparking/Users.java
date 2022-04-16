package com.example.smartparking;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Users {
    String name;
    String addr;
    Date createdOn;
    CardInfo card;
    String userId;
    int age;

    int spotId;
    String plot;
    Date bookedOn;
    boolean bookCheck;
    List<String> pastBills = new ArrayList<>();


    //  constructor
    public Users(String name, String addr, int age, CardInfo card){
        this.name = name;
        this.addr = addr;
        this.age = age;
        this.card  = card;
        this.createdOn = new Date();
        this.userId = generateId();
        this.bookCheck = false;
    }

    private String generateId(){
        String newId = UUID.randomUUID().toString();
        newId = newId.replace("-", "");
        return newId;
    }

    public Date getCreatedOn(){
        return this.createdOn;
    }

    public CardInfo getCard(){
        return this.card;
    }

    public String getUserId(){
        return this.userId;
    }

    public boolean updateBooking(String plot, int spotId, Date bookedOn){
        if(!this.bookCheck){
            this.plot = plot;
            this.spotId = spotId;
            this.bookedOn = bookedOn;
            this.bookCheck = true;
        }
        return this.bookCheck;

    }

    public void freeBook(){
        this.bookCheck = false;
    }

    public boolean getBookCheck(){
        return this.bookCheck;
    }

    public List<String> getPastBills(){
        return this.getPastBills();
    }

    public void addBill(String bill){
        this.pastBills.add(bill);
    }

}
