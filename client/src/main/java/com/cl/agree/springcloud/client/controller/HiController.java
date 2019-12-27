package com.cl.agree.springcloud.client.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

/**
 * <p>Descriptions...
 *
 * @author cl
 * @date 2019/11/30.
 */
@RestController
@Slf4j
public class HiController {
    @Value("${server.port}")
    private String port;
//    @Value("${spring.application.name}")
//    private String name;
    
    @GetMapping("/hi")
    public String home(@RequestParam String name){
        log.info("[client服务-{}] [hi]收到请求", port);
        log.info("[client服务-{}] [hi]返回请求",port);
        return "Hi " + name + ", I'm from port: " + port;
    }
    
    /**
     * 测试重试时间
     * @return
     */
    @RequestMapping("/timeout")
    public String timeOut(@RequestParam int mills) {
        log.info("[client服务-{}] [timeOut方法]收到请求,阻塞{}ms", port, mills);
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("[client服务-{}] [timeout]返回请求",port);
        return String.format("client服务-%s 请求ok!!!", port);
    }
}
