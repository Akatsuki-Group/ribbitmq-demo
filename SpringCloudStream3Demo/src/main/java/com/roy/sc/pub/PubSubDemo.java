package com.roy.sc.pub;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author yct
 * @desc 函数方式声明Binding。 例如声明一个函数source，那么直接可以用 source-in-0 声明一个Consumer的Binding。source-out-0声明一个Producer的Binding
 * source会不断发消息，sink会不断收消息。 不过看不明白这source一只发消息，除了测试还有什么用？
 */
//@Configuration
public class PubSubDemo {

    //Supplier声明一个消息生产者，source1就对应source1-out-0的生产者binding
    @Bean
    public Supplier<String> source1(){
        return ()->{
            String message = "FromSource1";
            System.out.println("******************");
            System.out.println("From Source1");
            System.out.println("******************");
            System.out.println("Sending value: " + message);
            return message;
        };
    }

    @Bean
    public Supplier<String> source2(){
        return () -> {
            String message = "FromSource2";
            System.out.println("******************");
            System.out.println("From Source2");
            System.out.println("******************");
            System.out.println("Sending value: " + message);
            return message;

        };
    }

    @Bean
    public Consumer<String> sink1() {
        return message -> {
            System.out.println("******************");
            System.out.println("At Sink1");
            System.out.println("******************");
            System.out.println("Received message " + message);
        };
    }

    @Bean
    public Consumer<String> sink2() {
        return message -> {
            System.out.println("******************");
            System.out.println("At Sink2");
            System.out.println("******************");
            System.out.println("Received message " + message);
        };
    }
}
