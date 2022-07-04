package com.yct.sc.sub;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author yct
 * @desc
 */
@Configuration
public class Subscriber {
    //注入Consumer方法就可以自动声明Consumer对应的binding了。binding的名字是input-in-0
    //input对应方法名，in表示是Consumer,后面的0表示数据流中只有单数据输入。
    @Bean
    public Consumer<String> input(){
        return message -> {
            System.out.println("received message from input binding ; message = "+message);
        };
    }

    //Function方式声明binding，相当于同时声明了一个Producer的Bindng和一个Consumer的Binding。echo-in-0 表示消费者  echo-out-0 表示生产者
    //其作用就在于，如果同时配置了echo-in-0和echo-out-0，那么echo-in-0收到的消息，立即就会通过echo-out-0发送出去。
    @Bean
    public Function<String,String> echo(){
        return message -> {
            System.out.println("echo: "+message);
            return "echo:"+message;
        };
    }

    @Bean
    public Consumer<String> consu(){
        return message -> {
            System.out.println("received message from echo2 binding ; message = "+message);
        };
    }
}
