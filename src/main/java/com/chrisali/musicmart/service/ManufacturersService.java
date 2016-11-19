package com.chrisali.musicmart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.chrisali.musicmart.dao.ManufacturersDao;
import com.chrisali.musicmart.model.product.Manufacturer;

/**
 * Contains CRUD methods to manipulate {@link Manufacturer} objects in the database 
 * 
 * @author Christopher Ali
 */
@Service("manufacturersService")
public class ManufacturersService {
	
	@Autowired
	private ManufacturersDao manufacturersDao;
	
	/**
	 * Adds/updates {@link Manufacturer} object in/to database, depending on if object already exists in database
	 * 
	 * @param manufacturer
	 */
	@Secured("ROLE_ADMIN")
	public void createOrUpdate(Manufacturer manufacturer) {
		manufacturersDao.createOrUpdate(manufacturer);
	}
	
	/**
	 * @param id
	 * @return whether {@link Manufacturer} object exists in database
	 */
	@Secured("ROLE_ADMIN")
	public boolean exists(int id) {
		return manufacturersDao.exists(id);
	}
	
	/**
	 * @return list of all {@link Manufacturer} objects  
	 */
	public List<Manufacturer> getPaginatedManufacturers(int pageNumber, int resultsSize) {
		return manufacturersDao.getPaginatedManufacturers(pageNumber, resultsSize);
	}
	
	/**
	 * @return count of {@link Manufacturer} objects in database
	 */
	public Long getTotalNumberOfManufacturers() {
		return manufacturersDao.getTotalNumberOfManufacturers();
	}
	
	/**
	 * @param id
	 * @return {@link Manufacturer} object from database
	 */
	public Manufacturer getManufacturer(int id) {
		return manufacturersDao.getManufacturer(id);
	}
	
	/**
	 * Deletes {@link Manufacturer} object from database
	 * @param id
	 */
	@Secured("ROLE_ADMIN")
	public void delete(int id) {
		manufacturersDao.delete(id);
	}
}
