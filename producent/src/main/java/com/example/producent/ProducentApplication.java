package com.example.producent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class ProducentApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProducentApplication.class, args);
	}

}
