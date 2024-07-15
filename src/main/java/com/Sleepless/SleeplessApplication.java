package com.Sleepless;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.io.FileNotFoundException;

@SpringBootApplication
@EnableMongoRepositories(basePackages = {"com.Sleepless.mongo.repository"})
@EnableJpaRepositories(basePackages = {"com.Sleepless.user.repository"})
public class SleeplessApplication {

	public static void main(String[] args) throws FileNotFoundException {
		SpringApplication.run(SleeplessApplication.class, args);
	}

}
