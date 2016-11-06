package com.chrisali.musicmart.model.product;

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

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import com.chrisali.musicmart.model.user.User;

@Entity
@Table(name="reviews")
public class Review implements Serializable {

	private static final long serialVersionUID = 8295635121257206102L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Range(min=1, max=5)
	private int rating;
	
	@Length(min=0, max=5000)
	private String text;
	
	@Column(name="date_added")
	private Date dateAdded;
	
	@Column(name="last_modified")
	private Date lastModified;
	
	@Range(min=0, max=99999)
	private int helpful;
	
	@ManyToOne
	@JoinColumn(name="products_id", referencedColumnName="id")
	private Product product;
	
	@ManyToOne
	@JoinColumn(name="users_id", referencedColumnName="id")
	private User user;
	
	public Review() {
		this.user = new User();
		this.product = new Product();
		this.dateAdded = new Date();
		this.lastModified = new Date();
	}

	public Review(int rating, Product product, User user, String text) {
		this.rating = rating;
		this.product = product;
		this.user = user;
		this.text = text;
		this.dateAdded = new Date();
		this.lastModified = new Date();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	public int getHelpful() {
		return helpful;
	}

	public void setHelpful(int helpful) {
		this.helpful = helpful;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Review [id=" + id + ", rating=" + rating + ", text=" + text + ", dateAdded=" + dateAdded
				+ ", lastModified=" + lastModified + ", helpful=" + helpful + ", product=" + product + ", user=" + user
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + rating;
		result = prime * result + ((text == null) ? 0 : text.hashCode());
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
		Review other = (Review) obj;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (rating != other.rating)
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
}
