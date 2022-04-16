package com.example.smartparking.data.model;

import com.google.gson.annotations.SerializedName;

public class GetUserByNameRequest{

	@SerializedName("name")
	private String name;

	public GetUserByNameRequest(String name) {
		this.name = name;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	@Override
 	public String toString(){
		return 
			"GetUserByNameRequest{" + 
			"name = '" + name + '\'' + 
			"}";
		}
}