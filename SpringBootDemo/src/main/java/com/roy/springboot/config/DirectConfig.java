package com.yct.springboot.config;

import com.yct.springboot.util.MyConstants;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 直连模式只需要声明队列，所有消息都通过队列转发。
 * @author yctkingw
 */
@Configuration
public class DirectConfig {

	@Bean
	public Queue directQueue() {
		return new Queue(MyConstants.QUEUE_DIRECT);
	}
}
