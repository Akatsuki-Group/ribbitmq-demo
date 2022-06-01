package org.example.fanout;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yuancetian   2021/9/4 20:21
 */
@Configuration
public class FanoutConfig {

    @Bean
    public Queue queueA() {
        return new Queue("fanout.A", true, false, false);
    }

    @Bean
    public Queue queueB() {
        return new Queue("fanout.B", true, false, false);
    }

    @Bean
    public Queue queueC() {
        return new Queue("fanout.C", true, false, false);
    }

    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }

    @Bean
    Binding bindingExchangeA() {
        return BindingBuilder.bind(queueA()).to(fanoutExchange());
    }

    @Bean
    Binding bindingExchangeB() {
        return BindingBuilder.bind(queueB()).to(fanoutExchange());
    }

    @Bean
    Binding bindingExchangeC() {
        return BindingBuilder.bind(queueC()).to(fanoutExchange());
    }

}
