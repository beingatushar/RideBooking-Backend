package com.beingatushar.ubercommons;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class UberCommonsApplication {

    public static void main(String[] args) {
        SpringApplication.run(UberCommonsApplication.class, args);
    }

}
