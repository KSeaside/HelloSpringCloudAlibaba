package com.seaside.hiorder.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Configuration
@Component
public class RestTemplateConfig {
    @Bean
    @LoadBalanced //使RestTemplate请求支持负载均衡
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }

}
