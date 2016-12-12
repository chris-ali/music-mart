package com.chrisali.musicmart.service;

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
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.chrisali.musicmart.config.DataSourceTestConfiguration;
import com.chrisali.musicmart.config.SecurityConfiguration;
import com.chrisali.musicmart.config.ServiceTestConfiguration;
import com.chrisali.musicmart.model.product.Product;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {SecurityConfiguration.class, 
								 DataSourceTestConfiguration.class,
								 ServiceTestConfiguration.class})
@SpringBootTest
@ActiveProfiles("test")
public class ProductsServiceTests extends ServiceTestData implements ServiceTests {

	@Before
	public void init() {
		clearDatabase();
	}
	
	@Test
	@WithMockUser(username="admin", roles={"USER","ADMIN"})
	@Override
	public void testCreateRetrieve() {
		
		manufacturersService.createOrUpdate(manufacturer1);
		manufacturersService.createOrUpdate(manufacturer2);
		manufacturersService.createOrUpdate(manufacturer3);
		
		categoriesService.createOrUpdate(category1);
		categoriesService.createOrUpdate(category2);
		categoriesService.createOrUpdate(category3);
		
		productsService.createOrUpdate(product1);
		productsService.createOrUpdate(product2);
		productsService.createOrUpdate(product3);
		
		productsService.createOrUpdate(product1);
		
		List<Product> products = new ArrayList<>();
		
		products.add(productsService.getProduct(product1.getId()));
		
		assertEquals("One product should be in list", 1, products.size());
		
		products.add(productsService.getProduct(product2.getId()));
		products.add(productsService.getProduct(product3.getId()));
		
		assertEquals("Three products should be in list", 3, products.size());
		//FIXME
		//long totalProductsCategory = productsService.getTotalNumberOfProductsForCategory(category1.getId());
		
		//assertEquals("One product in total should be in database for category 1", 1, totalProductsCategory);
		
		long totalProductsManufacturer = productsService.getTotalNumberOfProductsForManufacturer(manufacturer2.getId());
		
		assertEquals("One product in total should be in database for manufacturer 2", 1, totalProductsManufacturer);
	}

	@Test(expected = AuthenticationCredentialsNotFoundException.class)
	@Override
	public void testCreateRetrieveNoAuth() {
		testCreateRetrieve();
	}
	
	@Test
	@WithMockUser(username="admin", roles={"USER","ADMIN"})
	@Override
	public void testExists() {
		addTestData();
		
		assertTrue("Product product should exist in database", productsService.exists(product1.getId()));
		
		Product noExist = new Product(manufacturer1, "None", "www.none.com", 0);
		noExist.setId(Integer.MAX_VALUE);
		
		assertFalse(" should not exist in database", productsService.exists(noExist.getId()));
	}

	@Test(expected = AuthenticationCredentialsNotFoundException.class)
	@Override
	public void testExistsNoAuth() {
		testExists();
	}
	
	@Test
	@WithMockUser(username="admin", roles={"USER","ADMIN"})
	@Override
	public void testDelete() {
		addTestData();
		
		List<Product> products = new ArrayList<>();
		
		products.add(productsService.getProduct(product1.getId()));
		
		assertEquals("One product should be in list", 1, products.size());
		
		products.clear();
		productsService.delete(product2.getId());
		
		Product deleted = productsService.getProduct(product2.getId());
		
		assertNull(" should be null (not exist in database)", deleted);
	}

	@Test(expected = AuthenticationCredentialsNotFoundException.class)
	@Override
	public void testDeleteNoAuth() {
		testDelete();
	}
	
	@Test
	@WithMockUser(username="admin", roles={"USER","ADMIN"})
	@Override
	public void testUpdate() {
		addTestData();
		
		product2.setManufacturer(manufacturer3);
		
		productsService.createOrUpdate(product2);
		
		Product updated = productsService.getProduct(product2.getId());
		
		assertEquals("Updated product should equal product retrieved from database", product2, updated);
	}

	@Test(expected = AuthenticationCredentialsNotFoundException.class)
	@Override
	public void testUpdateNoAuth() {
		testUpdate();
	}
}
