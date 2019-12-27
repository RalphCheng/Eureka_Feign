package com.cl.agree.springcloud.ribbon.old.service;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p>Descriptions...
 *
 * @author cl
 * @date 2019/11/25.
 */
//@FeignClient(name = "sendJsonMsg", url = "https://192.9.200.71:10013", fallback = FeignFallback.class, configuration = FeignHttpsConfig.class)
public interface SendJsonMsg {
    
    @PostMapping("/")
    byte[] sndCreditBusiAccept(@RequestBody String value);
    
    //@Feign客户端向远程服务器发送请求
    //页面请求从网关到controller，controller注入了feignService，通过feignService的方法调用远程HTTP服务
    //feignService方法上的@PostMapping注解是什么用
    
    //封装一个工具类
    //生成报文头
    //生成应用头
    //获取HTTPS连接并发送报文
}
