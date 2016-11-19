package com.chrisali.musicmart.model.order;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.chrisali.musicmart.model.user.Country;
import com.chrisali.musicmart.model.user.User;

@Entity
@Table(name = "orders")
public class Order implements Serializable {

	private static final long serialVersionUID = 8974632863155985270L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "users_id", referencedColumnName = "id")
	private User user;
	
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
	
	@ManyToOne
	@JoinColumn(name = "billing_countries_id", referencedColumnName = "id")
	private Country billingCountry;
	
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
	
	@ManyToOne
	@JoinColumn(name = "shipping_countries_id", referencedColumnName = "id")
	private Country shippingCountry;
	
	@NotBlank
	@Column(name = "shipping_phone_number")
	@Size(min = 2, max = 45)
	private String shippingPhoneNumber;
	
	@NotBlank
	@Column(name = "payment_method")
	private String paymentMethod;
	
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
	
	@Column(name = "date_purchased")
	private Date datePurchased;
	
	@Column(name = "last_modified")
	private Date lastModified;
	
	@Column(name = "shipping_cost")
	private float shippingCost;
	
	@Column(name = "shipping_method")
	private ShippingMethod shippingMethod;
	
	@Column(name = "order_status")
	private OrderStatus orderStatus;
	
	@Column(name = "order_completed")
	private Date orderCompleted;
	
	@Size(max = 200)
	@Column(name = "comments")
	private String comments;
	
//	@ManyToMany(mappedBy = "products")
//	private List<Product> products;

	public Order() {
		this.user = new User();
		this.datePurchased = new Date();
		this.billingCountry = new Country();
		this.shippingCountry = new Country();
	}
	
	public Order(User user, Country billingCountry, Country shippingCountry) {
		this.user = user;
		this.billingCountry = billingCountry;
		this.shippingCountry = shippingCountry;
		this.datePurchased = new Date();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public Country getBillingCountry() {
		return billingCountry;
	}

	public void setBillingCountry(Country billingCountry) {
		this.billingCountry = billingCountry;
	}

	public Country getShippingCountry() {
		return shippingCountry;
	}

	public void setShippingCountry(Country shippingCountry) {
		this.shippingCountry = shippingCountry;
	}

//	public List<Product> getProducts() {
//		return products;
//	}
//
//	public void setProducts(List<Product> products) {
//		this.products = products;
//	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", user=" + user + ", billingName=" + billingName + ", billingAddress1="
				+ billingAddress1 + ", billingAddress2=" + billingAddress2 + ", billingCity=" + billingCity
				+ ", billingStateProvince=" + billingStateProvince + ", billingPostalCode=" + billingPostalCode
				+ ", billingCountry=" + billingCountry + ", billingPhoneNumber=" + billingPhoneNumber
				+ ", billingEmail=" + billingEmail + ", shippingName=" + shippingName + ", shippingAddress1="
				+ shippingAddress1 + ", shippingAddress2=" + shippingAddress2 + ", shippingCity=" + shippingCity
				+ ", shippingStateProvince=" + shippingStateProvince + ", shippingPostalCode=" + shippingPostalCode
				+ ", shippingCountry=" + shippingCountry + ", shippingPhoneNumber=" + shippingPhoneNumber
				+ ", paymentMethod=" + paymentMethod + ", creditCardType=" + creditCardType + ", creditCardName="
				+ creditCardName + ", creditCardNumber=" + creditCardNumber + ", creditCardExpiration="
				+ creditCardExpiration + ", datePurchased=" + datePurchased + ", lastModified=" + lastModified
				+ ", shippingCost=" + shippingCost + ", shippingMethod=" + shippingMethod + ", orderStatus="
				+ orderStatus + ", orderCompleted=" + orderCompleted + ", comments=" + comments + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((billingAddress1 == null) ? 0 : billingAddress1.hashCode());
		result = prime * result + ((billingAddress2 == null) ? 0 : billingAddress2.hashCode());
		result = prime * result + ((billingCity == null) ? 0 : billingCity.hashCode());
		result = prime * result + ((billingCountry == null) ? 0 : billingCountry.hashCode());
		result = prime * result + ((billingEmail == null) ? 0 : billingEmail.hashCode());
		result = prime * result + ((billingName == null) ? 0 : billingName.hashCode());
		result = prime * result + ((billingPhoneNumber == null) ? 0 : billingPhoneNumber.hashCode());
		result = prime * result + ((billingPostalCode == null) ? 0 : billingPostalCode.hashCode());
		result = prime * result + ((billingStateProvince == null) ? 0 : billingStateProvince.hashCode());
		result = prime * result + ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + ((creditCardExpiration == null) ? 0 : creditCardExpiration.hashCode());
		result = prime * result + ((creditCardName == null) ? 0 : creditCardName.hashCode());
		result = prime * result + ((creditCardNumber == null) ? 0 : creditCardNumber.hashCode());
		result = prime * result + ((creditCardType == null) ? 0 : creditCardType.hashCode());
		result = prime * result + ((orderStatus == null) ? 0 : orderStatus.hashCode());
		result = prime * result + ((paymentMethod == null) ? 0 : paymentMethod.hashCode());
		result = prime * result + ((shippingAddress1 == null) ? 0 : shippingAddress1.hashCode());
		result = prime * result + ((shippingAddress2 == null) ? 0 : shippingAddress2.hashCode());
		result = prime * result + ((shippingCity == null) ? 0 : shippingCity.hashCode());
		result = prime * result + Float.floatToIntBits(shippingCost);
		result = prime * result + ((shippingCountry == null) ? 0 : shippingCountry.hashCode());
		result = prime * result + ((shippingMethod == null) ? 0 : shippingMethod.hashCode());
		result = prime * result + ((shippingName == null) ? 0 : shippingName.hashCode());
		result = prime * result + ((shippingPhoneNumber == null) ? 0 : shippingPhoneNumber.hashCode());
		result = prime * result + ((shippingPostalCode == null) ? 0 : shippingPostalCode.hashCode());
		result = prime * result + ((shippingStateProvince == null) ? 0 : shippingStateProvince.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (billingAddress1 == null) {
			if (other.billingAddress1 != null)
				return false;
		} else if (!billingAddress1.equals(other.billingAddress1))
			return false;
		if (billingAddress2 == null) {
			if (other.billingAddress2 != null)
				return false;
		} else if (!billingAddress2.equals(other.billingAddress2))
			return false;
		if (billingCity == null) {
			if (other.billingCity != null)
				return false;
		} else if (!billingCity.equals(other.billingCity))
			return false;
		if (billingCountry == null) {
			if (other.billingCountry != null)
				return false;
		} else if (!billingCountry.equals(other.billingCountry))
			return false;
		if (billingEmail == null) {
			if (other.billingEmail != null)
				return false;
		} else if (!billingEmail.equals(other.billingEmail))
			return false;
		if (billingName == null) {
			if (other.billingName != null)
				return false;
		} else if (!billingName.equals(other.billingName))
			return false;
		if (billingPhoneNumber == null) {
			if (other.billingPhoneNumber != null)
				return false;
		} else if (!billingPhoneNumber.equals(other.billingPhoneNumber))
			return false;
		if (billingPostalCode == null) {
			if (other.billingPostalCode != null)
				return false;
		} else if (!billingPostalCode.equals(other.billingPostalCode))
			return false;
		if (billingStateProvince == null) {
			if (other.billingStateProvince != null)
				return false;
		} else if (!billingStateProvince.equals(other.billingStateProvince))
			return false;
		if (comments == null) {
			if (other.comments != null)
				return false;
		} else if (!comments.equals(other.comments))
			return false;
		if (creditCardExpiration == null) {
			if (other.creditCardExpiration != null)
				return false;
		} else if (!creditCardExpiration.equals(other.creditCardExpiration))
			return false;
		if (creditCardName == null) {
			if (other.creditCardName != null)
				return false;
		} else if (!creditCardName.equals(other.creditCardName))
			return false;
		if (creditCardNumber == null) {
			if (other.creditCardNumber != null)
				return false;
		} else if (!creditCardNumber.equals(other.creditCardNumber))
			return false;
		if (creditCardType != other.creditCardType)
			return false;
		if (orderStatus != other.orderStatus)
			return false;
		if (paymentMethod == null) {
			if (other.paymentMethod != null)
				return false;
		} else if (!paymentMethod.equals(other.paymentMethod))
			return false;
		if (shippingAddress1 == null) {
			if (other.shippingAddress1 != null)
				return false;
		} else if (!shippingAddress1.equals(other.shippingAddress1))
			return false;
		if (shippingAddress2 == null) {
			if (other.shippingAddress2 != null)
				return false;
		} else if (!shippingAddress2.equals(other.shippingAddress2))
			return false;
		if (shippingCity == null) {
			if (other.shippingCity != null)
				return false;
		} else if (!shippingCity.equals(other.shippingCity))
			return false;
		if (Float.floatToIntBits(shippingCost) != Float.floatToIntBits(other.shippingCost))
			return false;
		if (shippingCountry == null) {
			if (other.shippingCountry != null)
				return false;
		} else if (!shippingCountry.equals(other.shippingCountry))
			return false;
		if (shippingMethod != other.shippingMethod)
			return false;
		if (shippingName == null) {
			if (other.shippingName != null)
				return false;
		} else if (!shippingName.equals(other.shippingName))
			return false;
		if (shippingPhoneNumber == null) {
			if (other.shippingPhoneNumber != null)
				return false;
		} else if (!shippingPhoneNumber.equals(other.shippingPhoneNumber))
			return false;
		if (shippingPostalCode == null) {
			if (other.shippingPostalCode != null)
				return false;
		} else if (!shippingPostalCode.equals(other.shippingPostalCode))
			return false;
		if (shippingStateProvince == null) {
			if (other.shippingStateProvince != null)
				return false;
		} else if (!shippingStateProvince.equals(other.shippingStateProvince))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
}
