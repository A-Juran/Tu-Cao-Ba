package cn.envisions.tucaoba.service.impl;

import cn.envisions.tucaoba.common.exception.user.RegisterFailedException;
import cn.envisions.tucaoba.entity.domain.User;
import cn.envisions.tucaoba.entity.dto.LoginUserDTO;
import cn.envisions.tucaoba.entity.dto.RegisterUserDTO;
import cn.envisions.tucaoba.security.service.TokenService;
import cn.envisions.tucaoba.service.ILoginService;
import cn.envisions.tucaoba.service.IUserService;
import cn.envisions.tucaoba.utils.DateUtils;
import cn.envisions.tucaoba.utils.ip.IpUtils;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.digest.MD5;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LoginServiceImpl implements ILoginService {

    private static final Logger log = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    private IUserService userService;

    @Autowired
    private TokenService tokenService;

    /**
     * 用户注册
     *
     * @param registerUserDTO
     */
    @Override
    public void register(RegisterUserDTO registerUserDTO) {
        User one = getUserByUsername(registerUserDTO.getUsername());
        if (ObjectUtil.isNotNull(one))
            throw new RegisterFailedException("用户已经存在");
        //设置md5密码
        registerUserDTO.setPassword(MD5.create().digestHex(registerUserDTO.getPassword()));
        //创建用户
        User user = new User();
        BeanUtils.copyProperties(registerUserDTO, user);
        user.setCreatedAt(new Date());
        boolean save = userService.save(user);
        if (!save)
            throw new RegisterFailedException("注册失败");
    }

    @Override
    public String login(LoginUserDTO loginUserDTO) {
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        //封装用户的登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(loginUserDTO.getUsername(),
                loginUserDTO.getPassword());
        subject.login(token);

        //tokenService.createToken(loginUser)
        return subject.getPrincipal().toString();
    }

    /**
     * 记录登录信息
     *
     * @param userId 用户ID
     */
    public void recordLoginInfo(Long userId)
    {
        User sysUser = new User();
        sysUser.setLoginIp(IpUtils.getIpAddr());
        sysUser.setLastLoginAt(DateUtils.getNowDate());
        LambdaQueryWrapper<User> eq = new LambdaQueryWrapper<User>().eq(User::getId,userId);
        userService.update(sysUser,eq);
    }

    /**
     * 检查用户名是否存在
     *
     * @param username
     */
    private User getUserByUsername(String username) {
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getUsername, username);
        return userService.getOne(userLambdaQueryWrapper);
    }

}
