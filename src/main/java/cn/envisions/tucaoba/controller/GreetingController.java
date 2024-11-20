package cn.envisions.tucaoba.controller;

import cn.envisions.tucaoba.common.websocket.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class GreetingController {

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/hello")
    public void greeting() throws Exception {
        Message message = new Message();
        message.setContent("123");
        System.out.println(123);
        simpMessagingTemplate.convertAndSendToUser("greetings","/queue/chat",message);
//        this.simpMessagingTemplate.convertAndSend("/topic/greetings", message);
    }
}