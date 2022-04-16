package com.example.smartparking.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GetUserResponse implements Serializable {

	@SerializedName("address")
	private String address;

	@SerializedName("createdDate")
	private String createdDate;

	@SerializedName("bookedOn")
	private String bookedOn;

	@SerializedName("spotId")
	private int spotId;

	@SerializedName("__v")
	private int V;

	@SerializedName("name")
	private String name;

	@SerializedName("parkingLotId")
	private String parkingLotId;

	@SerializedName("_id")
	private String id;

	@SerializedName("history")
	private String history;

	@SerializedName("age")
	private int age;

	@SerializedName("card")
	private Card card;

	@SerializedName("bookCheck")
	private boolean bookCheck;

	public void setAddress(String address){
		this.address = address;
	}

	public String getAddress(){
		return address;
	}

	public void setCreatedDate(String createdDate){
		this.createdDate = createdDate;
	}

	public String getCreatedDate(){
		return createdDate;
	}

	public void setBookedOn(String bookedOn){
		this.bookedOn = bookedOn;
	}

	public String getBookedOn(){
		return bookedOn;
	}

	public void setSpotId(int spotId){
		this.spotId = spotId;
	}

	public int getSpotId(){
		return spotId;
	}

	public void setV(int V){
		this.V = V;
	}

	public int getV(){
		return V;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setParkingLotId(String parkingLotId){
		this.parkingLotId = parkingLotId;
	}

	public String getParkingLotId(){
		return parkingLotId;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setHistory(String history){
		this.history = history;
	}

	public String getHistory(){
		return history;
	}

	public void setAge(int age){
		this.age = age;
	}

	public int getAge(){
		return age;
	}

	public void setCard(Card card){
		this.card = card;
	}

	public Card getCard(){
		return card;
	}

	public void setBookCheck(boolean bookCheck){
		this.bookCheck = bookCheck;
	}

	public boolean getBookCheck(){
		return bookCheck;
	}

	@Override
 	public String toString(){
		return 
			"GetUserResponseItem{" + 
			"address = '" + address + '\'' + 
			",createdDate = '" + createdDate + '\'' + 
			",bookedOn = '" + bookedOn + '\'' + 
			",spotId = '" + spotId + '\'' + 
			",__v = '" + V + '\'' + 
			",name = '" + name + '\'' + 
			",parkingLotId = '" + parkingLotId + '\'' + 
			",_id = '" + id + '\'' + 
			",history = '" + history + '\'' + 
			",age = '" + age + '\'' + 
			",card = '" + card + '\'' +
					",bookCheck = '" + bookCheck + '\'' +
					"}";
		}
}