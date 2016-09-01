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

import com.chrisali.musicmart.model.Country;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class CountriesDaoTests extends DaoTestData implements DaoTests {

	@Before
	public void init() {
		clearDatabase();
	}
	
	@Test
	@Override
	public void testCreateRetrieve() {
		countriesDao.createOrUpdate(country1);
		
		List<Country> countries = countriesDao.getAllCountries();
		
		assertEquals("One country should be in list", 1, countries.size());
		
		countriesDao.createOrUpdate(country2);
		countriesDao.createOrUpdate(country3);
		
		countries = countriesDao.getAllCountries();
		
		assertEquals("Three countries should be in list", 3, countries.size());
	}

	@Test
	@Override
	public void testExists() {
		addTestData();
		
		assertTrue("Country should exist in database", countriesDao.exists(country1.getId()));
		
		Country noExist = new Country("Nowhere", "NOW", "NO");
		noExist.setId(Integer.MAX_VALUE);
		
		countriesDao.createOrUpdate(noExist);
		
		assertFalse("country should not exist in database", countriesDao.exists(noExist.getId()));
	}

	@Test
	@Override
	public void testDelete() {
		addTestData();
		
		List<Country> countries = countriesDao.getAllCountries();
		assertEquals("Three countries should be in list", 3, countries.size());
		
		countriesDao.delete(country2.getId());
		
		countries = countriesDao.getAllCountries();
		assertEquals("Two countries should be left in list", 2, countries.size());
	}

	@Test
	@Override
	public void testUpdate() {
		addTestData();
		
		country2.setName("Deutschland");
		
		countriesDao.createOrUpdate(country2);
		
		Country updatedcountry = countriesDao.getCountry(country2.getId());
		
		assertEquals("Updated country should equal country retrieved from database", country2, updatedcountry);
	}
}
