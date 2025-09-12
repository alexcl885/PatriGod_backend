package com.patrigod.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.patrigod")
@EnableJpaRepositories(basePackages = "com.patrigod")
@EntityScan(basePackages = "com.patrigod")
public class PatrigodApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatrigodApplication.class, args);
	}

}
