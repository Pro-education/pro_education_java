package ru.ershov.pro_education.config;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.testcontainers.containers.PostgreSQLContainer;

@Profile("it")
@Configuration
public class InitDb implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private PostgreSQLContainer sqlContainer;

    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        sqlContainer = new PostgreSQLContainer("postgres")
                .withDatabaseName("postgres")
                .withUsername("postgres")
                .withPassword("postgres");
        sqlContainer.start();

        TestPropertyValues.of(
                "spring.datasource.url=" + sqlContainer.getJdbcUrl(),
                "spring.datasource.username=" + sqlContainer.getUsername(),
                "spring.datasource.password=" + sqlContainer.getPassword()
        ).applyTo(configurableApplicationContext.getEnvironment());
    }
}
