package cn.envisions.tucaoba.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author JuRan
 * @date 2024/11/25 16:34
 * @description: 聊天室展示类
 */
@Data
public class ChatRoomVO {
    //唯一ID
    private String uniqueId;
    //聊天室名
    private String name;
    //头像
    private String avatar;
    //聊天室描述
    private String description;
    //聊天室创建时间
    @JsonFormat(pattern = "yyyy-MM-DD HH:mm:ss")
    private Date createdAt;
}
