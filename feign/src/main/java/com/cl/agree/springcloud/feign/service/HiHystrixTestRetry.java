package com.cl.agree.springcloud.feign.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Component;

/**
 * <p>Descriptions...
 *
 * @author cl
 * @date 2019/12/1.
 */
@Component
public class HiHystrixTestRetry implements FeignServiceTestRetry{
    @Autowired
    LoadBalancerClient loadBalancer;

    @Override
    public String timeout(int mills) {
        return null;
    }
}
