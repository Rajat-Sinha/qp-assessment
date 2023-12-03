package com.grocery.mgmt.platform.app.config;

import com.grocery.mgmt.platform.common.util.Constant;
import com.zaxxer.hikari.HikariDataSource;
import liquibase.integration.spring.SpringLiquibase;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories(basePackages = "com.grocery.mgmt.platform.app.repository", entityManagerFactoryRef = "entityManager", transactionManagerRef = "transactionManager")
public class DatabaseConfig {

    @Autowired
    AppConfig appConfig;

    @Bean(name = "entityManager")
    public LocalContainerEntityManagerFactoryBean entityManager() {
        Map<String, Object> jpaPropertiesMap = new HashMap<>();
        jpaPropertiesMap.put(AvailableSettings.FORMAT_SQL, appConfig.getFormatSql());
        jpaPropertiesMap.put(AvailableSettings.HBM2DDL_AUTO, appConfig.getDdlAuto());
        jpaPropertiesMap.put(AvailableSettings.DIALECT, appConfig.getHibernateDialect());
        jpaPropertiesMap.put(AvailableSettings.DEFAULT_SCHEMA, appConfig.getDbSchema());

        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(Constant.ENTITY_PACKAGE);
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabasePlatform(appConfig.getDatabasePlatform());
        vendorAdapter.setGenerateDdl(appConfig.getGenerateDdl());
        vendorAdapter.setShowSql(appConfig.getShowSql());
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaPropertyMap(jpaPropertiesMap);
        return em;
    }

    @Bean
    public DataSource dataSource() {

        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(appConfig.getDbDriverClass());
        dataSource.setJdbcUrl(appConfig.getDbUrl());
        dataSource.setSchema(appConfig.getDbSchema());
        dataSource.setUsername(appConfig.getDbUserName());
        dataSource.setPassword(appConfig.getDbPassword());
        if(appConfig.getDbConnectionTimeout() != null) {
            dataSource.setConnectionTimeout(appConfig.getDbConnectionTimeout());
        }
        if(appConfig.getDbMinimumIdle() != null) {
            dataSource.setMinimumIdle(appConfig.getDbMinimumIdle());
        }
        if(appConfig.getDbMaximumPoolSize() != null) {
            dataSource.setMaximumPoolSize(appConfig.getDbMaximumPoolSize());
        }
        if(appConfig.getDbIdleTimeout() != null) {
            dataSource.setIdleTimeout(appConfig.getDbIdleTimeout());
        }
        dataSource.setPoolName(appConfig.getDbPoolName());
        return dataSource;
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager() {

        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManager().getObject());
        return transactionManager;
    }

    @Bean
    public SpringLiquibase liquibase() {
        if(appConfig.getLiquibaseEnable() != null && Boolean.parseBoolean(appConfig.getLiquibaseEnable())) {
            var liquibase = new SpringLiquibase();
            liquibase.setChangeLog("classpath:db/changelog/db.changelog-master.yaml");
            liquibase.setDataSource(dataSource());
            return liquibase;
        }
        return null;
    }

}
