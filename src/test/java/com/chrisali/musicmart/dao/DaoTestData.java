package com.chrisali.musicmart.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;

import com.chrisali.musicmart.model.Country;
import com.chrisali.musicmart.model.product.Product;
import com.chrisali.musicmart.model.user.Address;
import com.chrisali.musicmart.model.user.CartItem;
import com.chrisali.musicmart.model.user.User;

@Component
@ActiveProfiles("test")
public class DaoTestData {
	
	// Users
	protected User user1 = new User("Chris", "Ali", "chrisali@test.com", "test1", true, "ROLE_ADMIN");
	protected User user2 = new User("Sohpie", "Frueh", "sophiefrueh@test.de", "test2", true, "ROLE_USER");
	protected User user3 = new User("Tadashi", "Saya", "tadashisaya@test.jp", "test3", true, "ROLE_USER");
	
	// Countries
	protected Country country1 = new Country("United States", "US", "USA");
	protected Country country2 = new Country("Germany", "DE", "DEU");
	protected Country country3 = new Country("Japan", "JP", "JPN");
	
	// Addresses
	protected Address address1 = new Address("Chris Ali", "1 Lockwood Lane", "", "East Windsor", "New Jersey", "08521", "609-631-2654", country1, user1);
	protected Address address2 = new Address("Sophia Frueh", "Inge Beisheim Platz 95", "", "Amelinghausen", "Niedersachsen", "21385", "04132 76 12 71", country2, user1);
	protected Address address3 = new Address("Tadashi Saya", "101-1013", "Harumi Ofuisutawa (30-kai)", "Chuou", "Tokyo", "104-6230", "8162-431-2300", country3, user1);
	protected Address address4 = new Address("Chris Ali", "2 Lockwood Lane", "", "East Windsor", "New Jersey", "08521", "609-631-2654", country1, user2);
	protected Address address5 = new Address("Sophia Frueh", "Inge Beisheim Platz 95", "", "Amelinghausen", "Niedersachsen", "21385", "04132 76 12 71", country2, user2);
	protected Address address6 = new Address("Tadashi Saya", "101-1013", "Harumi Ofuisutawa (30-kai)", "Chuou", "Tokyo", "104-6230", "8162-431-2300", country3, user2);
	protected Address address7 = new Address("Chris Ali", "3 Lockwood Lane", "", "East Windsor", "New Jersey", "08521", "609-631-2654", country1, user3);
	protected Address address8 = new Address("Sophia Frueh", "Inge Beisheim Platz 95", "", "Amelinghausen", "Niedersachsen", "21385", "04132 76 12 71", country2, user3);
	protected Address address9 = new Address("Tadashi Saya", "101-1013", "Harumi Ofuisutawa (30-kai)", "Chuou", "Tokyo", "104-6230", "8162-431-2300", country3, user3);
	
	// Items
	protected CartItem item1 = new CartItem(user1, new Product(), 1, 29.95f);
	protected CartItem item2 = new CartItem(user2, new Product(), 3, 33.00f);
	protected CartItem item3 = new CartItem(user3, new Product(), 4, 1120.00f);
	
	// DAO
	@Autowired
	protected UsersDao usersDao;
	
	@Autowired
	protected CountriesDao countriesDao;
	
	@Autowired
	protected AddressesDao addressBookDao;
	
	@Autowired
	protected CartItemsDao basketItemsDao;
	
	// Database Setup
	@Autowired
	private DataSource dataSource;
	
	protected void clearDatabase() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		
		jdbc.execute("delete from cart_items");
		jdbc.execute("delete from addresses");
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
		addressBookDao.createOrUpdate(address4);
		addressBookDao.createOrUpdate(address5);
		addressBookDao.createOrUpdate(address6);
		addressBookDao.createOrUpdate(address7);
		addressBookDao.createOrUpdate(address8);
		addressBookDao.createOrUpdate(address9);
		
//		basketItemsDao.createOrUpdate(item1);
//		basketItemsDao.createOrUpdate(item2);
//		basketItemsDao.createOrUpdate(item3);
	}
}
