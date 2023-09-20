package com.autarklab.sbootbasicsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class SbootBasicsApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SbootBasicsApiApplication.class, args);
    }

}
