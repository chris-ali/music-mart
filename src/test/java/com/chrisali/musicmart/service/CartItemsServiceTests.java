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
import com.chrisali.musicmart.model.product.Product;
import com.chrisali.musicmart.model.user.CartItem;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {SecurityConfiguration.class, 
								 DataSourceTestConfiguration.class,
								 ServiceTestConfiguration.class})
@SpringBootTest
@ActiveProfiles("test")
public class CartItemsServiceTests extends ServiceTestData implements ServiceTests {

	@Before
	public void init() {
		clearDatabase();
	}
	
	@Test
	@WithMockUser(username="admin", roles={"USER","ADMIN"})
	@Override
	public void testCreateRetrieve() {
		addTestData();
		
		cartItemsService.createOrUpdate(cartItem1);
		
		List<CartItem> cartItems = cartItemsService.getPaginatedCartItemsForUser(user1.getId(), 0, 10);
		
		assertEquals("One cart item should be in list for user 1", 1, cartItems.size());
		
		cartItemsService.createOrUpdate(cartItem2);
		
		cartItems = cartItemsService.getPaginatedCartItemsForUser(user2.getId(), 0, 10);
		
		assertEquals("One cart item should be in list for user 2", 1, cartItems.size());
		
		cartItemsService.createOrUpdate(cartItem3);
		
		cartItems = cartItemsService.getPaginatedCartItemsForUser(user3.getId(), 0, 10);
		
		assertEquals("One cart item should be in list for user 3", 1, cartItems.size());
		
		long totalCartItemsUser = cartItemsService.getTotalNumberOfCartItemsForUser(user3.getId());
		
		assertEquals("One cart item should be in database for user 3", 1, totalCartItemsUser);
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
		
		assertTrue("Cart item should exist in database", cartItemsService.exists(cartItem1.getId()));
		
		CartItem noExist = new CartItem(user1, new Product(), 10, 1233.00f);
		noExist.setId(Integer.MAX_VALUE);
		
		assertFalse("Cart item should not exist in database", cartItemsService.exists(noExist.getId()));
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
		
		List<CartItem> cartItems = cartItemsService.getPaginatedCartItemsForUser(user1.getId(), 0, 10);
		assertEquals("One cart item should be in list", 1, cartItems.size());
		
		cartItemsService.delete(cartItem1.getId());
		
		cartItems = cartItemsService.getPaginatedCartItemsForUser(user1.getId(), 0, 10);
		assertTrue("Cart should be empty", cartItems.isEmpty());
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
		
		cartItem2.setQuantity(6);
		
		cartItemsService.createOrUpdate(cartItem2);
		
		CartItem updatedCartItem = cartItemsService.getCartItem(cartItem2.getId());
		
		assertEquals("Updated cart item should equal cart item retrieved from database", cartItem2, updatedCartItem);
	}

	@Test(expected = AuthenticationCredentialsNotFoundException.class)
	@Override
	public void testUpdateNoAuth() {
		testUpdate();
	}
}
