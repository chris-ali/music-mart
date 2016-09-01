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
public class AddressDaoTests extends DaoTestData implements DaoTests {

	@Before
	public void init() {
		clearDatabase();
	}
	
	@Test
	@Override
	public void testCreateRetrieve() {
		addressBookDao.createOrUpdate(address1);
		addressBookDao.createOrUpdate(address2);
		addressBookDao.createOrUpdate(address3);
		
		List<Address> addresses = addressBookDao.getPaginatedAddresses(user1.getId(), 0, 10);
		
		assertEquals("Three addresses should be in list belonging to user 1", 3, addresses.size());
		
		addressBookDao.createOrUpdate(address4);
		addressBookDao.createOrUpdate(address5);
		addressBookDao.createOrUpdate(address6);
		addressBookDao.createOrUpdate(address7);
		addressBookDao.createOrUpdate(address8);
		addressBookDao.createOrUpdate(address9);
		
		addresses = addressBookDao.getPaginatedAddresses(user2.getId(), 0, 10);
		
		assertEquals("Three addresses should be in list belonging to user 2", 3, addresses.size());
		
		addresses = addressBookDao.getPaginatedAddresses(user3.getId(), 0, 10);
		
		assertEquals("Three addresses should be in list belonging to user 3", 3, addresses.size());
	}

	@Test
	@Override
	public void testExists() {
		addTestData();
		
		assertTrue("Country should exist in database", addressBookDao.exists(country1.getId()));
		
		Address noExist = new Address("Nobody", "131 Nowhere St", "", "Detroid", "Michigan", "48201", "555-555-8989", country1, user3);
		noExist.setId(Integer.MAX_VALUE);
		
		addressBookDao.createOrUpdate(noExist);
		
		assertFalse("country should not exist in database", addressBookDao.exists(noExist.getId()));
	}

	@Test
	@Override
	public void testDelete() {
		addTestData();
		
		List<Address> addresses = addressBookDao.getPaginatedAddresses(user2.getId(), 0, 10);
		
		addressBookDao.delete(address2.getId());
		
		addresses = addressBookDao.getPaginatedAddresses(user2.getId(), 0, 10);
		
		assertEquals("Two addresses should be left in list belonging to user 2", 2, addresses.size());
	}

	@Test
	@Override
	public void testUpdate() {
		addTestData();
		
		address4.setPhoneNumber("609-984-1285");
		address4.setCountry(country3);
		
		addressBookDao.createOrUpdate(address3);
		
		Address updatedAddress = addressBookDao.getAddress(address3.getId());
		
		assertEquals("Updated address should equal address retrieved from database", address3, updatedAddress);
	}
}
