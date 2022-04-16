package com.example.smartparking.data.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class SpotsItem{

	@SerializedName("directions")
	private List<String> directions;

	@SerializedName("id")
	private int id;

	@SerializedName("status")
	private boolean status;

	public void setDirections(List<String> directions){
		this.directions = directions;
	}

	public List<String> getDirections(){
		return directions;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"SpotsItem{" + 
			"directions = '" + directions + '\'' + 
			",id = '" + id + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}