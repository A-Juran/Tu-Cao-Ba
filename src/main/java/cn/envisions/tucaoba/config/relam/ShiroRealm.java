package cn.envisions.tucaoba.config.relam;

import cn.envisions.tucaoba.common.exception.user.UserNotExistException;
import cn.envisions.tucaoba.entity.domain.User;
import cn.envisions.tucaoba.service.IUserService;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author JuRan
 * @date 2024/11/21 11:44
 * @description: shiro认证、授权
 */
public class ShiroRealm extends AuthorizingRealm {
    @Autowired
    private IUserService userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken userToken = (UsernamePasswordToken) authenticationToken;
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getUsername, userToken.getUsername());
        User user = userService.getOne(userLambdaQueryWrapper);
        if (BeanUtil.isEmpty(user)) {
            throw new UserNotExistException("用户不存在");
        }
        return new SimpleAuthenticationInfo(user, user, "");
    }
}
