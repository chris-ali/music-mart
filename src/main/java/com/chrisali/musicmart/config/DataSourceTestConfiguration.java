package com.chrisali.musicmart.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@Profile("test")
public class DataSourceTestConfiguration {

	@Autowired
	Environment env;

	@Bean(name = "testDataDource")
	public DataSource dataSource() {
		DataSourceBuilder builder = DataSourceBuilder.create();

		builder.driverClassName(env.getProperty("spring.datasource.driver-class-name"))
				.url(env.getProperty("spring.datasource.url"))
				.username(env.getProperty("spring.datasource.username"))
				.password(env.getProperty("spring.datasource.password"));

		return builder.build();
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(new String[] {"com.chrisali.musicmart.model",
													   "com.chrisali.musicmart.dao",
													   "com.chrisali.musicmart.service"});
		sessionFactory.setHibernateProperties(hibernateProperties());

		return sessionFactory;
	}

	Properties hibernateProperties() {
		return new Properties() {
			private static final long serialVersionUID = 9057467730041849300L;
			{
				setProperty("hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect"));
				//setProperty("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.properties.hibernate.hbm2ddl.auto"));
				setProperty("hibernate.show_sql", env.getProperty("spring.jpa.show-sql"));
				setProperty("hibernate.format_sql", env.getProperty("spring.jpa.format-sql"));
				setProperty("hibernate.use_sql_comments", env.getProperty("spring.jpa.use-sql-comments"));
			}
		};
	}

	@Bean
	public DataSourceTransactionManager transactionManager(SessionFactory sessionFactory) {
		DataSourceTransactionManager txManager = new DataSourceTransactionManager();
		txManager.setDataSource(dataSource());

		return txManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}
}