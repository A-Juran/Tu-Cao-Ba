package cn.envisions.tucaoba.entity.dto;

import lombok.Data;
import java.io.Serializable;

@Data
public class ChatRoomDTO implements Serializable {

    private String name;

    private String avatar;

    private String description;
}