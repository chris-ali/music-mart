package com.chrisali.musicmart.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
import com.chrisali.musicmart.model.product.Category;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {SecurityConfiguration.class, 
								 DataSourceTestConfiguration.class,
								 ServiceTestConfiguration.class})
@SpringBootTest
@ActiveProfiles("test")
public class CategoriesServiceTests extends ServiceTestData implements ServiceTests {

	@Before
	public void init() {
		clearDatabase();
	}
	
	@Test
	@WithMockUser(username="admin", roles={"USER","ADMIN"})
	@Override
	public void testCreateRetrieve() {
		category1.setSuperCategory(category3);
		category2.setSuperCategory(category3);
		
		categoriesService.createOrUpdate(category1);
		categoriesService.createOrUpdate(category2);
		categoriesService.createOrUpdate(category3);
		
		List<Category> categories = categoriesService.getAllCategories();
		assertEquals("Three categories should be in list", 3, categories.size());
		
		categories = categoriesService.getSubCategories(category3.getId());
		assertEquals("Super-category 3 should have 2 sub-categories", 2, categories.size());
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
		
		assertTrue("Category should exist in database", categoriesService.exists(category1.getId()));
		
		Category noExist = new Category("www.test.com", "Nothing", null);
		noExist.setId(Integer.MAX_VALUE);
		
		assertFalse("category should not exist in database", categoriesService.exists(noExist.getId()));
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
		
		List<Category> categories = categoriesService.getAllCategories();
		assertEquals("Three categories should be in list", 3, categories.size());
		
		categoriesService.delete(category2.getId());
		
		categories = categoriesService.getAllCategories();
		assertEquals("Two categories should be left in list", 2, categories.size());
		
		categories = categoriesService.getSubCategories(category3.getId());
		assertEquals("Only one sub-category should remain", 1, categories.size());
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
		
		category2.setName("New Category");
		
		categoriesService.createOrUpdate(category2);
		
		Category updatedcategory = categoriesService.getCategory(category2.getId());
		
		assertEquals("Updated category should equal category retrieved from database", category2, updatedcategory);
	}

	@Test(expected = AuthenticationCredentialsNotFoundException.class)
	@Override
	public void testUpdateNoAuth() {
		testUpdate();
	}
}
