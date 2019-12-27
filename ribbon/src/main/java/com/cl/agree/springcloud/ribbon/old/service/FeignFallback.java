package com.cl.agree.springcloud.ribbon.old.service;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>Descriptions...
 *
 * @author cl
 * @date 2019/11/26.
 */
@Component
@RequestMapping("/fallback/cache")
public class FeignFallback implements SendJsonMsg {
    
    @Override
    public byte[] sndCreditBusiAccept(String value) {
        return null;
    }
    
//    @Override
//    public byte[] sendMsg(String value) {
//        return new String("请求失败").getBytes();
//    }
}
