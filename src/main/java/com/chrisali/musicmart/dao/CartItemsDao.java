package com.chrisali.musicmart.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.chrisali.musicmart.model.user.CartItem;
import com.chrisali.musicmart.model.user.User;

/**
 * DAO that communicates with MySQL using Hibernate to perform CRUD operations on {@link CartItem} objects
 * 
 * @author Christopher Ali
 *
 */
@Transactional
@Component("cartItemsDao")
@Repository
public class CartItemsDao extends AbstractDao {

	/**
	 * Creates or updates {@link CartItem} in database using {@link AbstractDao#createOrUpdateIntoDb(Object)}
	 * 
	 * @param user
	 */
	public void createOrUpdate(CartItem item) {
		createOrUpdateIntoDb(item);
	}
	
	/**
	 * @param pageNumber
	 * @param resultsSize
	 * @return paginated List of all {@link CartItem} in database belonging to {@link User} using Hibernate Criteria 
	 */
	@SuppressWarnings("unchecked")
	public List<CartItem> getPaginatedCartItems(int users_id, int pageNumber, int resultsSize) {
		Criteria criteria = getSession().createCriteria(CartItem.class)
										.add(Restrictions.idEq(users_id))
										.setMaxResults(resultsSize)
										.setFirstResult(pageNumber * resultsSize)
										.addOrder(Order.desc("date_added"));

		List<CartItem> items = criteria.list();
		
		closeSession();
		
		return items;
	}
	
	/**
	 * @return total number of {@link CartItem} in database belonging to {@link User} using HQL
	 */
	public Long getTotalNumberOfCartItemsForUser(int users_id) {
		Query criteria = getSession().createQuery("Select count (id) from CartItem where users_id=:users_id")
									 .setInteger("users_id", users_id);
		Long count = (Long)criteria.uniqueResult();
		
		closeSession();
		
		return count;
	}
	
	/**
	 * @param id
	 * @return specific {@link CartItem} using Hibernate Criteria
	 */
	public CartItem getCartItem(int id) {
		Criteria criteria = getSession().createCriteria(CartItem.class);
		criteria.add(Restrictions.idEq(id));
		CartItem item = (CartItem)criteria.uniqueResult();
		
		closeSession();
		
		return item;
	}
	
	/**
	 * @param id
	 * @return if {@link CartItem} was deleted successfully from database using HQL
	 */
	public boolean delete(int id) {
		Query query = getSession().createQuery("delete from CartItem where id=:id");
		query.setInteger("id", id);
		
		boolean isDeleted = (query.executeUpdate() == 1);
		closeSession();
		
		return isDeleted;
	}
	
	/**
	 * @param id
	 * @return if {@link CartItem} exists in database
	 */
	public boolean exists(int id) {
		return getCartItem(id) != null;
	}
}
