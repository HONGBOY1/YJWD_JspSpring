package com.kong.king.spring.security10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringStudySecurity10Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringStudySecurity10Application.class, args);
	}

}
