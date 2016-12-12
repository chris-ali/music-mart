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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.chrisali.musicmart.config.DataSourceTestConfiguration;
import com.chrisali.musicmart.config.SecurityConfiguration;
import com.chrisali.musicmart.config.ServiceTestConfiguration;
import com.chrisali.musicmart.model.user.Address;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {SecurityConfiguration.class, 
								 DataSourceTestConfiguration.class,
								 ServiceTestConfiguration.class})
@SpringBootTest
public class AddressesServiceTests extends ServiceTestData implements ServiceTests {

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
		
		countriesService.createOrUpdate(country1);
		countriesService.createOrUpdate(country2);
		countriesService.createOrUpdate(country3);
		
		addressesService.createOrUpdate(address1);
		addressesService.createOrUpdate(address2);
		addressesService.createOrUpdate(address3);
		
		List<Address> addresses = addressesService.getPaginatedAddressesForUser(user1.getId(), 0, 10);
		
		assertEquals("Three addresses should be in list belonging to user 1", 3, addresses.size());
		
		addressesService.createOrUpdate(address4);
		addressesService.createOrUpdate(address5);
		addressesService.createOrUpdate(address6);
		addressesService.createOrUpdate(address7);
		addressesService.createOrUpdate(address8);
		addressesService.createOrUpdate(address9);
		
		addresses = addressesService.getPaginatedAddressesForUser(user2.getId(), 0, 10);
		
		assertEquals("Three addresses should be in list belonging to user 2", 3, addresses.size());
		
		addresses = addressesService.getPaginatedAddressesForUser(user3.getId(), 0, 10);
		
		assertEquals("Three addresses should be in list belonging to user 3", 3, addresses.size());
		
		long totalAddressesUser = addressesService.getTotalNumberOfAddressesForUser(user2.getId());
		
		assertEquals("Three addresses should be in database belonging to user 2", 3, totalAddressesUser);
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
		
		assertTrue("Address should exist in database", addressesService.exists(address1.getId()));
		
		Address noExist = new Address("Nobody", "131 Nowhere St", "", "Detroid", "Michigan", "48201", "555-555-8989", country1, user3);
		noExist.setId(Integer.MAX_VALUE);
		
		assertFalse("Address should not exist in database", addressesService.exists(noExist.getId()));
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
		
		List<Address> addresses = addressesService.getPaginatedAddressesForUser(user2.getId(), 0, 10);
		
		addressesService.delete(address4.getId());
		
		addresses = addressesService.getPaginatedAddressesForUser(user2.getId(), 0, 10);
		
		assertEquals("Two addresses should be left in list belonging to user 2", 2, addresses.size());
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
		
		address7.setPhoneNumber("609-984-1285");
		address7.setCountry(country3);
		
		addressesService.createOrUpdate(address7);
		
		Address updatedAddress = addressesService.getAddress(address7.getId());
		
		assertEquals("Updated address should equal address retrieved from database", address7, updatedAddress);
	}

	@Test(expected = AuthenticationCredentialsNotFoundException.class)
	@Override
	public void testUpdateNoAuth() {
		testUpdate();
	}
}
