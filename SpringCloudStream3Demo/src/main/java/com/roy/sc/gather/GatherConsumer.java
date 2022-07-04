package com.yct.sc.gather;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author yct
 * @desc
 */

@Configuration
public class GatherConsumer {
    //例如这样的Function，就需要声明gather-in-0和gather-in-1。
    //如果输出的是个Tuple，那就同样需要声明gather-out-0和gather-out-1
    @Bean
    public Function<Tuple2<Flux<String>,Flux<String>>, Flux<String>> gather(){
        return tuple -> {
            Flux<String> t1 = tuple.getT1();
            Flux<String> t2 = tuple.getT2().map(str-> "gather2:"+str);
//            return Flux.merge(t1,t2);

            return Flux.combineLatest(t1,t2,(str1,str2) -> "gather1:"+str1+";"+str2);
        };
    }

    @Bean
    public Consumer<String> gatherEcho(){
        return message -> {
            System.out.println("received message from gatherCosuer : "+message);
        };
    }
}
