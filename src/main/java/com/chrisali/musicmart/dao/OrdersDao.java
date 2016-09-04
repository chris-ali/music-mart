package com.chrisali.musicmart.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.chrisali.musicmart.model.order.Order;
import com.chrisali.musicmart.model.user.User;

/**
 * DAO that communicates with MySQL using Hibernate to perform CRUD operations on {@link Order} objects
 * 
 * @author Christopher Ali
 *
 */
@Transactional
@Component("ordersDao")
@Repository
public class OrdersDao extends AbstractDao {

	/**
	 * Creates or updates {@link Order} in database using {@link AbstractDao#createOrUpdateIntoDb(Object)}
	 * 
	 * @param user
	 */
	public void createOrUpdate(Order item) {
		createOrUpdateIntoDb(item);
	}
	
	/**
	 * @param pageNumber
	 * @param resultsSize
	 * @return paginated List of all {@link Order} in database belonging to {@link User} using Hibernate Criteria 
	 */
	@SuppressWarnings("unchecked")
	public List<Order> getPaginatedOrdersForUser(int users_id, int pageNumber, int resultsSize) {
		Criteria criteria = getSession().createCriteria(Order.class)
										.createAlias("user", "u")
										.add(Restrictions.eq("u.id", users_id))
										.setMaxResults(resultsSize)
										.setFirstResult(pageNumber * resultsSize)
										.addOrder(org.hibernate.criterion.Order.desc("datePurchased"));

		List<Order> items = criteria.list();
		
		closeSession();
		
		return items;
	}
		/**
	 * @return total number of {@link Order} in database belonging to {@link User} using HQL
	 */
	public Long getTotalNumberOfOrdersForUser(int users_id) {
		Query criteria = getSession().createQuery("Select count (id) from Order where users_id=:users_id")
									 .setInteger("users_id", users_id);
		Long count = (Long)criteria.uniqueResult();
		
		closeSession();
		
		return count;
	}
	
	/**
	 * @param id
	 * @return specific {@link Order} using Hibernate Criteria
	 */
	public Order getOrder(int id) {
		Criteria criteria = getSession().createCriteria(Order.class);
		criteria.add(Restrictions.idEq(id));
		Order item = (Order)criteria.uniqueResult();
		
		closeSession();
		
		return item;
	}
	
	/**
	 * @param id
	 * @return if {@link Order} was deleted successfully from database using HQL
	 */
	public boolean delete(int id) {
		Query query = getSession().createQuery("delete from Order where id=:id");
		query.setInteger("id", id);
		
		boolean isDeleted = (query.executeUpdate() == 1);
		closeSession();
		
		return isDeleted;
	}
	
	/**
	 * @param id
	 * @return if {@link Order} exists in database
	 */
	public boolean exists(int id) {
		return getOrder(id) != null;
	}
}
