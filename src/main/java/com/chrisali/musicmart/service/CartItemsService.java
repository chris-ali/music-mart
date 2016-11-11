package com.chrisali.musicmart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.chrisali.musicmart.dao.CartItemsDao;
import com.chrisali.musicmart.model.user.CartItem;
import com.chrisali.musicmart.model.user.User;

/**
 * Contains CRUD methods to manipulate {@link CartItem} objects in the database 
 * 
 * @author Christopher Ali
 */
@Service("cartItemsService")
public class CartItemsService {
	
	@Autowired
	private CartItemsDao cartItemsDao;
	
	/**
	 * Adds/updates {@link CartItem} object in/to database, depending on if object already exists in database
	 * 
	 * @param user
	 */
	public void createOrUpdate(CartItem user) {
		cartItemsDao.createOrUpdate(user);
	}
	
	/**
	 * @param id
	 * @return whether {@link CartItem} object exists in database
	 */
	public boolean exists(int id) {
		return cartItemsDao.exists(id);
	}
	
	/**
	 * @param usersId
	 * @param pageNumber
	 * @param resultsSize
	 * @return list of paginated {@link CartItem} objects resultsSize-objects long for a given {@link User} 
	 */
	@Secured("ROLE_USER")
	public List<CartItem> getPaginatedCartItemsForUser(int usersId, int pageNumber, int resultsSize) {
		return cartItemsDao.getPaginatedCartItemsForUser(usersId, pageNumber, resultsSize);
	}
	
	/**
	 * @param usersId
	 * @return total number of {@link CartItem} objects in database for a given {@link User}
	 */
	@Secured("ROLE_USER")
	public Long getTotalNumberOfCartItemsForUser(int usersId) {
		return cartItemsDao.getTotalNumberOfCartItemsForUser(usersId);
	}
	
	/**
	 * Deletes {@link CartItem} object from database
	 * @param id
	 */
	@Secured("ROLE_USER")
	public void delete(int id) {
		cartItemsDao.delete(id);
	}
}
