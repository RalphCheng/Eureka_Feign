package com.cl.agree.springcloud.feign.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cl.agree.springcloud.feign.service.HiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

/**
 * <p>Descriptions...
 *
 * @author cl
 * @date 2019/11/30.
 */
@RestController
@Slf4j
public class HiController {
    @Autowired
    HiService service;
    
//    @Autowired
//    HiServiceTestRetry serviceTestRetry;
    
//    @GetMapping("/hi")
//    public String sayHi(@RequestParam(defaultValue = "Ralph", required = false) String name){
//        log.info("开始调用sayHi()");
//        return service.sayHi(name);
//    }
//
//    /**
//     * 测试重试时间
//     * @return
//     */
//    @RequestMapping("/timeout")
//    public String timeout(@RequestParam int mills){
//        log.info("[feign]---HiController---开始调用");
//        return service.timeout(mills);
//    }
    
    
    
//    @RequestMapping("/timeoutRetryable")
//    public String timeout1(@RequestParam int mills){
//        log.info("开始调用");
//        return service.timeout(mills);
//    }
    
//    @RequestMapping("/timeoutNeverRetry")
//    public String timeout2(@RequestParam int mills){
//        log.info("开始调用");
//        return serviceTestRetry.timeout(mills);
//    }
    
    
    
    @PostMapping(value = "sendJsonMsg")
    public void sendJsonMsg() throws UnsupportedEncodingException {
        String ss = "{\n" +
                            " \"MsgHeader\": {\n" +
                            "  \"M_serviceCode\": \"IBPSSndPayService\",\n" +
                            "  \"M_serviceVersion\": \"1.0.0\",\n" +
                            "  \"M_sceneCode\": \"SndCreditBusiAccept\",\n" +
                            "  \"M_sceneVersion\": \"1.0.0\",\n" +
                            "  \"M_callMethod\": \"1\",\n" +
                            "  \"M_requestIp\": \"xx.xx.xx.xx\",\n" +
                            "  \"M_requestPort\": \"9001\",\n" +
                            "  \"M_requestId\": \"20191126141000100007\",\n" +
                            "  \"M_priority\": \"0\",\n" +
                            "  \"M_timeOut\": \"60\",\n" +
                            "  \"M_reserve\": \"\"\n" +
                            " },\n" +
                            " \"Body\": {\n" +
                            "  \"AppHeader\": {\n" +
                            "   \"channelcode\": \"CNT\",\n" +
                            "   \"channeldate\": \"20191126\",\n" +
                            "   \"channeltime\": \"163011001\",\n" +
                            "   \"channelserno\": \"20191126141000100007\",\n" +
                            "   \"brno\": \"01011\",\n" +
                            "   \"tellerno\": \"80004\",\n" +
                            "   \"pageflag\": \"\",\n" +
                            "   \"currpage\": \"\",\n" +
                            "   \"pagenum\": \"\"\n" +
                            "  },\n" +
                            "  \"busitype\": \"C200\",\n" +
                            "  \"busikind\": \"02001\",\n" +
                            "  \"payeracc\": \"000000001\",\n" +
                            "  \"payername\": \"张三\",\n" +
                            "  \"payeracctype\": \"1\",\n" +
                            "  \"realpayeracc\": \"\",\n" +
                            "  \"realpayername\": \"\",\n" +
                            "  \"payeraccbank\": \"11111111\",\n" +
                            "  \"payeraccbankname\": \"中国银行测试\",\n" +
                            "  \"priority\": \"0\",\n" +
                            "  \"pwdmode\": \"A\",\n" +
                            "  \"cardpin\": \"123122121\",\n" +
                            "  \"fg_ckpswd_agree\": \"0\",\n" +
                            "  \"recvbank\": \"313611001018\",\n" +
                            "  \"payeeacc\": \"00000002\",\n" +
                            "  \"payeename\": \"测试客户2\",\n" +
                            "  \"currency\": \"CNY\",\n" +
                            "  \"amount\": \"1.00\",\n" +
                            "  \"postscript\": \"淘宝购物\",\n" +
                            "  \"telephone\": \"13843814138\"\n" +
                            " }\n" +
                            "}";
        System.out.println(ss);
        JSONObject jsonObject1 = JSON.parseObject(ss);
        byte[] bytes = service.sendJsonMsg(jsonObject1.toString());
        if (bytes == null){
            throw new IllegalStateException("返回报文信息为空！");
        }
        String s = new String(bytes, "utf-8");
        System.out.println("返回报文：" + s);
    }
}
