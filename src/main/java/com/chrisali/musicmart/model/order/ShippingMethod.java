package com.chrisali.musicmart.model.order;

public enum ShippingMethod {
	USPS_FIRST_CLASS_INTL  	("USPS First Class International"),
	USPS_FIRST_CLASS 		("USPS First Class"),
	USPS_PRIORITY 			("USPS Priority"),
	UPS_NEXT_DAY_AIR 		("UPS Next Day Air"),
	UPS_TWO_DAY_AIR 		("UPS Two Day Air"),
	UPS_GROUND 				("UPS Ground"),
	FEDEX_SAME_DAY 			("FedEx Same Day"),
	FEDEX_TWO_DAY 			("FedEx Two Day"),
	FEDEX_GROUND 			("FedEx Ground"),
	DHL_SAME_DAY 			("DHL Same Day"),
	DHL_EXPRESS 			("DHL Express");
	
	private String shippingMethod;
	
	private ShippingMethod (String shippingMethod) {this.shippingMethod = shippingMethod;}

	public String getShippingMethod() {return shippingMethod;}
}
