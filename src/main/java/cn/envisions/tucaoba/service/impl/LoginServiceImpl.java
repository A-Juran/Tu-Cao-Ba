package cn.envisions.tucaoba.service.impl;

import cn.envisions.tucaoba.common.exception.user.RegisterFailedException;
import cn.envisions.tucaoba.entity.domain.User;
import cn.envisions.tucaoba.entity.dto.LoginUserDTO;
import cn.envisions.tucaoba.entity.dto.RegisterUserDTO;
import cn.envisions.tucaoba.service.ILoginService;
import cn.envisions.tucaoba.service.IUserService;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LoginServiceImpl implements ILoginService {

    private final IUserService userService;

    public LoginServiceImpl(UserServeImpl userService) {
        this.userService = userService;
    }

    /**
     * 用户注册
     *
     * @param registerUserDTO
     */
    @Override
    public void register(RegisterUserDTO registerUserDTO) {
        checkUserNameExist(registerUserDTO.getUsername());
        //创建用户
        User user = new User();
        BeanUtils.copyProperties(registerUserDTO, user);
        user.setCreatedAt(new Date());
        boolean save = userService.save(user);
        if (!save)
            throw new RegisterFailedException("注册失败");
    }

    @Override
    public User login(LoginUserDTO loginUserDTO) {

        UsernamePasswordToken token = new UsernamePasswordToken(loginUserDTO.getUsername(),
                loginUserDTO.getPassword());

        return null;
    }

    /**
     * 检查用户名是否存在
     *
     * @param username
     */
    private User checkUserNameExist(String username) {
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getUsername, username);
        User one = userService.getOne(userLambdaQueryWrapper);
        if (ObjectUtil.isNotNull(one))
            throw new RegisterFailedException("用户已经存在");
        return one;
    }

}
