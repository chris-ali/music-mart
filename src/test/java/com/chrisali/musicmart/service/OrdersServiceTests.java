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
import com.chrisali.musicmart.model.order.Order;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SecurityConfiguration.class)
@SpringBootTest
@ActiveProfiles("test")
public class OrdersServiceTests extends ServiceTestData implements ServiceTests {

	@Before
	public void init() {
		clearDatabase();
	}

	@Test
	@WithMockUser(username="admin", roles={"USER","ADMIN"})
	@Override
	public void testCreateRetrieve() {
		addTestData();

		List<Order> orders = ordersService.getPaginatedOrdersForUser(user1.getId(), 0, 10);

		assertEquals("Three orders should be in list for user 1", 3, orders.size());

		orders = ordersService.getPaginatedOrdersForUser(user3.getId(), 0, 10);

		assertEquals("No orders should be in list for user 3", 0, orders.size());

		long totalOrdersUser = ordersService.getTotalNumberOfOrdersForUser(user1.getId());

		assertEquals("Three orders should be in database for user 1", 3, totalOrdersUser);
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

		assertTrue("Cart item should exist in database", ordersService.exists(ordersList.get(2).getId()));

		Order noExist = new Order(user1, country1, country3);
		noExist.setId(Integer.MAX_VALUE);

		assertFalse("Cart item should not exist in database", ordersService.exists(noExist.getId()));
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

		List<Order> orders = ordersService.getPaginatedOrdersForUser(user1.getId(), 0, 10);
		assertEquals("Three orders should be in list", 3, orders.size());

		ordersService.delete(ordersList.get(1).getId());

		orders = ordersService.getPaginatedOrdersForUser(user1.getId(), 0, 10);
		assertEquals("Two orders should now be in list", 2, orders.size());
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

		ordersList.get(1).setShippingName("Uosdwis R Dewoh");

		ordersService.createOrUpdate(ordersList.get(1));

		Order updatedOrder = ordersService.getOrder(ordersList.get(1).getId());

		assertEquals("Updated order should equal order retrieved from database", ordersList.get(1), updatedOrder);
	}

	@Test(expected = AuthenticationCredentialsNotFoundException.class)
	@Override
	public void testUpdateNoAuth() {
		testUpdate();
	}
}
