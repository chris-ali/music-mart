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

import com.chrisali.musicmart.model.product.Product;
import com.chrisali.musicmart.model.user.CartItem;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class CartItemsDaoTests extends DaoTestData implements DaoTests {

	@Before
	public void init() {
		clearDatabase();
	}
	
	@Test
	@Override
	public void testCreateRetrieve() {
		addTestData();
		
		cartItemsDao.createOrUpdate(cartItem1);
		
		List<CartItem> cartItems = cartItemsDao.getPaginatedCartItemsForUser(user1.getId(), 0, 10);
		
		assertEquals("One cart item should be in list for user 1", 1, cartItems.size());
		
		cartItemsDao.createOrUpdate(cartItem2);
		
		cartItems = cartItemsDao.getPaginatedCartItemsForUser(user2.getId(), 0, 10);
		
		assertEquals("One cart item should be in list for user 2", 1, cartItems.size());
		
		cartItemsDao.createOrUpdate(cartItem3);
		
		cartItems = cartItemsDao.getPaginatedCartItemsForUser(user3.getId(), 0, 10);
		
		assertEquals("One cart item should be in list for user 3", 1, cartItems.size());
		
		long totalCartItemsUser = cartItemsDao.getTotalNumberOfCartItemsForUser(user3.getId());
		
		assertEquals("One cart item should be in database for user 3", 1, totalCartItemsUser);
	}

	@Test
	@Override
	public void testExists() {
		addTestData();
		
		assertTrue("Cart item should exist in database", cartItemsDao.exists(cartItem1.getId()));
		
		CartItem noExist = new CartItem(user1, new Product(), 10, 1233.00f);
		noExist.setId(Integer.MAX_VALUE);
		
		assertFalse("Cart item should not exist in database", cartItemsDao.exists(noExist.getId()));
	}

	@Test
	@Override
	public void testDelete() {
		addTestData();
		
		List<CartItem> cartItems = cartItemsDao.getPaginatedCartItemsForUser(user1.getId(), 0, 10);
		assertEquals("One cart item should be in list", 1, cartItems.size());
		
		cartItemsDao.delete(cartItem1.getId());
		
		cartItems = cartItemsDao.getPaginatedCartItemsForUser(user1.getId(), 0, 10);
		assertTrue("Cart should be empty", cartItems.isEmpty());
	}

	@Test
	@Override
	public void testUpdate() {
		addTestData();
		
		cartItem2.setQuantity(6);
		
		cartItemsDao.createOrUpdate(cartItem2);
		
		CartItem updatedCartItem = cartItemsDao.getCartItem(cartItem2.getId());
		
		assertEquals("Updated cart item should equal cart item retrieved from database", cartItem2, updatedCartItem);
	}
}
