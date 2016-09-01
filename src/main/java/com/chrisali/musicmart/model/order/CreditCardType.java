package com.chrisali.musicmart.model.order;

public enum CreditCardType {
	VISA				("Visa"),
	DISCOVER		 	("Discover"),
	MASTER_CARD 		("Master Card"),
	AMERICAN_EXPRESS 	("American Express"),
	MAESTRO				("Maestro"),
	JCB					("Japan Credit Bureau");
	
	private String creditCardType;
	
	private CreditCardType (String creditCardType) {this.creditCardType = creditCardType;}

	public String getCreditCardType() {return creditCardType;}
}
