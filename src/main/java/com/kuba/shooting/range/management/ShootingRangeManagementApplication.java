package com.kuba.shooting.range.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.kuba.shooting.range.management.database.dao.springdata")



//@ComponentScan(basePackages = "com.kuba.shooting.range.management")
//@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
//@EnableJpaRepositories(basePackages = "com.kuba.shooting.range.management.database.dao.springdata")
//@EntityScan(basePackages = "com.kuba.shooting.range.management.model")
public class ShootingRangeManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShootingRangeManagementApplication.class, args);
	}

}
