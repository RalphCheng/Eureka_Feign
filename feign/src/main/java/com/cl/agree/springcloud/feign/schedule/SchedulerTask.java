package com.cl.agree.springcloud.feign.schedule;

import com.cl.agree.springcloud.feign.service.FeignService;
import com.cl.agree.springcloud.feign.service.HiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>Descriptions...
 *
 * @author cl
 * @date 2019/12/9.
 */
@Component
public class SchedulerTask {
//    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
//    private int count=0;
//    @Autowired
//    HiService service;
//
//
//    @Scheduled(cron="*/6 * * * * ?")
//    private void process(){
//        System.out.println("this is scheduler task runing  "+(count++));
//        System.out.println("现在时间：" + dateFormat.format(new Date()));
//        service.sayHi("prpr");
//    }
}
