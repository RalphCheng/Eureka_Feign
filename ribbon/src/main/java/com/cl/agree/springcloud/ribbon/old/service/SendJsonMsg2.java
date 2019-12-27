package com.cl.agree.springcloud.ribbon.old.service;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p>Descriptions...
 *
 * @author cl
 * @date 2019/11/26.
 */
//@FeignClient(name = "sendJsonMsg2", url = "https://192.9.200.71:10014", configuration = FeignHttpsConfig.class)
public interface SendJsonMsg2 {
    
    @PostMapping("/")
    byte[] sendMsg(@RequestBody String value);
}
