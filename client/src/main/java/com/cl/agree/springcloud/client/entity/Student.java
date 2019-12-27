package com.cl.agree.springcloud.client.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.openjdk.jol.info.ClassLayout;

import java.awt.*;

/**
 * <p>Descriptions...
 *
 * @author RalphCheng
 * @date 2019/12/20.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private String name;
    private int age;
    
    public static void main(String[] args) {
        Student s = new Student("zhangsan", 88);
        Student s1 = new Student();
        synchronized (s){
//            System.out.println(ClassLayout.parseInstance(s).toPrintable());
            System.out.println(ClassLayout.parseInstance(s1).toPrintable());
        }
    }
}
