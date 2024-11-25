package cn.envisions.tucaoba.entity.domain;

import cn.envisions.tucaoba.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("`tc_chat_room`")
public class ChatRoom  extends BaseEntity implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String uniqueId;

    private String name;

    private String avatar;

    private String description;
}