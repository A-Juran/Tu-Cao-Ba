package cn.envisions.tucaoba.common.websocket.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class MessageTemplate implements Serializable {

  private String type;

  @JsonSerialize(using = ToStringSerializer.class)
  private Long messageId;

  private String content;

  @JsonSerialize(using = ToStringSerializer.class)
  private Long userId;

  @JsonSerialize(using = ToStringSerializer.class)
  private Long fromUserId;

  @JsonSerialize(using = ToStringSerializer.class)
  private Long toUserId;
}