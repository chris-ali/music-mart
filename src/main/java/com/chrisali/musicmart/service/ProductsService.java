package com.chrisali.musicmart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.chrisali.musicmart.dao.ProductsDao;
import com.chrisali.musicmart.model.product.Category;
import com.chrisali.musicmart.model.product.Manufacturer;
import com.chrisali.musicmart.model.product.Product;

/**
 * Contains CRUD methods to manipulate {@link Product} objects in the database 
 * 
 * @author Christopher Ali
 */
@Service("productsService")
public class ProductsService {
	
	@Autowired
	private ProductsDao productsDao;
	
	/**
	 * Adds/updates {@link Product} object in/to database, depending on if object already exists in database
	 * 
	 * @param product
	 */
	@Secured("ROLE_ADMIN")
	public void createOrUpdate(Product product) {
		productsDao.createOrUpdate(product);
	}
	
	/**
	 * @param id
	 * @return whether {@link Product} object exists in database
	 */
	@Secured("ROLE_ADMIN")
	public boolean exists(int id) {
		return productsDao.exists(id);
	}
	
	/**
	 * @param categoriesId
	 * @param pageNumber
	 * @param resultsSize
	 * @return list of paginated {@link Product} objects resultsSize-objects long for a given {@link Category} 
	 */
	public List<Product> getPaginatedProductsForCategory(int categoriesId, int pageNumber, int resultsSize) {
		return productsDao.getPaginatedProductsForCategory(categoriesId, pageNumber, resultsSize);
	}
	
	/**
	 * @param manufacturersId
	 * @param pageNumber
	 * @param resultsSize
	 * @return list of paginated {@link Product} objects resultsSize-objects long for a given {@link Manufacturer}
	 */
	public List<Product> getPaginatedProductsForManufacturer(int manufacturersId, int pageNumber, int resultsSize) {
		return productsDao.getPaginatedProductsForManufacturer(manufacturersId, pageNumber, resultsSize);
	}
	
	/**
	 * @param id
	 * @return {@link Product} object from database
	 */
	public Product getProduct(int id) {
		return productsDao.getProduct(id);
	}
	
	/**
	 * @param categoriesId
	 * @return total number of {@link Product} objects in database for a given {@link Category}
	 */
	public Long getTotalNumberOfProductsForCategory(int categoriesId) {
		return productsDao.getTotalNumberOfProductsForCategory(categoriesId);
	}
	
	/**
	 * @param manufacturersId
	 * @return total number of {@link Product} objects in database for a given {@link Manufacturer}
	 */
	public Long getTotalNumberOfProductsForManufacturer(int manufacturersId) {
		return productsDao.getTotalNumberOfProductsForManufacturer(manufacturersId);
	}
	
	/**
	 * Deletes {@link Product} object from database
	 * @param id
	 */
	@Secured("ROLE_ADMIN")
	public void delete(int id) {
		productsDao.delete(id);
	}
}
