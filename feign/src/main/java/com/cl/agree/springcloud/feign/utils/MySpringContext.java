package com.cl.agree.springcloud.feign.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;

/**
 * <p>Descriptions...
 *
 * @author cl
 * @date 2019/12/2.
 */

public class MySpringContext implements ApplicationContextAware {
    private static ApplicationContext context;
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
    
    public static ApplicationContext getContext(){
        return context;
    }
}
