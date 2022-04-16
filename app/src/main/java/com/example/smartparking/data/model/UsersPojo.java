package com.example.smartparking.data.model;

import com.google.gson.annotations.SerializedName;

public class UsersPojo{

	@SerializedName("address")
	private String address;

	@SerializedName("createdDate")
	private String createdDate;

	@SerializedName("bookedOn")
	private String bookedOn;

	@SerializedName("spotId")
	private int spotId;

	@SerializedName("name")
	private String name;

	@SerializedName("parkingLotId")
	private String parkingLotId;

	@SerializedName("age")
	private int age;

	@SerializedName("card")
	private Card card;

	@SerializedName("bookCheck")
	private boolean bookCheck;

	@SerializedName("history")
	private String history;

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

	public boolean isBookCheck(){
		return bookCheck;
	}

	public void setHistory(String history){
		this.history = history;
	}

	public String getHistory(){
		return history;
	}



	public UsersPojo(String nn, String addr, String cDate, int aa, Card cc, int spI, String pI, String bkOn, boolean chk, String his){
		setName(nn);
		setAddress(addr);
		setCreatedDate(cDate);
		setAge(aa);
		setCard(cc);
		setSpotId(spI);
		setParkingLotId(pI);
		setBookedOn(bkOn);
		setBookCheck(chk);
		setHistory(his);
	}

	@Override
 	public String toString(){
		return 
			"UsersPojo{" + 
			"address = '" + address + '\'' + 
			",createdDate = '" + createdDate + '\'' + 
			",bookedOn = '" + bookedOn + '\'' + 
			",spotId = '" + spotId + '\'' + 
			",name = '" + name + '\'' + 
			",parkingLotId = '" + parkingLotId + '\'' + 
			",age = '" + age + '\'' + 
			",card = '" + card + '\'' + 
			",bookCheck = '" + bookCheck + '\'' +
					",history = '" + history + '\'' +
			"}";
		}
}