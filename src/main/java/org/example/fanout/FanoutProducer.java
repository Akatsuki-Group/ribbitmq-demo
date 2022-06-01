package org.example.fanout;

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
public class FanoutProducer {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @GetMapping("/fanoutMsg")
    public String confirmMsg() {

        Map<String, Object> map = new HashMap<>();
        map.put("messageId", String.valueOf(UUID.randomUUID()));
        map.put("data", "发送数据体" + System.currentTimeMillis());
        map.put("createTime", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        rabbitTemplate.convertAndSend("fanoutExchange", null, map);

        return JSONObject.toJSONString(map);
    }

}
