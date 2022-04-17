package com.example.smartparking.data.model;

import com.google.gson.annotations.SerializedName;

public class GetParkingByLotIdRequest{

	@SerializedName("lotId")
	private String lotId;

	public void setLotId(String lotId){
		this.lotId = lotId;
	}

	public String getLotId(){
		return lotId;
	}

	public GetParkingByLotIdRequest(String lotId) {
		this.lotId = lotId;
	}

	@Override
 	public String toString(){
		return 
			"GetParkingByLotIdRequest{" + 
			"lotId = '" + lotId + '\'' + 
			"}";
		}
}