package cn.envisions.tucaoba.entity.domain;

import cn.envisions.tucaoba.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("`tc_users`")
public class User extends BaseEntity implements Serializable{

    @TableId(type = IdType.AUTO)
    private Long id;

    private String username;

    private String password;

    private String nickName;

    private String sex;

    private String avatarUrl;

    private String status;

    private Date lastLoginAt;

    private String loginIp;

}