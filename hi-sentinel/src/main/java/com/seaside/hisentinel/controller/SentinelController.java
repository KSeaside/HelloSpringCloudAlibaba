package com.seaside.hisentinel.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.Tracer;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

public class SentinelController {

    public static final String RESOURCE_NAME = "sentinel";//资源名

    //进行Sentinel流控
    @RequestMapping(value = "/sentinel")
    public String sentinelTest(){
        Entry entry = null;
        try {
            //sentinel针对资源进行限制
            entry = SphU.entry(RESOURCE_NAME);
            //被保护的业务逻辑
            String str = "Sentinel接口正常";
            System.out.println("====" + str + "====");
            return str;
        } catch (BlockException e) {
            e.printStackTrace();
            //资源访问阻止，被限流或被降级
            //进行相应的处理操作
            System.out.println("Sentinel接口被流控了");
            return "Sentinel接口被流控了";
        }catch (Exception e){
            // 若需要配置降级规则，需要通过这种方式记录业务异常
            Tracer.traceEntry(e,entry);
        }finally {
            if(entry != null){
                entry.exit();
            }
        }
        return null;
    }

    /**
     * 定义规则
     *
     * spring的初始化方法
     */
    @PostConstruct
    private static void initFlowRules(){
        //流控规则
        List<FlowRule> rules = new ArrayList<>();
        //流控
        FlowRule rule = new FlowRule();
        //设置受保护的资源
        rule.setResource(RESOURCE_NAME);
        // 设置流控规则 QPS
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        //设置受保护资源的阈值
        // Set limit QPS to 20.
        rule.setCount(1);
        rules.add(rule);
        //加载配置好的规则
        FlowRuleManager.loadRules(rules);
    }

}
