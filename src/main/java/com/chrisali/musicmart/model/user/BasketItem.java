package com.chrisali.musicmart.model.user;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import javax.validation.constraints.Size;

import com.chrisali.musicmart.model.product.Product;

@Entity
@Table(name="basket_item")
public class BasketItem {
	
	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id")
	private User user;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id")
	private Product product;

	@Size(min=1, max=99)
	private int quantity;
	
	@Column(name="total_price")
	private float totalPrice;
	
	@Column(name="date_added")
	private Date dateAdded;
	
	
}
