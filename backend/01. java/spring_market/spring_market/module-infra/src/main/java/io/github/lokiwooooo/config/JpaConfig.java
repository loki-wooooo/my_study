package io.github.lokiwooooo.config;

import jakarta.persistence.EntityManagerFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author HSWoo
 * @version 0.0.1
 * @since 2025-02-25
 * <p>
 * Jpa Configuration 설정
 */
@Configuration
@EnableJpaRepositories(basePackages = {"io.github.lokiwooooo.domain.**.repository"})
@EntityScan(basePackages = {"io.github.lokiwooooo.domain.**.entity"})
@Slf4j
public class JpaConfig {

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setShowSql(true);
        adapter.setDatabase(Database.POSTGRESQL);
        adapter.setGenerateDdl(true);  // DDL 생성 활성화
        return adapter;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            @Value("${spring.jpa.hibernate.ddl-auto}") final String ddlAuto,
            @Value("${spring.jpa.properties.hibernate.dialect}") final String databasePlatform,
            @Value("${spring.jpa.properties.hibernate.default_schema}") final String schema,
            @Value("${spring.jpa.properties.hibernate.format_sql}") final Boolean formatSql,
            @Value("${spring.jpa.properties.hibernate.show_sql}") final Boolean showSql,
            DataSource dataSource,
            JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(dataSource);
        factory.setJpaVendorAdapter(jpaVendorAdapter);
        factory.setPackagesToScan("io.github.lokiwooooo.domain.**.entity");

        Properties jpaProperties = new Properties();
        jpaProperties.setProperty("hibernate.dialect", databasePlatform);
        jpaProperties.setProperty("hibernate.hbm2ddl.auto", ddlAuto);
        jpaProperties.setProperty("hibernate.default_schema", schema);
        jpaProperties.setProperty("hibernate.format_sql", formatSql.toString());
        jpaProperties.setProperty("hibernate.show_sql", showSql.toString());

        factory.setJpaProperties(jpaProperties);

        return factory;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
