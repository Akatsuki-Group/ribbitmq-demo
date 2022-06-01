package org.example.confirm;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * @author yuancetian   2021/9/4 21:46
 */
@Slf4j
@Component
@RabbitListener(queues = "directQueue")
public class ConfigDirectConsumer {

    @RabbitHandler
    public void process(Map<String, Object> message, Channel channel, Message mqMsg) throws IOException {
        try {
            System.out.println("消费者收到消息  : " + message.toString());
            channel.basicAck(mqMsg.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            if (mqMsg.getMessageProperties().getRedelivered()) {
                log.error("消息已重复处理失败，不在返回队列...");
                channel.basicReject(mqMsg.getMessageProperties().getDeliveryTag(), false);
            } else {
                log.error("消息即将再次返回队列处理...");
                channel.basicNack(mqMsg.getMessageProperties().getDeliveryTag(), false, true);
            }
        }
    }
}
