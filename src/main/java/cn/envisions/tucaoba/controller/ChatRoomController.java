package cn.envisions.tucaoba.controller;

import cn.envisions.tucaoba.common.BaseController;
import cn.envisions.tucaoba.common.response.AjaxResult;
import cn.envisions.tucaoba.entity.dto.ChatRoomDTO;
import cn.envisions.tucaoba.entity.vo.ChatRoomVO;
import cn.envisions.tucaoba.service.IChatRoomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author JuRan
 * @date 2024/11/25 16:28
 * @description: 聊天室控制层
 */
@RestController
@RequestMapping("chat")
@Api(tags = "聊天室")
public class ChatRoomController extends BaseController {

    @Autowired
    private IChatRoomService iChatRoomService;

    /**
     * 创建聊天室
     *
     * @return 成功信息
     */
    @PostMapping("create")
    @ApiOperation(value = "创建聊天室")
    public AjaxResult crateChatRoom(@RequestBody ChatRoomDTO chatRoomDTO) {
        boolean res = iChatRoomService.crateChatRoom(chatRoomDTO);
        if (res)
            return success("创建成功");
        return error("创建失败");
    }

    /**
     * 获取聊天室列表
     *
     * @return 返回聊天室列表
     */
    @GetMapping("list")
    @ApiOperation(value = "获取聊天室列表")
    public AjaxResult getChatList() {
        List<ChatRoomVO> chatRoomVOList = iChatRoomService.getChatList();
        return success(chatRoomVOList);
    }
}
