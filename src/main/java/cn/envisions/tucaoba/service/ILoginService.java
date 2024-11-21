package cn.envisions.tucaoba.service;

import cn.envisions.tucaoba.entity.dto.LoginUserDTO;
import cn.envisions.tucaoba.entity.dto.RegisterUserDTO;

public interface ILoginService {
    void register(RegisterUserDTO registerUserDTO);

    String login(LoginUserDTO loginUserDTO);
}
