package com.example.mastercardservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
public class MastercardserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MastercardserviceApplication.class, args);
    }
    @Bean
    public RestTemplate restTemplate() {

        return new RestTemplate();
    }
}
