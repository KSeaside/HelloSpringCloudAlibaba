package com.seaside.hiorder;

import com.seaside.hiorder.ribbon.RibbonRuleConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;

@SpringBootApplication
@EnableDiscoveryClient
@RibbonClients(value = {
        @RibbonClient(name = "hi-stock",configuration = RibbonRuleConfig.class)
})
public class HiOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(HiOrderApplication.class, args);
    }

}
