package cn.envisions.tucaoba.common.websocket.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.*;

import java.io.Serializable;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageTemplate implements Serializable {

  @JsonSerialize(using = ToStringSerializer.class)
  private String fromUserName;

  @JsonSerialize(using = ToStringSerializer.class)
  private String msgType;

  @JsonSerialize(using = ToStringSerializer.class)
  private String content;

  @JsonSerialize(using = ToStringSerializer.class)
  private String createTime;
}