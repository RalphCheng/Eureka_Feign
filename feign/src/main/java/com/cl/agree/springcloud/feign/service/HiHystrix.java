package com.cl.agree.springcloud.feign.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Component;

/**
 * <p>Descriptions...
 *
 * @author cl
 * @date 2019/11/30.
 */
@Component
public class HiHystrix implements FeignService{
    @Autowired
    LoadBalancerClient loadBalancer;
    
    @Override
    public String sayHiFromEurekaClient(String name) {
        return "Feign Mission Failed! Port: " + loadBalancer.choose("eureka-client").getPort();
    }
    
    @Override
    public String timeout(int mills) {
        System.out.println("熔断");
        return "熔断了";
    }
}
