package com.kuba.shooting.range.management.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = {
        "com.kuba.shooting.range.management.database.dao.springdata",
        "com.kuba.shooting.range.management.services",
        "com.kuba.shooting.range.management.session"
})
@EnableJpaRepositories("com.kuba.shooting.range.management.database.dao.springdata")
public class TestConfiguration {

}
