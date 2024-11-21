package cn.envisions.tucaoba.controller;

import cn.envisions.tucaoba.common.BaseController;
import cn.envisions.tucaoba.common.constant.Constants;
import cn.envisions.tucaoba.common.response.AjaxResult;
import cn.envisions.tucaoba.entity.dto.LoginUserDTO;
import cn.envisions.tucaoba.entity.dto.RegisterUserDTO;
import cn.envisions.tucaoba.service.ILoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Tag(name = "登录")
public class LoginController extends BaseController {

    private final ILoginService loginService;

    public LoginController(ILoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/register")
    @Operation(summary = "注册")
    public AjaxResult register(@RequestBody RegisterUserDTO registerUserDTO) {
        loginService.register(registerUserDTO);
        return success("注册成功");
    }

    @PostMapping("/login")
    @Operation(summary = "登录")
    public AjaxResult login(@RequestBody LoginUserDTO loginUserDTO) {
        String token = loginService.login(loginUserDTO);
        return success().put(Constants.TOKEN,token);
    }
}
