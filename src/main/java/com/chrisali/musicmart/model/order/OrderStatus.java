package com.chrisali.musicmart.model.order;

public enum OrderStatus {
	PAYMENT_RECEIVED	("Payment Received"),
	SHIPPED			 	("Shipped"),
	IN_TRANSIT 			("In Transit"),
	DELIVERED 			("Delivered"),
	CANCELLED			("Cancelled");
	
	private String orderStatus;
	
	private OrderStatus (String orderStatus) {this.orderStatus = orderStatus;}

	public String getOrderStatus() {return orderStatus;}
}
