package com.cl.agree.springcloud.ribbon.old;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.request.RequestContextListener;

@EnableEurekaClient
@SpringBootApplication
//@EnableFeignClients
public class PomCbpApplication {
    public static void main(String[] args) {
        SpringApplication.run(PomCbpApplication.class, args);
    }
    
    @Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }
    
//    @Bean
//    public EmbeddedServletContainerFactory servletContainer() {
//        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
//        tomcat.addAdditionalTomcatConnectors(createStandardConnector()); // 添加http
//        return tomcat;
//    }
//
//    // 配置http
//    private Connector createStandardConnector() {
//        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
//        connector.setPort(8812);
//        return connector;
//    }
}
