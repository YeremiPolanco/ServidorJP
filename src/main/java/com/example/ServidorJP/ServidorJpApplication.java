package com.example.ServidorJP;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ServidorJpApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServidorJpApplication.class, args);
	}

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Bean
	public CommandLineRunner createdPasswordsCommand(){
		return args -> {
			System.out.println(passwordEncoder.encode("admin"));
			System.out.println(passwordEncoder.encode("password123"));
			System.out.println(passwordEncoder.encode("password456"));
		};
	}

}
