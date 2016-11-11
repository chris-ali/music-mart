package com.chrisali.musicmart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.chrisali.musicmart.dao.UsersDao;
import com.chrisali.musicmart.model.user.User;

/**
 * Contains CRUD methods to manipulate {@link User} objects in the database 
 * 
 * @author Christopher Ali
 */
@Service("usersService")
public class UsersService {
	
	@Autowired
	private UsersDao usersDao;
	
	/**
	 * Adds/updates {@link User} object in/to database, depending on if object already exists in database
	 * 
	 * @param user
	 */
	public void createOrUpdate(User user) {
		usersDao.createOrUpdate(user);
	}
	
	/**
	 * @param id
	 * @return whether {@link User} object exists in database
	 */
	public boolean exists(int id) {
		return usersDao.exists(id);
	}
	
	/**
	 * @param pageNumber
	 * @param resultsSize
	 * @return list of paginated {@link User} objects resultsSize objects long 
	 */
	@Secured("ROLE_ADMIN")
	public List<User> getPaginatedUsers(int pageNumber, int resultsSize) {
		return usersDao.getPaginatedUsers(pageNumber, resultsSize);
	}
	
	/**
	 * @return total number of {@link User} objects in database
	 */
	@Secured("ROLE_ADMIN")
	public Long getTotalNumberOfUsers() {
		return usersDao.getTotalNumberOfUsers();
	}
	
	/**
	 * Deletes {@link User} object from database
	 * @param id
	 */
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	public void delete(int id) {
		usersDao.delete(id);
	}
}
