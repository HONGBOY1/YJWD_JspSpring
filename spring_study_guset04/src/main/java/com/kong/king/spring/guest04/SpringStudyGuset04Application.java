package com.kong.king.spring.guest04;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringStudyGuset04Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringStudyGuset04Application.class, args);
	}

}
