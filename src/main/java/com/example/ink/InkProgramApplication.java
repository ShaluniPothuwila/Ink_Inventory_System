package com.example.ink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.ink") // This is the secret fix!
public class InkProgramApplication {
    public static void main(String[] args) {
        SpringApplication.run(InkProgramApplication.class, args);
    }
}