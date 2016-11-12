package com.chrisali.musicmart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.chrisali.musicmart.dao.CategoriesDao;
import com.chrisali.musicmart.model.product.Category;

/**
 * Contains CRUD methods to manipulate {@link Category} objects in the database 
 * 
 * @author Christopher Ali
 */
@Service("categoriesService")
public class CategoriesService {
	
	@Autowired
	private CategoriesDao categoriesDao;
	
	/**
	 * Adds/updates {@link Category} object in/to database, depending on if object already exists in database
	 * 
	 * @param category
	 */
	@Secured("ROLE_ADMIN")
	public void createOrUpdate(Category category) {
		categoriesDao.createOrUpdate(category);
	}
	
	/**
	 * @param id
	 * @return whether {@link Category} object exists in database
	 */
	@Secured("ROLE_ADMIN")
	public boolean exists(int id) {
		return categoriesDao.exists(id);
	}
	
	/**
	 * @return list of all {@link Category} objects  
	 */
	public List<Category> getAllCategories() {
		return categoriesDao.getAllCategories();
	}
	
	/**
	 * @param parentId
	 * @return list of all sub-categories for a {@link Category} object
	 */
	public List<Category> getSubCategories(int parentId) {
		return categoriesDao.getSubCategories(parentId);
	}
	
	/**
	 * @param id
	 * @return {@link Category} object from database
	 */
	public Category getCategory(int id) {
		return categoriesDao.getCategory(id);
	}
	
	/**
	 * Deletes {@link Category} object from database
	 * @param id
	 */
	@Secured("ROLE_ADMIN")
	public void delete(int id) {
		categoriesDao.delete(id);
	}
}
