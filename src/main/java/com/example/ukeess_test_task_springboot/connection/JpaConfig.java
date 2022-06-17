package com.example.ukeess_test_task_springboot.connection;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class JpaConfig {

    @Value("${url}")
    private String URL;
    @Value("${username}")
    private String USERNAME;
    @Value("${password}")
    private String PASSWORD;
    @Value("${driver-class-name}")
    private String JDBC_DRIVER;

    @Bean
    public DataSource getDataSource()
    {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(JDBC_DRIVER);
        dataSourceBuilder.url(URL);
        dataSourceBuilder.username(USERNAME);
        dataSourceBuilder.password(PASSWORD);
        return dataSourceBuilder.build();
    }
}
