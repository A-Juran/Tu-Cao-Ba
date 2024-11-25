package cn.envisions.tucaoba.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author JuRan
 * @date 2024/11/25 16:49
 * @description: 基础实体类
 */
@Data
public class BaseEntity {

    private Long createBy;

    private Date createdAt;

    private Date updatedAt;
}
