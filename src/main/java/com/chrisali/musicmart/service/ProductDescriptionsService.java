package com.chrisali.musicmart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.chrisali.musicmart.dao.ProductDescriptionsDao;
import com.chrisali.musicmart.model.product.ProductDescription;

/**
 * Contains CRUD methods to manipulate {@link ProductDescription} objects in the database 
 * 
 * @author Christopher Ali
 */
@Service("productDescriptionsService")
public class ProductDescriptionsService {
	
	@Autowired
	private ProductDescriptionsDao productDescriptionsDao;
	
	/**
	 * Adds/updates {@link ProductDescription} object in/to database, depending on if object already exists in database
	 * 
	 * @param description
	 */
	@Secured("ROLE_ADMIN")
	public void createOrUpdate(ProductDescription description) {
		productDescriptionsDao.createOrUpdate(description);
	}
	
	/**
	 * @param id
	 * @return whether {@link ProductDescription} object exists in database
	 */
	@Secured("ROLE_ADMIN")
	public boolean exists(int id) {
		return productDescriptionsDao.exists(id);
	}
	
	/**
	 * @param id
	 * @return {@link ProductDescription} object from database
	 */
	public ProductDescription getProductDescription(int id) {
		return productDescriptionsDao.getProductDescription(id);
	}
	
	/**
	 * Deletes {@link ProductDescription} object from database
	 * @param id
	 */
	@Secured("ROLE_ADMIN")
	public void delete(int id) {
		productDescriptionsDao.delete(id);
	}
}
