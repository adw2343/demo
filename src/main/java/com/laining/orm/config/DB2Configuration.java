package com.laining.orm.config;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.laining.orm.repository.db2.StudentRepository;

/**
 * 设置为Primary的EntityManager能够使用框架默认注入的PlatformTransactionManager,而其他的EntityManager需要手动开启事务管理器
 * 
 * @see {@link https://docs.spring.io/spring-boot/docs/2.0.0.M3/reference/htmlsingle/#howto-use-two-entity-managers}
 * 
 * @author laining
 *
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "db2EntityManagerFactory", basePackageClasses = {
		StudentRepository.class }, transactionManagerRef = "db2TransactionManager")
public class DB2Configuration {

	@Autowired
	private JpaProperties jpaProperties;

	@Bean
	@ConfigurationProperties("app.datasource.db2")
	public DataSourceProperties db2DataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean
	@ConfigurationProperties("app.datasource.db2")
	public DataSource db2DataSource() {
		return db2DataSourceProperties().initializeDataSourceBuilder().build();
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean db2EntityManagerFactory(EntityManagerFactoryBuilder builder) {
		return builder.dataSource(db2DataSource()).properties(getVendorProperties(db2DataSource()))
				.packages("com.laining.orm.model.db2", "com.laining.orm.repository.db2").persistenceUnit("db2").build();
	}

	@Bean(name = "db2TransactionManager")
	PlatformTransactionManager db2TransactionManager(EntityManagerFactoryBuilder builder) {
		return new JpaTransactionManager(db2EntityManagerFactory(builder).getObject());
	}

	private Map<String, String> getVendorProperties(DataSource dataSource) {
		return jpaProperties.getHibernateProperties(dataSource);
	}

}
