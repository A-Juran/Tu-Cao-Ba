package cn.envisions.tucaoba.config.socket.service;

import cn.envisions.tucaoba.common.websocket.entity.MessageTemplate;

/**
 * @author JuRan
 * @date 2024/12/16 0:38
 * @description:
 */
public interface SingleMessageService {

    //保存信息
    void saveSingleMessage(MessageTemplate template);

}
