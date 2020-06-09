package com.base.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.base.bean.SysUser;
import com.base.mapper.sysMapper.SysUserMapper;
import com.base.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: 小猴子
 * @date: 2020-05-23 16:15
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired(required = false)
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser getUserByUserName(Wrapper<SysUser> wrapper) {
        return sysUserMapper.selectOne(wrapper);
    }
}
