package com.cl.agree.springcloud.feign.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>Descriptions...
 *
 * @author cl
 * @date 2019/11/30.
 */
@Service
public class HiService {
    @Autowired
    FeignService service;
    
    @Autowired
    HttpsTest https;
    
    public String sayHi(String name){
        return service.sayHiFromEurekaClient(name);
    }
    
    public String timeout(int mills) {
        return service.timeout(mills);
    }
    
    public byte[] sendJsonMsg(String name){return https.sndCreditBusiAccept(name);}
}
