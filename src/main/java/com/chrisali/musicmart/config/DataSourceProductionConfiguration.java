package com.chrisali.musicmart.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DataSourceProductionConfiguration {
	/*
	@Autowired
	Environment env;

	@Bean(name = "dataSource")
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
	*/
}