package com.base.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.base.bean.SysUser;
import com.base.mapper.sysMapper.SysUserMapper;
import com.base.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;



/**
 * @description:
 * @author: 小猴子
 * @date: 2020-06-08 14:36
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired(required = false)
    private SysUserMapper sysUserMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<SysUser>();
        wrapper.setEntity(new SysUser(s,null));
        SysUser user = sysUserMapper.selectOne(wrapper);
        if(user == null){
            throw new UsernameNotFoundException("用户不存在");
        }
        //Collection<? extends GrantedAuthority> authorities;
        //authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(user.getAuthorities());

        return  User.withUsername(user.getUsername()).password(new BCryptPasswordEncoder().encode(user.password)).authorities("/home").build();
    }
}
