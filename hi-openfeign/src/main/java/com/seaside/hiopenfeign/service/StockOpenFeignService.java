package com.seaside.hiopenfeign.service;

import com.seaside.hiopenfeign.config.OpenFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "hi-stock",path = "stock", configuration = OpenFeignConfig.class)
public interface StockOpenFeignService {
    //声明需要调用的rest接口对应的方法
    /**
     * 库存扣减
     * @return
     */
    @RequestMapping("/subStock")
    public String subStock();

    /**
     * 库存新增
     * @return
     */
    @RequestMapping("/addStock")
    public String addStock();
}
