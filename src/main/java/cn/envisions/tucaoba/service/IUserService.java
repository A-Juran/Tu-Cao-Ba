package cn.envisions.tucaoba.service;

import cn.envisions.tucaoba.entity.domain.User;
import cn.envisions.tucaoba.entity.vo.UserInfoVO;
import com.baomidou.mybatisplus.extension.service.IService;

public interface IUserService extends IService<User> {

    UserInfoVO getLoginUserInfo(Long id);
}
