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

import com.chrisali.musicmart.model.product.Manufacturer;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ManufacturersDaoTests extends DaoTestData implements DaoTests {

	@Before
	public void init() {
		clearDatabase();
	}
	
	@Test
	@Override
	public void testCreateRetrieve() {
		manufacturersDao.createOrUpdate(manufacturer1);
		
		List<Manufacturer> manufacturers = manufacturersDao.getPaginatedManufacturers(0, 10);
		
		assertEquals("One manufacturer should be in list", 1, manufacturers.size());
		
		manufacturersDao.createOrUpdate(manufacturer2);
		
		manufacturers = manufacturersDao.getPaginatedManufacturers(0, 10);
		
		assertEquals("Two manufacturer should be in list", 2, manufacturers.size());
		
		manufacturersDao.createOrUpdate(manufacturer3);
		
		manufacturers = manufacturersDao.getPaginatedManufacturers(0, 10);
		
		assertEquals("Three manufacturer should be in list", 3, manufacturers.size());
	}

	@Test
	@Override
	public void testExists() {
		addTestData();
		
		assertTrue("Cart item should exist in database", manufacturersDao.exists(manufacturer3.getId()));
		
		Manufacturer noExist = new Manufacturer("no", "no", "no");
		noExist.setId(Integer.MAX_VALUE);
		
		assertFalse("Cart item should not exist in database", manufacturersDao.exists(noExist.getId()));
	}

	@Test
	@Override
	public void testDelete() {
		addTestData();
		
		List<Manufacturer> manufacturers = manufacturersDao.getPaginatedManufacturers(0, 10);
		assertEquals("Three manufacturers should be in list", 3, manufacturers.size());
		
		manufacturersDao.delete(manufacturer1.getId());
		
		manufacturers = manufacturersDao.getPaginatedManufacturers(0, 10);
		assertEquals("Two manufacturers should be in list", 2, manufacturers.size());
	}

	@Test
	@Override
	public void testUpdate() {
		addTestData();
		
		manufacturer2.setName("New Name");
		
		manufacturersDao.createOrUpdate(manufacturer2);
		
		Manufacturer updatedManufacturer = manufacturersDao.getManufacturer(manufacturer2.getId());
		
		assertEquals("Updated manufacturer should equal manufacturer retrieved from database", manufacturer2, updatedManufacturer);
	}
}
