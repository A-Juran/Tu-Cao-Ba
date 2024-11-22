package cn.envisions.tucaoba.controller;

import cn.envisions.tucaoba.common.BaseController;
import cn.envisions.tucaoba.common.constant.Constants;
import cn.envisions.tucaoba.common.response.AjaxResult;
import cn.envisions.tucaoba.entity.dto.LoginUserDTO;
import cn.envisions.tucaoba.entity.dto.RegisterUserDTO;
import cn.envisions.tucaoba.service.ILoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Api(tags = "首页模块")
public class LoginController extends BaseController {

    private final ILoginService loginService;

    public LoginController(ILoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/register")
    @ApiOperation(value = "注册", notes = "注册")
    public AjaxResult register(@RequestBody RegisterUserDTO registerUserDTO) {
        loginService.register(registerUserDTO);
        return success("注册成功");
    }

    @PostMapping("/login")
    @ApiOperation(value = "登录", notes = "登录")
    public AjaxResult login(@RequestBody LoginUserDTO loginUserDTO) {
        String token = loginService.login(loginUserDTO);
        return success().put(Constants.TOKEN,token);
    }
}
