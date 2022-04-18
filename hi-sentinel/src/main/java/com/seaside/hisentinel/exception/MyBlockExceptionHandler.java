package com.seaside.hisentinel.exception;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class MyBlockExceptionHandler implements BlockExceptionHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, BlockException e) throws Exception {
        System.out.println("资源规则的详细信息：" + e.getRule());
        JSONObject resultObj = new JSONObject();
        if(e instanceof FlowException){
            resultObj.put("code",100);
            resultObj.put("msg","接口限流");
        }if(e instanceof DegradeException){
            resultObj.put("code",101);
            resultObj.put("msg","服务降级");
        }if(e instanceof ParamFlowException){
            resultObj.put("code",102);
            resultObj.put("msg","热点参数限流");
        }if(e instanceof SystemBlockException){
            resultObj.put("code",103);
            resultObj.put("msg","触发系统保护规则");
        }if(e instanceof AuthorityException){
            resultObj.put("code",104);
            resultObj.put("msg","授权规则不通过");
        }

        //返回json数据
        response.setStatus(500);
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(resultObj.toString());
    }
}
