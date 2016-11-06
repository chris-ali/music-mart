package com.chrisali.musicmart.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.chrisali.musicmart.model.product.Category;

/**
 * DAO that communicates with MySQL using Hibernate to perform CRUD operations on {@link Category} objects
 * 
 * @author Christopher Ali
 *
 */
@Transactional
@Component("categoriesDao")
@Repository
public class CategoriesDao extends AbstractDao {

	/**
	 * Creates or updates {@link Category} in database using {@link AbstractDao#createOrUpdateIntoDb(Object)}
	 * 
	 * @param user
	 */
	public void createOrUpdate(Category item) {
		createOrUpdateIntoDb(item);
	}
	
	/**
	 * @return List of all {@link Category} in database using Hibernate Criteria 
	 */
	@SuppressWarnings("unchecked")
	public List<Category> getAllCategories() {
		Criteria criteria = getSession().createCriteria(Category.class);

		List<Category> items = criteria.list();
		
		closeSession();
		
		return items;
	}
	
	/**
	 * @param parentId 
	 * @return List of all sub-{@link Category} for a given super-category ID using Hibernate Criteria
	 */
	@SuppressWarnings("unchecked")
	public List<Category> getSubCategories(int parentId) {
		Criteria criteria = getSession().createCriteria(Category.class)
				.createAlias("superCategory", "sc")
				.add(Restrictions.eq("sc.id", parentId))
				.addOrder(Order.asc("name"));

		List<Category> items = criteria.list();
		
		closeSession();
		
		return items;
	}
	
	/**
	 * 
	 * @param id
	 * @return specific {@link Category} using Hibernate Criteria
	 */
	public Category getCategory(int id) {
		Criteria criteria = getSession().createCriteria(Category.class);
		criteria.add(Restrictions.idEq(id));
		Category item = (Category)criteria.uniqueResult();
		
		closeSession();
		
		return item;
	}
	
	/**
	 * @param id
	 * @return if {@link Category} was deleted successfully from database using HQL
	 */
	public boolean delete(int id) {
		Query query = getSession().createQuery("delete from Category where id=:id");
		query.setInteger("id", id);
		
		boolean isDeleted = (query.executeUpdate() == 1);
		closeSession();
		
		return isDeleted;
	}
	
	/**
	 * @param id
	 * @return if {@link Category} exists in database
	 */
	public boolean exists(int id) {
		return getCategory(id) != null;
	}
}
