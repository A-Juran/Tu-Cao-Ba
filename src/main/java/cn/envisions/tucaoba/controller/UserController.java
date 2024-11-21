package cn.envisions.tucaoba.controller;

import cn.envisions.tucaoba.common.BaseController;
import cn.envisions.tucaoba.common.response.AjaxResult;
import cn.envisions.tucaoba.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
@Tag(name = "用户")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    @GetMapping("getInfo")
    @Operation(summary = "获取用户信息")
    public AjaxResult getUserInfo(){
//        userService.getUserInfo();
        return success();
    }
}
