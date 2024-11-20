package cn.envisions.tucaoba.common;

import cn.envisions.tucaoba.common.response.AjaxResult;

public class BaseController {
    /**
     * 返回成功
     */
    public AjaxResult success()
    {
        return AjaxResult.success();
    }

    /**
     * 返回成功消息
     */
    public AjaxResult success(String message)
    {
        return AjaxResult.success(message);
    }

    /**
     * 返回成功消息
     */
    public AjaxResult success(Object data)
    {
        return AjaxResult.success(data);
    }

    /**
     * 返回失败消息
     */
    public AjaxResult error()
    {
        return AjaxResult.error();
    }

    /**
     * 返回失败消息
     */
    public AjaxResult error(String message)
    {
        return AjaxResult.error(message);
    }

    /**
     * 返回警告消息
     */
    public AjaxResult warn(String message)
    {
        return AjaxResult.warn(message);
    }


    /**
     * 获取用户缓存信息
     */
//    public LoginUser getLoginUser()
//    {
//        return SecurityUtils.getLoginUser();
//    }

    /**
     * 获取登录用户id
     */
//    public Long getUserId()
//    {
//        return getLoginUser().getUserId();
//    }

    /**
     * 获取登录部门id
     */
//    public Long getDeptId()
//    {
//        return getLoginUser().getDeptId();
//    }

    /**
     * 获取登录用户名
     */
//    public String getUsername()
//    {
//        return getLoginUser().getUsername();
//    }
}
