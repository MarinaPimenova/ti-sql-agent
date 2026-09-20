package com.wk.ti.config.database;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.autoconfigure.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "knowledgeEntityManagerFactory",
        transactionManagerRef = "knowledgeTransactionManager"
)
public class KnowledgeDatabaseConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.knowledge")
    public DataSourceProperties knowledgeDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource knowledgeDataSource(
            @Qualifier("knowledgeDataSourceProperties")
            DataSourceProperties properties) {

        return properties.initializeDataSourceBuilder().build();
    }
}