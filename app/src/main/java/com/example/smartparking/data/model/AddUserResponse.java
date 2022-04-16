package com.example.smartparking.data.model;

import com.google.gson.annotations.SerializedName;

public class AddUserResponse{

	@SerializedName("success")
	private String success;

	@SerializedName("message")
	private String message;

	public void setSuccess(String success){
		this.success = success;
	}

	public String getSuccess(){
		return success;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	@Override
 	public String toString(){
		return 
			"AddUserResponse{" + 
			"success = '" + success + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}