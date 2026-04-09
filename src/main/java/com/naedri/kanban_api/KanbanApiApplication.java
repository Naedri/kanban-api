package com.naedri.kanban_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class KanbanApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(KanbanApiApplication.class, args);
        // ConfigurableApplicationContext context = SpringApplication.run(KanbanApiApplication.class, args);
        // Environment env = context.getEnvironment();
    }

}
