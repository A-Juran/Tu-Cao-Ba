package cn.envisions.tucaoba.config.socket.handler;

import cn.envisions.tucaoba.common.websocket.entity.MessageTemplate;
import cn.envisions.tucaoba.config.socket.service.SingleMessageService;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

//@Component
@Slf4j
public class SingleMessageHandler {

    @Resource
    private SingleMessageService singleMessageService;

    /**
     * 客户端事件  SINGLE_CHAT
     *
     * @param client   客户端信息
     * @param request  请求信息
     * @param template 客户端发送数据
     */
    @OnEvent(value = "chat")
    public void onSingleChat(SocketIOClient client, AckRequest request, MessageTemplate template) {
        singleMessageService.saveSingleMessage(template);
    }

}
