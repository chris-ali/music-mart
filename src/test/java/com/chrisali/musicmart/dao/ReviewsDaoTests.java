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

import com.chrisali.musicmart.model.product.Review;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ReviewsDaoTests extends DaoTestData implements DaoTests {

	@Before
	public void init() {
		clearDatabase();
	}
	
	@Test
	@Override
	public void testCreateRetrieve() {
		usersDao.createOrUpdate(user1);
		usersDao.createOrUpdate(user2);
		usersDao.createOrUpdate(user3);
		
		manufacturersDao.createOrUpdate(manufacturer1);
		manufacturersDao.createOrUpdate(manufacturer2);
		manufacturersDao.createOrUpdate(manufacturer3);
		
		productsDao.createOrUpdate(product1);
		productsDao.createOrUpdate(product2);
		productsDao.createOrUpdate(product3);
		
		productDescriptionsDao.createOrUpdate(description1);
		productDescriptionsDao.createOrUpdate(description2);
		productDescriptionsDao.createOrUpdate(description3);
		
		category1.setSuperCategory(category3);
		category2.setSuperCategory(category3);
		
		categoriesDao.createOrUpdateIntoDb(category1);
		categoriesDao.createOrUpdateIntoDb(category2);
		categoriesDao.createOrUpdateIntoDb(category3);
		
		reviewsDao.createOrUpdate(review1);
		
		List<Review> reviews = reviewsDao.getPaginatedReviewsForUser(user1.getId(), 0, 10);
		
		assertEquals("One review should be in list for user 1", 1, reviews.size());
		
		reviewsDao.createOrUpdate(review2);
		reviewsDao.createOrUpdate(review3);
		reviewsDao.createOrUpdate(review4);
		reviewsDao.createOrUpdate(review5);
		
		reviews = reviewsDao.getPaginatedReviewsForUser(user2.getId(), 0, 10);
		
		assertEquals("Two reviews should be in list for user 2", 2, reviews.size());
	}

	@Test
	@Override
	public void testExists() {
		addTestData();
		
		assertTrue("Review should exist in database", reviewsDao.exists(review1.getId()));
		
		Review noExist = new Review(1, product1, user2, "Does not exist");
		noExist.setId(Integer.MAX_VALUE);
		
		assertFalse("Review should not exist in database", reviewsDao.exists(noExist.getId()));
	}

	@Test
	@Override
	public void testDelete() {
		addTestData();
		
		List<Review> reviews = reviewsDao.getPaginatedReviewsForUser(user1.getId(), 0, 10);
		assertEquals("Two reviews should be in list for user 1", 2, reviews.size());
		
		reviewsDao.delete(review4.getId());
		
		reviews = reviewsDao.getPaginatedReviewsForUser(user1.getId(), 0, 10);
		assertEquals("One review should be left in list for user 1", 1, reviews.size());
	}

	@Test
	@Override
	public void testUpdate() {
		addTestData();
		
		review2.setText("New text for existing review");
		review2.setRating(5);
		
		reviewsDao.createOrUpdate(review2);
		
		Review updatedReview = reviewsDao.getReview(review2.getId());
		
		assertEquals("Updated review should equal review retrieved from database", review2, updatedReview);
	}
}
