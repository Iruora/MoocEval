package com.arouri;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@SpringBootApplication
public class MicsysApplication {
	public static void main(String[] args) {
		SpringApplication.run(MicsysApplication.class, args);
	}
}
