package cn.envisions.tucaoba;

import com.corundumstudio.socketio.SocketIOServer;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.envisions.tucaoba.mapper")
@Slf4j
public class TuCaoBaApplication implements CommandLineRunner {

    @Autowired
    private SocketIOServer socketIOServer;

    public static void main(String[] args) {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication.run(TuCaoBaApplication.class, args);
    }

    @Override
    public void run(String... strings) {
        socketIOServer.start();
        log.info("socket.io 启动成功");
    }

}
