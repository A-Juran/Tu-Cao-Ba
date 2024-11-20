package cn.envisions.tucaoba.controller;

import cn.envisions.tucaoba.common.BaseController;
import cn.envisions.tucaoba.common.response.AjaxResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("user")
public class LoginController extends BaseController {

    @PostMapping("register")
    public AjaxResult register(){
        

        return success("注册成功");
    }
}
