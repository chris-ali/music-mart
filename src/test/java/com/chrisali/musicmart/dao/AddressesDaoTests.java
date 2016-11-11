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

import com.chrisali.musicmart.model.user.Address;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class AddressesDaoTests extends DaoTestData implements DaoTests {

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
		
		countriesDao.createOrUpdate(country1);
		countriesDao.createOrUpdate(country2);
		countriesDao.createOrUpdate(country3);
		
		addressesDao.createOrUpdate(address1);
		addressesDao.createOrUpdate(address2);
		addressesDao.createOrUpdate(address3);
		
		List<Address> addresses = addressesDao.getPaginatedAddressesForUser(user1.getId(), 0, 10);
		
		assertEquals("Three addresses should be in list belonging to user 1", 3, addresses.size());
		
		addressesDao.createOrUpdate(address4);
		addressesDao.createOrUpdate(address5);
		addressesDao.createOrUpdate(address6);
		addressesDao.createOrUpdate(address7);
		addressesDao.createOrUpdate(address8);
		addressesDao.createOrUpdate(address9);
		
		addresses = addressesDao.getPaginatedAddressesForUser(user2.getId(), 0, 10);
		
		assertEquals("Three addresses should be in list belonging to user 2", 3, addresses.size());
		
		addresses = addressesDao.getPaginatedAddressesForUser(user3.getId(), 0, 10);
		
		assertEquals("Three addresses should be in list belonging to user 3", 3, addresses.size());
	}

	@Test
	@Override
	public void testExists() {
		addTestData();
		
		assertTrue("Address should exist in database", addressesDao.exists(address1.getId()));
		
		Address noExist = new Address("Nobody", "131 Nowhere St", "", "Detroid", "Michigan", "48201", "555-555-8989", country1, user3);
		noExist.setId(Integer.MAX_VALUE);
		
		assertFalse("Address should not exist in database", addressesDao.exists(noExist.getId()));
	}

	@Test
	@Override
	public void testDelete() {
		addTestData();
		
		List<Address> addresses = addressesDao.getPaginatedAddressesForUser(user2.getId(), 0, 10);
		
		addressesDao.delete(address4.getId());
		
		addresses = addressesDao.getPaginatedAddressesForUser(user2.getId(), 0, 10);
		
		assertEquals("Two addresses should be left in list belonging to user 2", 2, addresses.size());
	}

	@Test
	@Override
	public void testUpdate() {
		addTestData();
		
		address7.setPhoneNumber("609-984-1285");
		address7.setCountry(country3);
		
		addressesDao.createOrUpdate(address7);
		
		Address updatedAddress = addressesDao.getAddress(address7.getId());
		
		assertEquals("Updated address should equal address retrieved from database", address7, updatedAddress);
	}
}
