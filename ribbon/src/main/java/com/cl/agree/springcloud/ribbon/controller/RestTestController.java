package com.cl.agree.springcloud.ribbon.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * <p>Descriptions...
 *
 * @author cl
 * @date 2019/11/30.
 */
@Slf4j
@RestController
public class RestTestController {
    @GetMapping("/testRest")
    public String testRest(){
        RestTemplate template = new RestTemplate();
        return template.getForObject("https://www.baidu.com/", String.class);
        // RestTemplate 的使用很简单，它支持 Xml、JSON 数据格式，默认实现了序列化，可以自动将JOSN字符串转换为实体
        //User user = template.getForObject("https://www.xxx.com/", User.class);
    }
    
    /**
     * 测试重试时间
     * @return
     */
    @RequestMapping("/timeout")
    public String timeOut(@RequestParam int mills) {
        log.info("[ribbon服务] [timeOut方法]收到请求,阻塞{}ms", mills);
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("[ribbon服务] [timeout]返回请求");
        return String.format("ribbon服务-%s 请求ok!!!");
    }
}
