package cn.envisions.tucaoba.entity.domain;

import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.util.Set;

/**
 * 登录用户身份权限
 * 
 * @author death
 */
@Data
public class LoginUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 用户唯一标识
     */
    @Getter
    private String token;

    /**
     * 登录时间
     */
    private Long loginTime;

    /**
     * 过期时间
     */
    private Long expireTime;

    /**
     * 登录IP地址
     */
    private String ipaddr;

    /**
     * 登录地点
     */
    private String loginLocation;

    /**
     * 浏览器类型
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 权限列表
     */
    private Set<String> permissions;

    /**
     * 用户信息
     */
    private User user;

    public LoginUser()
    {
    }

    public LoginUser(Long userId, User user, Set<String> permissions)
    {
        this.userId = userId;
        this.user = user;
        this.permissions = permissions;
    }
}
