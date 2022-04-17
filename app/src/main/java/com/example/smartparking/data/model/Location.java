package com.example.smartparking.data.model;

import com.google.gson.annotations.SerializedName;

public class Location{

	@SerializedName("address")
	private String address;

	@SerializedName("lat")
	private double lat;

	@SerializedName("long")
	private double lon;

	public void setAddress(String address){
		this.address = address;
	}

	public String getAddress(){
		return address;
	}

	public void setLat(double lat){
		this.lat = lat;
	}

	public double getLat(){
		return lat;
	}

	public void setJsonMemberLong(double jsonMemberLong){
		this.lon = jsonMemberLong;
	}

	public double getJsonMemberLong(){
		return lon;
	}

	public Location(String address, double lat, double lon) {
		this.address = address;
		this.lat = lat;
		this.lon = lon;
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

	@Override
 	public String toString(){
		return 
			"Location{" + 
			"address = '" + address + '\'' + 
			",lat = '" + lat + '\'' + 
			",long = '" + lon + '\'' +
			"}";
		}
}