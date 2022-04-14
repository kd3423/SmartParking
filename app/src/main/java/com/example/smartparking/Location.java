package com.example.smartparking;

public class Location {
    double lat;
    double lon;
    String place;

    //constructor
    public Location(double lat, double lon, String place){
        this.lat = lat;
        this.lon = lon;
        this.place = place;
    }

    public double getDistance(Location obj1, Location obj2){
        int R = 6371;
        double theta = obj1.lon - obj2.lon;
        double dist = Math.sin(deg2rad(obj1.lat)) * Math.sin(deg2rad(obj2.lat)) + Math.cos(deg2rad(obj1.lat)) * Math.cos(deg2rad(obj2.lat)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        return dist; // in miles
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::  This method converts decimal degrees to radians             :*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::  This method converts radians to decimal degrees             :*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

}
