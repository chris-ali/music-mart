package com.chrisali.musicmart.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.chrisali.musicmart.model.product.Category;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class CategoriesDaoTests extends DaoTestData implements DaoTests {

	@Before
	public void init() {
		clearDatabase();
	}
	
	@Test
	@Override
	public void testCreateRetrieve() {
		category1.setSuperCategory(category3);
		category2.setSuperCategory(category3);
		
		categoriesDao.createOrUpdate(category1);
		categoriesDao.createOrUpdate(category2);
		categoriesDao.createOrUpdate(category3);
		
		List<Category> categories = categoriesDao.getAllCategories();
		assertEquals("Three categories should be in list", 3, categories.size());
		
		categories = categoriesDao.getSubCategories(category3.getId());
		assertEquals("Super-category 3 should have 2 sub-categories", 2, categories.size());
	}

	@Test
	@Override
	public void testExists() {
		addTestData();
		
		assertTrue("Category should exist in database", categoriesDao.exists(category1.getId()));
		
		Category noExist = new Category("www.test.com", "Nothing", null);
		noExist.setId(Integer.MAX_VALUE);
		
		assertFalse("category should not exist in database", categoriesDao.exists(noExist.getId()));
	}

	@Test
	@Override
	public void testDelete() {
		addTestData();
		
		List<Category> categories = categoriesDao.getAllCategories();
		assertEquals("Three categories should be in list", 3, categories.size());
		
		categoriesDao.delete(category2.getId());
		
		categories = categoriesDao.getAllCategories();
		assertEquals("Two categories should be left in list", 2, categories.size());
		
		categories = categoriesDao.getSubCategories(category3.getId());
		assertEquals("Only one sub-category should remain", 1, categories.size());
	}

	@Test
	@Override
	public void testUpdate() {
		addTestData();
		
		category2.setName("New Category");
		
		categoriesDao.createOrUpdate(category2);
		
		Category updatedcategory = categoriesDao.getCategory(category2.getId());
		
		assertEquals("Updated category should equal category retrieved from database", category2, updatedcategory);
	}
}
