package cn.envisions.tucaoba.common.exception.user;

import cn.envisions.tucaoba.common.exception.base.BaseException;

/**
 * @author JuRan
 * @date 2024/11/21 11:24
 * @description: 用户异常基类
 */
public class UserException extends BaseException {
    public UserException(String code, Object[] args)
    {
        super("user", code, args, null);
    }

    public UserException(String msg)
    {
        super("user", msg);
    }


}
