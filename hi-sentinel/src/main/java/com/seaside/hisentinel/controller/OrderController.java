package com.seaside.hisentinel.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单服务
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    /**
     * 新增订单
     * @return
     */
    @RequestMapping("/addOrder")
    public String addOrder(){
        System.out.println("订单新增成功");

        return "订单服务-订单新增成功";
    }
}
