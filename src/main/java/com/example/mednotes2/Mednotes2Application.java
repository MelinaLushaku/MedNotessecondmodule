package com.example.mednotes2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class Mednotes2Application {

    public static void main(String[] args) {
        SpringApplication.run(Mednotes2Application.class, args);
    }

}
