package com.example.ticketvalidator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class TicketValidationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TicketValidationServiceApplication.class, args);
    }

}
