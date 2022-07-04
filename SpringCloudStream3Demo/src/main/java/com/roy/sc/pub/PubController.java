package com.yct.sc.pub;

import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author yct
 * @desc
 */
@RestController
public class PubController {
    @Resource
    private StreamBridge streamBridge;
    //直接发消息到output 这个binding
    @RequestMapping("send")
    public Object send(String message){
        streamBridge.send("output",message);
        return "Message sended :"+message;
    }

    @RequestMapping("sendGatherMessage")
    public Object sendGatherMessage(String mess1,String mess2){
        streamBridge.send("gather1",mess1);
        streamBridge.send("gather2",mess2);
        return "Gather Message sended :"+mess1+"; "+mess2;
    }

}
