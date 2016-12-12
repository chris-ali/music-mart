package com.chrisali.musicmart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.chrisali.musicmart.dao.AddressesDao;
import com.chrisali.musicmart.dao.CartItemsDao;
import com.chrisali.musicmart.dao.CategoriesDao;
import com.chrisali.musicmart.dao.CountriesDao;
import com.chrisali.musicmart.dao.ManufacturersDao;
import com.chrisali.musicmart.dao.OrdersDao;
import com.chrisali.musicmart.dao.ProductDescriptionsDao;
import com.chrisali.musicmart.dao.ProductsDao;
import com.chrisali.musicmart.dao.ReviewsDao;
import com.chrisali.musicmart.dao.UsersDao;
import com.chrisali.musicmart.service.AddressesService;
import com.chrisali.musicmart.service.CartItemsService;
import com.chrisali.musicmart.service.CategoriesService;
import com.chrisali.musicmart.service.CountriesService;
import com.chrisali.musicmart.service.ManufacturersService;
import com.chrisali.musicmart.service.OrdersService;
import com.chrisali.musicmart.service.ProductDescriptionsService;
import com.chrisali.musicmart.service.ProductsService;
import com.chrisali.musicmart.service.ReviewsService;
import com.chrisali.musicmart.service.UsersService;

@Configuration
public class ServiceTestConfiguration {
	
	@Bean
	public UsersService usersService() {
		return new UsersService();
	}
	
	@Bean
	public AddressesService addressesService() {
		return new AddressesService();
	}
	
	@Bean
	public CartItemsService cartItemsService() {
		return new CartItemsService();
	}
	
	@Bean
	public CategoriesService categoriesService() {
		return new CategoriesService();
	}
	
	@Bean
	public CountriesService countriesService() {
		return new CountriesService();
	}
	
	@Bean
	public ManufacturersService manufacturersService() {
		return new ManufacturersService();
	}
	
	@Bean
	public OrdersService ordersService() {
		return new OrdersService();
	}
	
	@Bean
	public ProductsService productsService() {
		return new ProductsService();
	}
	
	@Bean
	public ProductDescriptionsService productDescriptionsService() {
		return new ProductDescriptionsService();
	}
	
	@Bean
	public ReviewsService reviewsService() {
		return new ReviewsService();
	}
	
	@Bean
	public UsersDao usersDao() {
		return new UsersDao();
	}
	
	@Bean
	public AddressesDao addressesDao() {
		return new AddressesDao();
	}
	
	@Bean
	public CartItemsDao cartItemsDao() {
		return new CartItemsDao();
	}
	
	@Bean
	public CategoriesDao categoriesDao() {
		return new CategoriesDao();
	}
	
	@Bean
	public CountriesDao countriesDao() {
		return new CountriesDao();
	}
	
	@Bean
	public ManufacturersDao manufacturersDao() {
		return new ManufacturersDao();
	}
	
	@Bean
	public OrdersDao ordersDao() {
		return new OrdersDao();
	}
	
	@Bean
	public ProductsDao productsDao() {
		return new ProductsDao();
	}
	
	@Bean
	public ProductDescriptionsDao productDescriptionsDao() {
		return new ProductDescriptionsDao();
	}
	
	@Bean
	public ReviewsDao reviewsDao() {
		return new ReviewsDao();
	}
}
