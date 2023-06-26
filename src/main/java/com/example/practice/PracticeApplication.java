package com.example.practice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;


import java.util.Scanner;
@SpringBootApplication
@EnableAsync
public class PracticeApplication {

	@Value("${info.company.name}") // Accessing from application properties
	private String company;

	@Autowired
	private Environment env;
	//static char ch;
	public static void main(String[] args) {
		SpringApplication.run(PracticeApplication.class, args);
		//System.out.println(company);
	}

	@Bean
	CommandLineRunner commandLineRunner() {
		return args -> {
			System.out.println("CLI Runner");
			System.out.println(company);
			System.out.println(env.getProperty("info.company.name"));
		};
	}
}
