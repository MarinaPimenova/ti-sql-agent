package com.wk.ti.config.database;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.autoconfigure.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class KnowledgeDatabaseConfig {

    @Bean
    @ConfigurationProperties("datasource.knowledge")
    public DataSourceProperties knowledgeDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource knowledgeDataSource(
            @Qualifier("knowledgeDataSourceProperties")
            DataSourceProperties properties) {

        return properties.initializeDataSourceBuilder().build();
    }

    @Bean
    public JdbcClient jdbcClient(@Qualifier("knowledgeDataSource") DataSource knowledgeDataSource) {
        return JdbcClient.create(knowledgeDataSource);
    }
}