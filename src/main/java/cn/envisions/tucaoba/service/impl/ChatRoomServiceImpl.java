package cn.envisions.tucaoba.service.impl;

import cn.envisions.tucaoba.entity.domain.ChatRoom;
import cn.envisions.tucaoba.entity.dto.ChatRoomDTO;
import cn.envisions.tucaoba.entity.vo.ChatRoomVO;
import cn.envisions.tucaoba.mapper.ChatRoomMapper;
import cn.envisions.tucaoba.service.IChatRoomService;
import cn.envisions.tucaoba.utils.DateUtils;
import cn.envisions.tucaoba.utils.uuid.IdUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author JuRan
 * @date 2024/11/25 16:31
 * @description: 聊天室服务层实现类
 */
@Service
public class ChatRoomServiceImpl extends ServiceImpl<ChatRoomMapper, ChatRoom> implements IChatRoomService {

    /**
     * 聊天室创建
     *
     * @return 聊天室创建
     */
    @Override
    public boolean crateChatRoom(ChatRoomDTO chatRoomDTO) {
        ChatRoom chatRoom = new ChatRoom();
        BeanUtils.copyProperties(chatRoomDTO, chatRoom);
        //生成unique_Id
        String chatUniqueId = IdUtils.fastSimpleUUID();
        chatRoom.setUniqueId(chatUniqueId);
        chatRoom.setCreatedAt(DateUtils.getNowDate());
        //todo 设置聊天室创建人
        chatRoom.setCreateBy(1000L);
        return save(chatRoom);
    }

    /**
     * 获取聊天室列表信息。
     *
     * @return 聊天室列表。
     */
    @Override
    public List<ChatRoomVO> getChatList() {
        List<ChatRoom> list = list();
        return list.stream().map((ChatRoom v) -> {
            ChatRoomVO chatRoomVO = new ChatRoomVO();
            BeanUtils.copyProperties(v, chatRoomVO);
            return chatRoomVO;
        }).toList();
    }
}
