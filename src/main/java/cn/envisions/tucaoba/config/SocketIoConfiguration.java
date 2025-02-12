package cn.envisions.tucaoba.config;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author JuRan
 * @date 2024/12/15 23:54
 * @description: 配置 netty-socketio 服务,用于加入SpringIoc管理
 */
@Configuration
public class SocketIoConfiguration {

    @Value("${socketio.host}")
    private String host;

    @Value("${socketio.port}")
    private Integer port;

    @Bean
    public SocketIOServer socketIOServer(){
        com.corundumstudio.socketio.Configuration configuration = new com.corundumstudio.socketio.Configuration();
        configuration.setHostname(host);
        configuration.setPort(port);
        configuration.setOrigin("*");

        return new SocketIOServer(configuration);
    }

    /**
     * 用于扫描 netty-socketio 注解 比如 @OnConnect、@OnEvent
     */
    @Bean
    public SpringAnnotationScanner springAnnotationScanner(){
        return new SpringAnnotationScanner(socketIOServer());
    }
}
