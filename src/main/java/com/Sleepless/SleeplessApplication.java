package com.Sleepless;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableMongoRepositories(basePackages = {"com.Sleepless.repositories.mongo"})
@EnableJpaRepositories(basePackages = {"com.Sleepless.repositories.jpa"})
@EnableElasticsearchRepositories(basePackages = {"com.Sleepless.repositories.es"})
@EnableTransactionManagement
public class SleeplessApplication {

    public static void main(String[] args) {
        SpringApplication.run(SleeplessApplication.class, args);
    }

}
