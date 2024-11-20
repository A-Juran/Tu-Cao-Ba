package cn.envisions.tucaoba.service.impl;

import cn.envisions.tucaoba.entity.domain.User;
import cn.envisions.tucaoba.mapper.UserMapper;
import cn.envisions.tucaoba.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserServeImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
