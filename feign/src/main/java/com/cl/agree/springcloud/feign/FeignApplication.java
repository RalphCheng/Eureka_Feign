package com.cl.agree.springcloud.feign;

import com.cl.agree.springcloud.feign.interceptor.DemoInterceptor;
import com.cl.agree.springcloud.feign.utils.MySpringContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Feign 的源码实现过程如下。
 * (1) 首先通过＠EnableFeignClients 注解开启 FeignClient 的功能。
 *          只有这个注解存在，才 会在程序启动时开启对＠FeignClient 注解的包扫描。
 * (2）根据 Feign 的规则实现接口，井在接口上面加上＠FeignClient 注解。
 * (3）程序启动后，会进行包扫描，扫描所有的＠ FeignClient 的注解的类，并将这些信息 注入 IoC 容器中。
 * (4）当接口的方法被调用时，通过 JDK 的代理来生成具体的 RequestTemplate 棋根对象。
 * (5）根据 RequestTemplate 再生成 Http 请求的 Request 对象。
 * (6) Request对象交给 Client 去处理， 其中 Client 的网络请求框架可以是 HttpURLConnection、
 *          HttpClient 和 OkHttp。
 * (7）最后 Client 被封装到 LoadBalanceClient 类，这个类结合类 Ribbon 做到了负载均衡
 */

//@EnableHystrix    切记不能跟Ribbon整合Hystrix一样打上该注解，Feign已经整合了Hystrix
@EnableEurekaClient
@EnableFeignClients
//@EnableCircuitBreaker
@EnableScheduling
@SpringBootApplication
public class FeignApplication implements WebMvcConfigurer {
    public static void main(String[] args) {
        SpringApplication.run(FeignApplication.class, args);
    }
    
    @Bean
    public DemoInterceptor demoInterceptor(){
        return new DemoInterceptor();
    }
    
//    @Bean
//    public MySpringContext myCOntext(){
//        System.out.println("MySpringContext已经创建！！！！！！！！！！！！！！！");
//        return new MySpringContext();
//    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(demoInterceptor());
    }
}
