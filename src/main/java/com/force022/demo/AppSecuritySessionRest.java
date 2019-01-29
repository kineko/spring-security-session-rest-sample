package com.force022.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value={"com.force022.demo"})
public class AppSecuritySessionRest {

    public static void main(String[] args) {
        SpringApplication.run(AppSecuritySessionRest.class, args);
    }

}
