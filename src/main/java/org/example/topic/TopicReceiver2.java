package org.example.topic;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author yuancetian   2021/9/4 21:46
 */
@Component
@RabbitListener(queues = "topic.queue2")
public class TopicReceiver2 {

    @RabbitHandler
    public void process(Map<String, Object> message) {
        System.out.println("topic.queue2 收到消息  : " + message.toString());
    }
}
