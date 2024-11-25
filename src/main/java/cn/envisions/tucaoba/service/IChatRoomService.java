package cn.envisions.tucaoba.service;

import cn.envisions.tucaoba.entity.domain.ChatRoom;
import cn.envisions.tucaoba.entity.dto.ChatRoomDTO;
import cn.envisions.tucaoba.entity.vo.ChatRoomVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface IChatRoomService extends IService<ChatRoom> {

    List<ChatRoomVO> getChatList();

    boolean crateChatRoom(ChatRoomDTO chatRoomDTO);
}
