package com.seaside.hiopenfeign.config;

import feign.Logger;
import feign.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenFeignConfig {

    /**
     * 日志
     * @return
     */
    @Bean
    public Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }


    /**
     * 超时时间配置
     * @return
     */
    @Bean
    public Request.Options options(){
        return new Request.Options(5000,10000);
    }
}
