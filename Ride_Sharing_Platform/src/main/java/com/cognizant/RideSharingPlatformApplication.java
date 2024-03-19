package com.cognizant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Configuration
@EnableJpaRepositories(basePackages = "com.cognizant.repositories")
public class RideSharingPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(RideSharingPlatformApplication.class, args);
	}

}
