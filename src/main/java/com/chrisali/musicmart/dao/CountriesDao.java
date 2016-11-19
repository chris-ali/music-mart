package com.chrisali.musicmart.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.chrisali.musicmart.model.user.Country;

@Transactional
@Component("countriesDao")
@Repository
public class CountriesDao extends AbstractDao {
	
	/**
	 * Creates or updates {@link Country} in database using {@link AbstractDao#createOrUpdateIntoDb(Object)}
	 * 
	 * @param user
	 */
	public void createOrUpdate(Country item) {
		createOrUpdateIntoDb(item);
	}
	
	/**
	 * @param pageNumber
	 * @param resultsSize
	 * @return List of all {@link Country} in database using Hibernate Criteria 
	 */
	@SuppressWarnings("unchecked")
	public List<Country> getAllCountries() {
		Criteria criteria = getSession().createCriteria(Country.class)
										.addOrder(Order.desc("name"));

		List<Country> items = criteria.list();
		
		closeSession();
		
		return items;
	}
		
	/**
	 * @param id
	 * @return specific {@link Country} using Hibernate Criteria
	 */
	public Country getCountry(int id) {
		Criteria criteria = getSession().createCriteria(Country.class);
		criteria.add(Restrictions.idEq(id));
		Country item = (Country)criteria.uniqueResult();
		
		closeSession();
		
		return item;
	}
	
	/**
	 * @param id
	 * @return if {@link Country} was deleted successfully from database using HQL
	 */
	public boolean delete(int id) {
		Query query = getSession().createQuery("delete from Country where id=:id");
		query.setInteger("id", id);
		
		boolean isDeleted = (query.executeUpdate() == 1);
		closeSession();
		
		return isDeleted;
	}
	
	/**
	 * @param id
	 * @return if {@link Country} exists in database
	 */
	public boolean exists(int id) {
		return getCountry(id) != null;
	}
}
