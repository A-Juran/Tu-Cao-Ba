package cn.envisions.tucaoba;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.envisions.tucaoba.mapper")
public class TuCaoBaApplication {

    public static void main(String[] args) {
        SpringApplication.run(TuCaoBaApplication.class, args);
    }
}
