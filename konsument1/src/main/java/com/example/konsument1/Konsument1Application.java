package com.example.konsument1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class Konsument1Application {

	public static void main(String[] args) {
		SpringApplication.run(Konsument1Application.class, args);
	}

}
