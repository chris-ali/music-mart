package com.chrisali.musicmart.model.user;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.chrisali.musicmart.model.Country;



@Entity
@Table(name="address_book")
public class AddressBook implements Serializable {

	private static final long serialVersionUID = 8773280814073481778L;

	@Id
	@GeneratedValue
	private int id;
	
	@NotBlank
	@Size(min = 2, max = 45)
	private String name;
	
	@NotBlank
	@Column(name="address_line_1")
	@Size(min = 2, max = 60)
	private String addressLine1;
	
	@Column(name="address_line_2")
	@Size(min = 0, max = 60)
	private String addressLine2;
	
	@NotBlank
	@Size(min = 2, max = 45)
	private String city;
	
	@NotBlank
	@Column(name="state_province")
	@Size(min = 2, max = 45)
	private String stateProvince;
	
	@NotBlank
	@Column(name="postal_code")
	private String postalCode;
	
	@NotBlank
	@Column(name="phone_number")
	private String phoneNumber;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="country")
	private Country country;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id")
	private User user;
	
	public AddressBook() {
		this.user = new User();
		this.country = new Country();
	}

	public AddressBook(String name, String addressLine1, String addressLine2, String city, String stateProvince,
			String postalCode, String phoneNumber, Country country, User user) {
		this.name = name;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.city = city;
		this.stateProvince = stateProvince;
		this.postalCode = postalCode;
		this.phoneNumber = phoneNumber;
		this.country = country;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStateProvince() {
		return stateProvince;
	}

	public void setStateProvince(String stateProvince) {
		this.stateProvince = stateProvince;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "AddressBook [id=" + id + ", name=" + name + ", addressLine1=" + addressLine1 + ", addressLine2="
				+ addressLine2 + ", city=" + city + ", stateProvince=" + stateProvince + ", postalCode=" + postalCode
				+ ", country=" + country + ", user=" + user + "]";
	}

	
}
