package com.chrisali.musicmart.model.order;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.chrisali.musicmart.model.Country;
import com.chrisali.musicmart.model.product.Product;

@Entity
@Table(name = "orders")
public class Order implements Serializable {

	private static final long serialVersionUID = 8974632863155985270L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotBlank
	@Column(name = "billing_name")
	@Size(min = 2, max = 45)
	private String billingName;
	
	@NotBlank
	@Column(name = "billing_address_1")
	@Size(min = 2, max = 45)
	private String billingAddress1;
	
	@Column(name = "billing_address_2")
	private String billingAddress2;
	
	@NotBlank
	@Column(name = "billing_city")
	@Size(min = 2, max = 45)
	private String billingCity;
	
	@NotBlank
	@Column(name = "billing_state_province")
	@Size(min = 2, max = 45)
	private String billingStateProvince;
	
	@NotBlank
	@Column(name = "billing_postal_code")
	@Size(min = 2, max = 45)
	private String billingPostalCode;
	
	@NotBlank
	@Column(name = "billing_phone_number")
	@Size(min = 2, max = 45)
	private String billingPhoneNumber;
	
	@NotBlank
	@Column(name = "billing_email")
	@Email
	private String billingEmail;
	
	@NotBlank
	@Column(name = "shipping_name")
	@Size(min = 2, max = 45)
	private String shippingName;
	
	@NotBlank
	@Column(name = "shipping_address_1")
	@Size(min = 2, max = 45)
	private String shippingAddress1;
	
	@Column(name = "shipping_address_2")
	private String shippingAddress2;
	
	@NotBlank
	@Column(name = "shipping_city")
	@Size(min = 2, max = 45)
	private String shippingCity;
	
	@NotBlank
	@Column(name = "shipping_state_province")
	@Size(min = 2, max = 45)
	private String shippingStateProvince;
	
	@NotBlank
	@Column(name = "shipping_postal_code")
	@Size(min = 2, max = 45)
	private String shippingPostalCode;
	
	@NotBlank
	@Column(name = "shipping_phone_number")
	@Size(min = 2, max = 45)
	private String shippingPhoneNumber;
	
	@NotBlank
	@Column(name = "payment_method")
	private String paymentMethod;
	
	@NotBlank
	@Column(name = "credit_card_type")
	private CreditCardType creditCardType;
	
	@NotBlank
	@Size(min = 2, max = 100)
	@Column(name = "credit_card_name")
	private String creditCardName;
	
	@NotBlank
	@Size(min = 2, max = 45)
	@Column(name = "credit_card_number")
	private String creditCardNumber;
	
	@NotBlank
	@Size(min = 2, max = 20)
	@Column(name = "credit_card_expiration")
	private String creditCardExpiration;
	
	@NotBlank
	@Column(name = "date_purchased")
	private Date datePurchased;
	
	@Column(name = "last_modified")
	private Date lastModified;
	
	@NotBlank
	@Column(name = "shipping_cost")
	private float shippingCost;
	
	@NotBlank
	@Column(name = "shipping_method")
	private ShippingMethod shippingMethod;
	
	@Column(name = "order_status")
	private OrderStatus orderStatus;
	
	@Column(name = "order_completed")
	private Date orderCompleted;
	
	@Size(min = 0, max = 200)
	@Column(name = "comments")
	private String comments;
	
	@ManyToMany
	private List<Country> countries;
	
//	@ManyToMany(mappedBy = "products")
//	private List<Product> products;

	public Order() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBillingName() {
		return billingName;
	}

	public void setBillingName(String billingName) {
		this.billingName = billingName;
	}

	public String getBillingAddress1() {
		return billingAddress1;
	}

	public void setBillingAddress1(String billingAddress1) {
		this.billingAddress1 = billingAddress1;
	}

	public String getBillingAddress2() {
		return billingAddress2;
	}

	public void setBillingAddress2(String billingAddress2) {
		this.billingAddress2 = billingAddress2;
	}

	public String getBillingCity() {
		return billingCity;
	}

	public void setBillingCity(String billingCity) {
		this.billingCity = billingCity;
	}

	public String getBillingStateProvince() {
		return billingStateProvince;
	}

	public void setBillingStateProvince(String billingStateProvince) {
		this.billingStateProvince = billingStateProvince;
	}

	public String getBillingPostalCode() {
		return billingPostalCode;
	}

	public void setBillingPostalCode(String billingPostalCode) {
		this.billingPostalCode = billingPostalCode;
	}

	public String getBillingPhoneNumber() {
		return billingPhoneNumber;
	}

	public void setBillingPhoneNumber(String billingPhoneNumber) {
		this.billingPhoneNumber = billingPhoneNumber;
	}

	public String getBillingEmail() {
		return billingEmail;
	}

	public void setBillingEmail(String billingEmail) {
		this.billingEmail = billingEmail;
	}

	public String getShippingName() {
		return shippingName;
	}

	public void setShippingName(String shippingName) {
		this.shippingName = shippingName;
	}

	public String getShippingAddress1() {
		return shippingAddress1;
	}

	public void setShippingAddress1(String shippingAddress1) {
		this.shippingAddress1 = shippingAddress1;
	}

	public String getShippingAddress2() {
		return shippingAddress2;
	}

	public void setShippingAddress2(String shippingAddress2) {
		this.shippingAddress2 = shippingAddress2;
	}

	public String getShippingCity() {
		return shippingCity;
	}

	public void setShippingCity(String shippingCity) {
		this.shippingCity = shippingCity;
	}

	public String getShippingStateProvince() {
		return shippingStateProvince;
	}

	public void setShippingStateProvince(String shippingStateProvince) {
		this.shippingStateProvince = shippingStateProvince;
	}

	public String getShippingPostalCode() {
		return shippingPostalCode;
	}

	public void setShippingPostalCode(String shippingPostalCode) {
		this.shippingPostalCode = shippingPostalCode;
	}

	public String getShippingPhoneNumber() {
		return shippingPhoneNumber;
	}

	public void setShippingPhoneNumber(String shippingPhoneNumber) {
		this.shippingPhoneNumber = shippingPhoneNumber;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public CreditCardType getCreditCardType() {
		return creditCardType;
	}

	public void setCreditCardType(CreditCardType creditCardType) {
		this.creditCardType = creditCardType;
	}

	public String getCreditCardName() {
		return creditCardName;
	}

	public void setCreditCardName(String creditCardName) {
		this.creditCardName = creditCardName;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public String getCreditCardExpiration() {
		return creditCardExpiration;
	}

	public void setCreditCardExpiration(String creditCardExpiration) {
		this.creditCardExpiration = creditCardExpiration;
	}

	public Date getDatePurchased() {
		return datePurchased;
	}

	public void setDatePurchased(Date datePurchased) {
		this.datePurchased = datePurchased;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	public float getShippingCost() {
		return shippingCost;
	}

	public void setShippingCost(float shippingCost) {
		this.shippingCost = shippingCost;
	}

	public ShippingMethod getShippingMethod() {
		return shippingMethod;
	}

	public void setShippingMethod(ShippingMethod shippingMethod) {
		this.shippingMethod = shippingMethod;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Date getOrderCompleted() {
		return orderCompleted;
	}

	public void setOrderCompleted(Date orderCompleted) {
		this.orderCompleted = orderCompleted;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public List<Country> getCountries() {
		return countries;
	}

	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}

//	public List<Product> getProducts() {
//		return products;
//	}
//
//	public void setProducts(List<Product> products) {
//		this.products = products;
//	}

	
	
}
