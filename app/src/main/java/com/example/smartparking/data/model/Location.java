package com.example.smartparking.data.model;

import com.google.gson.annotations.SerializedName;

public class Location{

	@SerializedName("address")
	private String address;

	@SerializedName("lat")
	private double lat;

	@SerializedName("long")
	private double jsonMemberLong;

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
		this.jsonMemberLong = jsonMemberLong;
	}

	public double getJsonMemberLong(){
		return jsonMemberLong;
	}

	@Override
 	public String toString(){
		return 
			"Location{" + 
			"address = '" + address + '\'' + 
			",lat = '" + lat + '\'' + 
			",long = '" + jsonMemberLong + '\'' + 
			"}";
		}
}