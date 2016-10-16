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

import com.chrisali.musicmart.model.product.ProductDescription;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ProductDescriptionsDaoTests extends DaoTestData implements DaoTests {

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
		
		productsDao.createOrUpdate(product1);
		productsDao.createOrUpdate(product2);
		productsDao.createOrUpdate(product3);
		
		productDescriptionsDao.createOrUpdate(description1);
		
		List<ProductDescription> productDescriptions = new ArrayList<>();
		
		productDescriptions.add(productDescriptionsDao.getProductDescription(description1.getId()));
		
		assertEquals("One description should be in list", 1, productDescriptions.size());
		
		productDescriptions.add(productDescriptionsDao.getProductDescription(description2.getId()));
		productDescriptions.add(productDescriptionsDao.getProductDescription(description3.getId()));
		
		assertEquals("Three descriptions should be in list", 3, productDescriptions.size());
	}

	@Test
	@Override
	public void testExists() {
		addTestData();
		
		assertTrue("Product description should exist in database", productDescriptionsDao.exists(description1.getId()));
		
		ProductDescription noExist = new ProductDescription(null, "Nothing", "Nothing", "www.nothing.com");
		noExist.setId(Integer.MAX_VALUE);
		
		assertFalse("Description should not exist in database", productDescriptionsDao.exists(noExist.getId()));
	}

	@Test
	@Override
	public void testDelete() {
		addTestData();
		
		List<ProductDescription> productDescriptions = new ArrayList<>();
		
		productDescriptions.add(productDescriptionsDao.getProductDescription(description1.getId()));
		
		assertEquals("One description should be in list", 1, productDescriptions.size());
		
		productDescriptions.clear();
		productDescriptionsDao.delete(description2.getId());
		
		ProductDescription deletedDescription = productDescriptionsDao.getProductDescription(description2.getId());
		
		assertNull("Description should be null (not exist in database)", deletedDescription);
	}

	@Test
	@Override
	public void testUpdate() {
		addTestData();
		
		description2.setName("Updated Description");
		
		productDescriptionsDao.createOrUpdate(description2);
		
		ProductDescription updatedDescription = productDescriptionsDao.getProductDescription(description2.getId());
		
		assertEquals("Updated description should equal description retrieved from database", description2, updatedDescription);
	}
}
