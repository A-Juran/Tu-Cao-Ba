package cn.envisions.tucaoba.controller;

import cn.envisions.tucaoba.common.websocket.entity.Message;
import org.springframework.stereotype.Controller;

@Controller
public class GreetingController {

    public void greeting() throws Exception {
        Message message = new Message();
        message.setContent("123");
        System.out.println(123);
//        this.simpMessagingTemplate.convertAndSend("/topic/greetings", message);
    }
}