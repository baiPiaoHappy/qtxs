package com.base.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.base.bean.SysUser;



/**
 * @description:
 * @author: 小猴子
 * @date: 2020-05-23 16:15
 */

public interface SysUserService  {

    SysUser getUserByUserName(Wrapper<SysUser> wrapper);
}
