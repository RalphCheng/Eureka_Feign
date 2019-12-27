package com.cl.agree.springcloud.ribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * <p>Descriptions...
 *
 * @author cl
 * @date 2019/11/30.
 */
@Service
public class RibbonService {
    @Autowired
    RestTemplate restTemplate;
    
    @Autowired
    private LoadBalancerClient loadBalancer;
    
//    https://www.cnblogs.com/yinjihuan/p/10940403.html
//    @HystrixCommand(fallbackMethod = "hiError", commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
//    })
    @HystrixCommand(fallbackMethod = "hiError")
    public String hi(String name){
        return restTemplate.getForObject("http://eureka-client/hi?name=" + name, String.class);
    }
    
    public String hiError(String name){
        return "Port: " + loadBalancer.choose("eureka-client").getPort() + " Mission Failed, " + name;
    }
}
