package cn.envisions.tucaoba.config.socket;

import cn.envisions.tucaoba.common.websocket.entity.MessageTemplate;
import cn.envisions.tucaoba.common.websocket.entity.vo.OnlineVO;
import cn.hutool.core.util.ObjectUtil;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author JuRan
 * @date 2024/12/15 23:59
 * @description: 配置netty-socketIo服务
 */
@Component
public class SocketConnection {

    //SocketIOServer
    private final SocketIOServer socketIOServer;

    @Autowired
    public SocketConnection(SocketIOServer socketIOServer) {
        this.socketIOServer = socketIOServer;
    }

    private final static Logger log = LoggerFactory.getLogger(SocketConnection.class);

    //存储已经用户
    @Setter
    @Getter
    private Map<String, UUID> connectionUser = new ConcurrentHashMap<>(16);


    //接受来自用户的消息。
    @OnEvent("sendMsg")
    public void sendMsg(SocketIOClient socketIOClient, AckRequest ackRequest,
                        MessageTemplate messageTemplate) {

        messageTemplate.setCreateTime(getCurrentDateTimeString());
        //向所有人主动推送信息
        connectionUser.forEach((k, v) -> {
            if (ObjectUtil.isNotEmpty(v)) {
                log.info("收到的信息为: {}", messageTemplate.toString());
                socketIOServer.getClient(v).sendEvent("receiveMsg", messageTemplate);
            }
        });

    }

    //展示用户信息状态
    @OnEvent("userConnectStatus")
    public void userConnectStatus(UUID uuid, OnlineVO onlineVO) {
        connectionUser.forEach((k, v) -> {
            System.out.println(v);
            if (ObjectUtil.isNotEmpty(v) && ObjectUtil.notEqual(uuid,v)) {
                socketIOServer.getClient(v).sendEvent("userOnline", onlineVO);
            }
        });
    }

    public String getCurrentDateTimeString() {
        Calendar instance = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(instance.getTime());
    }

    //用户连接
    @OnConnect
    public void onConnect(SocketIOClient client) {
        String userName = client.getHandshakeData().getSingleUrlParam("userName");
        if (ObjectUtil.isNotEmpty(userName)) {
            log.info("用户{}加入连接, nettySocketSessionId: {},nettyRemoteAddress:{}",
                    userName, client.getSessionId(), client.getRemoteAddress());

            //加入在线用户列表之中
            connectionUser.put(userName, client.getSessionId());

            this.userConnectStatus(client.getSessionId(), new OnlineVO(getCurrentDateTimeString(),
                    userName, 1));
        }
        log.info("connect success");
    }

    @OnDisconnect
    public void disconnect(SocketIOClient client) {
        String userName = client.getHandshakeData().getSingleUrlParam("userName");

        log.info("用户{}断开连接, nettySocketSessionId: {}, nettyRemoteAddress{}",
                userName, client.getSessionId(), client.getRemoteAddress());

        if (ObjectUtil.isNotEmpty(connectionUser.get(userName))) {
            connectionUser.remove(userName);
        }

        this.userConnectStatus(client.getSessionId(), new OnlineVO(getCurrentDateTimeString(),
                userName, 2));
    }

}
