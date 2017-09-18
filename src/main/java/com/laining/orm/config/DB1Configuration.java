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
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.laining.orm.repository.db1.BookRepository;

@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "db1EntityManagerFactory", basePackageClasses = {
		BookRepository.class })
@EnableTransactionManagement()
public class DB1Configuration {

	@Autowired
	private JpaProperties jpaProperties;

	@Bean
	@Primary
	@ConfigurationProperties("app.datasource.db1")
	public DataSourceProperties db1DataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean
	@Primary
	@ConfigurationProperties("app.datasource.db1")
	public DataSource db1DataSource() {
		return db1DataSourceProperties().initializeDataSourceBuilder().build();
	}

	@Bean
	@Primary
	public LocalContainerEntityManagerFactoryBean db1EntityManagerFactory(EntityManagerFactoryBuilder builder) {
		return builder.dataSource(db1DataSource()).properties(getVendorProperties(db1DataSource()))
				.packages("com.laining.orm.model.db1", "com.laining.orm.repository.db1").persistenceUnit("db1").build();
	}

	@Bean(name = "transactionManager")
	@Primary
	PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder) {
		return new JpaTransactionManager(db1EntityManagerFactory(builder).getObject());
	}

	private Map<String, String> getVendorProperties(DataSource dataSource) {
		return jpaProperties.getHibernateProperties(dataSource);
	}

}
