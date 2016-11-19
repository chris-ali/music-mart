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

import com.chrisali.musicmart.config.SecurityConfiguration;
import com.chrisali.musicmart.model.product.Review;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SecurityConfiguration.class)
@SpringBootTest
@ActiveProfiles("test")
public class ReviewsServiceTests extends ServiceTestData implements ServiceTests {

	@Before
	public void init() {
		clearDatabase();
	}
	
	@Test
	@WithMockUser(username="admin", roles={"USER","ADMIN"})
	@Override
	public void testCreateRetrieve() {
		usersService.createOrUpdate(user1);
		usersService.createOrUpdate(user2);
		usersService.createOrUpdate(user3);
		
		manufacturersService.createOrUpdate(manufacturer1);
		manufacturersService.createOrUpdate(manufacturer2);
		manufacturersService.createOrUpdate(manufacturer3);
		
		productsService.createOrUpdate(product1);
		productsService.createOrUpdate(product2);
		productsService.createOrUpdate(product3);
		
		productDescriptionsService.createOrUpdate(description1);
		productDescriptionsService.createOrUpdate(description2);
		productDescriptionsService.createOrUpdate(description3);
		
		category1.setSuperCategory(category3);
		category2.setSuperCategory(category3);
		
		categoriesService.createOrUpdate(category1);
		categoriesService.createOrUpdate(category2);
		categoriesService.createOrUpdate(category3);
		
		reviewsService.createOrUpdate(review1);
		
		List<Review> reviews = reviewsService.getPaginatedReviewsForUser(user1.getId(), 0, 10);
		
		assertEquals("One review should be in list for user 1", 1, reviews.size());
		
		reviewsService.createOrUpdate(review2);
		reviewsService.createOrUpdate(review3);
		reviewsService.createOrUpdate(review4);
		reviewsService.createOrUpdate(review5);
		
		reviews = reviewsService.getPaginatedReviewsForUser(user2.getId(), 0, 10);
		
		assertEquals("Two reviews should be in list for user 2", 2, reviews.size());
		
		long totalReviewsProduct = reviewsService.getTotalNumberOfReviewsForProduct(product2.getId());

		assertEquals("Two review in total should be in database for product 2", 2, totalReviewsProduct);
		
		long totalReviewsUser = reviewsService.getTotalNumberOfReviewsForUser(user3.getId());
		
		assertEquals("Two review in total should be in database for user 3", 1, totalReviewsUser);
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
		
		assertTrue("Review should exist in database", reviewsService.exists(review1.getId()));
		
		Review noExist = new Review(1, product1, user2, "Does not exist");
		noExist.setId(Integer.MAX_VALUE);
		
		assertFalse("Review should not exist in database", reviewsService.exists(noExist.getId()));
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
		
		List<Review> reviews = reviewsService.getPaginatedReviewsForUser(user1.getId(), 0, 10);
		assertEquals("Two reviews should be in list for user 1", 2, reviews.size());
		
		reviewsService.delete(review4.getId());
		
		reviews = reviewsService.getPaginatedReviewsForUser(user1.getId(), 0, 10);
		assertEquals("One review should be left in list for user 1", 1, reviews.size());
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
		
		review2.setText("New text for existing review");
		review2.setRating(5);
		
		reviewsService.createOrUpdate(review2);
		
		Review updatedReview = reviewsService.getReview(review2.getId());
		
		assertEquals("Updated review should equal review retrieved from database", review2, updatedReview);
	}

	@Test(expected = AuthenticationCredentialsNotFoundException.class)
	@Override
	public void testUpdateNoAuth() {
		testUpdate();
	}
}
