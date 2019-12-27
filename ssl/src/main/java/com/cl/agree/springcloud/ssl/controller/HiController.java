package com.cl.agree.springcloud.ssl.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Descriptions...
 *
 * @author cl
 * @date 2019/12/5.
 */

@RestController
@Slf4j
public class HiController {
    
    @GetMapping("hi")
    public String hi(@RequestParam String name){
        return "Hi, " + name + "!";
    }
    /**
     * 使用SSL协议的单向认证：仅在客户端验证服务器的证书（客户端已经把服务器的证书添加到信任文件中）
     * 验证方式：客户端使用已经持有的服务器的公钥去解密服务器证书（证书链的第一本？）的签名？
     *
     *  签名，建立信任链关系(服务器和客户端只有信任同一个CA链，才能信任对方。
     *
     *
     *  自己做根证书CA（自己充当类似于VeriSign的角色），然后让用户下载安装根CA（当然了，其中只含有公钥）到机器中，
     *  12306就是这样干的（SRCA就是12306的根证书），然后再自己给自己颁发服务器证书，这样用户机器上也有他的CA，
     *  服务器发来的服务器证书也是这本CA颁发的，当然也顺利通过了。
     *
     *
     * 证书预置和申请
     * 1：客户端浏览器会预置根证书， 里面包含CA公钥
     * 2：服务器去CA申请一个证书
     * 3： CA用自己的签名去签一个证书，指纹信息保存在证书的数字摘要里面， 然后发送给服务器
     * 一次访问流程（简化）
     * 1： 客户端 sayHello
     * 2： 服务器返回证书
     * 3-1： 客户端验证证书内容有效性（过期时间，域名是否相同等）
     * 3-2： 验证证书的有效性 （是否被串改）， 通过本地根证书的CA公钥解密数字摘要，看是否匹配。
     * 3-3： 如果数字签名验证通过， 就可以使用服务器证书里面提供的公钥进行下一步通信。
     */
}
