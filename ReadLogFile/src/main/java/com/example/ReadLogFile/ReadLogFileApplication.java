package com.example.ReadLogFile;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.slf4j.Logger;

@SpringBootApplication
public class ReadLogFileApplication {
	private static final Logger log = LoggerFactory.getLogger(ReadLogFileApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ReadLogFileApplication.class, args);
	}
}
