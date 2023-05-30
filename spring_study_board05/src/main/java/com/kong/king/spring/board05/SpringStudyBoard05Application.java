package com.kong.king.spring.board05;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringStudyBoard05Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringStudyBoard05Application.class, args);
	}

}
