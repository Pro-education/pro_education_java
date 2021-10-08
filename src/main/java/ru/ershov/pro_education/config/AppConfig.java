package ru.ershov.pro_education.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class AppConfig {

//    @Bean
//    public JdbcTemplate getJdbcTemplate() {
//        return new JdbcTemplate(getDataSource());
//    }
//
//    private DataSource getDataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setUrl("spring.datasource.url");
//        dataSource.setUsername("spring.datasource.username");
//        dataSource.setPassword("spring.datasource.username");
//        dataSource.setDriverClassName("spring.datasource.driver-class-name");
//        return dataSource;
//    }

}
