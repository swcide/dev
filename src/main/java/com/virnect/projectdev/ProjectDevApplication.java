package com.virnect.projectdev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProjectDevApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectDevApplication.class, args);
	}

}
