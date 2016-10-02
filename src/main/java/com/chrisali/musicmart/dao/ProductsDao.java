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
import com.chrisali.musicmart.model.product.Manufacturer;
import com.chrisali.musicmart.model.product.Product;
import com.chrisali.musicmart.model.user.User;

/**
 * DAO that communicates with MySQL using Hibernate to perform CRUD operations on {@link Product} objects
 * 
 * @author Christopher Ali
 *
 */
//TODO Test
@Transactional
@Component("productsDao")
@Repository
public class ProductsDao extends AbstractDao {

	/**
	 * Creates or updates {@link Product} in database using {@link AbstractDao#createOrUpdateIntoDb(Object)}
	 * 
	 * @param user
	 */
	public void createOrUpdate(Product product) {
		createOrUpdateIntoDb(product);
	}
	
	/**
	 * @param pageNumber
	 * @param resultsSize
	 * @return paginated List of all {@link Product} in database belonging to {@link Manufacturer} using Hibernate Criteria 
	 */
	@SuppressWarnings("unchecked")
	public List<Product> getPaginatedProductsForManufacturer(int manufacturers_id, int pageNumber, int resultsSize) {
		Criteria criteria = getSession().createCriteria(Product.class)
										.createAlias("manufacturer", "m")
										.add(Restrictions.eq("m.id", manufacturers_id))
										.setMaxResults(resultsSize)
										.setFirstResult(pageNumber * resultsSize)
										.addOrder(Order.desc("dateAdded"));

		List<Product> products = criteria.list();
		
		closeSession();
		
		return products;
	}
	
	/**
	 * @return total number of {@link Product} in database belonging to {@link User} using HQL
	 */
	public Long getTotalNumberOfProductsForManufacturer(int manufacturers_id) {
		Query criteria = getSession().createQuery("Select count (id) from Product where manufacturers_id=:manufacturers_id")
									 .setInteger("manufacturers_id", manufacturers_id);
		Long count = (Long)criteria.uniqueResult();
		
		closeSession();
		
		return count;
	}
	
	/**
	 * @param pageNumber
	 * @param resultsSize
	 * @return paginated List of all {@link Product} in database belonging to {@link Category} using Hibernate Criteria 
	 */
	@SuppressWarnings("unchecked")
	public List<Product> getPaginatedProductsForCategory(int categories_id, int pageNumber, int resultsSize) {
		Criteria criteria = getSession().createCriteria(Category.class)
										.createAlias("category", "c")
										.add(Restrictions.eq("c.id", categories_id))
										.setMaxResults(resultsSize)
										.setFirstResult(pageNumber * resultsSize)
										.addOrder(Order.desc("dateAdded"));

		List<Product> products = criteria.list();
		
		closeSession();
		
		return products;
	}
	
	/**
	 * @return total number of {@link Product} in database belonging to {@link Category} using HQL
	 */
	public Long getTotalNumberOfProductsForCategory(int categories_id) {
		Query criteria = getSession().createQuery("Select count (id) from Product where categories_id=:categories_id")
									 .setInteger("categories_id", categories_id);
		Long count = (Long)criteria.uniqueResult();
		
		closeSession();
		
		return count;
	}
	
	/**
	 * @param id
	 * @return specific {@link Product} using Hibernate Criteria
	 */
	public Product getProduct(int id) {
		Criteria criteria = getSession().createCriteria(Product.class);
		criteria.add(Restrictions.idEq(id));
		Product product = (Product)criteria.uniqueResult();
		
		closeSession();
		
		return product;
	}
	
	/**
	 * @param id
	 * @return if {@link Product} was deleted successfully from database using HQL
	 */
	public boolean delete(int id) {
		Query query = getSession().createQuery("delete from Product where id=:id");
		query.setInteger("id", id);
		
		boolean isDeleted = (query.executeUpdate() == 1);
		closeSession();
		
		return isDeleted;
	}
	
	/**
	 * @param id
	 * @return if {@link Product} exists in database
	 */
	public boolean exists(int id) {
		return getProduct(id) != null;
	}
}
