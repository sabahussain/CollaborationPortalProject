package com.niit.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages= "com.niit")
public class AppConfig implements TransactionManagementConfigurer{
	/*@Autowired
	SessionFactory sessionFactory=ApplicationContextConfig.getSessionFactory(ApplicationContextConfig.getOracleDataSource());*/
	private static final Logger Logger = LoggerFactory.getLogger(AppConfig.class);
	@Bean
	public ViewResolver viewResolver(){
		Logger.debug("Starting of the method viewResolver");
		
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/");
		viewResolver.setSuffix(".html");
		Logger.debug("Ending of the method viewResolver");
		return viewResolver;
	}

	
	
/*	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver multipartResolver() 
	{
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		return resolver;
	}
*/	
	/*@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer){
		configurer.enable();
	}
	*/
	@Bean(name = "dataSource")
	public DataSource getH2DataSource() {

		DriverManagerDataSource datasource=new DriverManagerDataSource();
		datasource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		datasource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		
	    datasource.setUsername("system");
	    datasource.setPassword("password");
	    
	   Properties connectionProperties = new Properties();
	    connectionProperties.setProperty("hibernate.hbm2ddl.auto","update");
	    connectionProperties.setProperty("hibernate.show_sql", "true");
	    connectionProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
	    connectionProperties.setProperty("hibernate.jdbc.use_get_generated_keys", "true");
	    //connectionProperties.setProperty("hibernate.default_schema", "system"); 		
	    datasource.setConnectionProperties(connectionProperties);
	    
		return datasource;
	}

	
	private Properties getHibernateProperties() {
		Properties properties = new Properties();
		
		properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		return properties;
	}

	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) {

		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
		sessionBuilder.addProperties(getHibernateProperties());
		
			return sessionBuilder.buildSessionFactory();
	}

	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);

		return transactionManager;
	}


    @Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(getH2DataSource());
	}



	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return new DataSourceTransactionManager(getH2DataSource());
	}

	
}
