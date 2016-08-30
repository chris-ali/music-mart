package com.chrisali.musicmart.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.chrisali.musicmart.model.user.BasketItem;
import com.chrisali.musicmart.model.user.User;

/**
 * DAO that communicates with MySQL using Hibernate to perform CRUD operations on {@link BasketItem} objects
 * 
 * @author Christopher Ali
 *
 */
@Transactional
@Component("basketItemDao")
@Repository
public class BasketItemsDao extends AbstractDao {

	/**
	 * Creates or updates {@link BasketItem} in database using {@link AbstractDao#createOrUpdateIntoDb(Object)}
	 * 
	 * @param user
	 */
	public void createOrUpdate(BasketItem item) {
		createOrUpdateIntoDb(item);
	}
	
	/**
	 * @param pageNumber
	 * @param resultsSize
	 * @return paginated List of all {@link BasketItem} in database belonging to {@link User} using Hibernate Criteria 
	 */
	@SuppressWarnings("unchecked")
	public List<BasketItem> getPaginatedBasketItems(int users_id, int pageNumber, int resultsSize) {
		Criteria criteria = getSession().createCriteria(BasketItem.class)
										.add(Restrictions.idEq(users_id))
										.setMaxResults(resultsSize)
										.setFirstResult(pageNumber * resultsSize)
										.addOrder(Order.desc("date_added"));

		List<BasketItem> items = criteria.list();
		
		closeSession();
		
		return items;
	}
	
	/**
	 * @return total number of {@link BasketItem} in database belonging to {@link User} using HQL
	 */
	public Long getTotalNumberOfItemsForUser(int users_id) {
		Query criteria = getSession().createQuery("Select count (id) from BasketItem where users_id=:users_id")
									 .setInteger("users_id", users_id);
		Long count = (Long)criteria.uniqueResult();
		
		closeSession();
		
		return count;
	}
	
	/**
	 * @param id
	 * @return specific {@link BasketItem} using Hibernate Criteria
	 */
	public BasketItem getBasketItem(int id) {
		Criteria criteria = getSession().createCriteria(BasketItem.class);
		criteria.add(Restrictions.idEq(id));
		BasketItem item = (BasketItem)criteria.uniqueResult();
		
		closeSession();
		
		return item;
	}
	
	/**
	 * @param id
	 * @return if {@link BasketItem} was deleted successfully from database using HQL
	 */
	public boolean delete(int id) {
		Query query = getSession().createQuery("delete from BasketItem where id=:id");
		query.setInteger("id", id);
		
		boolean isDeleted = (query.executeUpdate() == 1);
		closeSession();
		
		return isDeleted;
	}
	
	/**
	 * @param id
	 * @return if {@link BasketItem} exists in database
	 */
	public boolean exists(int id) {
		return getBasketItem(id) != null;
	}
}
