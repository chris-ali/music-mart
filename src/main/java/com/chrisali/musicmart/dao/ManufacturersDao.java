package com.chrisali.musicmart.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.chrisali.musicmart.model.product.Manufacturer;

/**
 * DAO that communicates with MySQL using Hibernate to perform CRUD operations on {@link Manufacturer} objects
 * 
 * @author Christopher Ali
 *
 */
@Transactional
@Component("manufacturersDao")
@Repository
public class ManufacturersDao extends AbstractDao {

	/**
	 * Creates or updates {@link Manufacturer} in database using {@link AbstractDao#createOrUpdateIntoDb(Object)}
	 * 
	 * @param user
	 */
	public void createOrUpdate(Manufacturer item) {
		createOrUpdateIntoDb(item);
	}
	
	/**
	 * @param pageNumber
	 * @param resultsSize
	 * @return paginated List of all {@link Manufacturer} in database using Hibernate Criteria 
	 */
	@SuppressWarnings("unchecked")
	public List<Manufacturer> getPaginatedManufacturers(int pageNumber, int resultsSize) {
		Criteria criteria = getSession().createCriteria(Manufacturer.class)
										.setMaxResults(resultsSize)
										.setFirstResult(pageNumber * resultsSize);

		List<Manufacturer> items = criteria.list();
		
		closeSession();
		
		return items;
	}
	
	/**
	 * @return total number of {@link Manufacturer} in database using HQL
	 */
	public Long getTotalNumberOfManufacturers() {
		Query criteria = getSession().createQuery("Select count (id) from Manufacturer");

		Long count = (Long)criteria.uniqueResult();
		
		closeSession();
		
		return count;
	}
	
	/**
	 * @param id
	 * @return specific {@link Manufacturer} using Hibernate Criteria
	 */
	public Manufacturer getManufacturer(int id) {
		Criteria criteria = getSession().createCriteria(Manufacturer.class);
		criteria.add(Restrictions.idEq(id));
		Manufacturer item = (Manufacturer)criteria.uniqueResult();
		
		closeSession();
		
		return item;
	}
	
	/**
	 * @param id
	 * @return if {@link Manufacturer} was deleted successfully from database using HQL
	 */
	public boolean delete(int id) {
		Query query = getSession().createQuery("delete from Manufacturer where id=:id");
		query.setInteger("id", id);
		
		boolean isDeleted = (query.executeUpdate() == 1);
		closeSession();
		
		return isDeleted;
	}
	
	/**
	 * @param id
	 * @return if {@link Manufacturer} exists in database
	 */
	public boolean exists(int id) {
		return getManufacturer(id) != null;
	}
}
