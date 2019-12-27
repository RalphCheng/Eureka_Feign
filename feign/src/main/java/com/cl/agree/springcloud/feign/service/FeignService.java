package com.cl.agree.springcloud.feign.service;

import com.cl.agree.springcloud.feign.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>Descriptions...
 *  FeignClient 注解被＠Target(ElementType.TYPE）修饰，表示 FeignClient 注解的作用目标在
 *  接口上。＠Retention(RetentionPolicy.RUNTIME）注解表明该注解会在 Class 字节码文件中存在，
 *  在运行时可以通过反射获取到。＠Documented 表示该注解将被包含在 Javadoc 中。
 *
 *  在代码中， value（）和 name（）一样，是被调用的服务的 Serviceld。
 *  url（）直接填写硬编码的 Uri 地址。
 *  decode404（）即 404 是被解码，还是抛异常。
 *  configuration（）指明 FeignClient 的配置类， 默认的配置类为 FeignClientsConfiguration 类，
 *  在缺省的情况下， 这个类注入了默认的 Decoder、 Encoder 和 Contract 等配置的 Bean。
 *  fallback（）为配置熔断器的处理类。
 * @author cl
 * @date 2019/11/30.
 */
@FeignClient(value = "eureka-client", configuration = FeignConfig.class, fallback = HiHystrix.class)
public interface FeignService {
    @GetMapping("/hi")
    String sayHiFromEurekaClient(@RequestParam(value = "name") String name);
    
    @GetMapping("/timeout")
    String timeout(@RequestParam(value = "mills") int mills);
}
