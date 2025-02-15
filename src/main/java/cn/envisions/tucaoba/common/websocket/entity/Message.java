package cn.envisions.tucaoba.common.websocket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    private String type;

    private Long id;

    private String content;

    private Date createTime;

}
