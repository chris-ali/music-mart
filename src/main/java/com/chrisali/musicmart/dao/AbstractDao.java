package com.chrisali.musicmart.dao;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * DAO superclass that contains Hibernate Session and SessionFactory objects and common methods to
 * open and close database connection secssions
 * 
 * @author Christopher Ali
 *
 */
@Transactional
@Component("abstractDao")
@Repository
public class AbstractDao {
	
	@Autowired
	protected SessionFactory sessionFactory;
	protected Session session;
	
	@Autowired
	private Logger logger;
	
	/**
	 * Establishes database connection with Hibernate SessionFactory for subclass DAO objects
	 * 
	 * @return Hibernate session object
	 */
	protected Session getSession() {
		session = null;
		
		try {
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			session = sessionFactory.openSession();
			logger.info("Opening new Hibernate session");
		}
		
		return session;
	}
	
	/**
	 * Creates or updates Object in database using saveOrUpdate() from Session object.
	 * beginTransaction() starts the process, flush() is called after saveOrUpdate(), then the Transaction
	 * is committed as long as no exception is thrown, in which case the transaction is rolled back, ensuring
	 * ACID behavior of the database
	 * 
	 * @param object
	 */
	protected void createOrUpdateIntoDb(Object object) {
		Transaction tx = null;
		session = sessionFactory.openSession();
		
		try {
			tx = session.beginTransaction();
			session.saveOrUpdate(object);
			session.flush();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) tx.rollback();
		} finally {
			session.close();
		}
	}
	
	/**
	 * Closes Hibernate session object
	 */
	protected void closeSession() {session.close();}
}
