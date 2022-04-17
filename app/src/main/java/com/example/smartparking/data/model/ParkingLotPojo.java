package com.example.smartparking.data.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ParkingLotPojo{

	@SerializedName("_id")
	private String _id;

	@SerializedName("spots")
	private List<SpotsItem> spots;

	@SerializedName("lotId")
	private String lotId;

	@SerializedName("location")
	private Location location;

	public void setSpots(List<SpotsItem> spots){
		this.spots = spots;
	}

	public List<SpotsItem> getSpots(){
		return spots;
	}

	public void setLotId(String lotId){
		this.lotId = lotId;
	}

	public String getLotId(){
		return lotId;
	}

	public void setLocation(Location location){
		this.location = location;
	}

	public Location getLocation(){
		return location;
	}

	@Override
 	public String toString(){
		return 
			"ParkingLotPojo{" + 
			"spots = '" + spots + '\'' + 
			",lotId = '" + lotId + '\'' + 
			",location = '" + location + '\'' + 
			"}";
		}
}