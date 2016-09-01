package com.chrisali.musicmart.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.chrisali.musicmart.model.order.Order;
import com.chrisali.musicmart.model.user.Address;

/**
 * Country object that contains 2 and 3 digit ISO codes 
 * 
 * @author Christopher Ali
 *
 */
@Entity
@Table(name="countries")
public class Country implements Serializable {

	private static final long serialVersionUID = 7315294166485587215L;

	@Id
	@GeneratedValue
	private int id;
	
	@NotBlank
	@Size(min=2, max=45)
	private String name;
	
	@Column(name="iso_code_2")
	@Size(min=2, max=2)
	private String isoCode2;
	
	@Column(name="iso_code_3")
	@Size(min=3, max=3)
	private String isoCode3;
	
	@OneToMany(mappedBy = "country")
	private List<Address> addresses;
	
	//@ManyToMany(mappedBy = "countries", cascade = CascadeType.ALL)
	//private List<Order> orders;
	
	public Country() {}

	public Country(String name, String isoCode2, String isoCode3) {
		this.name = name;
		this.isoCode2 = isoCode2;
		this.isoCode3 = isoCode3;
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

	public String getIsoCode2() {
		return isoCode2;
	}

	public void setIsoCode2(String isoCode2) {
		this.isoCode2 = isoCode2;
	}

	public String getIsoCode3() {
		return isoCode3;
	}

	public void setIsoCode3(String isoCode3) {
		this.isoCode3 = isoCode3;
	}
	
	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	@Override
	public String toString() {
		return "Country [id=" + id + ", name=" + name + ", isoCode2=" + isoCode2 + ", isoCode3=" + isoCode3 + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((isoCode2 == null) ? 0 : isoCode2.hashCode());
		result = prime * result + ((isoCode3 == null) ? 0 : isoCode3.hashCode());
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
		Country other = (Country) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (isoCode2 == null) {
			if (other.isoCode2 != null)
				return false;
		} else if (!isoCode2.equals(other.isoCode2))
			return false;
		if (isoCode3 == null) {
			if (other.isoCode3 != null)
				return false;
		} else if (!isoCode3.equals(other.isoCode3))
			return false;
		return true;
	}
}
