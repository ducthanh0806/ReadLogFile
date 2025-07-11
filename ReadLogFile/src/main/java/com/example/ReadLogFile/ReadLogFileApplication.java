package com.example.ReadLogFile;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import org.slf4j.Logger;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@ComponentScan(basePackages = "com.example.ReadLogFile.model, com.example.ReadLogFile.controller, com.example.ReadLogFile.service" )

public class ReadLogFileApplication {
	private static final Logger log = LoggerFactory.getLogger(ReadLogFileApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ReadLogFileApplication.class, args);
	}
}
