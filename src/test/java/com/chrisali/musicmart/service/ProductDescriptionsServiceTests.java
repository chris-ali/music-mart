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

import com.chrisali.musicmart.config.SecurityConfiguration;
import com.chrisali.musicmart.model.product.ProductDescription;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SecurityConfiguration.class)
@SpringBootTest
@ActiveProfiles("test")
public class ProductDescriptionsServiceTests extends ServiceTestData implements ServiceTests {

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
		
		productsService.createOrUpdate(product1);
		productsService.createOrUpdate(product2);
		productsService.createOrUpdate(product3);
		
		productDescriptionsService.createOrUpdate(description1);
		
		List<ProductDescription> productDescriptions = new ArrayList<>();
		
		productDescriptions.add(productDescriptionsService.getProductDescription(description1.getId()));
		
		assertEquals("One description should be in list", 1, productDescriptions.size());
		
		productDescriptions.add(productDescriptionsService.getProductDescription(description2.getId()));
		productDescriptions.add(productDescriptionsService.getProductDescription(description3.getId()));
		
		assertEquals("Three descriptions should be in list", 3, productDescriptions.size());
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
		
		assertTrue("Product description should exist in database", productDescriptionsService.exists(description1.getId()));
		
		ProductDescription noExist = new ProductDescription(null, "Nothing", "Nothing", "www.nothing.com");
		noExist.setId(Integer.MAX_VALUE);
		
		assertFalse("Description should not exist in database", productDescriptionsService.exists(noExist.getId()));
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
		
		List<ProductDescription> productDescriptions = new ArrayList<>();
		
		productDescriptions.add(productDescriptionsService.getProductDescription(description1.getId()));
		
		assertEquals("One description should be in list", 1, productDescriptions.size());
		
		productDescriptions.clear();
		productDescriptionsService.delete(description2.getId());
		
		ProductDescription deletedDescription = productDescriptionsService.getProductDescription(description2.getId());
		
		assertNull("Description should be null (not exist in database)", deletedDescription);
	}

	@Test(expected = AuthenticationCredentialsNotFoundException.class)
	@Override
	public void testDeleteNoAuth() {
		testDeleteNoAuth();
	}
	
	@Test
	@WithMockUser(username="admin", roles={"USER","ADMIN"})
	@Override
	public void testUpdate() {
		addTestData();
		
		description2.setName("Updated Description");
		
		productDescriptionsService.createOrUpdate(description2);
		
		ProductDescription updatedDescription = productDescriptionsService.getProductDescription(description2.getId());
		
		assertEquals("Updated description should equal description retrieved from database", description2, updatedDescription);
	}

	@Test(expected = AuthenticationCredentialsNotFoundException.class)
	@Override
	public void testUpdateNoAuth() {
		testUpdate();
	}
}
