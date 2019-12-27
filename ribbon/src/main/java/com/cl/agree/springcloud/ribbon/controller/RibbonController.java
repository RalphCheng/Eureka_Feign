package com.cl.agree.springcloud.ribbon.controller;

import com.cl.agree.springcloud.ribbon.service.RibbonService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Descriptions...
 *
 * @author cl
 * @date 2019/11/30.
 */
@RestController
public class RibbonController {
    @Autowired
    RibbonService service;
    
    /**
     * 负载均衡器 LoadBalancerClient 是从 Eureka Client 获取服务注册列表信息的，
     * 并将服务注册列表信息缓存了一份。 在 LoadBalancerCJient 调用 choose（）方法时，
     * 根据负载均衡策略选择一个服务实例的信息，从而进行了负载均衡。 负载均衡器也可以不从
     * Eureka Client获取注册列表信息， 这时需要自己维护一份服务注册列表信息。
     */
    @Autowired
    private LoadBalancerClient loadBalancer;
    
    @GetMapping("/hi")
    public String hi(@RequestParam(required = false, defaultValue = "Ralph") String name){
        return service.hi(name);
    }
    
    @GetMapping("/testRibbon")
    public String testRibbon(){
        ServiceInstance instance = loadBalancer.choose("eureka-client");
        return instance.getHost() + ": " +instance.getPort();
    }
}
