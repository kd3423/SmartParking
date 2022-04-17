package com.example.smartparking.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Card implements Serializable {

	@SerializedName("nameOnCard")
	private String nameOnCard;

	@SerializedName("postalCode")
	private int postalCode;

	@SerializedName("securityCode")
	private int securityCode;

	@SerializedName("validateInfo")
	private boolean validateInfo;

	@SerializedName("_id")
	private String id;

	@SerializedName("cardNumber")
	private long cardNumber;

	public void setNameOnCard(String nameOnCard){
		this.nameOnCard = nameOnCard;
	}

	public String getNameOnCard(){
		return nameOnCard;
	}

	public void setPostalCode(int postalCode){
		this.postalCode = postalCode;
	}

	public int getPostalCode(){
		return postalCode;
	}

	public void setSecurityCode(int securityCode){
		this.securityCode = securityCode;
	}

	public int getSecurityCode(){
		return securityCode;
	}

	public void setValidateInfo(boolean validateInfo){
		this.validateInfo = validateInfo;
	}

	public boolean isValidateInfo(){
		return validateInfo;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setCardNumber(long cardNumber){
		this.cardNumber = cardNumber;
	}

	public long getCardNumber(){
		return cardNumber;
	}

	@Override
 	public String toString(){
		return 
			"Card{" + 
			"nameOnCard = '" + nameOnCard + '\'' + 
			",postalCode = '" + postalCode + '\'' + 
			",securityCode = '" + securityCode + '\'' + 
			",validateInfo = '" + validateInfo + '\'' + 
			",_id = '" + id + '\'' + 
			",cardNumber = '" + cardNumber + '\'' + 
			"}";
		}

	public Card(long cardNumber, int securityCode,String nameOnCard, int postalCode) {
		boolean c1 = false,c2 = false;

		if((int)(Math.log10(cardNumber)+1) == 16){
			c1 = true;
		}

		if((int)(Math.log10(securityCode)+1) == 3){
			c2 = true;
		}
		if(c1 && c2){
			this.nameOnCard = nameOnCard;
			this.postalCode = postalCode;
			this.securityCode = securityCode;
			this.cardNumber = cardNumber;
			this.validateInfo = true;
		}
	}

	public boolean authenticatePayment(){
		return this.validateInfo;
	}
}