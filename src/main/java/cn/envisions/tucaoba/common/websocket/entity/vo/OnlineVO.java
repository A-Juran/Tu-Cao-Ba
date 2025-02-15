package cn.envisions.tucaoba.common.websocket.entity.vo;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author JuRan
 * @date 15/2/2025 上午 1:01
 * @description: 上线实体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OnlineVO implements Serializable {

    //上线时间
    private String time;

    //上线人
    private String userName;

    //状态(1、上线 2,下线)
    private Integer status;

}
