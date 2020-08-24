package com.saif.parameta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class PatametaInterviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatametaInterviewApplication.class, args);
	}

}
