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

import com.chrisali.musicmart.model.order.Order;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class OrdersDaoTests extends DaoTestData implements DaoTests {

	@Before
	public void init() {
		clearDatabase();
	}
	
	@Test
	@Override
	public void testCreateRetrieve() {
		addTestData();
		
		List<Order> orders = ordersDao.getPaginatedOrdersForUser(user1.getId(), 0, 10);
		
		assertEquals("One order should be in list for user 1", 3, orders.size());
		
		orders = ordersDao.getPaginatedOrdersForUser(user3.getId(), 0, 10);
		
		assertEquals("No orders should be in list for user 3", 0, orders.size());
	}

	@Test
	@Override
	public void testExists() {
		addTestData();
		
		assertTrue("Cart item should exist in database", ordersDao.exists(ordersList.get(2).getId()));
		
		Order noExist = new Order(user1, country1, country3);
		noExist.setId(Integer.MAX_VALUE);
		
		assertFalse("Cart item should not exist in database", ordersDao.exists(noExist.getId()));
	}

	@Test
	@Override
	public void testDelete() {
		addTestData();
		
		List<Order> orders = ordersDao.getPaginatedOrdersForUser(user1.getId(), 0, 10);
		assertEquals("Three orders should be in list", 3, orders.size());
		
		ordersDao.delete(ordersList.get(1).getId());
		
		orders = ordersDao.getPaginatedOrdersForUser(user1.getId(), 0, 10);
		assertEquals("Two orders should now be in list", 2, orders.size());
	}

	@Test
	@Override
	public void testUpdate() {
		addTestData();
		
		ordersList.get(1).setShippingName("Uosdwis R Dewoh");
		
		ordersDao.createOrUpdate(ordersList.get(1));
		
		Order updatedOrder = ordersDao.getOrder(ordersList.get(1).getId());
		
		assertEquals("Updated order should equal order retrieved from database", ordersList.get(1), updatedOrder);
	}
}
