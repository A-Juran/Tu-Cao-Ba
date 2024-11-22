package cn.envisions.tucaoba.controller;

import cn.envisions.tucaoba.common.BaseController;
import cn.envisions.tucaoba.common.response.AjaxResult;
import cn.envisions.tucaoba.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
@Api(tags = "用户")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    @GetMapping("getInfo")
    @ApiOperation(value = "获取用户信息")
    public AjaxResult getUserInfo(){

        return success();
    }
}
