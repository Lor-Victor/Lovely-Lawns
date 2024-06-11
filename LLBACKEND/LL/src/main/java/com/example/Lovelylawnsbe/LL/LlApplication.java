package com.example.Lovelylawnsbe.LL;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.Lovelylawnsbe.LL")

public class LlApplication {

	public static void main(String[] args) {
		SpringApplication.run(LlApplication.class, args);
	}

}
