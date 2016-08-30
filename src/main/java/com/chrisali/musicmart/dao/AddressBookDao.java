package com.chrisali.musicmart.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.chrisali.musicmart.model.user.AddressBook;
import com.chrisali.musicmart.model.user.User;

/**
 * DAO that communicates with MySQL using Hibernate to perform CRUD operations on {@link AddressBook} objects
 * 
 * @author Christopher Ali
 *
 */
@Transactional
@Component("addressBookDao")
@Repository
public class AddressBookDao extends AbstractDao {
	
	/**
	 * Creates or updates {@link AddressBook} in database using {@link AbstractDao#createOrUpdateIntoDb(Object)}
	 * 
	 * @param user
	 */
	public void createOrUpdate(AddressBook item) {
		createOrUpdateIntoDb(item);
	}
	
	/**
	 * @param pageNumber
	 * @param resultsSize
	 * @return paginated List of all {@link AddressBook} in database belonging to {@link User} using Hibernate Criteria 
	 */
	@SuppressWarnings("unchecked")
	public List<AddressBook> getPaginatedAddressBooks(int users_id, int pageNumber, int resultsSize) {
		Criteria criteria = getSession().createCriteria(AddressBook.class)
										.add(Restrictions.idEq(users_id))
										.setMaxResults(resultsSize)
										.setFirstResult(pageNumber * resultsSize)
										.addOrder(Order.desc("date_added"));

		List<AddressBook> items = criteria.list();
		
		closeSession();
		
		return items;
	}
	
	/**
	 * @return total number of {@link AddressBook} in database belonging to {@link User} using HQL
	 */
	public Long getTotalNumberOfAddressesForUser(int users_id) {
		Query criteria = getSession().createQuery("Select count (id) from AddressBook where users_id=:users_id")
									 .setInteger("users_id", users_id);
		Long count = (Long)criteria.uniqueResult();
		
		closeSession();
		
		return count;
	}
	
	/**
	 * @param id
	 * @return specific {@link AddressBook} using Hibernate Criteria
	 */
	public AddressBook getAddressBook(int id) {
		Criteria criteria = getSession().createCriteria(AddressBook.class);
		criteria.add(Restrictions.idEq(id));
		AddressBook item = (AddressBook)criteria.uniqueResult();
		
		closeSession();
		
		return item;
	}
	
	/**
	 * @param id
	 * @return if {@link AddressBook} was deleted successfully from database using HQL
	 */
	public boolean delete(int id) {
		Query query = getSession().createQuery("delete from AddressBook where id=:id");
		query.setInteger("id", id);
		
		boolean isDeleted = (query.executeUpdate() == 1);
		closeSession();
		
		return isDeleted;
	}
	
	/**
	 * @param id
	 * @return if {@link AddressBook} exists in database
	 */
	public boolean exists(int id) {
		return getAddressBook(id) != null;
	}
}
