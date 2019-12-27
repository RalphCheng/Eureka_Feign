package com.cl.agree.springcloud.ribbon.old.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Descriptions...
 *
 * @author cl
 * @date 2019/11/25.
 */
@RestController
@Slf4j
@RequestMapping("/cbp/SndCreditBusiAccept")
public class SndCreditBusiAcceptController {
//    @Autowired
//    private SendJsonMsg sendJsonMsg;
//
//    @Autowired
//    private SendJsonMsg2 sendJsonMsg2;
//
//    public static void main(String[] args) {
//        String ss = "{\n" +
//            " \"MsgHeader\": {\n" +
//            "  \"M_serviceCode\": \"IBPSSndPayService\",\n" +
//            "  \"M_serviceVersion\": \"1.0.0\",\n" +
//            "  \"M_sceneCode\": \"SndCreditBusiAccept\",\n" +
//            "  \"M_sceneVersion\": \"1.0.0\",\n" +
//            "  \"M_callMethod\": \"1\",\n" +
//            "  \"M_requestIp\": \"xx.xx.xx.xx\",\n" +
//            "  \"M_requestPort\": \"9001\",\n" +
//            "  \"M_requestId\": \"20191126141000100007\",\n" +
//            "  \"M_priority\": \"0\",\n" +
//            "  \"M_timeOut\": \"60\",\n" +
//            "  \"M_reserve\": \"\"\n" +
//            " },\n" +
//            " \"Body\": {\n" +
//            "  \"AppHeader\": {\n" +
//            "   \"channelcode\": \"CNT\",\n" +
//            "   \"channeldate\": \"20191126\",\n" +
//            "   \"channeltime\": \"163011001\",\n" +
//            "   \"brno\": \"01011\",\n" +
//            "   \"tellerno\": \"80004\",\n" +
//            "   \"pageflag\": \"\",\n" +
//            "   \"currpage\": \"\",\n" +
//            "   \"pagenum\": \"\"\n" +
//            "  }\n" +
//            "}\n" +
//           "}";
//        System.out.println(ss);
//        JSONObject jsonObject1 = JSON.parseObject(ss);
//        JSONObject body = (JSONObject) jsonObject1.get("Body");
//        JSONObject appHeader = (JSONObject) body.get("AppHeader");
//        //交易流水号，uuid生成
//        String channelserno = "11111111111111111111";
//        appHeader.put("channelserno", channelserno);
//        System.out.println(jsonObject1);
//
//    }
//
//    @PostMapping(value = "sendJsonMsg")
//    public RestResultMessage sendJsonMsg(@RequestBody String requestBody) throws UnsupportedEncodingException {
//        log.info("往账贷记受理——参数[{}]", requestBody);
//        JSONObject jsonObject = JSON.parseObject(requestBody);
//        String ss = "{\n" +
//            " \"MsgHeader\": {\n" +
//            "  \"M_serviceCode\": \"IBPSSndPayService\",\n" +
//            "  \"M_serviceVersion\": \"1.0.0\",\n" +
//            "  \"M_sceneCode\": \"SndCreditBusiAccept\",\n" +
//            "  \"M_sceneVersion\": \"1.0.0\",\n" +
//            "  \"M_callMethod\": \"1\",\n" +
//            "  \"M_requestIp\": \"xx.xx.xx.xx\",\n" +
//            "  \"M_requestPort\": \"9001\",\n" +
//            "  \"M_requestId\": \"20191126141000100007\",\n" +
//            "  \"M_priority\": \"0\",\n" +
//            "  \"M_timeOut\": \"60\",\n" +
//            "  \"M_reserve\": \"\"\n" +
//            " },\n" +
//            " \"Body\": {\n" +
//            "  \"AppHeader\": {\n" +
//            "   \"channelcode\": \"CNT\",\n" +
//            "   \"channeldate\": \"20191126\",\n" +
//            "   \"channeltime\": \"163011001\",\n" +
//            "   \"brno\": \"01011\",\n" +
//            "   \"tellerno\": \"80004\",\n" +
//            "   \"pageflag\": \"\",\n" +
//            "   \"currpage\": \"\",\n" +
//            "   \"pagenum\": \"\"\n" +
//            "  }\n" +
//            "}\n" +
//            "}";
//        System.out.println(ss);
//        JSONObject jsonObject1 = JSON.parseObject(ss);
//        JSONObject body = (JSONObject) jsonObject1.get("Body");
//        JSONObject appHeader = (JSONObject) body.get("AppHeader");
//        //channelserno交易流水号，uuid生成
//        String channelserno = UUID.randomUUID().toString();
//        appHeader.put("channelserno", channelserno);
//        System.out.println("替换完交易流水号：" + jsonObject1);
//        body.putAll(jsonObject);
//        System.out.println("加上前端页面返回对象：" + jsonObject1.toString());
//        byte[] bytes = sendJsonMsg.sndCreditBusiAccept(jsonObject1.toString());
//        if (bytes == null){
//            return new RestResultMessage(ResultConstant.AFAREQUESTFAILED);
//        }
//        String s = new String(bytes, "utf-8");
//        System.out.println("返回报文：" + s);
//        return new RestResultMessage(ResultConstant.SUCCESS);
//    }
//
//
//    @PostMapping(value = "sendJsonMsg2")
//    public void sendJsonMsg2(@RequestBody String requestBody) throws UnsupportedEncodingException {
//
//
//        String ss = "{\n" +
//                            "        \"MsgHeader\": {\n" +
//                            "                \"M_serviceCode\": \"EPCCProtocolManageService\",\n" +
//                            "                \"M_serviceVersion\": \"1.0.0\",\n" +
//                            "                \"M_sceneCode\": \"SndProtocolCancelApply\",\n" +
//                            "                \"M_sceneVersion\": \"1.0.0\",\n" +
//                            "                \"M_callMethod\": \"0\",\n" +
//                            "                \"M_requestId\": \"11223445566\",\n" +
//                            "                \"M_priority\": \"0\",\n" +
//                            "                \"M_timeOut\": \"60\",\n" +
//                            "                \"M_reserve\": \"\"\n" +
//                            "        },\n" +
//                            "        \"Body\": {\n" +
//                            "                \"AppHeader\": {\n" +
//                            "                        \"channelcode\": \"001\",\n" +
//                            "                        \"channeldate\": \"20190101\",\n" +
//                            "                        \"channeltime\": \"141300\",\n" +
//                            "                        \"channelserno\": \"1234456\",\n" +
//                            "                        \"brno\": \"101001\",\n" +
//                            "                        \"tellerno\": \"wltest\",\n" +
//                            "                        \"pageflag\": \"\",\n" +
//                            "                        \"currpage\": \"\",\n" +
//                            "                        \"pagenum\": \"\"\n" +
//                            "                },\n" +
//                            "                \"accbank\": \"C0000000000019\",\n" +
//                            "                \"acctype\": \"00\",\n" +
//                            "                \"protocolno\": \"111222333\"\n" +
//                            "        }\n" +
//                            "}";
//        byte[] bytes = sendJsonMsg.sndCreditBusiAccept(ss);
//        String s = new String(bytes, "utf-8");
//        System.out.println("返回报文：" + s);
//    }
    
    
    
//    @PostMapping(value = "sendJsonMsg2")
//    public void sendJsonMsg2(@RequestBody String requestBody) throws UnsupportedEncodingException {
//        String ss = "{\n" +
//            "        \"MsgHeader\": {\n" +
//            "                \"M_serviceCode\": \"EPCCProtocolManageService\",\n" +
//            "                \"M_serviceVersion\": \"1.0.0\",\n" +
//            "                \"M_sceneCode\": \"SndProtocolCancelApply\",\n" +
//            "                \"M_sceneVersion\": \"1.0.0\",\n" +
//            "                \"M_callMethod\": \"0\",\n" +
//            "                \"M_requestId\": \"11223445566\",\n" +
//            "                \"M_priority\": \"0\",\n" +
//            "                \"M_timeOut\": \"60\",\n" +
//            "                \"M_reserve\": \"\"\n" +
//            "        },\n" +
//            "        \"Body\": {\n" +
//            "                \"AppHeader\": {\n" +
//            "                        \"channelcode\": \"001\",\n" +
//            "                        \"channeldate\": \"20190101\",\n" +
//            "                        \"channeltime\": \"141300\",\n" +
////            "                        \"channelserno\": \"1234456\",\n" +
//            "                        \"brno\": \"101001\",\n" +
//            "                        \"tellerno\": \"wltest\",\n" +
//            "                        \"pageflag\": \"\",\n" +
//            "                        \"currpage\": \"\",\n" +
//            "                        \"pagenum\": \"\"\n" +
//            "                }\n" +
//            "        }\n" +
//            "}";
//        JSONObject jsonObject = JSON.parseObject(requestBody);
//        System.out.println(ss);
//        JSONObject jsonObject1 = JSON.parseObject(ss);
//        JSONObject body = (JSONObject) jsonObject1.get("Body");
//        JSONObject appHeader = (JSONObject) body.get("AppHeader");
//        //channelserno交易流水号，uuid生成
//        String channelserno = UUID.randomUUID().toString();
//        appHeader.put("channelserno", channelserno);
//        System.out.println("替换完交易流水号：" + jsonObject1);
//        body.putAll(jsonObject);
//        System.out.println("加上前端页面返回对象：" + jsonObject1.toString());
//        byte[] bytes = sendJsonMsg.sndCreditBusiAccept(jsonObject1.toString());
//        String s = new String(bytes, "utf-8");
//        System.out.println("返回报文：" + s);
//    }
}
