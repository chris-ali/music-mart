package com.chrisali.musicmart.model.product;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="products")
public class Product implements Serializable {

	private static final long serialVersionUID = -4203213453845127953L;

	@Id
	@GeneratedValue
	private int id;
}
