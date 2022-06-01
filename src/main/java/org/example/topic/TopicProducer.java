package org.example.topic;

import com.alibaba.fastjson.JSONObject;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author yuancetian   2021/9/4 21:27
 */
@RestController
@RequestMapping
public class TopicProducer {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @GetMapping("/queue1")
    public String queue1() {

        Map<String, Object> map = new HashMap<>();
        map.put("messageId", String.valueOf(UUID.randomUUID()));
        map.put("data", "发送数据体" + System.currentTimeMillis());
        map.put("createTime", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        //将消息携带绑定键值：directRouting 发送到交换机 directExchange
        rabbitTemplate.convertAndSend("topicExchange", "topic.queue1", map);

        return JSONObject.toJSONString(map);
    }

    @GetMapping("/queue2")
    public String queue2() {

        Map<String, Object> map = new HashMap<>();
        map.put("messageId", String.valueOf(UUID.randomUUID()));
        map.put("data", "发送数据体" + System.currentTimeMillis());
        map.put("createTime", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        //将消息携带绑定键值：directRouting 发送到交换机 directExchange
        rabbitTemplate.convertAndSend("topicExchange", "topic.queue2", map);

        return JSONObject.toJSONString(map);
    }

}
