package com.chrisali.musicmart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.chrisali.musicmart.dao.CountriesDao;
import com.chrisali.musicmart.model.Country;

/**
 * Contains CRUD methods to manipulate {@link Country} objects in the database 
 * 
 * @author Christopher Ali
 */
@Service("countriesService")
public class CountriesService {
	
	@Autowired
	private CountriesDao countriesDao;
	
	/**
	 * Adds/updates {@link Country} object in/to database, depending on if object already exists in database
	 * 
	 * @param country
	 */
	@Secured("ROLE_ADMIN")
	public void createOrUpdate(Country country) {
		countriesDao.createOrUpdate(country);
	}
	
	/**
	 * @param id
	 * @return whether {@link Country} object exists in database
	 */
	@Secured("ROLE_ADMIN")
	public boolean exists(int id) {
		return countriesDao.exists(id);
	}
	
	/**
	 * @return list of all {@link Country} objects  
	 */
	public List<Country> getAllCountries() {
		return countriesDao.getAllCountries();
	}
	
	/**
	 * @param id
	 * @return {@link Country} object from database
	 */
	public Country getCountry(int id) {
		return countriesDao.getCountry(id);
	}
	
	/**
	 * Deletes {@link Country} object from database
	 * @param id
	 */
	@Secured("ROLE_ADMIN")
	public void delete(int id) {
		countriesDao.delete(id);
	}
}
