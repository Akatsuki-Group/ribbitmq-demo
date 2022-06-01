package org.example.direct;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author yuancetian   2021/9/4 21:46
 */
@Component
@RabbitListener(queues = "directQueue")
public class DirectConsumer {

    @RabbitHandler
    public void process(Map<String, Object> message) {
        System.out.println("消费者1，DirectReceiver 消费者收到消息  : " + message.toString());
    }
}
