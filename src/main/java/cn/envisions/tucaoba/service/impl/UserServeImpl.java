package cn.envisions.tucaoba.service.impl;

import cn.envisions.tucaoba.entity.domain.User;
import cn.envisions.tucaoba.entity.vo.UserInfoVO;
import cn.envisions.tucaoba.mapper.UserMapper;
import cn.envisions.tucaoba.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServeImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 获取用户登录信息
     * @param id 用户Id
     * @return 用户展示信息
     */
    @Override
    public UserInfoVO getLoginUserInfo(Long id) {
        User one = this.getOne(new LambdaQueryWrapper<User>()
                .eq(User::getId, id));
        UserInfoVO userInfoVO = new UserInfoVO();
        BeanUtils.copyProperties(one, userInfoVO);
        return userInfoVO;
    }
}
