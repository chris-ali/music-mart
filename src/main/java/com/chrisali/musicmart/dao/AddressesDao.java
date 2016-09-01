package com.chrisali.musicmart.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.chrisali.musicmart.model.user.Address;
import com.chrisali.musicmart.model.user.User;

/**
 * DAO that communicates with MySQL using Hibernate to perform CRUD operations on {@link Address} objects
 * 
 * @author Christopher Ali
 *
 */
@Transactional
@Component("addressesDao")
@Repository
public class AddressesDao extends AbstractDao {
	
	/**
	 * Creates or updates {@link Address} in database using {@link AbstractDao#createOrUpdateIntoDb(Object)}
	 * 
	 * @param user
	 */
	public void createOrUpdate(Address item) {
		createOrUpdateIntoDb(item);
	}
	
	/**
	 * @param pageNumber
	 * @param resultsSize
	 * @return paginated List of all {@link Address} in database belonging to {@link User} using Hibernate Criteria 
	 */
	@SuppressWarnings("unchecked")
	public List<Address> getPaginatedAddresses(int users_id, int pageNumber, int resultsSize) {
		Criteria criteria = getSession().createCriteria(Address.class)
										.createAlias("user", "u")
										.add(Restrictions.eq("u.id", users_id))
										.setMaxResults(resultsSize)
										.setFirstResult(pageNumber * resultsSize);

		List<Address> items = criteria.list();
		
		closeSession();
		
		return items;
	}
	
	/**
	 * @return total number of {@link Address} in database belonging to {@link User} using HQL
	 */
	public Long getTotalNumberOfAddressesForUser(int users_id) {
		Query criteria = getSession().createQuery("Select count (id) from Address where users_id=:users_id")
									 .setInteger("users_id", users_id);
		Long count = (Long)criteria.uniqueResult();
		
		closeSession();
		
		return count;
	}
	
	/**
	 * @param id
	 * @return specific {@link Address} using Hibernate Criteria
	 */
	public Address getAddress(int id) {
		Criteria criteria = getSession().createCriteria(Address.class);
		criteria.add(Restrictions.idEq(id));
		Address item = (Address)criteria.uniqueResult();
		
		closeSession();
		
		return item;
	}
	
	/**
	 * @param id
	 * @return if {@link Address} was deleted successfully from database using HQL
	 */
	public boolean delete(int id) {
		Query query = getSession().createQuery("delete from Address where id=:id");
		query.setInteger("id", id);
		
		boolean isDeleted = (query.executeUpdate() == 1);
		closeSession();
		
		return isDeleted;
	}
	
	/**
	 * @param id
	 * @return if {@link Address} exists in database
	 */
	public boolean exists(int id) {
		return getAddress(id) != null;
	}
}
