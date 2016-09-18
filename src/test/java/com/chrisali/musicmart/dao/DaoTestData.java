package com.chrisali.musicmart.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;

import com.chrisali.musicmart.model.Country;
import com.chrisali.musicmart.model.order.CreditCardType;
import com.chrisali.musicmart.model.order.Order;
import com.chrisali.musicmart.model.order.OrderStatus;
import com.chrisali.musicmart.model.order.ShippingMethod;
import com.chrisali.musicmart.model.product.Manufacturer;
import com.chrisali.musicmart.model.product.Product;
import com.chrisali.musicmart.model.product.ProductDescription;
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
	
	// Manufacturers
	protected Manufacturer manufacturer1 = new Manufacturer("test1", "www.test1.com/logo.jpg", "www.test1.com");
	protected Manufacturer manufacturer2 = new Manufacturer("test2", "www.test2.com/logo.jpg", "www.test2.com");
	protected Manufacturer manufacturer3 = new Manufacturer("test3", "www.test3.com/logo.jpg", "www.test3.com");
	
	// Product Descriptions
	//protected ProductDescription description1 = new ProductDescription(product, name, description, url)
	
	// Products
	protected Product product1 = new Product(manufacturer1, new ProductDescription()); 
	protected Product product2 = new Product(manufacturer2, new ProductDescription());
	protected Product product3 = new Product(manufacturer3, new ProductDescription());
	
	// Items
	protected CartItem cartItem1 = new CartItem(user1, product1, 1, 29.95f);
	protected CartItem cartItem2 = new CartItem(user2, product2, 3, 33.00f);
	protected CartItem cartItem3 = new CartItem(user3, product3, 4, 1120.00f);
	
	// List of Orders
	protected List<Order> ordersList = new ArrayList<>();
	
	// DAO
	@Autowired
	protected UsersDao usersDao;
	
	@Autowired
	protected CountriesDao countriesDao;
	
	@Autowired
	protected AddressesDao addressesDao;
	
	@Autowired
	protected CartItemsDao cartItemsDao;
	
	@Autowired
	protected OrdersDao ordersDao;
	
	// Database Setup
	@Autowired
	private DataSource dataSource;
	
	protected void clearDatabase() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		
		jdbc.execute("delete from cart_items");
		jdbc.execute("delete from addresses");
		jdbc.execute("delete from countries");
		jdbc.execute("delete from orders");
		jdbc.execute("delete from users");
	}
	
	protected void addTestData() {
		usersDao.createOrUpdate(user1);
		usersDao.createOrUpdate(user2);
		usersDao.createOrUpdate(user3);
		
		countriesDao.createOrUpdate(country1);
		countriesDao.createOrUpdate(country2);
		countriesDao.createOrUpdate(country3);
		
		addressesDao.createOrUpdate(address1);
		addressesDao.createOrUpdate(address2);
		addressesDao.createOrUpdate(address3);
		addressesDao.createOrUpdate(address4);
		addressesDao.createOrUpdate(address5);
		addressesDao.createOrUpdate(address6);
		addressesDao.createOrUpdate(address7);
		addressesDao.createOrUpdate(address8);
		addressesDao.createOrUpdate(address9);
		
		cartItemsDao.createOrUpdate(cartItem1);
		cartItemsDao.createOrUpdate(cartItem2);
		cartItemsDao.createOrUpdate(cartItem3);
		
		setUpOrders();
		
		for(Order order : ordersList)
			ordersDao.createOrUpdate(order);
	}
	
	protected void setUpOrders() {
		Order order;
		for (int i = 0; i < 3; i++) {
			order = new Order(user1, country1, country2);
			order.setBillingName(address1.getName());
			order.setBillingAddress1(address1.getAddressLine1());
			order.setBillingAddress2(address1.getAddressLine2());
			order.setBillingCity(address1.getCity());
			order.setBillingPhoneNumber(address1.getPhoneNumber());
			order.setBillingEmail(user1.getEmail());
			order.setBillingPostalCode(address1.getPostalCode());
			order.setBillingStateProvince(address1.getStateProvince());
			
			order.setShippingName(address1.getName());
			order.setShippingAddress1(address1.getAddressLine1());
			order.setShippingAddress2(address1.getAddressLine2());
			order.setShippingCity(address1.getCity());
			order.setShippingPhoneNumber(address1.getPhoneNumber());
			order.setShippingPostalCode(address1.getPostalCode());
			order.setShippingStateProvince(address1.getStateProvince());
			
			order.setPaymentMethod("Credit Card");
			order.setCreditCardType(CreditCardType.VISA);
			order.setCreditCardName(address1.getName());
			order.setCreditCardNumber("01234 5678 9012 3456");
			order.setCreditCardExpiration("09/18");
			
			order.setShippingMethod(ShippingMethod.FEDEX_GROUND);
			order.setShippingCost(10.99f);
			order.setDatePurchased(new Date());
			order.setOrderStatus(OrderStatus.IN_TRANSIT);
			order.setLastModified(new Date());
			order.setOrderCompleted(new Date());
			order.setComments("test");
			
			ordersList.add(order);
		}
	}
}
