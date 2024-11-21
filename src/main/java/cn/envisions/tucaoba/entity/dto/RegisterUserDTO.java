package cn.envisions.tucaoba.entity.dto;

import lombok.Data;

@Data
public class RegisterUserDTO {

    private String username;

    private String nickName;

    private String password;

    private String avatarUrl;
}