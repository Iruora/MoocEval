package com.arouri;

import com.arouri.dto.RegistrationForm;
import com.arouri.entity.AppRole;
import com.arouri.service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@ComponentScan
@SpringBootApplication
public class MicsysApplication {
	public static void main(String[] args) {
		SpringApplication.run(MicsysApplication.class, args);
	}

	@Bean
	CommandLineRunner start(AccountService accountService) {
		return args -> {
			accountService.saveRole(new AppRole("USER"));
			accountService.saveRole(new AppRole("ADMIN"));
			accountService.saveUser(
					new RegistrationForm(
							"admin",
							"test",
							"test",
							"Admin",
							"Test"
					)
			);
			accountService.saveUser(
					new RegistrationForm(
							"user",
							"test",
							"test",
							"User",
							"Test"
					)
			);
			accountService.addRoleToUser("admin", "ADMIN");
		};
	}
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
