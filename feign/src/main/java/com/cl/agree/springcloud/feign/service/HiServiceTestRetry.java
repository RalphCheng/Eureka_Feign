package com.cl.agree.springcloud.feign.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>Descriptions...
 *
 * @author cl
 * @date 2019/12/1.
 */
@Service
public class HiServiceTestRetry {
    @Autowired
    FeignServiceTestRetry feignServiceTestRetry;

    public String timeout(int mills) {
        return feignServiceTestRetry.timeout(mills);
    }
}
