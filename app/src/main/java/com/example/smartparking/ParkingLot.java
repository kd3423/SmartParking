package com.example.smartparking;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.UUID;
import java.util.Random;

public class ParkingLot {
    Hashtable<Boolean,List<Integer>> spotStatus = new Hashtable<>();
    Hashtable<Integer, List<String>> spotDirections = new Hashtable<>();
    String lotID;
    Location pLotLocation;

    public ParkingLot(int size, double lat, double lon, String placeName){
        this.lotID = UUID.randomUUID().toString();
        this.populateLot(size);
        this.pLotLocation = new Location(lat,lon,placeName);
    }

    private void populateLot(int size){
        // add a total of size parking spots (id starts from 1)
        List<Integer> spots = new ArrayList<>();
        for(int i=1;i<=size; i++){
            spots.add(i);
            // the directions are as follows:
            // the spotId mod 20 tells the floor at which the spot is placed. This means that there can only be 20 spots per floor.
            int ups = (i%20) + 1;
            List<String> directions = new ArrayList<>();
            directions.add("Cross the entry barrier and maintain a steady speed of 10 mph");
            directions.add("You are assigned a parking spot: "+Integer.toString(i));
            directions.add("its on the floor: "+ Integer.toString(ups));

            if(i%2 ==0){
                directions.add("Drive to Right side of the parking level");
                directions.add("Follow the Blue arrows on the surface");
                directions.add("Your spot is "+Integer.toString(i%ups)+ "from your current position on left side");
            }
            else{
                directions.add("Follow the Red arrows on the surface");
                directions.add("Your spot is "+Integer.toString(i%ups)+ "from your current position on right side");
            }
            spotDirections.put(i,directions);
        }
        spotStatus.put(true,spots);
        spotStatus.put(false,new ArrayList<Integer>());
    }

    public void freeSpot(int spotId, Users u1){
        List<Integer> ava = spotStatus.get(true);
        ava.add(spotId);
        spotStatus.put(true, ava);

        List<Integer> booked = spotStatus.get(false);
        booked.remove(spotId);

        u1.freeBook();
    }

    public int bookSpot(){
        int spotId = 0; // default 0, means no spot assigned
        if(this.getAvailableSpots() > 0) {
            // get a random spot
            List<Integer> items = spotStatus.get(true);
            spotId = items.get(new Random().nextInt(items.size()));
            items.remove(spotId);
            spotStatus.put(true, items);

            // update spot to be booked
            List<Integer> booked = spotStatus.get(false);
            booked.add(spotId);
        }

        return spotId;
    }

    public List<String> getDirections(int spotId){
        return this.spotDirections.get(spotId);
    }

    public int getAvailableSpots(){
        return this.spotStatus.get(true).size();
    }

    public int getBookedSpots(){
        return this.spotStatus.get(false).size();
    }

    public Location getpLotLocation(){
        return this.pLotLocation;
    }

    public String getLotID(){
        return this.lotID;
    }

}
