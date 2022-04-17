package com.seaside.hiorder.controller;


import com.seaside.hiorder.config.RestTemplateConfig;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 库存服务
 */
@RestController
@RequestMapping("/addOrder")
public class OrderController {

    RestTemplate restTemplate = new RestTemplate();
    public String addOrder() {
        System.out.println("订单新增成功");
        //调用库存扣减
        String apiReqResult = restTemplate.getForObject("http://service-stock/stock/subStock", String.class);
        return "订单服务-订单新增成功:" + apiReqResult;
    }

}
