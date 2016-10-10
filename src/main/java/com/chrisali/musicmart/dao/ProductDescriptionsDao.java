package com.chrisali.musicmart.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.chrisali.musicmart.model.product.ProductDescription;

/**
 * DAO that communicates with MySQL using Hibernate to perform CRUD operations on {@link ProductDescription} objects
 * 
 * @author Christopher Ali
 *
 */
//TODO Test
@Transactional
@Component("productDescriptionsDao")
@Repository
public class ProductDescriptionsDao extends AbstractDao {

	/**
	 * Creates or updates {@link ProductDescription} in database using {@link AbstractDao#createOrUpdateIntoDb(Object)}
	 * 
	 * @param user
	 */
	public void createOrUpdate(ProductDescription productDescription) {
		createOrUpdateIntoDb(productDescription);
	}
	
	/**
	 * @param id
	 * @return specific {@link ProductDescription} using Hibernate Criteria
	 */
	public ProductDescription getProductDescription(int id) {
		Criteria criteria = getSession().createCriteria(ProductDescription.class);
		criteria.add(Restrictions.idEq(id));
		ProductDescription productDescription = (ProductDescription)criteria.uniqueResult();
		
		closeSession();
		
		return productDescription;
	}
	
	/**
	 * @param productId
	 * @return specific {@link ProductDescription} using Hibernate Criteria
	 */
	public ProductDescription getProductDescriptionForProduct(int productId) {
		Criteria criteria = getSession().createCriteria(ProductDescription.class)
										.createAlias("product", "p")
										.add(Restrictions.eq("p.id", productId));
		
		ProductDescription productDescription = (ProductDescription)criteria.uniqueResult();
		
		closeSession();
		
		return productDescription;
	}
	
	/**
	 * @param id
	 * @return if {@link ProductDescription} was deleted successfully from database using HQL
	 */
	public boolean delete(int id) {
		Query query = getSession().createQuery("delete from ProductDescription where id=:id");
		query.setInteger("id", id);
		
		boolean isDeleted = (query.executeUpdate() == 1);
		closeSession();
		
		return isDeleted;
	}
	
	/**
	 * @param id
	 * @return if {@link ProductDescription} exists in database
	 */
	public boolean exists(int id) {
		return getProductDescription(id) != null;
	}
}
