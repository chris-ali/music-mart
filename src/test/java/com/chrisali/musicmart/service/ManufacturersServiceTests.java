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
import com.chrisali.musicmart.model.product.Manufacturer;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {SecurityConfiguration.class, 
								 DataSourceTestConfiguration.class,
								 ServiceTestConfiguration.class})
@SpringBootTest
@ActiveProfiles("test")
public class ManufacturersServiceTests extends ServiceTestData implements ServiceTests {

	@Before
	public void init() {
		clearDatabase();
	}
	
	@Test
	@WithMockUser(username="admin", roles={"USER","ADMIN"})
	@Override
	public void testCreateRetrieve() {
		manufacturersService.createOrUpdate(manufacturer1);
		
		List<Manufacturer> manufacturers = manufacturersService.getPaginatedManufacturers(0, 10);
		
		assertEquals("One manufacturer should be in list", 1, manufacturers.size());
		
		manufacturersService.createOrUpdate(manufacturer2);
		
		manufacturers = manufacturersService.getPaginatedManufacturers(0, 10);
		
		assertEquals("Two manufacturers should be in list", 2, manufacturers.size());
		
		manufacturersService.createOrUpdate(manufacturer3);
		
		manufacturers = manufacturersService.getPaginatedManufacturers(0, 10);
		
		assertEquals("Three manufacturers should be in list", 3, manufacturers.size());
		
		long totalCount = manufacturersService.getTotalNumberOfManufacturers();
		
		assertEquals("Three manufacturers should be in database", 3, totalCount);
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
		
		assertTrue("Cart item should exist in database", manufacturersService.exists(manufacturer3.getId()));
		
		Manufacturer noExist = new Manufacturer("no", "no", "no");
		noExist.setId(Integer.MAX_VALUE);
		
		assertFalse("Cart item should not exist in database", manufacturersService.exists(noExist.getId()));
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
		
		List<Manufacturer> manufacturers = manufacturersService.getPaginatedManufacturers(0, 10);
		assertEquals("Three manufacturers should be in list", 3, manufacturers.size());
		
		manufacturersService.delete(manufacturer1.getId());
		
		manufacturers = manufacturersService.getPaginatedManufacturers(0, 10);
		assertEquals("Two manufacturers should be in list", 2, manufacturers.size());
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
		
		manufacturer2.setName("New Name");
		
		manufacturersService.createOrUpdate(manufacturer2);
		
		Manufacturer updatedManufacturer = manufacturersService.getManufacturer(manufacturer2.getId());
		
		assertEquals("Updated manufacturer should equal manufacturer retrieved from database", manufacturer2, updatedManufacturer);
	}

	@Test(expected = AuthenticationCredentialsNotFoundException.class)
	@Override
	public void testUpdateNoAuth() {
		testDelete();
	}
}
