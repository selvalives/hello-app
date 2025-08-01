package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class HelloAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(HelloAppApplication.class, args);
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello, DevOps! DONE DONE DONE";
    }
}
