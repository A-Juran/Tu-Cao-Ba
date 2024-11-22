package cn.envisions.tucaoba;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("cn.envisions.tucaoba.mapper")
@ComponentScan(value = {"cn.envisions.tucaoba.config","cn.envisions.tucaoba"})
public class TuCaoBaApplication {
    public static void main(String[] args) {
        try {
            SpringApplication.run(TuCaoBaApplication.class, args);
        }catch(Throwable e) {
            e.printStackTrace();
        }
    }
}
