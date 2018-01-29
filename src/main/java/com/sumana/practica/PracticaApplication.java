package com.sumana.practica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PracticaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PracticaApplication.class, args);
	}
}