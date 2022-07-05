package org.example.topic;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yuancetian   2021/9/5 23:37
 */
@Configuration
public class TopicConfig {

    @Bean
    public Queue queue1() {
        return new Queue("topic.queue1", true, false, false);
    }

    @Bean
    public Queue queue2() {
        return new Queue("topic.queue2", true, false, false);
    }

    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange("topicExchange", true, false);
    }

    @Bean
    Binding bindingQueue1() {
        return BindingBuilder.bind(queue1()).to(topicExchange()).with("topic.queue1");
    }

    @Bean
    Binding bindingQueue2() {
        return BindingBuilder.bind(queue2()).to(topicExchange()).with("topic.#");
    }

}
