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
import com.chrisali.musicmart.model.user.Country;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SecurityConfiguration.class)
@SpringBootTest
@ActiveProfiles("test")
public class CountriesServiceTests extends ServiceTestData implements ServiceTests {

	@Before
	public void init() {
		clearDatabase();
	}
	
	@Test
	@WithMockUser(username="admin", roles={"USER","ADMIN"})
	@Override
	public void testCreateRetrieve() {
		countriesService.createOrUpdate(country1);
		
		List<Country> countries = countriesService.getAllCountries();
		
		assertEquals("One country should be in list", 1, countries.size());
		
		countriesService.createOrUpdate(country2);
		countriesService.createOrUpdate(country3);
		
		countries = countriesService.getAllCountries();
		
		assertEquals("Three countries should be in list", 3, countries.size());
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
		
		assertTrue("Country should exist in database", countriesService.exists(country1.getId()));
		
		Country noExist = new Country("Nowhere", "NOW", "NO");
		noExist.setId(Integer.MAX_VALUE);
		
		assertFalse("country should not exist in database", countriesService.exists(noExist.getId()));
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
		
		List<Country> countries = countriesService.getAllCountries();
		assertEquals("Three countries should be in list", 3, countries.size());
		
		countriesService.delete(country2.getId());
		
		countries = countriesService.getAllCountries();
		assertEquals("Two countries should be left in list", 2, countries.size());
	}

	@Test(expected = AuthenticationCredentialsNotFoundException.class)
	@Override
	public void testDeleteNoAuth() {
		testDeleteNoAuth();
	}
	
	@Test
	@WithMockUser(username="admin", roles={"USER","ADMIN"})
	@Override
	public void testUpdate() {
		addTestData();
		
		country2.setName("Deutschland");
		
		countriesService.createOrUpdate(country2);
		
		Country updatedcountry = countriesService.getCountry(country2.getId());
		
		assertEquals("Updated country should equal country retrieved from database", country2, updatedcountry);
	}

	@Test(expected = AuthenticationCredentialsNotFoundException.class)
	@Override
	public void testUpdateNoAuth() {
		testUpdate();
	}
}
