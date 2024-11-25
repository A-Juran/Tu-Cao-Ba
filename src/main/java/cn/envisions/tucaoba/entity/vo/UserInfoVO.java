package cn.envisions.tucaoba.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author JuRan
 * @date 2024/11/25 16:01
 * @description: 已登录用户信息
 */
@Data
public class UserInfoVO {
    //用户ID
    private Long id;
    //用户名
    private String username;
    //昵称
    private String nickName;
    //性别
    private String sex;
    //头像
    private String avatarUrl;
    //最后登录时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastLoginAt;
    //当前登录IP
    private String loginIp;
    //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;
}
