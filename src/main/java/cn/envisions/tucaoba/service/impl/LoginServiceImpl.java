package cn.envisions.tucaoba.service.impl;

import cn.envisions.tucaoba.common.exception.user.RegisterFailedException;
import cn.envisions.tucaoba.common.exception.user.UserNotExistException;
import cn.envisions.tucaoba.entity.domain.LoginUser;
import cn.envisions.tucaoba.entity.domain.User;
import cn.envisions.tucaoba.entity.dto.LoginUserDTO;
import cn.envisions.tucaoba.entity.dto.RegisterUserDTO;
import cn.envisions.tucaoba.security.auth.ShiroJwtAuthenticationToken;
import cn.envisions.tucaoba.security.service.TokenService;
import cn.envisions.tucaoba.service.ILoginService;
import cn.envisions.tucaoba.service.IUserService;
import cn.envisions.tucaoba.utils.DateUtils;
import cn.envisions.tucaoba.utils.SubjectSecurityUtils;
import cn.envisions.tucaoba.utils.ip.IpUtils;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.digest.MD5;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.shiro.SecurityUtils;
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
        log.info(loginUserDTO.toString());
        //check username and Password.
        User user = getUserByUsername(loginUserDTO.getUsername());
        if (ObjectUtil.isNull(user)) {
            throw new UserNotExistException("用户名或密码错误");
        }
        SubjectSecurityUtils.checkUserPassword(loginUserDTO.getPassword(), user.getPassword());
        //1.根据用户信息生成用户名和密码的token
        String token = tokenService.createUserAndPasswordToken(user.getUsername());
        SecurityUtils.getSubject().login(new ShiroJwtAuthenticationToken(token));
        //获取认证登录主体
        return tokenService.createToken(createLoginUser(token, user));
    }


    public LoginUser createLoginUser(String token, User user) {
        recordLoginInfo(user.getId());
        return new LoginUser(user.getId(), user, null, token);
    }

    /**
     * 记录登录信息
     *
     * @param userId 用户ID
     */
    public void recordLoginInfo(Long userId) {
        User sysUser = new User();
        sysUser.setLoginIp(IpUtils.getIpAddr());
        sysUser.setLastLoginAt(DateUtils.getNowDate());
        LambdaQueryWrapper<User> eq = new LambdaQueryWrapper<User>().eq(User::getId, userId);
        userService.update(sysUser, eq);
    }

    /**
     * 检查用户名是否存在
     *
     * @param username 用户名
     */
    private User getUserByUsername(String username) {
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getUsername, username);
        return userService.getOne(userLambdaQueryWrapper);
    }

}
