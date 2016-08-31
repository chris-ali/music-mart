package com.chrisali.musicmart.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.chrisali.musicmart.model.user.User;

/**
 * DAO that communicates with MySQL using Hibernate to perform CRUD operations on {@link User} objects
 * 
 * @author Christopher Ali
 *
 */
@Transactional
@Component("usersDao")
@Repository
public class UsersDao extends AbstractDao {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	/**
	 * Creates or updates {@link User} in database using {@link AbstractDao#createOrUpdateIntoDb(Object)}. Password is encoded using PasswordEncoder.
	 * 
	 * @param user
	 */
	public void createOrUpdate(User user) {
		user.setPassword(passwordEncoder.encode(user.getRawPassword()));
		
		createOrUpdateIntoDb(user);
	}
	
	/**
	 * @param pageNumber
	 * @param resultsSize
	 * @return paginated List of all {@link User} in database using Hibernate Criteria 
	 */
	@SuppressWarnings("unchecked")
	public List<User> getPaginatedUsers(int pageNumber, int resultsSize) {
		Criteria criteria = getSession().createCriteria(User.class)
										.setMaxResults(resultsSize)
										.setFirstResult(pageNumber * resultsSize)
										.addOrder(Order.asc("lastName"));
		
		List<User> users = criteria.list();
		
		closeSession();
		
		return users;
	}
	
	/**
	 * @return total number of {@link User} in database using HQL
	 */
	public Long getTotalNumberOfUsers() {
		Query criteria = getSession().createQuery("Select count (id) from User");
		Long count = (Long)criteria.uniqueResult();
		
		closeSession();
		
		return count;
	}
	
	/**
	 * @param id
	 * @return specific {@link User} using Hibernate Criteria
	 */
	public User getUser(int id) {
		Criteria criteria = getSession().createCriteria(User.class);
		criteria.add(Restrictions.idEq(id));
		User user = (User)criteria.uniqueResult();
		
		closeSession();
		
		return user;
	}
	
	/**
	 * @param id
	 * @return if {@link User} was deleted successfully from database using HQL
	 */
	public boolean delete(int id) {
		Query query = getSession().createQuery("delete from User where id=:id");
		query.setInteger("id", id);
		
		boolean isDeleted = (query.executeUpdate() == 1);
		closeSession();
		
		return isDeleted;
	}
	
	/**
	 * @param id
	 * @return if {@link User} exists in database
	 */
	public boolean exists(int id) {
		return getUser(id) != null;
	}
}
