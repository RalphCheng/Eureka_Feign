package com.cl.agree.springcloud.feign.service;

import com.cl.agree.springcloud.feign.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p>Descriptions...
 *
 * @author cl
 * @date 2019/12/5.
 */
@FeignClient(name = "sendJsonMsg", url = "https://192.9.200.71:10013", configuration = FeignConfig.class)
public interface HttpsTest {
    @PostMapping("/")
    byte[] sndCreditBusiAccept(@RequestBody String value);
}
