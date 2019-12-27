package com.cl.agree.springcloud.feign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>Descriptions...
 *  测试全局不重试，部分重试
 *  xxxTestRetry中的是不重试的
 * @author cl
 * @date 2019/12/1.
 */
@FeignClient(value = "eureka-ribbon-client", fallback = HiHystrix.class)
public interface FeignServiceTestRetry {
    @GetMapping("/timeout")
    String timeout(@RequestParam(value = "mills") int mills);
}
