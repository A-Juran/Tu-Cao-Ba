package cn.envisions.tucaoba.config.socket.service;

import cn.envisions.tucaoba.common.websocket.entity.MessageTemplate;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SingleMessageServiceImpl implements SingleMessageService {

    @Override
    public void saveSingleMessage(MessageTemplate template) {
        try {
            log.info("接收消息：{} {}", "chat", JSONObject.toJSONString(template));
        } catch (Exception exception) {
            log.info("连接错误 [{}]", exception.getMessage());
        }
    }
}