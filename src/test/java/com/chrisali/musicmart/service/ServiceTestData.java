package com.chrisali.musicmart.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.chrisali.musicmart.model.order.CreditCardType;
import com.chrisali.musicmart.model.order.Order;
import com.chrisali.musicmart.model.order.OrderStatus;
import com.chrisali.musicmart.model.order.ShippingMethod;
import com.chrisali.musicmart.model.product.Category;
import com.chrisali.musicmart.model.product.Manufacturer;
import com.chrisali.musicmart.model.product.Product;
import com.chrisali.musicmart.model.product.ProductDescription;
import com.chrisali.musicmart.model.product.Review;
import com.chrisali.musicmart.model.user.Address;
import com.chrisali.musicmart.model.user.CartItem;
import com.chrisali.musicmart.model.user.Country;
import com.chrisali.musicmart.model.user.User;

@Component
public class ServiceTestData {
	
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
	
	// Products
	protected Product product1 = new Product(manufacturer1, "Model 1", "www.test1.com/logo1.jpg", 10); 
	protected Product product2 = new Product(manufacturer2, "Model 2", "www.test2.com/logo2.jpg", 50);
	protected Product product3 = new Product(manufacturer3, "Model 3", "www.test3.com/logo3.jpg", 0);
	
	// Product Descriptions
	protected ProductDescription description1 = new ProductDescription(product1, "Product 1", "Description for Product 1", "www.testDescription1.com");
	protected ProductDescription description2 = new ProductDescription(product2, "Product 2", "Description for Product 2", "www.testDescription2.com");
	protected ProductDescription description3 = new ProductDescription(product3, "Product 3", "Description for Product 3", "www.testDescription3.com");
	
	// Categories
	protected Category category1 = new Category("www.test.com/image.jpg", "Guitars", new ArrayList<>(Arrays.asList(new Product[] {product1, product2})));
	protected Category category2 = new Category("www.test.com/image.jpg", "Basses", new ArrayList<>(Arrays.asList(new Product[] {product3})));
	protected Category category3 = new Category(new ArrayList<>(Arrays.asList(new Category[] {category1, category2})), null, "www.test.com/image.jpg", "Stringed Instruments");
	
	// Items
	protected CartItem cartItem1 = new CartItem(user1, product1, 1, 29.95f);
	protected CartItem cartItem2 = new CartItem(user2, product2, 3, 33.00f);
	protected CartItem cartItem3 = new CartItem(user3, product3, 4, 1120.00f);
	
	// List of Orders
	protected List<Order> ordersList = new ArrayList<>();
	
	// Reviews
	protected Review review1 = new Review(3, product1, user1, "Review for product");
	protected Review review2 = new Review(2, product2, user2, "Another review for product");
	protected Review review3 = new Review(1, product3, user3, "Yet another review for product");
	protected Review review4 = new Review(4, product2, user1, "Review for this product");
	protected Review review5 = new Review(5, product1, user2, "Test review for test product");
	
	// DAO
	@Autowired
	protected UsersService usersService;
	
	@Autowired
	protected CountriesService countriesService;
	
	@Autowired
	protected AddressesService addressesService;
	
	@Autowired
	protected CartItemsService cartItemsService;
	
	@Autowired
	protected OrdersService ordersService;
	
	@Autowired
	protected ManufacturersService manufacturersService;
	
	@Autowired 
	protected ProductsService productsService;
	
	@Autowired
	protected ProductDescriptionsService productDescriptionsService;
	
	@Autowired
	protected CategoriesService categoriesService;
	
	@Autowired
	protected ReviewsService reviewsService;
	
	// Database Setup
	@Autowired
	@Qualifier("testDataSource")
	protected DataSource dataSource;
	
	protected void clearDatabase() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		
		jdbc.execute("delete from reviews");
		jdbc.execute("delete from product_description");
		jdbc.execute("delete from products");
		jdbc.execute("delete from manufacturers");
		jdbc.execute("delete from cart_items");
		jdbc.execute("delete from categories");
		jdbc.execute("delete from addresses");
		jdbc.execute("delete from countries");
		jdbc.execute("delete from orders");
		jdbc.execute("delete from users");
	}
	
	protected void addTestData() {
		usersService.createOrUpdate(user1);
		usersService.createOrUpdate(user2);
		usersService.createOrUpdate(user3);
		
		countriesService.createOrUpdate(country1);
		countriesService.createOrUpdate(country2);
		countriesService.createOrUpdate(country3);
		
		addressesService.createOrUpdate(address1);
		addressesService.createOrUpdate(address2);
		addressesService.createOrUpdate(address3);
		addressesService.createOrUpdate(address4);
		addressesService.createOrUpdate(address5);
		addressesService.createOrUpdate(address6);
		addressesService.createOrUpdate(address7);
		addressesService.createOrUpdate(address8);
		addressesService.createOrUpdate(address9);
		
		manufacturersService.createOrUpdate(manufacturer1);
		manufacturersService.createOrUpdate(manufacturer2);
		manufacturersService.createOrUpdate(manufacturer3);
		
		productsService.createOrUpdate(product1);
		productsService.createOrUpdate(product2);
		productsService.createOrUpdate(product3);
		
		productDescriptionsService.createOrUpdate(description1);
		productDescriptionsService.createOrUpdate(description2);
		productDescriptionsService.createOrUpdate(description3);
		
		category1.setSuperCategory(category3);
		category2.setSuperCategory(category3);
		
		categoriesService.createOrUpdate(category1);
		categoriesService.createOrUpdate(category2);
		categoriesService.createOrUpdate(category3);
		
		cartItemsService.createOrUpdate(cartItem1);
		cartItemsService.createOrUpdate(cartItem2);
		cartItemsService.createOrUpdate(cartItem3);
		
		reviewsService.createOrUpdate(review1);
		reviewsService.createOrUpdate(review2);
		reviewsService.createOrUpdate(review3);
		reviewsService.createOrUpdate(review4);
		reviewsService.createOrUpdate(review5);
		
		setUpOrders();
		
		for(Order order : ordersList)
			ordersService.createOrUpdate(order);
	}
	
	private void setUpOrders() {
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
