package com.teOrganiza_service.shared.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import javax.sql.DataSource;
import org.springframework.core.io.ByteArrayResource;

@Configuration
public class SchemaConfig {

    @Bean
    public DataSourceInitializer dataSourceInitializer(DataSource dataSource) {
        DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(
            new ByteArrayResource("CREATE SCHEMA IF NOT EXISTS identity;".getBytes())
        );
        populator.addScript(
            new ByteArrayResource("CREATE SCHEMA IF NOT EXISTS finance;".getBytes())
        );
        initializer.setDatabasePopulator(populator);
        return initializer;
    }
}
