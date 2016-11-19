package com.chrisali.musicmart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.chrisali.musicmart.dao.ReviewsDao;
import com.chrisali.musicmart.model.product.Product;
import com.chrisali.musicmart.model.product.Review;
import com.chrisali.musicmart.model.user.User;

/**
 * Contains CRUD methods to manipulate {@link Review} objects in the database 
 * 
 * @author Christopher Ali
 */
@Service("reviewsService")
public class ReviewsService {
	
	@Autowired
	private ReviewsDao reviewsDao;
	
	/**
	 * Adds/updates {@link Review} object in/to database, depending on if object already exists in database
	 * 
	 * @param review
	 */
	public void createOrUpdate(Review review) {
		reviewsDao.createOrUpdate(review);
	}
	
	/**
	 * @param id
	 * @return whether {@link Review} object exists in database
	 */
	public boolean exists(int id) {
		return reviewsDao.exists(id);
	}
	
	/**
	 * @param id
	 * @return {@link Review} object from database for given id
	 */
	public Review getReview(int id) {
		return reviewsDao.getReview(id);
	}
	
	/**
	 * @param usersId
	 * @param pageNumber
	 * @param resultsSize
	 * @return list of paginated {@link Review} objects resultsSize-objects long for a given {@link User} 
	 */
	@Secured("ROLE_USER")
	public List<Review> getPaginatedReviewsForUser(int usersId, int pageNumber, int resultsSize) {
		return reviewsDao.getPaginatedReviewsForUser(usersId, pageNumber, resultsSize);
	}
	
	/**
	 * @param productsId
	 * @param pageNumber
	 * @param resultsSize
	 * @return list of paginated {@link Review} objects resultsSize-objects long for a given {@link User}
	 */
	@Secured("ROLE_USER")
	public List<Review> getPaginatedReviewsForProduct(int productsId, int pageNumber, int resultsSize) {
		return reviewsDao.getPaginatedReviewsForProduct(productsId, pageNumber, resultsSize);
	}
	
	/**
	 * @param usersId
	 * @return total number of {@link Review} objects in database for a given {@link User}
	 */
	@Secured("ROLE_USER")
	public Long getTotalNumberOfReviewsForUser(int usersId) {
		return reviewsDao.getTotalNumberOfReviewsForUser(usersId);
	}
	
	/**
	 * @param productsId
	 * @return total number of {@link Review} objects in database for a given {@link Product}
	 */
	@Secured("ROLE_USER")
	public Long getTotalNumberOfReviewsForProduct(int productsId) {
		return reviewsDao.getTotalNumberOfReviewsForProduct(productsId);
	}
	
	/**
	 * Deletes {@link Review} object from database
	 * @param id
	 */
	@Secured("ROLE_USER")
	public void delete(int id) {
		reviewsDao.delete(id);
	}
}
