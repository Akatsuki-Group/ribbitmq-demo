package org.example.confirm;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yuancetian   2021/9/4 20:21
 */
@Configuration
public class ConfigDirectRabbitConfig {

    /**
     * 队列 起名：directQueue
     */
    @Bean
    public Queue directQueue() {
        // durable: 是否持久化,默认是false,持久化队列：会被存储在磁盘上，当消息代理重启时仍然存在，暂存队列：当前连接有效
        // exclusive: 默认也是false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
        // autoDelete: 是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除。
        return new Queue("directQueue", true, false, false);
    }

    /**
     * Direct交换机 起名：directExchange
     */
    @Bean
    DirectExchange directExchange() {
        return new DirectExchange("directExchange", true, false);
    }

    /**
     * 绑定，将队列和交换机绑定, 并设置用于匹配键：directRouting
     */
    @Bean
    Binding bindingDirect() {
        return BindingBuilder.bind(directQueue()).to(directExchange()).with("directRouting");
    }

}
