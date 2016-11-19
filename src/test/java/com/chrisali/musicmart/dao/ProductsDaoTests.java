package com.chrisali.musicmart.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.chrisali.musicmart.model.product.Product;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ProductsDaoTests extends DaoTestData implements DaoTests {

	@Before
	public void init() {
		clearDatabase();
	}
	
	@Test
	@Override
	public void testCreateRetrieve() {
		
		manufacturersDao.createOrUpdate(manufacturer1);
		manufacturersDao.createOrUpdate(manufacturer2);
		manufacturersDao.createOrUpdate(manufacturer3);
		
		categoriesDao.createOrUpdate(category1);
		categoriesDao.createOrUpdate(category2);
		categoriesDao.createOrUpdate(category3);
		
		productsDao.createOrUpdate(product1);
		productsDao.createOrUpdate(product2);
		productsDao.createOrUpdate(product3);
		
		productsDao.createOrUpdate(product1);
		
		List<Product> products = new ArrayList<>();
		
		products.add(productsDao.getProduct(product1.getId()));
		
		assertEquals("One product should be in list", 1, products.size());
		
		products.add(productsDao.getProduct(product2.getId()));
		products.add(productsDao.getProduct(product3.getId()));
		
		assertEquals("Three products should be in list", 3, products.size());
		//FIXME
		//long totalProductsCategory = productsDao.getTotalNumberOfProductsForCategory(category1.getId());
		
		//assertEquals("One product in total should be in database for category 1", 1, totalProductsCategory);
		
		long totalProductsManufacturer = productsDao.getTotalNumberOfProductsForManufacturer(manufacturer2.getId());
		
		assertEquals("One product in total should be in database for manufacturer 2", 1, totalProductsManufacturer);
	}

	@Test
	@Override
	public void testExists() {
		addTestData();
		
		assertTrue("Product product should exist in database", productsDao.exists(product1.getId()));
		
		Product noExist = new Product(manufacturer1, "None", "www.none.com", 0);
		noExist.setId(Integer.MAX_VALUE);
		
		assertFalse(" should not exist in database", productsDao.exists(noExist.getId()));
	}

	@Test
	@Override
	public void testDelete() {
		addTestData();
		
		List<Product> products = new ArrayList<>();
		
		products.add(productsDao.getProduct(product1.getId()));
		
		assertEquals("One product should be in list", 1, products.size());
		
		products.clear();
		productsDao.delete(product2.getId());
		
		Product deleted = productsDao.getProduct(product2.getId());
		
		assertNull(" should be null (not exist in database)", deleted);
	}

	@Test
	@Override
	public void testUpdate() {
		addTestData();
		
		product2.setManufacturer(manufacturer3);
		
		productsDao.createOrUpdate(product2);
		
		Product updated = productsDao.getProduct(product2.getId());
		
		assertEquals("Updated product should equal product retrieved from database", product2, updated);
	}
}
