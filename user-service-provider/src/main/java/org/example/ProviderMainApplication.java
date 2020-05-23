package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class ProviderMainApplication {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("provider.xml");
        ioc.start();
        System.out.println("生产者模块成功运行...");
        System.in.read(); //控制台输入任意字符停止程序
    }
}
