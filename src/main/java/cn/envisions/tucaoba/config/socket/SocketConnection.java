package cn.envisions.tucaoba.config.socket;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

/**
 * @author JuRan
 * @date 2024/12/15 23:59
 * @description: 配置netty-socketIo服务
 */
@Configuration
public class SocketConnection {

    private final static Logger log = LoggerFactory.getLogger(SocketConnection.class);

    //用户连接
    @OnConnect
    public void Connect(SocketIOClient client){
        log.info("connect success");
    }

    @OnDisconnect
    public void disconnect(SocketIOClient client){
        log.info("disconnect user");
    }

}
