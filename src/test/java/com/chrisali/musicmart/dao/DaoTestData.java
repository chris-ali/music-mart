package com.chrisali.musicmart.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.chrisali.musicmart.model.Country;
import com.chrisali.musicmart.model.product.Product;
import com.chrisali.musicmart.model.user.AddressBook;
import com.chrisali.musicmart.model.user.BasketItem;
import com.chrisali.musicmart.model.user.User;

@Component
public class DaoTestData {
	
	// Users
	protected User user1 = new User("Chris", "Ali", "chrisali@test.com", "test1", true, "ROLE_ADMIN");
	protected User user2 = new User("Sohpie", "Frueh", "sophiefrueh@test.com", "test2", true, "ROLE_USER");
	protected User user3 = new User("Tadashi", "Saya", "tadashisaya@test.com", "test3", true, "ROLE_USER");
	
	// Countries
	protected Country country1 = new Country("United States", "US", "USA");
	protected Country country2 = new Country("Germany", "DE", "DEU");
	protected Country country3 = new Country("Japan", "JP", "JPN");
	
	// Addresses
	protected AddressBook address1 = new AddressBook("Chris Ali", "8 Allerton Way", "", "East Windsor", "New Jersey", "08520", "609-651-2054", country1, user1);
	protected AddressBook address2 = new AddressBook("Sophia Frueh", "Inge Beisheim Platz 95", "", "Amelinghausen", "Niedersachsen", "21385", "04132 76 12 71", country2, user2);
	protected AddressBook address3 = new AddressBook("Tadashi Saya", "101-1013", "Harumi Ofuisutawa (30-kai)", "Chuou", "Tokyo", "104-6230", "+8162-431-2300", country3, user3);
	
	// Items
	protected BasketItem item1 = new BasketItem(user1, new Product(), 1, 29.95f);
	protected BasketItem item2 = new BasketItem(user2, new Product(), 3, 33.00f);
	protected BasketItem item3 = new BasketItem(user3, new Product(), 4, 1120.00f);
	
	// DAO
	@Autowired
	protected UsersDao usersDao;
	
	@Autowired
	protected CountriesDao countriesDao;
	
	@Autowired
	protected AddressBookDao addressBookDao;
	
	@Autowired
	protected BasketItemsDao basketItemsDao;
	
	// Database Setup
	@Autowired
	private DataSource dataSource;
	
	protected void clearDatabase() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		
		jdbc.execute("delete from basket_item");
		jdbc.execute("delete from address_book");
		jdbc.execute("delete from countries");
		jdbc.execute("delete from users");
	}
	
	protected void addTestData() {
		usersDao.createOrUpdate(user1);
		usersDao.createOrUpdate(user2);
		usersDao.createOrUpdate(user3);
		
		countriesDao.createOrUpdate(country1);
		countriesDao.createOrUpdate(country2);
		countriesDao.createOrUpdate(country3);
		
		addressBookDao.createOrUpdate(address1);
		addressBookDao.createOrUpdate(address2);
		addressBookDao.createOrUpdate(address3);
		
		basketItemsDao.createOrUpdate(item1);
		basketItemsDao.createOrUpdate(item2);
		basketItemsDao.createOrUpdate(item3);
	}
}
