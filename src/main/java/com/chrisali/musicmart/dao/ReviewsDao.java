package com.chrisali.musicmart.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.chrisali.musicmart.model.product.Review;

/**
 * DAO that communicates with MySQL using Hibernate to perform CRUD operations on {@link Review} objects
 * 
 * @author Christopher Ali
 *
 */
@Transactional
@Component("reviewsDao")
@Repository
public class ReviewsDao extends AbstractDao {

	/**
	 * Creates or updates {@link Review} in database using {@link AbstractDao#createOrUpdateIntoDb(Object)}
	 * 
	 * @param review
	 */
	public void createOrUpdate(Review review) {
		createOrUpdateIntoDb(review);
	}
	
	/**
	 * @param pageNumber
	 * @param resultsSize
	 * @return paginated List of all {@link Review} in database using Hibernate Criteria 
	 */
	@SuppressWarnings("unchecked")
	public List<Review> getPaginatedReviews(int pageNumber, int resultsSize) {
		Criteria criteria = getSession().createCriteria(Review.class)
										.setMaxResults(resultsSize)
										.setFirstResult(pageNumber * resultsSize)
										.addOrder(Order.asc("dateAdded"));
		
		List<Review> reviews = criteria.list();
		
		closeSession();
		
		return reviews;
	}
	
	/**
	 * @return total number of {@link Review} in database using HQL
	 */
	public Long getTotalNumberOfReviews() {
		Query criteria = getSession().createQuery("Select count (id) from Review");
		Long count = (Long)criteria.uniqueResult();
		
		closeSession();
		
		return count;
	}
	
	/**
	 * @param id
	 * @return specific {@link Review} using Hibernate Criteria
	 */
	public Review getReview(int id) {
		Criteria criteria = getSession().createCriteria(Review.class);
		criteria.add(Restrictions.idEq(id));
		Review review = (Review)criteria.uniqueResult();
		
		closeSession();
		
		return review;
	}
	
	/**
	 * @param id
	 * @return if {@link Review} was deleted successfully from database using HQL
	 */
	public boolean delete(int id) {
		Query query = getSession().createQuery("delete from Review where id=:id");
		query.setInteger("id", id);
		
		boolean isDeleted = (query.executeUpdate() == 1);
		closeSession();
		
		return isDeleted;
	}
	
	/**
	 * @param id
	 * @return if {@link Review} exists in database
	 */
	public boolean exists(int id) {
		return getReview(id) != null;
	}
}
