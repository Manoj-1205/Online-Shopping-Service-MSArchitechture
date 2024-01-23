package com.microservices.orderservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.support.RestTemplateAdapter;

@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate createRestTemplate(){
        return new RestTemplate();
    }
}
