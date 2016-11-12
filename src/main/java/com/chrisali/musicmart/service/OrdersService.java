package com.chrisali.musicmart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.chrisali.musicmart.dao.OrdersDao;
import com.chrisali.musicmart.model.order.Order;
import com.chrisali.musicmart.model.user.User;

/**
 * Contains CRUD methods to manipulate {@link Order} objects in the database 
 * 
 * @author Christopher Ali
 */
@Service("ordersService")
public class OrdersService {
	
	@Autowired
	private OrdersDao ordersDao;
	
	/**
	 * Adds/updates {@link Order} object in/to database, depending on if object already exists in database
	 * 
	 * @param order
	 */
	@Secured("ROLE_ADMIN")
	public void createOrUpdate(Order order) {
		ordersDao.createOrUpdate(order);
	}
	
	/**
	 * @param id
	 * @return whether {@link Order} object exists in database
	 */
	@Secured("ROLE_USER")
	public boolean exists(int id) {
		return ordersDao.exists(id);
	}
	
	/**
	 * @param usersId
	 * @param pageNumber
	 * @param resultsSize
	 * @return list of paginated {@link Order} objects resultsSize-objects long for a given {@link User} 
	 */
	@Secured("ROLE_USER")
	public List<Order> getPaginatedOrdersForUser(int usersId, int pageNumber, int resultsSize) {
		return ordersDao.getPaginatedOrdersForUser(usersId, pageNumber, resultsSize);
	}
	
	/**
	 * @param usersId
	 * @return total number of {@link Order} objects in database for a given {@link User}
	 */
	@Secured("ROLE_USER")
	public Long getTotalNumberOfOrdersForUser(int usersId) {
		return ordersDao.getTotalNumberOfOrdersForUser(usersId);
	}
	
	/**
	 * Deletes {@link Order} object from database
	 * @param id
	 */
	@Secured("ROLE_ADMIN")
	public void delete(int id) {
		ordersDao.delete(id);
	}
}
